package kr.mjc.wooyong.web.model1.article;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.mjc.wooyong.web.dao.article.Article;
import kr.mjc.wooyong.web.dao.article.ArticleDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

/**
 * 회원 등록
 */
@WebServlet("/servlets/article/signup")
@Slf4j
public class ArticleAddServlet extends HttpServlet {

    private ArticleDao articleDao;

    @Override
    public void init() {
        // for standalone container
        ApplicationContext applicationContext =
                WebApplicationContextUtils.getRequiredWebApplicationContext(
                        getServletContext());
        articleDao = applicationContext.getBean(ArticleDao.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Article article = new Article();
        article.setTitle(req.getParameter("title"));
        article.setName(req.getParameter("name"));
        article.setContent(req.getParameter("content"));

        try {
            articleDao.addArticle(article);
            // 등록 성공
            HttpSession session = req.getSession();
            session.setAttribute("me.name", article.getName());
            session.setAttribute("me.title", article.getTitle());
            session.setAttribute("me.content",article.getContent());
            resp.sendRedirect(
                    req.getContextPath() + "/servlets/article/articleList?count=20&page=1");
        } catch (DataAccessException e) { // 등록 실패
            log.error(e.getCause().toString());
            resp.sendRedirect(req.getContextPath() + "/servlets/article/articleList?mode=FAILURE");
        }
    }
}