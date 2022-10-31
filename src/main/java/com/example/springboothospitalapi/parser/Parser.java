package com.example.springboothospitalapi.parser;

public interface Parser<T> {
    public T parse(String str);
}
