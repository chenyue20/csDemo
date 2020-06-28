package com.cs.entity;

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
  String userName;
  Integer age;
  String customerId;
}
