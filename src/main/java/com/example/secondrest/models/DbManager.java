package com.example.secondrest.models;

import com.example.secondrest.models.tables.TableCharacters;

public class DbManager {

    private static DbManager instance = null;

    public static DbManager getInstance()
    {
        if(instance==null)
        {
            instance = new DbManager();
        }
        return instance;
    }

    private String URL = "jdbc:postgresql://localhost:55000/postgres?currentSchema=pornstars";
    private String LOGIN = "postgres";
    private String PASSWORD = "";

    public TableCharacters tableCharacters;

    public DbManager() {
        tableCharacters = new TableCharacters(URL, LOGIN, PASSWORD);
    }
}
