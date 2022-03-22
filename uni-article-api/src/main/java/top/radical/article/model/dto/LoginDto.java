package top.radical.article.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : radical
 * @description :登录dto对象
 * @data : 2022/3/22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
    private String phone;
    private String password;
}
