package kr.mjc.wooyong.web.model1.article;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mjc.wooyong.web.dao.article.Article;
import kr.mjc.wooyong.web.dao.article.ArticleDao;

import kr.mjc.wooyong.web.dao.Limit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.List;

@WebServlet("/servlets/article/articleList")
@Slf4j
public class ArticleListServlet extends HttpServlet {

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
            throws ServletException, IOException {
        //파라미터로 count와 page를 받는다.
        int count = Integer.parseInt(req.getParameter("count"));
        int page = Integer.parseInt(req.getParameter("page"));
        List<Article> articleList = articleDao.listArticles(new Limit());
        //"이름" , 오브젝트
        // server에서 데이터는 set, JSP 는 get
        req.setAttribute("articleList", articleList);
        req.getRequestDispatcher("/WEB-INF/jsp/user/articleList.jsp")
                .forward(req, resp);
    }
}