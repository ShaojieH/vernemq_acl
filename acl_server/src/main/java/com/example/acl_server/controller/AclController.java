package com.example.acl_server.controller;

import com.example.acl_server.entity.auth.*;
import com.example.acl_server.service.UserSessionService;
import com.sun.javaws.exceptions.InvalidArgumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/vernemq")
@Slf4j
public class AclController {

    private UserSessionService userSessionService;

    public AclController(UserSessionService userSessionService){
        this.userSessionService = userSessionService;
    }

    @PostMapping(value = "/reg", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public RegAuthResponse authOnReg(@RequestBody RegAuthRequest regAuthRequest) {
        // TODO verify password
        userSessionService.saveUserSession(regAuthRequest);
        return getDefaultRegResponse();
    }

    @PostMapping(value = "/pub", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public PubAuthResponse authOnPub(@RequestBody PubAuthRequest pubAuthRequest) {
        // TODO verify whether user can pub
        if(userSessionService.exists(pubAuthRequest)){
            return (PubAuthResponse) new PubAuthResponse()
                    .setResult("ok");
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping(value = "/sub", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public SubAuthResponse authOnSub(@RequestBody SubAuthRequest subAuthRequest) {
        log.info(subAuthRequest.toString());
        // TODO verify whether user can sub
        if(userSessionService.exists(subAuthRequest)){
            return (SubAuthResponse) new SubAuthResponse()
                    .setResult("ok");
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }

    private RegAuthResponse getDefaultRegResponse() {
        // TODO fetch from db
        ArrayList<RegAuthResponse.Acl> publish_acl = new ArrayList<>();
        publish_acl.add(new RegAuthResponse.Acl("test/", 2));
        ArrayList<RegAuthResponse.Acl> subscribe_acl = new ArrayList<>();
        subscribe_acl.add(new RegAuthResponse.Acl("test/", 2));
        return (RegAuthResponse) new RegAuthResponse()
                .setPublish_acl(publish_acl)
                .setSubscribe_acl(subscribe_acl)
                .setResult("ok");
    }
}
