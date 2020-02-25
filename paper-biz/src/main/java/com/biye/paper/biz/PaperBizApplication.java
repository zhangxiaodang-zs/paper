package com.biye.paper.biz;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@EnableScheduling
@CrossOrigin(origins = "*")
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("com.biye.paper.biz.*.")
@SpringBootApplication(scanBasePackages = {"com.biye.paper.core", "com.biye.paper.biz"})
public class PaperBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaperBizApplication.class, args);
    }

}
