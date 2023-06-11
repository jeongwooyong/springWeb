package kr.mjc.wooyong.web.model1.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 로그아웃
 */
@WebServlet("/servlets/user/signout")
public class SignoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.getSession().invalidate();
        //Session 에 있는 정보가 날라감
        resp.sendRedirect(
                req.getContextPath() + "/servlets/user/userList?count=20&page=1");
    }
}