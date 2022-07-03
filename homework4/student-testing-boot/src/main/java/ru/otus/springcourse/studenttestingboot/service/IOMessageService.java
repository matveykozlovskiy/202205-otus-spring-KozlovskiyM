package ru.otus.springcourse.studenttestingboot.service;

import org.springframework.stereotype.Service;

@Service
public class IOMessageService {
    private final IOService ioService;
    private final MessageService messageService;

    public IOMessageService(IOService ioService, MessageService messageService) {
        this.ioService = ioService;
        this.messageService = messageService;
    }

    public String printLocalizedMessageAndRead(String message, Object[] params) {
        return ioService.printAndRead(messageService.geLocalizedMessage(message, params));
    }

    public void printLocalizedMessage(String message, Object[] params) {
        ioService.print(messageService.geLocalizedMessage(message, params));
    }
}
