package kr.mjc.wooyong.web;

import kr.mjc.wooyong.web.springmvc.SigninInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ServletComponentScan(basePackages = "kr.mjc.wooyong.web")
@Slf4j
public class SpringWebApplication extends SpringBootServletInitializer
        implements WebMvcConfigurer {
    /**
     * Spring Boot 구동
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringWebApplication.class, args);
    }

    /**
     * SpringBootServletInitializer : for standalone container
     */
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(SpringWebApplication.class);
    }

    /**
     * 인터셉터 등록
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SigninInterceptor())
                .addPathPatterns("/user/myInfo", "/user/passwordEdit",
                        "/user/updatePassword", "/article/articleForm",
                        "/article/addArticle", "/article/articleEdit",
                        "/article/updateArticle", "/article/deleteArticle",
                        "/movie/movieForm", "/movie/addMovie",
                        "/movie/movieEdit", "/movie/updateMovie",
                        "/movie/deleteMovie", "/song/songForm",
                        "/song/addSong", "/song/songEdit",
                        "/song/updateSong", "/song/deleteSong",
                        "/post/postForm", "/post/addPost",
                        "/post/postEdit","/post/updatePost",
                        "/post/deletePost","/review/addReview");
        log.info("signinInterceptor 등록");
    }
}