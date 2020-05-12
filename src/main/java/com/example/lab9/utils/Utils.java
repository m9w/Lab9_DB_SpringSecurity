package com.example.lab9.utils;


import java.util.Optional;

public class Utils {
    public static Optional<Integer> StrToInt(String s){
        try{
            int temp = Integer.parseInt(s);
            if(temp<0) return Optional.empty();
            return Optional.of(temp);
        }catch (NumberFormatException ignored){
            return Optional.empty();
        }
    }
    public static Optional<Integer> StrToInt(String s, boolean largeFromZero){
        if(largeFromZero) {
            int temp = StrToInt(s).orElse(0);
            if (temp == 0) return Optional.empty();
        } return StrToInt(s);
    }



    public static long pageCount(final long countRecords, final long pageSize){
        return countRecords/pageSize+countRecords%pageSize==0?0:1;
    }

    public static String HTMLSpecialChars(String text){
        return text
                .replaceAll("%", "%25")   // Процент
                .replaceAll(" ", "%20")   // Пробел
                .replaceAll("\t", "%20")  // Табуляция (заменяем на пробел)
                .replaceAll("\n", "%20")  // Переход строки (заменяем на пробел)
                .replaceAll("\r", "%20")  // Возврат каретки (заменяем на пробел)
                .replaceAll("!", "%21")   // Восклицательный знак
                .replaceAll("\"", "%22")  // Двойная кавычка
                .replaceAll("#", "%23")   // Октоторп, решетка
                .replaceAll("\\$", "%24") // Знак доллара
                .replaceAll("&", "%26")   // Амперсанд
                .replaceAll("'", "%27")   // Одиночная кавычка
                .replaceAll("\\(", "%28") // Открывающаяся скобка
                .replaceAll("\\)", "%29") // Закрывающаяся скобка
                .replaceAll("\\*", "%2a") // Звездочка
                .replaceAll("\\+", "%2b") // Знак плюс
                .replaceAll(",", "%2c")   // Запятая
                .replaceAll("-", "%2d")   // Дефис
                .replaceAll("\\.", "%2e") // Точка
                .replaceAll("/", "%2f")   // Слеш, косая черта
                .replaceAll(":", "%3a")   // Двоеточие
                .replaceAll(";", "%3b")   // Точка с запятой
                .replaceAll("<", "%3c")   // Открывающаяся угловая скобка
                .replaceAll("=", "%3d")   // Знак равно
                .replaceAll(">", "%3e")   // Закрывающаяся угловая скобка
                .replaceAll("\\?", "%3f") // Вопросительный знак
                .replaceAll("@", "%40")   // At sign, по цене, собачка
                .replaceAll("\\[", "%5b") // Открывающаяся квадратная скобка
                .replaceAll("\\\\", "%5c") // Одиночный обратный слеш '\'
                .replaceAll("\\]", "%5d") // Закрывающаяся квадратная скобка
                .replaceAll("\\^", "%5e") // Циркумфлекс
                .replaceAll("_", "%5f")   // Нижнее подчеркивание
                .replaceAll("`", "%60")   // Гравис
                .replaceAll("\\{", "%7b") // Открывающаяся фигурная скобка
                .replaceAll("\\|", "%7c") // Вертикальная черта
                .replaceAll("\\}", "%7d") // Закрывающаяся фигурная скобка
                .replaceAll("~", "%7e");  // Тильда
    }

    public static String getGenericClassNameForSpringIterable(Iterable<?> i) throws ClassNotFoundException {
        boolean flag = false;
        for(String s :i.toString().split(" "))
            if(s.equals("containing")) flag = true;
            else if(flag) return s;
        throw new ClassNotFoundException();
    }
    public static String capitalize(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
