package com.example.acl_server.entity;

import lombok.Data;

@Data
public class RegBody {
    private String client_id;
    private String mountpoint;
    private String username;
    private String password;
}
