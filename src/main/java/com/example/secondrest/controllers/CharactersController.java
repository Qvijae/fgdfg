package com.example.secondrest.controllers;

import com.example.secondrest.models.DbManager;
import com.example.secondrest.models.entities.Character;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CharactersController {

    @GetMapping(value = "/characters/getAll")
    public ArrayList<Character> getAll() throws Exception {
        DbManager db = DbManager.getInstance();
        return db.tableCharacters.getAll();
    }

    @PostMapping(value = "/characters/insertOne")
    public void insertOne(@RequestBody Character character) throws Exception{
        DbManager db = DbManager.getInstance();
        db.tableCharacters.insertOne(character);
    }

    @PutMapping(value = "/characters/updateById/{id}")
    public void insertOne(@PathVariable int id, @RequestBody Character character) throws Exception{
        DbManager db = DbManager.getInstance();
        db.tableCharacters.updateById(id, character);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("ERROR: "+exception.getMessage());
    }


}

