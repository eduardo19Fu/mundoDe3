package com.aglayatech.mundo3.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "comercialkairos.reports")
@Data
public class ReportProperties {

    private String reportPath;
    private String imagePath;

}
