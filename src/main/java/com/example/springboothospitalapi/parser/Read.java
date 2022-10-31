package com.example.springboothospitalapi.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Read<T> {
    private Parser<T> parse;
    boolean removeColumnName = false;

    public Read(Parser<T> parse){
        this.parse = parse;
    }

    public Read(Parser<T> parse, boolean removeColumnName){
        this.parse = parse;
        this.removeColumnName = removeColumnName;
    }
    public List<T> readLines(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        List<T> myList = new ArrayList<>();
        String line;
        int exception_count = 0;
        if(removeColumnName){
            br.readLine();
        }
        while ((line = br.readLine()) != null) {
            try {
                T tmp = parse.parse(line);
                myList.add(tmp);
            } catch (Exception e) {
                exception_count ++;
            }
        }
        System.out.println("파싱 문제 건수 : " + exception_count);
        br.close();
        return myList;
    }
}
