package ru.gopdop.bot.platforms.telegram;

import ru.gopdop.bot.logic.RequestHandler;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import ru.gopdop.bot.logic.Response;
import ru.gopdop.bot.logic.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TGBot extends TelegramLongPollingBot {
    private String botUsername;
    private String botToken;
    private final RequestHandler requestHandler;

    public TGBot(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        
        Properties props = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/bot.properties")) {
            props.load(input);
            botUsername = props.getProperty("bot.name");
            botToken = props.getProperty("bot.token");
        } catch (IOException e) {
            e.printStackTrace();
            botUsername = botToken = "";
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            Request request = new Request(messageText, chatId);
            TGOutputWriter outputWriter = new TGOutputWriter(this, chatId.toString());
            requestHandler.handle(request, outputWriter);
        }
    }
}