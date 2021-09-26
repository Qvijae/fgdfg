package com.example.secondrest.models.tables;

import com.example.secondrest.models.entities.Character;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class TableCharacters {
    private String url;
    private String login;
    private String password;

    public TableCharacters(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public ArrayList<Character> getAll() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");

            Properties props = new Properties();
            props.setProperty("user", login);
            props.setProperty("password", password);
            props.setProperty("ssl", "false");

            Connection connection = DriverManager.getConnection(url, props);

            Statement statement = connection.createStatement();

            String query = String.format("SELECT * FROM characters");
            ResultSet resultSet = statement.executeQuery(query);

            ArrayList<Character> characters = new ArrayList<>();

            while (resultSet.next() == true) {
                Character character = new Character(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("rating")
                );

                characters.add(character);
            }

            connection.close();

            return characters;
        } catch (Exception e) {
            throw e;
        }
    }
}
