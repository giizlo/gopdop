package ru.gopdop.bot.commands;

import ru.gopdop.bot.logic.Request;

public class StartBotCommand implements BotCommand {
    @Override
    public boolean canExecute(String command) {
        return command.startsWith("/start");
    }

    @Override
    public String execute(Request request) {
        return "Привет! Я бот для управления списком ботов.\n" +
               "Доступные команды:\n" +
               "/add - добавить нового бота\n" +
               "/list - показать список ботов";
    }
}
