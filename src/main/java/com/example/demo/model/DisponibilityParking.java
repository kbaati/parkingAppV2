package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class DisponibilityParking implements Serializable {

    // J'ai était forcé de mettre Map<String, Object> au lieu de Map<String, String> du à la réponse du service de Paris
    private Map<String, Object> properties= new HashMap<>();;

    @JsonAnySetter
    public void add(String key, String value) {
        properties.put(key, value);
    }

}