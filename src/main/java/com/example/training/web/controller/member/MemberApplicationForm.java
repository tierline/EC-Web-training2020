package com.example.training.web.controller.member;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.training.common.domain.Member;
import com.example.training.common.domain.value.DigestPassword;

import lombok.Data;

/**
 * 会員新規登録フォームのクラス
 */

@Data
public class MemberApplicationForm {

	/**
	 * Eメール
	 */
	private String email;

	/**
	 * パスワード
	 */
	@NotEmpty
	@Size(max = 16, message = "パスワードは16文字以内で入力してください")
	private String password;

	/**
	 *
	 * 新規会員登録用のMemberを作る。
	 *
	 * @param passwordDigest
	 * @return 会員登録用のメンバーオブジェクト
	 */
	public Member createMember(DigestPassword digestPassword) {
		return new Member(this, digestPassword);
	}

}