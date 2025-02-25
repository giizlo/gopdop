package ru.gopdop.bot.states;

import ru.gopdop.bot.dao.BotDAO;
import ru.gopdop.bot.dao.UserDialogStateDAO;
import ru.gopdop.bot.logic.Request;
import ru.gopdop.bot.models.Bot;
import ru.gopdop.bot.models.UserDialogState;
import ru.gopdop.bot.models.UserState;

public class WaitingForBotDescriptionState implements DialogState {
    private final UserDialogStateDAO stateDAO;
    private final BotDAO botDAO;

    public WaitingForBotDescriptionState(UserDialogStateDAO stateDAO, BotDAO botDAO) {
        this.stateDAO = stateDAO;
        this.botDAO = botDAO;
    }

    @Override
    public boolean canHandle(Request request) {
        UserDialogState state = stateDAO.getState(request.getChatId());
        return state != null && state.getCurrentState() == UserState.WAITING_FOR_BOT_DESCRIPTION;
    }

    @Override
    public String processMessage(Request request) {
        UserDialogState state = stateDAO.getState(request.getChatId());
        if (state != null) {
            state.setTempDescription(request.getMessage());
            Bot bot = state.createBot();
            botDAO.addBot(bot);
            stateDAO.removeState(request.getChatId());
            return "Бот успешно добавлен!";
        }
        return "Что-то пошло не так. Попробуйте начать сначала с команды /add";
    }
}
