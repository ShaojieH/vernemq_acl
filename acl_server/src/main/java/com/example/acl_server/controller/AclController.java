package com.example.acl_server.controller;

import com.example.acl_server.entity.ApiResponse;
import com.example.acl_server.entity.RegBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/vernemq")
@Slf4j
public class AclController {

    /*
    @PostMapping(value = "/reg",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse onReg(HttpEntity<String> httpEntity){
        ArrayList<ApiResponse.Acl> publish_acl = new ArrayList<>();
        ArrayList<ApiResponse.Acl> subscribe_acl = new ArrayList<>();
        publish_acl.add(new ApiResponse.Acl("test/", 2));
        subscribe_acl.add(new ApiResponse.Acl("test/", 2));
        ApiResponse response = new ApiResponse("ok",publish_acl,subscribe_acl);
        log.info(httpEntity.getBody());
        log.info(response.toString());
        return response;
    }
    */


    @PostMapping(value = "/reg",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse onReg(@RequestBody RegBody regBody){
        ArrayList<ApiResponse.Acl> publish_acl = new ArrayList<>();
        publish_acl.add(new ApiResponse.Acl("test/", 2));
        ArrayList<ApiResponse.Acl> subscribe_acl = new ArrayList<>();
        subscribe_acl.add(new ApiResponse.Acl("test/", 2));
        ApiResponse response = new ApiResponse("ok",publish_acl, subscribe_acl);
        log.info(regBody.getClient_id()+regBody.getMountpoint()+regBody.getUsername()+regBody.getPassword());
        log.info(response.toString());
        return response;
    }


    /*
    @PostMapping(value = "/pub",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse onPub(HttpEntity<String> httpEntity){
        return new ApiResponse("ok",new ArrayList<>(), new ArrayList<>());
    }

    @PostMapping(value = "/sub",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse onSub(HttpEntity<String> httpEntity){
        return new ApiResponse("ok",new ArrayList<>(), new ArrayList<>());
    }
    */
}
