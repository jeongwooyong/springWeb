package kr.mjc.wooyong.web.model2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

@WebServlet("/mvc/*")
//mvc로 시작하는 url
@Slf4j
public class SimpleDispatcherServlet extends HttpServlet {

    private UserController userController;
    private ArticleController articleController;

    @Override
    public void init() {
        ApplicationContext applicationContext =
                WebApplicationContextUtils.getRequiredWebApplicationContext(
                        getServletContext());
        userController = applicationContext.getBean(UserController.class);
        articleController = applicationContext.getBean(ArticleController.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        log.debug("pathInfo={}", pathInfo);
        switch (pathInfo) {
            //url 분기를 함 ->로 바뀌어서 break 자동생성
            //여러개의 조건일때는 ,를 쓰면 된다
            case "/user/userList" -> userController.userList(req, resp);
            case "/article/articleList" -> articleController.articleList(req, resp);
            case "/user/signout" -> userController.signout(req, resp);
            case "/user/signinForm", "/user/signupForm", "/user/myInfo", "/user/passwordEdit" ->
                    userController.mapDefault(req, resp);
            default -> resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String pathInfo = req.getPathInfo();
        log.debug("pathInfo={}", pathInfo);
        switch (pathInfo) {
            case "/user/signin" -> userController.signin(req, resp);
            case "/user/signup" -> userController.signup(req, resp);
            case "/user/updatePassword" -> userController.updatePassword(req, resp);
        }
    }
}
//doGet 과 doPost의 차이점은
//Get은 Url을 치면 바로 뜨지만, doPost는 Url을 친다고 페이지가 뜨지 않음.
//이유는 doPost에서 signin을 치면 로그인이 바로 되는 것이 아닌 것 처럼
//데이터가 있어야 동작하는 공간.