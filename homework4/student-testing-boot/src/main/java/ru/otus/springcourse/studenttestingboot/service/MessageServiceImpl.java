package ru.otus.springcourse.studenttestingboot.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.springcourse.studenttestingboot.config.LocaleConfig;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageSource messageSource;
    private final LocaleConfig localeConfig;

    public MessageServiceImpl(MessageSource messageSource, LocaleConfig localeConfig) {
        this.messageSource = messageSource;
        this.localeConfig = localeConfig;
    }

    public String geLocalizedMessage(String message, Object[] params) {
        return messageSource.getMessage(message, params, Locale.forLanguageTag(localeConfig.getLocaleCode()));
    }
}
