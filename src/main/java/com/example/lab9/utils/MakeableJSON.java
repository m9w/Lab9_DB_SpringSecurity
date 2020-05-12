package com.example.lab9.utils;

import com.example.lab9.domain.RegEx;

import java.lang.reflect.Field;

import static com.example.lab9.utils.Utils.getGenericClassNameForSpringIterable;

public interface MakeableJSON{
    static String toJSON(Iterable<? extends MakeableJSON> iterator){
        JSONBuilder JSON = new JSONBuilder();
        String[] pages = iterator.toString().split(" ");

        JSON.append(new JSONBuilder(true)
            .append("current",Long.parseLong(pages[1]))
            .append("max",Long.parseLong(pages[3]))
        );

        try {
            JSON.append(getRegExJSON(Class.forName(getGenericClassNameForSpringIterable(iterator))));
        } catch (ClassNotFoundException ignored) {}

        for(MakeableJSON makeJSON: iterator)
            JSON.append(makeJSON.toJSONStringObject());
        return JSON.build();
    }

    String toJSONStringObject();

    static String getRegExJSON(Class c) {
        JSONBuilder JSON = new JSONBuilder(true);
        for(Field field: c.getDeclaredFields())
            if(field.isAnnotationPresent(RegEx.class))
                JSON.append(field.getName(),field.getAnnotation(RegEx.class).value());
        return JSON.build();
    }
}
