package com.example.acl_server.entity.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RedisHash("UserSession")
@Accessors(chain = true)
public class UserSession implements Serializable {
    @Id
    private String sessionID;
    @Indexed
    private String username;
    private Long time;
    @Indexed
    private String clientID;
}
