package ru.gopdop.bot.states;

import ru.gopdop.bot.dao.UserDialogStateDAO;
import ru.gopdop.bot.logic.Request;
import ru.gopdop.bot.models.UserDialogState;
import ru.gopdop.bot.models.UserState;

public class WaitingForBotLinkState implements DialogState {
    private final UserDialogStateDAO stateDAO;

    public WaitingForBotLinkState(UserDialogStateDAO stateDAO) {
        this.stateDAO = stateDAO;
    }

    @Override
    public boolean canHandle(Request request) {
        UserDialogState state = stateDAO.getState(request.getChatId());
        return state != null && state.getCurrentState() == UserState.WAITING_FOR_BOT_LINK;
    }

    @Override
    public String processMessage(Request request) {
        UserDialogState state = stateDAO.getState(request.getChatId());
        if (state != null) {
            state.setTempLink(request.getMessage());
            stateDAO.saveState(state);
            return "Введите описание бота:";
        }
        return "Что-то пошло не так. Попробуйте начать сначала с команды /add";
    }
}
