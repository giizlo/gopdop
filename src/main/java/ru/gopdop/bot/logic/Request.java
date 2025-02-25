package ru.gopdop.bot.logic;

public class Request {
    private String message;
    private Long chatId;

    public Request(String message, Long chatId) {
        this.message = message;
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }

    public Long getChatId() {
        return chatId;
    }
} 