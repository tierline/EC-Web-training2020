package com.example.training.member.domain;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class MemberApplicationForm {

	@Email
	private String email;

	@NotEmpty
	@Size(max = 16, message = "パスワードは16文字以内で入力してください")
	private String password;

	public Boolean isExistedMember(Optional<Member> member) {
		if (member.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public Member createMember(String passwordDigest) {
		return new Member(this, passwordDigest);
	}

}
