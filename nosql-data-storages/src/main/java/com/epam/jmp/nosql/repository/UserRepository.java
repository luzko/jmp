package com.epam.jmp.nosql.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;

import com.epam.jmp.nosql.model.User;

public interface UserRepository extends CouchbaseRepository<User, String> {
    Optional<User> findByEmail(String email);

    @Query("#{#n1ql.selectEntity} WHERE ANY sport IN sports SATISFIES sport.sportName = $1 END")
    List<User> findBySportName(String sportName);
}
