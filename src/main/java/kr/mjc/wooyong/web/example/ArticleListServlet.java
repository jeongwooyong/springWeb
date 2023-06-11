package kr.mjc.wooyong.web.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mjc.wooyong.web.dao.article.ArticleDao;
import kr.mjc.wooyong.web.dao.article.Article;

import kr.mjc.wooyong.web.dao.Limit;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.List;

@WebServlet("/examples/articleList")
public class ArticleListServlet extends HttpServlet{
    private ArticleDao articleDao;

    public void init() {
        // for standalone container
        ApplicationContext applicationContext = (ApplicationContext)
                getServletContext().getAttribute(
                        "org.springframework.web.context.WebApplicationContext.ROOT");
        articleDao = applicationContext.getBean(ArticleDao.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        List<Article> articleList = articleDao.listArticles(new Limit());

        String html = """
        <!DOCTYPE html>
        <html>
        <body>
        <pre>%s</pre>
        </body>
        </html>
        """.formatted(articleList);
        //c언어의 print("%s",userList)느낌임.

        resp.setContentType("text/html");
        resp.getWriter().println(html);
    }
}

