package ru.gopdop.bot.logic;

public class EchoRequestHanger implements RequestHanger{

    public void handle(Request request, OutputWriter outputWriter) {
        Response response = new Response(request.getMessage());
        outputWriter.write(response);
    }
}
