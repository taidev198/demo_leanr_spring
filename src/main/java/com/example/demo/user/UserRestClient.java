package com.example.demo.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.util.List;

@Component
public class UserRestClient {
    private final RestClient restClient;

    public UserRestClient(RestClient.Builder restClient) {
        this.restClient = restClient
                .baseUrl("https://jsonplaceholder.typicode.com")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    public List<User> findAllUser() {

      return  restClient
              .get()
              .uri("/users")
              .retrieve()
              .body(new ParameterizedTypeReference<>() {
              });

    }

    public User findUserById(String id) {
        return restClient
                .get()
                .uri("/users/{id}", id)
                .retrieve()
                .body(User.class);
    }




}
