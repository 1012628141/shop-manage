package cn.pyj520.shop.api.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TimeZone;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestParam {

    private Integer id;

    private String countryLanguage;//国家语言

    private TimeZone timeZone;//时区

    private String systemVersion;//安卓系统版本

    private String signature;//签名校验码

    private String refreshToken;

    private String accessToken;



}
