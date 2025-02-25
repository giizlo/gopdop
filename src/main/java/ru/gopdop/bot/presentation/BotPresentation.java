package ru.gopdop.bot.presentation;

import ru.gopdop.bot.models.Bot;
import java.util.List;

public class BotPresentation {
    public String formatBotList(List<Bot> bots) {
        if (bots.isEmpty()) {
            return "Список ботов пуст 😢";
        }

        StringBuilder result = new StringBuilder("📋 Список доступных ботов:\n\n");
        for (Bot bot : bots) {
            result.append(formatBot(bot)).append("\n\n");
        }
        return result.toString();
    }

    public String formatBot(Bot bot) {
        return String.format("🤖 %s\n📎 %s\n📝 %s", 
            bot.getName(), 
            bot.getLink(), 
            bot.getDescription());
    }

    public String formatError(String message) {
        return "❌ " + message;
    }
} 