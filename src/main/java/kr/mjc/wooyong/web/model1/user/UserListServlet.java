package kr.mjc.wooyong.web.model1.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.mjc.wooyong.web.dao.Limit;
import kr.mjc.wooyong.web.dao.user.User;
import kr.mjc.wooyong.web.dao.user.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.List;

@WebServlet("/servlets/user/userList")
@Slf4j
public class UserListServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() {
        // for standalone container
        ApplicationContext applicationContext =
                WebApplicationContextUtils.getRequiredWebApplicationContext(
                        getServletContext());
        userDao = applicationContext.getBean(UserDao.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //파라미터로 count와 page를 받는다.
        int count = Integer.parseInt(req.getParameter("count"));
        int page = Integer.parseInt(req.getParameter("page"));
        List<User> userList = userDao.listUsers(new Limit());
        //"이름" , 오브젝트
        // server에서 데이터는 set, JSP 는 get
        req.setAttribute("userList", userList);
        req.getRequestDispatcher("/WEB-INF/jsp/user/userList.jsp")
                .forward(req, resp);
    }
}