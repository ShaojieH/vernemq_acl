package com.example.acl_server.repository;

import com.example.acl_server.entity.db.UserSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSessionRepository extends CrudRepository<UserSession, String> {
    Optional<UserSession> findByUsernameAndClientID(String username, String clientID);
}
