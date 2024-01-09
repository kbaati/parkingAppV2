package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Kbaati
 * elle gere la reponse
 * @param <T> type generique
 */
@Data
@NoArgsConstructor
public class ApiResponse<T> implements Serializable {

    private int total;
    @JsonProperty("total_count")
    private int totalCount;
    private String next;
    private List<T> results;
}
