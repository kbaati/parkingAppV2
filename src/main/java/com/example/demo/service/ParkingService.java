package com.example.demo.service;

import com.example.demo.dto.ParkingAppDTO;
import com.example.demo.dto.ParkingDTO;
import com.example.demo.exception.DataRetrievalException;
import com.example.demo.model.ApiResponse;
import com.example.demo.model.DisponibilityParking;
import com.example.demo.model.ListParking;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface ParkingService {
    // Méthode générique pour appeler l'API
    default <T> List<T> fetchData(String baseUrl, String nextUrl, String[] selectFields, ParameterizedTypeReference<ApiResponse<T>> responseType) {

        // ya des trucs à ameliorer ici dans le traitement des url..
        List<T> results = new ArrayList<>();
        URL url;
        if (selectFields != null && selectFields.length > 0) {
            // Filtrer les champs non vides
            String nonEmptyFields = Arrays.stream(selectFields)
                    .filter(field -> !field.isBlank())
                    .collect(Collectors.joining(","));

            if (!nonEmptyFields.isEmpty()) {
                nextUrl = nextUrl + "?select=" + nonEmptyFields;
            }
        }


        try {
            while (nextUrl != null && !nextUrl.isEmpty()) {
                url = new URL(nextUrl);
                // System.out.println("url: " + url);
                WebClient webClient = WebClient.builder().baseUrl(url.toString()).build();
                Mono<ApiResponse<T>> response = webClient.get()
                        .uri(url.toURI())
                        .retrieve()
                        .bodyToMono(responseType);

                ApiResponse<T> apiResponse = response.blockOptional()
                        .orElseThrow(() -> new DataRetrievalException("Réponse nulle lors de la récupération des données."));

                if (apiResponse.getResults() != null) {
                    results.addAll(apiResponse.getResults());
                } else {
                    throw new DataRetrievalException("Les résultats de la récupération des données sont nuls.");
                }
                //System.out.println("apiResponse.getNext(): " + apiResponse.getNext());
                nextUrl = apiResponse.getNext();
            }
        } catch (MalformedURLException e) {
            throw new DataRetrievalException("L'url construit avec les parametres est faux");
        } catch (Exception e) {
            throw new DataRetrievalException("Erreur lors de la récupération des données.", e);
        }
        return results;
    }

    /**
     * récupérer les informations de la gestion du parking relative à la commune
     * @param commune
     * @return
     */
    ParkingDTO getParkingByCommune(String commune);

    /**
     * Récupère la liste des parkings à partir de l'API externe.
     *
     * @param parkingDTO
     * @return Une liste des parkings.
     * @throws DataRetrievalException En cas d'échec lors de la récupération des parkings.
     */
    List<ListParking> getListParking(ParkingDTO parkingDTO);

    /**
     * Récupère la disponibilité des parkings à partir de l'API externe.
     *
     * @param parkingDTO
     * @return Une liste des disponibilités de parkings.
     * @throws DataRetrievalException En cas d'échec lors de la récupération des données de disponibilité.
     */
    List<DisponibilityParking> getDispoParking(ParkingDTO parkingDTO);

    /**
     * Récupère la liste des parkings avec leur disponibilité.
     *
     * @param parkingDTO
     * @return Une liste de DTO représentant la disponibilité des parkings.
     */
    List<ParkingAppDTO> getParkingWithDisponibility(ParkingDTO parkingDTO);
    /**
     * Récupère la liste des parkings avec leur disponibilité avec pagination.
     *
     * @param parkingDTO
     * @return Une liste de DTO représentant la disponibilité des parkings.
     */
    List<ParkingAppDTO> getParkingWithDisponibility(ParkingDTO parkingDTO, int page, int size);

    }
