package io.github.w7mike.controller;

import io.github.w7mike.JobConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
class InfoController {

    private DataSourceProperties datasource;
    private JobConfigurationProperties myProp;

    public InfoController(final DataSourceProperties datasource, final JobConfigurationProperties myProp) {
        this.datasource = datasource;
        this.myProp = myProp;
    }

    @GetMapping("/url")
    String url(){
        return datasource.getUrl();
    }
    @GetMapping("/myprop")
    boolean myProp(){
        return myProp.getTemplate().isAllowMultipleJobs();
    }
}
