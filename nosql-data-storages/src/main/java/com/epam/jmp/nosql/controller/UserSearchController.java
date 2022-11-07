package com.epam.jmp.nosql.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.jmp.nosql.model.User;
import com.epam.jmp.nosql.service.UserSearchService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/search/user")
@AllArgsConstructor
public class UserSearchController {
    private final UserSearchService userSearchService;

    @GetMapping
    public ResponseEntity<List<User>> search(@RequestParam(value = "q") String query) {
        return ResponseEntity.ok(userSearchService.search(query));
    }
}
