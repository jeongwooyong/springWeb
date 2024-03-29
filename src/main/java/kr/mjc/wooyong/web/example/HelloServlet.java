package kr.mjc.wooyong.web.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/examples/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String html = """
        <!DOCTYPE html>
        <html>
        <body>
        <h1 style="color:blue;">Hello, 서블릿!</h1>
        </body>
        </html>
        """;

        resp.setContentType("text/html");
        resp.getWriter().println(html);
    }
}