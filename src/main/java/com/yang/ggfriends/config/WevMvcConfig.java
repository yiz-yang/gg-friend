package com.yang.ggfriends.config;


import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class WevMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("http://127.0.0.1:5173","http://localhost:5173","http://8.138.135.232:5173","http://localhost:80","http://127.0.0.1:80","http://8.138.135.232:80","http://localhost:4173","http://8.138.135.232","http://127.0.0.1:4173","http://www.friendsmaker.icu","http://friendsmaker.icu")
//                .allowedOriginPatterns("*")
                //是否允许证书，不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(3600)
                .allowedHeaders("*");
    }

}
