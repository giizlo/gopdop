package ru.gopdop.bot.logic;

import ru.gopdop.bot.states.DialogState;
import java.util.List;

public class StateRequestHandler implements RequestHandler {
    private final List<DialogState> states;

    public StateRequestHandler(List<DialogState> states) {
        this.states = states;
    }

    @Override
    public void handle(Request request, OutputWriter outputWriter) {
        String responseText = null;

        for (DialogState state : states) {
            if (state.canHandle(request)) {
                responseText = state.processMessage(request);
                break;
            }
        }
        
        if (responseText == null) {
            responseText = "Неизвестная команда. Используйте /start для начала работы.";
        }
        
        outputWriter.write(new Response(responseText));
    }
}
