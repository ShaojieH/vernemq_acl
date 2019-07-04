package com.example.acl_server.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SubAuthRequest extends BaseAuthRequest implements Serializable {
    private ArrayList<Topic> topics;

    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @AllArgsConstructor
    private static class Topic implements Serializable {
        private String topic;
        private int qos;
    }

}
