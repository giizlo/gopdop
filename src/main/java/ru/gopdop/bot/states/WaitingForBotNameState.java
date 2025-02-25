package ru.gopdop.bot.states;

import ru.gopdop.bot.dao.UserDialogStateDAO;
import ru.gopdop.bot.logic.Request;
import ru.gopdop.bot.models.UserDialogState;
import ru.gopdop.bot.models.UserState;

public class WaitingForBotNameState implements DialogState {
    private final UserDialogStateDAO stateDAO;

    public WaitingForBotNameState(UserDialogStateDAO stateDAO) {
        this.stateDAO = stateDAO;
    }

    @Override
    public boolean canHandle(Request request) {
        UserDialogState state = stateDAO.getState(request.getChatId());
        return state != null && state.getCurrentState() == UserState.WAITING_FOR_BOT_NAME;
    }

    @Override
    public String processMessage(Request request) {
        UserDialogState state = stateDAO.getState(request.getChatId());
        if (state != null) {
            state.setTempName(request.getMessage());
            stateDAO.saveState(state);
            return "Отлично! Теперь введите ссылку на бота:";
        }
        return "Что-то пошло не так. Попробуйте начать сначала с команды /add";
    }
}
