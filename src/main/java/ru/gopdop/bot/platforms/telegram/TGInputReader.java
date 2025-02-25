package ru.gopdop.bot.platforms.telegram;

import ru.gopdop.bot.logic.InputReader;
import ru.gopdop.bot.logic.Request;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TGInputReader implements InputReader {
    private final Update update;

    public TGInputReader(Update update) {
        this.update = update;
    }

    @Override
    public Request read() {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();


            return new Request(messageText, chatId);
        }
        return null;
    }
}