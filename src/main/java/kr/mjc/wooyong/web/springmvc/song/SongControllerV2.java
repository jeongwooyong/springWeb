package kr.mjc.wooyong.web.springmvc.song;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mjc.wooyong.web.HttpUtils;
import kr.mjc.wooyong.web.dao.Limit;
import kr.mjc.wooyong.web.dao.song.SongDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
@Controller
@AllArgsConstructor
public class SongControllerV2 {
    private static final String CURRENT_SONG_LIST = "CURRENT_SONG_LIST";
    private final SongDao songDao;

    @GetMapping("/song/songList")
    public void songList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 현재 목록을 세션에 저장
        req.getSession().setAttribute(CURRENT_SONG_LIST,
                HttpUtils.getRequestURLWithQueryString(req));

        Limit limit =
                new Limit(req.getParameter("count"), req.getParameter("page"));
        req.setAttribute("songList", songDao.listSongs(limit));
        req.setAttribute("limit", limit);
        HttpUtils.forward(req, resp);
    }
}
