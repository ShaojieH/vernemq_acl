package com.example.acl_server.service;

import com.example.acl_server.entity.auth.BaseAuthRequest;
import com.example.acl_server.entity.auth.PubAuthRequest;
import com.example.acl_server.entity.auth.RegAuthRequest;
import com.example.acl_server.entity.db.UserSession;
import com.example.acl_server.repository.UserSessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserSessionService {

    private UserSessionRepository userSessionRepository;

    public UserSessionService(UserSessionRepository userSessionRepository){
        this.userSessionRepository = userSessionRepository;
    }

    @Transactional
    public boolean exists(BaseAuthRequest baseAuthRequest){
        return userSessionRepository.findByUsernameAndClientID(baseAuthRequest.getUsername(), baseAuthRequest.getClient_id()).isPresent();
    }

    @Transactional
    public UserSession saveUserSession(RegAuthRequest regAuthRequest){
        String sessionID = UUID.randomUUID().toString();    // TODO change to real session id
        Long time = System.currentTimeMillis();
        Optional<UserSession> userSession = userSessionRepository.findByUsernameAndClientID(regAuthRequest.getUsername(), regAuthRequest.getClient_id());
        if(userSession.isPresent()){
            return userSessionRepository.save(
                    userSession.get()
                            .setTime(time));
        }else{
            return userSessionRepository.save(
                    new UserSession(sessionID,
                            regAuthRequest.getUsername(),
                            time,
                            regAuthRequest.getClient_id()));
        }

    }
}
