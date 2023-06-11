package kr.mjc.wooyong.web.dao.song;

import lombok.Data;
import org.owasp.encoder.Encode;

@Data
public class Song {
    int songId;
    String title;
    String name;

    public String getTitleEncoded() {
        return Encode.forHtml(title);
    }

    public String getnameEncoded() {
        return Encode.forHtml(name);
    }
}