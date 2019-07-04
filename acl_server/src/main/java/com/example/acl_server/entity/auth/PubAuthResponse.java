package com.example.acl_server.entity.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PubAuthResponse extends BaseAuthResponse{
    // possible modifiers
}
