package ru.gopdop.bot.platforms.console;

import ru.gopdop.bot.logic.OutputWriter;
import ru.gopdop.bot.logic.Response;

public class ConsoleOutputWriter implements OutputWriter {

    @Override
    public void write(Response response) {
        System.out.println("Бот: " + response.getText());
    }
}