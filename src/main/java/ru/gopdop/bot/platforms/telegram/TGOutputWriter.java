package ru.gopdop.bot.platforms.telegram;

import ru.gopdop.bot.logic.OutputWriter;
import ru.gopdop.bot.logic.Response;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TGOutputWriter implements OutputWriter {
    private static final Logger logger = LoggerFactory.getLogger(TGOutputWriter.class);
    private final TGBot telegramBot;
    private final String chatId;

    public TGOutputWriter(TGBot telegramBot, String chatId) {
        this.telegramBot = telegramBot;
        this.chatId = chatId;
    }

    @Override
    public void write(Response response) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(response.getText());

        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            logger.error("Failed to send message", e);
        }
    }
}