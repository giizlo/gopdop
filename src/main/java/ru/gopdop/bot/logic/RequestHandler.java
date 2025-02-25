package ru.gopdop.bot.logic;

public interface RequestHandler {
    void handle(Request request, OutputWriter outputWriter);
}
