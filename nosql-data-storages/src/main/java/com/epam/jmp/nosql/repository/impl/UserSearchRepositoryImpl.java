package com.epam.jmp.nosql.repository.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.search.SearchQuery;
import com.couchbase.client.java.search.result.SearchRow;
import com.epam.jmp.nosql.model.User;
import com.epam.jmp.nosql.repository.UserRepository;
import com.epam.jmp.nosql.repository.UserSearchRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserSearchRepositoryImpl implements UserSearchRepository {
    private final Cluster cluster;
    private final UserRepository userRepository;

    @Value("${search.index.name}")
    private String searchIndexName;

    @Override
    public List<User> search(String query) {
        return userRepository.findAllById(cluster.searchQuery(searchIndexName, SearchQuery.queryString(query))
                .rows()
                .stream()
                .map(SearchRow::id)
                .collect(Collectors.toList()));
    }
}
