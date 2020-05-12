package com.example.lab9.domain;

import com.example.lab9.controller.MainController;

public enum ListAccessTables {
    dept,
    emp;


    public static TableInterface getObject(ListAccessTables table){
        switch (table){
            case emp: return new Employee();
            case dept: return new Department();
            default:  throw new MainController.ResourceNotFoundException();
        }
    }

    public static boolean contains(String test) {
        for (ListAccessTables c : ListAccessTables.values())
            if (c.name().equals(test))
                return true;
        return false;
    }
}
