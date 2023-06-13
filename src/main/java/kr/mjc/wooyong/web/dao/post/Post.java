package kr.mjc.wooyong.web.dao.post;

import lombok.Data;
import org.owasp.encoder.Encode;

@Data
public class Post {
    int postId;
    String contents;
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
                "\nPost{postId=%d,contents=%s, userId=%d, name=%s, cdate=%s}",
                postId, contents, userId, name, cdate);
    }
}