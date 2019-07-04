package com.example.acl_server.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RegAuthResponse extends BaseAuthResponse{
    private List<Acl> publish_acl;
    private List<Acl> subscribe_acl;

    @Data
    @AllArgsConstructor
    public static class Acl{
        private String pattern;
        private int max_qos;
    }
}
