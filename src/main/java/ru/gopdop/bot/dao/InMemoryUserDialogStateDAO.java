package ru.gopdop.bot.dao;

import ru.gopdop.bot.models.UserDialogState;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserDialogStateDAO implements UserDialogStateDAO {
    private final ConcurrentHashMap<Long, UserDialogState> states = new ConcurrentHashMap<>();

    @Override
    public UserDialogState getState(Long chatId) {
        return states.get(chatId);
    }

    @Override
    public void saveState(UserDialogState state) {
        if (state != null && state.getChatId() != null) {
            states.put(state.getChatId(), state);
        }
    }

    @Override
    public void removeState(Long chatId) {
        if (chatId != null) {
            states.remove(chatId);
        }
    }
}
