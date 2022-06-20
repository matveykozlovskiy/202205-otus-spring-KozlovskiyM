package ru.otus.springcourse.studenttestingboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("application")
@Component
public class ApplicationConfig implements LocaleConfig, FileConfig{
    private String localeCode = "en-US";

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public String getFileName(){
        return "questions_"+ localeCode.substring(0,2) + ".csv";
    }
}
