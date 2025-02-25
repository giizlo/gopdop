package ru.gopdop.bot.dao;

import ru.gopdop.bot.models.UserDialogState;

public interface UserDialogStateDAO {
    UserDialogState getState(Long chatId);
    void saveState(UserDialogState state);
    void removeState(Long chatId);
}
