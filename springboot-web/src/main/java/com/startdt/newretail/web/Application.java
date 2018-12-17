package com.startdt.newretail.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.servlet.MultipartConfigElement;

/**
 * @author weilong
 */
@EnableTransactionManagement
@ServletComponentScan
@SpringBootApplication(
        scanBasePackages = {"com.startdt.newretail"}
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 设置文件大小
     * @return javax.servlet.MultipartConfigElement
     * @author weilong
     * @since  2018/10/28 下午5:30
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("10240KB");
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
    @Bean("restTemplate")
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}


