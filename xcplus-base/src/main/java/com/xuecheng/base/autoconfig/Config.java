package com.xuecheng.base.autoconfig;

import com.xuecheng.base.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ComponentScan("com.xuecheng.base")
@Configuration
@Import(MybatisPlusConfig.class)
public class Config {
}
