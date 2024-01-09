package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class ListParking implements Serializable {


    private Map<String, Object> properties= new HashMap<>();;

    @JsonAnySetter
    public void add(String key, Object value) {
        properties.put(key, value);
    }

}