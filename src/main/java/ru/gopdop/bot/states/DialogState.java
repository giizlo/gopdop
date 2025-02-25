package ru.gopdop.bot.states;

import ru.gopdop.bot.logic.Request;

public interface DialogState {
    String processMessage(Request request);
    boolean canHandle(Request request);
}
