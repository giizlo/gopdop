package ru.gopdop.bot.models;


public class UserDialogState {
    private Long chatId;
    private UserState currentState;
    private Long botId;

    private String tempName;
    private String tempLink;
    private String tempDescription;

    public UserDialogState(Long chatId) {
        this.chatId = chatId;
        this.currentState = UserState.WAITING_FOR_COMMAND;
        this.botId = System.currentTimeMillis();
    }

    public Long getChatId() {
        return chatId;
    }

    public UserState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(UserState currentState) {
        this.currentState = currentState;
    }

    public Long getBotId() {
        return botId;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
        this.currentState = UserState.WAITING_FOR_BOT_LINK;
    }

    public String getTempLink() {
        return tempLink;
    }

    public void setTempLink(String tempLink) {
        this.tempLink = tempLink;
        this.currentState = UserState.WAITING_FOR_BOT_DESCRIPTION;
    }

    public String getTempDescription() {
        return tempDescription;
    }

    public void setTempDescription(String tempDescription) {
        this.tempDescription = tempDescription;
        this.currentState = UserState.WAITING_FOR_COMMAND;
    }

    public Bot createBot() {
        return new Bot(botId, tempName, tempLink, tempDescription);
    }

    public void clearTempData() {
        this.tempName = null;
        this.tempLink = null;
        this.tempDescription = null;
        this.currentState = UserState.WAITING_FOR_COMMAND;
    }
}
