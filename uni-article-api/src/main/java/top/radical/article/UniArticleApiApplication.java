package top.radical.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动主类
 * @author radical
 */
@SpringBootApplication
@MapperScan("top.radical.article.mapper")
public class UniArticleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniArticleApiApplication.class, args);
    }

}
