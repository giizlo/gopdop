package ru.gopdop.bot.platforms.console;

import ru.gopdop.bot.logic.InputReader;
import ru.gopdop.bot.logic.Request;

import java.util.Scanner;

public class ConsoleInputReader implements InputReader {
    private Scanner scanner;

    public ConsoleInputReader() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Request read() {
        System.out.print("Введите сообщение: ");
        String userMessage = scanner.nextLine();
        Long chatId = 0L;


        return new Request(userMessage, chatId);
    }
}