<#assign myParentDir="domain">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage};




import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import org.springframework.context.annotation.Bean;

import com.yqjp.springboot.starter.mybatis.EnableyqjpMybatis;
import com.yqjp.web.annotation.WebConfigurer;
import com.yqjp.web.annotation.WebExceptionHandler;
import com.yqjp.springboot.starter.mybatis.EnableyqjpMybatis;
import org.mybatis.spring.annotation.MapperScan;

<#include "/copyright_class.include" >
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableyqjpMybatis
@MapperScan(basePackages = "${basepackage}.dao.mapper")
@WebExceptionHandler
@WebConfigurer
public class ${projectname}Application {
    @Bean   
    public MultipartConfigElement multipartConfigElement() {   
            MultipartConfigFactory factory = new MultipartConfigFactory();  
            factory.setMaxFileSize("50MB"); //KB,MB  
            factory.setMaxRequestSize("100MB");   
            return factory.createMultipartConfig();   
    } 
    
    public static void main(String[] args) {
    	SpringApplication.run(${projectname}Application.class, args);
    }
    
    
}



