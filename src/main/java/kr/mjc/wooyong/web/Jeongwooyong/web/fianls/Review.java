package kr.mjc.wooyong.web.Jeongwooyong.web.fianls;

import lombok.Data;
import org.owasp.encoder.Encode;

@Data
public class Review {
    int reviewId;
    String contents;
    int grade;
    int userId;
    String name;
    String cdate;

    public String getContentsEncoded() {
        return Encode.forHtml(contents);
    }


    /**
     * new line을 <br> 태그로 변환
     */

    @Override
    public String toString() {
        return String.format(
                "\nReview{reviewId=%d,contents=%s, userId=%d, grade=%d name=%s, cdate=%s}",
                reviewId, contents, userId, grade, name, cdate);
    }
}