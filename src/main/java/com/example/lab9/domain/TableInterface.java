package com.example.lab9.domain;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Map;

import static com.example.lab9.utils.Utils.capitalize;


public interface TableInterface {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    default TableInterface set(Map<String, String[]> map) throws DomainTable.setFieldException {
        for(Field f: getClass().getDeclaredFields()){
            try {
                String str = String.join(",", map.get(f.getName()).clone());
                if(!str.matches(f.getAnnotation(RegEx.class).value()))
                    throw new DomainTable.setFieldException("Argument "+f.getName()+" not matches with RegEx annotation. Str = " + str);
                if (str.length() == 0) continue;
                getClass().getMethod("set" + capitalize(f.getName()), String.class).invoke(this, str);
            } catch (InvocationTargetException e){
                throw new DomainTable.setFieldException(e.getCause().getMessage(), e.getCause().getCause());
            } catch (NullPointerException | NoSuchMethodException | IllegalAccessException ignored){}
        }
        return this;
    }
}
