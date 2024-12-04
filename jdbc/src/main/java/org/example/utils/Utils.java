package org.example.utils;

public class Utils {
    public java.sql.Date getDate(java.util.Date jud){
        return new java.sql.Date(jud.getTime());
    };
}
