package com.github.teocci.formatter;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrFormatter
{
    private static final String fieldStart = "\\$\\{";
    private static final String fieldEnd = "\\}";

    private static final String regex = fieldStart + "([^}]+)" + fieldEnd;
    private static final Pattern pattern = Pattern.compile(regex);

    public static String format(String format, Map<String, Object> objects)
    {
        Matcher m = pattern.matcher(format);
        String result = format;
        try {
            while (m.find()) {
                String[] found = m.group(1).split("\\.");
                Object o = objects.get(found[0]);
                Field f = null;
                f = o.getClass().getField(found[1]);
                String newVal = f.get(o).toString();
                result = result.replaceFirst(regex, newVal);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
