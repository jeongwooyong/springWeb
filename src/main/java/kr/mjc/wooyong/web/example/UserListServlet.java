package kr.mjc.wooyong.web.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mjc.wooyong.web.dao.User;
import kr.mjc.wooyong.web.dao.UserDao;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.List;

@WebServlet("/examples/userList")
//이 URL로 연결을 하면, 유저 List가 나온다.
public class UserListServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() {
        // for standalone container
        ApplicationContext applicationContext = (ApplicationContext)
                getServletContext().getAttribute(
                        "org.springframework.web.context.WebApplicationContext.ROOT");
        userDao = applicationContext.getBean(UserDao.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        List<User> userList = userDao.listUsers(10, 1);

        String html = """
        <!DOCTYPE html>
        <html>
        <body>
        <pre>%s</pre>
        </body>
        </html>
        """.formatted(userList);
        //c언어의 print("%s",userList)느낌임.

        resp.setContentType("text/html");
        resp.getWriter().println(html);
    }
}