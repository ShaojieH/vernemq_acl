package com.example.acl_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String result;
    private List<Acl> publish_acl;
    private List<Acl> subscribe_acl;

    @Data
    @AllArgsConstructor
    public static class Acl{
        private String pattern;
        private int max_qos;
    }
}
