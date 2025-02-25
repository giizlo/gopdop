package ru.gopdop.bot.states;

import ru.gopdop.bot.commands.BotCommand;
import ru.gopdop.bot.dao.UserDialogStateDAO;
import ru.gopdop.bot.logic.Request;
import ru.gopdop.bot.models.UserDialogState;
import ru.gopdop.bot.models.UserState;

import java.util.List;

public class WaitingForCommandState implements DialogState {
    private final UserDialogStateDAO stateDAO;
    private final List<BotCommand> commands;

    public WaitingForCommandState(UserDialogStateDAO stateDAO, List<BotCommand> commands) {
        this.stateDAO = stateDAO;
        this.commands = commands;
    }

    @Override
    public boolean canHandle(Request request) {
        UserDialogState state = stateDAO.getState(request.getChatId());
        return state == null || state.getCurrentState() == UserState.WAITING_FOR_COMMAND;
    }

    @Override
    public String processMessage(Request request) {
        String message = request.getMessage();
        
        for (BotCommand command : commands) {
            if (command.canExecute(message)) {
                return command.execute(request);
            }
        }

        return "Неизвестная команда. Используйте /start для получения списка команд.";
    }
}
