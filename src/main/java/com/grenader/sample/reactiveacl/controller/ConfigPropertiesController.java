package com.grenader.sample.reactiveacl.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigPropertiesController {

    @Value("${acl.generic.property:Default Generic Property Value}")
    private String genericProperty;

    @Value("${acl.application.property:Default Application Property value}")
    private String applicationProperty;

    @RequestMapping("/property/generic")
    String getGenericProperty() {
        return this.genericProperty;
    }

    @RequestMapping("/property/application")
    String getApplicationProperty() {
        return this.applicationProperty;
    }
}
