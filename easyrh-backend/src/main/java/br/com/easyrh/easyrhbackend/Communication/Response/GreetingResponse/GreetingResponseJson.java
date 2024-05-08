package br.com.easyrh.easyrhbackend.Communication.Response.GreetingResponse;

public class GreetingResponseJson {
    private final long Id;
    private final String Content;

    public GreetingResponseJson(String Content, long Id) {
        this.Content = Content;
        this.Id = Id;
    }

    public long getId() {
        return Id;
    }

    public String getContent() {
        return Content;
    }
}
