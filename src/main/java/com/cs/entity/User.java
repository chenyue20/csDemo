package com.cs.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;

import java.io.Serializable;

@Builder
@Getter
@Setter
@ToString
public class User implements Serializable {

    @Tolerate
    public User() {
    }

    Long id;
    private String username;
    private String password;
    private String name;
    private String email;
}
