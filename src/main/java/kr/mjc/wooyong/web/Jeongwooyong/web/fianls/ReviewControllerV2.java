package kr.mjc.wooyong.web.Jeongwooyong.web.fianls;

import jakarta.servlet.http.HttpServletRequest;
import kr.mjc.wooyong.web.HttpUtils;
import kr.mjc.wooyong.web.dao.Limit;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;

/**
 * Servlet API를 사용하는 컨트롤러
 *
 */
@Controller
@AllArgsConstructor
public class ReviewControllerV2 {
    private static final String CURRENT_REVIEW_LIST = "CURRENT_REVIEW_LIST";
    private final ReviewDao reviewDao;

    @GetMapping("/review/reviewList")
    public void reviewList(HttpServletRequest req, Limit limit, Model model) {
        // 현재 목록을 세션에 저장
        // forward는 Limit가 필요함 simple 타입이 아니라면 comend임
        //ModelAttribute 생략
        //req url이 필요한 경우는 필요하다.
        req.getSession().setAttribute(CURRENT_REVIEW_LIST,
                HttpUtils.getRequestURLWithQueryString(req));

        req.setAttribute("reviewList", reviewDao.listReviews(limit));
        //String 으로 하고 return을 해도 됨
        // return "review/reviewList"; //forward /WEB_INF/jsp/review/reviewList.jsp
    }
    //reviewForm은 Just Forward 이기 때문에 아무것도 안해도 됨
    @GetMapping("/review/addReview")
    public String addReview(Review review,
                          @SessionAttribute("me_userId") int userId,
                          @SessionAttribute("me_name") String name) {
        review.setUserId(userId);
        review.setName(name);
        reviewDao.addReview(review);
        return "redirect:/app/review/reviewList";
    }
    @GetMapping("/review/reviewForm")
    public void mapDefault() {
    }

    @GetMapping("/review/review")
    public void review(int reviewId, Model model) {
        model.addAttribute("review", reviewDao.getReview(reviewId));
    }
        /**
     * 게시글의 권한 체크
     *
     * @throws ResponseStatusException 권한이 없을 경우
     */
    private Review getUserReview(int reviewId, int userId) {
        try {
            return reviewDao.getUserReview(reviewId, userId);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}