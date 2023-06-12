package kr.mjc.wooyong.web.springmvc.song;

import jakarta.servlet.http.HttpServletRequest;
import kr.mjc.wooyong.web.HttpUtils;
import kr.mjc.wooyong.web.dao.Limit;
import kr.mjc.wooyong.web.dao.song.Song;
import kr.mjc.wooyong.web.dao.song.SongDao;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;

@Controller
@AllArgsConstructor
public class SongControllerV2 {
    private static final String CURRENT_SONG_LIST = "CURRENT_SONG_LIST";
    private final SongDao songDao;

    @GetMapping("/song/songList")
    public void songList(HttpServletRequest req, Limit limit, Model model) {
        // 현재 목록을 세션에 저장
        req.getSession().setAttribute(CURRENT_SONG_LIST,
                HttpUtils.getRequestURLWithQueryString(req));

        req.setAttribute("songList", songDao.listSongs(limit));
    }

    @GetMapping("/song/songForm")
    public void mapDefault() {
    }

    @PostMapping("song/addSong")
    public String addSong(Song song) {
        songDao.addSong(song);
        return "redirect:/app/song/songList";
    }

    @GetMapping("/song/song")
    public void song(int songId, Model model) {
        model.addAttribute("song", songDao.getSong(songId));
    }

    @GetMapping("/song/songEdit")
    public void songEdit(int songId, Model model) {
        Song song = getUserSong(songId);
        model.addAttribute("song", song);
    }

    @PostMapping("/song/updateSong")
    public String updateSong(Song song) {
        songDao.updateSong(song);
        return "redirect:/app/song/song?songId=" + song.getSongId();
    }

    @GetMapping("/song/deleteSong")
    public String deleteSong(int songId,
                             @SessionAttribute(CURRENT_SONG_LIST) String currentSongList) {
        getUserSong(songId);
        songDao.deleteSong(songId);
        return "redirect:" + currentSongList;
    }

    /**
     * 게시글의 권한 체크
     *
     * @throws ResponseStatusException 권한이 없을 경우
     */
    private Song getUserSong(int songId) {
        try {
            return songDao.getUserSong(songId);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
