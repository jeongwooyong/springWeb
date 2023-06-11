package kr.mjc.wooyong.web.model2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mjc.wooyong.web.dao.article.Article;
import kr.mjc.wooyong.web.dao.article.ArticleDao;
import kr.mjc.wooyong.web.dao.Limit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class ArticleController {
    private ArticleDao articleDao;

    public void mapDefault(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp%s.jsp".formatted(req.getPathInfo()))
                .forward(req, resp);
    }

    public void articleList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Limit limit =
                new Limit(req.getParameter("count"), req.getParameter("page"));
        req.setAttribute("limit", limit);
        List<Article> articleList = articleDao.listArticles(limit);
        req.setAttribute("articleList", articleList);
        req.getRequestDispatcher("/WEB-INF/jsp/article/articleList.jsp")
                .forward(req, resp);
    }
}
