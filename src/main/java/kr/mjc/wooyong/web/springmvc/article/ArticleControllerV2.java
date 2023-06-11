package kr.mjc.wooyong.web.springmvc.article;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.mjc.wooyong.web.HttpUtils;
import kr.mjc.wooyong.web.dao.article.Article;
import kr.mjc.wooyong.web.dao.article.ArticleDao;
import kr.mjc.wooyong.web.dao.Limit;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

/**
 * Servlet API를 사용하는 컨트롤러
 *
 */
@Controller
@AllArgsConstructor
public class ArticleControllerV2 {
    private static final String CURRENT_ARTICLE_LIST = "CURRENT_ARTICLE_LIST";
    private final ArticleDao articleDao;

    @GetMapping("/article/articleList")
    public void articleList(HttpServletRequest req, Limit limit, Model model) {
        // 현재 목록을 세션에 저장
        // forward는 Limit가 필요함 simple 타입이 아니라면 comend임
        //ModelAttribute 생략
        //req url이 필요한 경우는 필요하다.
        req.getSession().setAttribute(CURRENT_ARTICLE_LIST,
                HttpUtils.getRequestURLWithQueryString(req));

        req.setAttribute("articleList", articleDao.listArticles(limit));
        //String 으로 하고 return을 해도 됨
        // return "article/articleList"; //forward /WEB_INF/jsp/article/articleList.jsp
    }
    //articleForm은 Just Forward 이기 때문에 아무것도 안해도 됨
    @GetMapping("/article/articleForm")
    public void mapDefault() {
    }

    @PostMapping("/article/addArticle")
    public String addArticle(Article article,
                             @SessionAttribute("me_userId") int userId,
                             @SessionAttribute("me_name") String name) {
        article.setUserId(userId);
        article.setName(name);
        articleDao.addArticle(article);
        return "redirect:/app/article/articleList";
    }

    @GetMapping("/article/article")
    public void article(int articleId, Model model) {
        model.addAttribute("article", articleDao.getArticle(articleId));
    }

    @GetMapping("/article/articleEdit")
    public void articleEdit(int articleId,
                            @SessionAttribute("me_userId") int userId, Model model) {
        Article article = getUserArticle(articleId, userId);
        model.addAttribute("article", article);
    }

    @PostMapping("/article/updateArticle")
    public String updateArticle(Article article,
                                @SessionAttribute("me_userId") int userId) {
        getUserArticle(article.getArticleId(), userId);
        article.setUserId(userId);
        articleDao.updateArticle(article);
        return "redirect:/app/article/article?articleId=" + article.getArticleId();
    }

    @GetMapping("/article/deleteArticle")
    public String deleteArticle(int articleId,
                                @SessionAttribute("me_userId") int userId,
                                @SessionAttribute(CURRENT_ARTICLE_LIST) String currentArticleList) {
        getUserArticle(articleId, userId);
        articleDao.deleteArticle(articleId, userId);
        return "redirect:" + currentArticleList;
    }

    /**
     * 게시글의 권한 체크
     *
     * @throws ResponseStatusException 권한이 없을 경우
     */
    private Article getUserArticle(int articleId, int userId) {
        try {
            return articleDao.getUserArticle(articleId, userId);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}