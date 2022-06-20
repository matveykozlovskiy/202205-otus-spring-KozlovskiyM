package ru.otus.springcourse.studenttestingboot.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.springcourse.studenttestingboot.config.LocaleConfig;

import java.util.Locale;

@Service
public class MessageService {
    private final MessageSource messageSource;
    private final LocaleConfig localeConfig;

    public MessageService(MessageSource messageSource, LocaleConfig localeConfig) {
        this.messageSource = messageSource;
        this.localeConfig = localeConfig;
    }

    public String geLocalizedMessage(String message){
        return messageSource.getMessage(message, null, Locale.forLanguageTag(localeConfig.getLocaleCode()));
    }
}
