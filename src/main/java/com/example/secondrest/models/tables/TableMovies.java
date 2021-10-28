package com.example.secondrest.models.tables;

import com.example.secondrest.models.entities.Character;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
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

        Connection connection = null;

        try {
            connection = getConnection();

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

            return characters;
        } catch (Exception e) {
            throw new Exception("Ошибка получения персонажей из базы данных");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Character getById(int id) throws Exception {
        Connection connection = null;

        try {
            connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format("SELECT * FROM characters WHERE id=%d", id);
            ResultSet resultSet = statement.executeQuery(query);

            Character character = null;

            if (resultSet.next() == true) {
                character = new Character(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("rating")
                );
            } else {
                throw new Exception();
            }

            return character;
        } catch (Exception e) {
            throw new Exception("Ошибка получения персонажа по ИД из базы данных");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


    public void insertOne(Character character) throws Exception {
        Connection connection = null;
        try {
            connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format(Locale.US, "insert into characters (name, rating) values ('%s',%f)", character.name, character.rating);
            statement.executeUpdate(query);

        } catch (Exception e) {
            throw new Exception("Ошибка вставки персонажа в базу данных");
        }
    }

    public void deleteById(int id) throws Exception {
        Connection connection = null;
        try {
            connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format("delete from characters where id=%d", id);
            statement.executeUpdate(query);

        } catch (Exception e) {
            throw new Exception("Ошибка удаления персонажа из базы данных");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void updateById(int id, Character character) throws Exception {
        Connection connection = null;
        try {
            connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format(Locale.US, "update characters set name = '%s', rating = %f where id = %d", character.name, character.rating, id);
            statement.executeUpdate(query);

        } catch (Exception e) {
            throw new Exception("Ошибка обновления персонажа в базе данных");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
