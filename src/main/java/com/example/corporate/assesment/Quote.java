package com.example.corporate.assesment;

public class Quote
{
    private String name;
    private String quote;

    public String getQuote() {
        return quote;
    }

    public String getName() {
        return name;
    }
    Quote(String name, String quote)
    {
        this.name = name;
        this.quote = quote;
    }
}
