package com.example.training.web.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.training.common.domain.value.DigestPassword;
import com.example.training.common.domain.value.Email;
import com.example.training.common.entity.MemberEntity;
import com.example.training.common.repository.MemberRepository;
import com.example.training.web.controller.member.MemberLoginForm;

@Service
public class DigestPasswordService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * 登録されているパスワードと一致するか
	 */
	// TODO
	public Boolean isMatched(MemberLoginForm memberLoginForm) {
		Email email = new Email(memberLoginForm.getEmail());
		String rawPassword = memberLoginForm.getPassword();
		MemberEntity memberEntity = memberRepository.findByEmail(email).orElseThrow();
		String digestPassword = memberEntity.getPassword();
		return bCryptPasswordEncoder.matches(rawPassword, digestPassword);
	}

	/*
	 * 暗号化パスワードの生成
	 */
	public DigestPassword generate(String password) {
		String digest = passwordEncoder.encode(password);
		return new DigestPassword(digest);
	}

}