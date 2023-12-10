package com.testing.test.controller;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.testing.test.model.Users;

@RestController
@RequestMapping("/todos")
public class UserController {

	private static final String USER_VALID_KEY = "userValid";
	private static final String USER_DETAIL_STRING = "userDetails";
	
    private final String apiUrl = "https://jsonplaceholder.typicode.com/todos";

    private final RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(Users.class);


    @Autowired
    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Users[] getTodos() {
        return restTemplate.getForObject(apiUrl, Users[].class);
    }

    public Users getTodo(Long todoId) {
        return restTemplate.getForObject(apiUrl + "/" + todoId, Users.class);
    }

    @GetMapping
    public Users[] getAllUsers() {
        return getTodos();
    }

    @RequestMapping("/{todoId}")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getUsersById(@PathVariable Long todoId) {
        Users[] allTodos = getTodos();

        try {
            for (Users todo : allTodos) {
                Long currentTodoId = todo.getId();
                if (currentTodoId != null && currentTodoId.equals(todoId)) {
                    Map<String, Object> response = new HashMap<>();
                    response.put(USER_DETAIL_STRING, todo);
                    response.put(USER_VALID_KEY, true);
                    return ResponseEntity.ok(response);
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put(USER_DETAIL_STRING, new HashMap<>());
            response.put(USER_VALID_KEY, false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            log.error("An error occurred while processing the request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

}
