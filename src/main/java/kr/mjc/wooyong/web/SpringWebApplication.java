package kr.mjc.wooyong.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan(basePackages = "kr.mjc.wooyong.web")
@Slf4j
public class SpringWebApplication extends SpringBootServletInitializer {
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
}