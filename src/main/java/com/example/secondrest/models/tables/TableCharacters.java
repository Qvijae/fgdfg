package com.example.secondrest.models.tables;

import com.example.secondrest.models.entities.Character;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

public class TableCharacters {
    private String url;
    private String login;
    private String password;

    private Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");

        Properties props = new Properties();
        props.setProperty("user", login);
        props.setProperty("password", password);
        props.setProperty("ssl", "false");

        return DriverManager.getConnection(url, props);
    }

    public TableCharacters(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public ArrayList<Character> getAll() throws Exception {
        try {
            Connection connection = getConnection();

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
            throw new Exception("Ошибка выборки данных на строне сервера");
        }
    }

    public void insertOne(Character character) throws Exception {
        try {
            Connection connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format(Locale.US,"insert into characters (name, rating) values ('%s',%f)", character.name, character.rating);
            statement.executeUpdate(query);

            connection.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteById(int id) throws Exception {
        try {
            Connection connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format("delete from characters where id=%d",id);
            statement.executeUpdate(query);

            connection.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public void updateById(int id, Character character) throws Exception {
        try {
            Connection connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format(Locale.US,"update characters set name = '%s', rating = %f where id = %d", character.name, character.rating, id);
            statement.executeUpdate(query);

            connection.close();
        } catch (Exception e) {
            throw e;
        }
    }
}
