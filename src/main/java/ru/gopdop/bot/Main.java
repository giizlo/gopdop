package ru.gopdop.bot;

import ru.gopdop.bot.platforms.console.*;
import ru.gopdop.bot.platforms.telegram.*;
import ru.gopdop.bot.logic.*;
import ru.gopdop.bot.states.*;
import ru.gopdop.bot.commands.*;
import ru.gopdop.bot.dao.*;
import ru.gopdop.bot.presentation.BotPresentation;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Инициализация DAO
            BotDAO botDAO = new InMemoryBotDAO();
            UserDialogStateDAO stateDAO = new InMemoryUserDialogStateDAO();
            BotPresentation presentation = new BotPresentation();

            // Инициализация команд
            List<BotCommand> commands = new ArrayList<>();
            commands.add(new StartBotCommand());
            commands.add(new ListBotsCommand(botDAO, presentation));
            commands.add(new AddBotCommand(stateDAO));

            // Инициализация состояний
            List<DialogState> states = new ArrayList<>();
            states.add(new WaitingForCommandState(stateDAO, commands));
            states.add(new WaitingForBotNameState(stateDAO));
            states.add(new WaitingForBotLinkState(stateDAO));
            states.add(new WaitingForBotDescriptionState(stateDAO, botDAO));

            // Инициализация обработчика запросов
            RequestHandler requestHandler = new StateRequestHandler(states);

            // Запуск консольного бота
            InputReader consoleInputReader = new ConsoleInputReader();
            OutputWriter consoleOutputWriter = new ConsoleOutputWriter();
            ConsoleBot consoleBot = new ConsoleBot(consoleInputReader, requestHandler, consoleOutputWriter);
            new Thread(consoleBot::startBot).start();

            // Запуск Telegram бота
            try {
                TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
                TGBot telegramBot = new TGBot(requestHandler);
                botsApi.registerBot(telegramBot);
                System.out.println("Telegram бот успешно запущен!");
            } catch (TelegramApiException e) {
                System.err.println("Ошибка при запуске Telegram бота: " + e.getMessage());
                System.err.println("Проверьте правильность конфигурации в файле bot.properties");
            }

        } catch (Exception e) {
            System.err.println("Ошибка при запуске бота: " + e.getMessage());
            e.printStackTrace();
        }
    }
}