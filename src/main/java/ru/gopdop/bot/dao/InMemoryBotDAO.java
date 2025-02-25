package ru.gopdop.bot.dao;

import ru.gopdop.bot.models.Bot;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Optional;

public class InMemoryBotDAO implements BotDAO {
    private final List<Bot> bots = new ArrayList<>();
    
    @Override
    public void addBot(Bot bot) {
        bots.add(bot);
    }
    
    @Override
    public List<Bot> getAllBots() {
        return Collections.unmodifiableList(bots);
    }

    @Override
    public Optional<Bot> getBotByName(String name) {
        return bots.stream()
            .filter(bot -> bot.getName().equals(name))
            .findFirst();
    }
} 