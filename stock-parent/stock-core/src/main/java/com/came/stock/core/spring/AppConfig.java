package com.came.stock.core.spring;

import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableAsync
@EnableWebMvc
@Configuration
@ComponentScan({
	"com.came.stock.core",
	"com.came.stock.model",
	"com.came.stock.utilidades"})
@Import({WebService.class, DbCfgDirect.class, InitialData.class, AsyncCustomizeExcutor.class})
public class AppConfig extends SpringBootServletInitializer {

}
