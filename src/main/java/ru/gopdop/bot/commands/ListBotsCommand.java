package ru.gopdop.bot.commands;

import ru.gopdop.bot.dao.BotDAO;
import ru.gopdop.bot.logic.Request;
import ru.gopdop.bot.presentation.BotPresentation;

public class ListBotsCommand implements BotCommand {
    private final BotDAO botDAO;
    private final BotPresentation presentation;

    public ListBotsCommand(BotDAO botDAO, BotPresentation presentation) {
        this.botDAO = botDAO;
        this.presentation = presentation;
    }

    @Override
    public boolean canExecute(String command) {
        return command.startsWith("/list");
    }

    @Override
    public String execute(Request request) {
        return presentation.formatBotList(botDAO.getAllBots());
    }
}
