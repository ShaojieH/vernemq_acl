package com.example.acl_server.controller;

import com.example.acl_server.entity.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class AclController {
    @PostMapping(value = "/vernemq",consumes = {MediaType.ALL_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse getAcl(){
        ArrayList<ApiResponse.Acl> publish_acl = new ArrayList<>();
        publish_acl.add(new ApiResponse.Acl("p1", 2));
        publish_acl.add(new ApiResponse.Acl("p2", 1));

        ArrayList<ApiResponse.Acl> subscribe_acl = new ArrayList<>();
        subscribe_acl.add(new ApiResponse.Acl("s1", 2));
        subscribe_acl.add(new ApiResponse.Acl("s2", 0));
        ApiResponse response = new ApiResponse("ok",publish_acl, subscribe_acl);
        log.info(response.toString());
        return response;
    }
}
