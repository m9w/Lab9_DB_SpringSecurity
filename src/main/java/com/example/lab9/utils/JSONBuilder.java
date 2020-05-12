package com.example.lab9.utils;

public class JSONBuilder {
    private StringBuilder builder;
    boolean object;
    public JSONBuilder() {
        builder = new StringBuilder("[");
    }
    public JSONBuilder(boolean object) {
        this.object = true;
        builder = new StringBuilder("{");
    }
    public JSONBuilder append(String key, String value){
        builder.append('"').append(key).append("\":\"").append(value).append("\",");
        return this;
    }
    public JSONBuilder append(String key, Long value){
        builder.append('"').append(key).append("\":").append(value).append(",");
        return this;
    }
    public JSONBuilder append(String key, Boolean value){
        builder.append('"').append(key).append("\":").append(value).append(",");
        return this;
    }
    public JSONBuilder append(String key, Double value){
        builder.append('"').append(key).append("\":").append(value).append(",");
        return this;
    }
    public JSONBuilder append(JSONBuilder value){
        builder.append(value).append(",");
        return this;
    }
    public JSONBuilder append(String value){
        builder.append(value).append(",");
        return this;
    }
    public String build(){
        if(builder.length()>1)
        builder.setLength(builder.length()-1);
        builder.append(object?"}":"]");
        return builder.toString();
    }

    @Override
    @Deprecated //Usage build
    public String toString() {
        return build();
    }
}
