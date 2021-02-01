package com.example.pojo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.ZoneId;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InitDestroyBean {

    private ZoneId zoneId;

    @PostConstruct
    public void init() {
        zoneId = ZoneId.of("Z");
        System.out.println("init InitDestroyBean");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy InitDestroyBean");
    }

    public void Test() {
        System.out.println("this hashcode=" + this.hashCode());
        System.out.println("zoneId=" + zoneId.getId());
    }
}
