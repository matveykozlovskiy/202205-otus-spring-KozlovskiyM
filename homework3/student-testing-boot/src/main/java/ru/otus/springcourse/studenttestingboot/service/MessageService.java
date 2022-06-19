package ru.otus.springcourse.studenttestingboot.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.springcourse.studenttestingboot.config.LocaleConfig;

import java.util.Locale;

@Service
public class MessageService {
    private final MessageSource messageSource;
    private final IOService ioService;
    private final LocaleConfig localeConfig;

    public MessageService(MessageSource messageSource, IOService ioService, LocaleConfig localeConfig) {
        this.messageSource = messageSource;
        this.ioService = ioService;
        this.localeConfig = localeConfig;
    }

    public void requestLanguage() {
        String language = ioService.printAndRead("Current language is "+ localeConfig.getLocaleCode() +". Choose your language (en/ru)");
        localeConfig.setLocaleCode(language);
    }

    public String geLocalizedMessage(String message){
        return messageSource.getMessage(message, null, Locale.forLanguageTag(localeConfig.getLocaleCode()));
    }
}
