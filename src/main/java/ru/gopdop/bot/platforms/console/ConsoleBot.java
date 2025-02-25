package ru.gopdop.bot.platforms.console;

import ru.gopdop.bot.logic.*;
import ru.gopdop.bot.platforms.Bot;

public class ConsoleBot implements Bot {
    private final InputReader inputReader;
    private final OutputWriter outputWriter;
    private final RequestHandler requestHandler;

    public ConsoleBot(InputReader consoleInputReader, RequestHandler requestHandler, OutputWriter outputWriter) {
        this.inputReader = consoleInputReader;
        this.outputWriter = outputWriter;
        this.requestHandler = requestHandler;
    }

    @Override
    public void startBot() {
        while (true) {
            Request request = inputReader.read();
            if (request != null) {
                requestHandler.handle(request, outputWriter);
            }
        }
    }
}