package com.example.secondrest.controllers;

import com.example.secondrest.models.DbManager;
import com.example.secondrest.models.entities.Character;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/characters", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CharactersController {

    private void checkApiKey(String apiKey) throws Exception {
        String originalApiKey = "1212";

        if (originalApiKey.equals(apiKey) == false) {
            throw new Exception("Ошибка неверный API ключ");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("ERROR: " + exception.getMessage());
    }


    @GetMapping(value = "/getAll")
    public ArrayList<Character> getAll(@RequestHeader("APIKEY") String apiKey) throws Exception {
        checkApiKey(apiKey);

        DbManager db = DbManager.getInstance();
        return db.tableCharacters.getAll();
    }

    @GetMapping(value = "/getById/{id}")
    public Character getById(@RequestHeader("APIKEY") String apiKey, @PathVariable int id) throws Exception {
        checkApiKey(apiKey);

        DbManager db = DbManager.getInstance();
        return db.tableCharacters.getById(id);
    }

    @PostMapping(value = "/insertOne")
    public void insertOne(@RequestHeader("APIKEY") String apiKey, @RequestBody Character character) throws Exception {
        checkApiKey(apiKey);

        DbManager db = DbManager.getInstance();
        db.tableCharacters.insertOne(character);
    }

    @PutMapping(value = "/updateById/{id}")
    public void updateById(@RequestHeader("APIKEY") String apiKey, @PathVariable int id, @RequestBody Character character) throws Exception {
        checkApiKey(apiKey);

        DbManager db = DbManager.getInstance();
        db.tableCharacters.getById(id);
        db.tableCharacters.updateById(id, character);
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public void deleteById(@RequestHeader("APIKEY") String apiKey, @PathVariable int id) throws Exception {
        checkApiKey(apiKey);

        DbManager db = DbManager.getInstance();
        db.tableCharacters.getById(id);
        db.tableCharacters.deleteById(id);
    }


}

