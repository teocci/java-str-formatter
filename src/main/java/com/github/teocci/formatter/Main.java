package com.github.teocci.formatter;

import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        Dog d = new Dog();
        d.name = "Fido";
        d.owner = "Jane Doe";
        d.pronoun = "his";
        Map<String, Object> map = new HashMap<>();
        map.put("d", d);

        String str = StrFormatter.format("My dog is named ${d.name}, and ${d.owner} owns ${d.pronoun}.", map);
        System.out.println(str);

    }
}