package top.radical.article.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author : radical
 * @description :文件上传工具类
 * @data : 2022/3/26
 **/

@Data
@Component
@PropertySource("classpath:file.properties")
@ConfigurationProperties(prefix = "file")
public class FileResource {
    private String host;
    private String endPoint;
    private String bucketName;
    private String objectName;
    private String ossHost;
}
