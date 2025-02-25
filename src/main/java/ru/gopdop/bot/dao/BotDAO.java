package ru.gopdop.bot.dao;

import ru.gopdop.bot.models.Bot;
import java.util.List;
import java.util.Optional;

public interface BotDAO {
    void addBot(Bot bot);
    List<Bot> getAllBots();
    Optional<Bot> getBotByName(String name);
} 