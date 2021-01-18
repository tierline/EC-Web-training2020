package com.example.training.api.member;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.example.training.member.Service.MemberService;
import com.example.training.member.domain.Email;
import com.example.training.member.domain.Member;
import com.example.training.member.domain.MemberApplicationForm;
import com.example.training.member.domain.MemberLoginForm;
import com.example.training.member.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")

public class ApiMemberController {

	@Autowired
	private HttpSession session;

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@CrossOrigin
	@PostMapping("/applicate")
	@ResponseBody
	public Boolean create(@RequestBody MemberApplicationForm memberApplicationForm) {
		Optional<Member> member = memberRepository.findByEmail(memberApplicationForm.getEmail());
		if (member.isEmpty()) {
			memberService.applicate(memberApplicationForm);
			Optional<Member> memberDetail = memberRepository.findByEmail(memberApplicationForm.getEmail());
			session.setAttribute(Member.SESSION_NAME, memberDetail.get());
			return true;
		} else {
			return false;
		}
	}

	@CrossOrigin
	@PostMapping("/login")
	@ResponseBody
	public Boolean login(@RequestBody MemberLoginForm memberLoginForm) {
		String password = memberLoginForm.getPassword();
		Optional<Member> memberDetail = memberRepository.findByEmail(memberLoginForm.getEmail());
		if (memberDetail.isPresent()) {
			String hashPassword = memberDetail.get().getPassword();
			Boolean result = bCryptPasswordEncoder.matches(password, hashPassword);
			session.setAttribute(Member.SESSION_NAME, memberDetail.get());
			return result;
		}

		return false;

	}

	/*
	 * 住所情報があったら表示する
	 */
	@PostMapping("/address")
	@ResponseBody
	public Member fetchMemberAddress(@RequestBody Email email) {
		Member member = memberRepository.findAddress(email.getEmail()); // member型 で address の変数名はおかしい
		return member;
	}

}
