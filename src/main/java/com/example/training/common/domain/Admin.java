package com.example.training.common.domain;

import com.example.training.common.domain.value.DigestPassword;

import lombok.Data;

/**
 * 管理者のクラス
 */
@Data
public class Admin {
  public static final String SESSION_NAME = "ADMIN";
  /**
   * 管理者ID
   */
  private int id;
  /**
   * 名前
   */
  private String name;
  /**
   * パスワード
   */
  private DigestPassword password;
  /**
   * 権限
   */
  private String roles = "ROLE_USER,ROLE_ADMIN";

}
