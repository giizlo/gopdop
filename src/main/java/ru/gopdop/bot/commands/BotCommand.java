package ru.gopdop.bot.commands;

import ru.gopdop.bot.logic.Request;

public interface BotCommand {
    boolean canExecute(String command);
    String execute(Request request);
}
