package com.example.training.web.controller.authentication;

import java.util.Collection;

import com.example.training.web.domain.admin.Admin;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginAdminDetails extends User {
  private static final long serialVersionUID = 1L;
  // DBより検索したAdminエンティティ
  // アプリケーションから利用されるのでフィールドに定義
  private Admin admin;

  /**
   * データベースより検索したuserエンティティよりSpring Securityで使用するユーザー認証情報のインスタンスを作る。
   *
   * @param admin adminエンティティ
   */
  public LoginAdminDetails(Admin admin) {
    super(admin.getName(), admin.getPassword().getValue(), createRole(admin));
    this.admin = admin;
  }

  public Admin getMember() {
    return admin;
  }

  private static Collection<? extends GrantedAuthority> createRole(Admin admin) {
    String authorityString = admin.getRoles();
    return AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString);
  }

}
