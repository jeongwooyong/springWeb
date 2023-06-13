package kr.mjc.wooyong.web.springmvc.movie;

import jakarta.servlet.http.HttpServletRequest;
import kr.mjc.wooyong.web.HttpUtils;
import kr.mjc.wooyong.web.dao.Limit;
import kr.mjc.wooyong.web.dao.movie.Movie;
import kr.mjc.wooyong.web.dao.movie.MovieDao;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;


/**
 * Servlet API를 사용하는 컨트롤러
 */
@Controller
@AllArgsConstructor
public class MovieControllerV2 {
    private static final String CURRENT_MOVIE_LIST = "CURRENT_MOVIE_LIST";
    private final MovieDao movieDao;

    @GetMapping("/movie/movieList")
    public void movieList(HttpServletRequest req, Limit limit, Model model) {
        // 현재 목록을 세션에 저장
        req.getSession().setAttribute(CURRENT_MOVIE_LIST,
                HttpUtils.getRequestURLWithQueryString(req));

        req.setAttribute("movieList", movieDao.listMovies(limit));
    }

    @GetMapping("/movie/movieForm")
    public void mapDefault() {
    }

    @PostMapping("/movie/addMovie")
    public String addMovie(Movie movie) {
        movieDao.addMovie(movie);
        return "redirect:/app/movie/movieList";
    }

    @GetMapping("/movie/movie")
    public void movie(int movieId, Model model) {
        model.addAttribute("movie", movieDao.getMovie(movieId));
    }

    @GetMapping("/movie/movieEdit")
    public void movieEdit(int movieId, Model model) {
        Movie movie = getUserMovie(movieId);
        model.addAttribute("movie", movie);
    }

    @PostMapping("/movie/updateMovie")
    public String updateMovie(Movie movie) {
        movieDao.updateMovie(movie);
        return "redirect:/app/movie/movie?movieId=" + movie.getMovieId();
    }

    @GetMapping("/movie/deleteMovie")
    public String deleteMovie(int movieId,@SessionAttribute(CURRENT_MOVIE_LIST) String currentMovieList) {
        getUserMovie(movieId);
        movieDao.deleteMovie(movieId);
        return "redirect:" + currentMovieList;
    }

    /**
     * 게시글의 권한 체크
     *
     * @throws ResponseStatusException 권한이 없을 경우
     */
    private Movie getUserMovie(int movieId) {
        try {
            return movieDao.getUserMovie(movieId);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}