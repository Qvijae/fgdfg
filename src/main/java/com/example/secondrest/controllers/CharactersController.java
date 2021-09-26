package com.example.secondrest.controllers;

import com.example.secondrest.models.DbManager;
import com.example.secondrest.models.entities.Character;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Collections;

@RestController
public class CharactersController {

    @GetMapping(value = "/characters/getAll")
    public ArrayList<Character> getAll() throws Exception {
        DbManager db = DbManager.getInstance();
        return db.tableCharacters.getAll();
    }
}

