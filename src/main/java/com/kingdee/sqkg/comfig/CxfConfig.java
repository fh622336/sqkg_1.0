package com.kingdee.sqkg.comfig;

import com.kingdee.sqkg.service.EmployeeService;
import com.kingdee.sqkg.service.impl.EmployeeServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.xml.ws.Endpoint;
@Configuration
public class CxfConfig {
    @Bean
    public ServletRegistrationBean dispatcherServlets() {
        return new ServletRegistrationBean<>(new CXFServlet(),"/sqkg/*");
    }
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public EmployeeService WebService() {
        return new EmployeeServiceImpl();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), WebService());
        endpoint.publish("/services");
        return endpoint;
    }
}

