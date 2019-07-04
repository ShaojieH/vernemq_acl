package com.example.acl_server.entity.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PubAuthRequest extends BaseAuthRequest{
    private int qos;
    private String topic;
    private String payload;
    private boolean retain;
}
