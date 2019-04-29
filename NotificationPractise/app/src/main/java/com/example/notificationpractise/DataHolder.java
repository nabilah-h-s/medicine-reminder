package com.example.notificationpractise;

import java.util.ArrayList;


public class DataHolder {
    final ArrayList<Medicinedetails> prev = new ArrayList<Medicinedetails>();

    private DataHolder() {}

    static DataHolder getInstance() {
        if( instance == null ) {
            instance = new DataHolder();
        }
        return instance;
    }

    private static DataHolder instance;
}
