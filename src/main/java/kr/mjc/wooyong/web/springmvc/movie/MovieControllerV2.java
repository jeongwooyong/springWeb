package kr.mjc.wooyong.web.springmvc.movie;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.mjc.wooyong.web.HttpUtils;

import kr.mjc.wooyong.web.dao.Limit;
import kr.mjc.wooyong.web.dao.movie.Movie;
import kr.mjc.wooyong.web.dao.movie.MovieDao;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

/**
 * Servlet API를 사용하는 컨트롤러
 */
@Controller
@AllArgsConstructor
public class MovieControllerV2 {
    private static final String CURRENT_MOVIE_LIST = "CURRENT_MOVIE_LIST";
    private final MovieDao movieDao;

    @GetMapping("/movie/movieList")
    public void movieList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 현재 목록을 세션에 저장
        req.getSession().setAttribute(CURRENT_MOVIE_LIST,
                HttpUtils.getRequestURLWithQueryString(req));

        Limit limit =
                new Limit(req.getParameter("count"), req.getParameter("page"));
        req.setAttribute("movieList", movieDao.listMovies(limit));
        req.setAttribute("limit", limit);
        HttpUtils.forward(req, resp);
    }

    @GetMapping("/movie/movieForm")
    public void mapDefault() {
    }


}