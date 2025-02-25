package ru.gopdop.bot.commands;

import ru.gopdop.bot.dao.UserDialogStateDAO;
import ru.gopdop.bot.logic.Request;
import ru.gopdop.bot.models.UserDialogState;
import ru.gopdop.bot.models.UserState;

public class AddBotCommand implements BotCommand {
    private final UserDialogStateDAO stateDAO;

    public AddBotCommand(UserDialogStateDAO stateDAO) {
        this.stateDAO = stateDAO;
    }

    @Override
    public boolean canExecute(String command) {
        return command.startsWith("/add");
    }

    @Override
    public String execute(Request request) {
        UserDialogState state = new UserDialogState(request.getChatId());
        state.setCurrentState(UserState.WAITING_FOR_BOT_NAME);
        stateDAO.saveState(state);
        return "Введите название бота:";
    }
}
