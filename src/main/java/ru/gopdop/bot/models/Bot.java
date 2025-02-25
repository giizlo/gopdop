package ru.gopdop.bot.models;

public class Bot {
    private Long id;
    private String name;
    private String link;
    private String description;

    public Bot() {
    }

    public Bot(Long id, String name, String link, String description) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}