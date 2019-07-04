package com.example.acl_server.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public
class BaseAuthRequest {
    private String client_id;
    private String username;
    private String mountpoint;
}
