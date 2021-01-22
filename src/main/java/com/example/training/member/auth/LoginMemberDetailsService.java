package com.example.training.member.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.training.member.MemberEntity;
import com.example.training.member.domain.Member;
import com.example.training.member.repository.MemberRepository;

@Component("LoginMemberDetailsService")
public class LoginMemberDetailsService implements UserDetailsService {
	@Autowired
	MemberRepository memberRepository;

	/**
	 * メールアドレスで検索したユーザーのuserエンティティをLoginUserDetailsクラスのインスタンスへ変換する
	 *
	 * @param email 検索するユーザーのメールアドレス
	 * @return メールアドレスで検索できたユーザーのユーザー情報
	 * @throws UsernameNotFoundException メールアドレスでユーザーが検索できなかった場合にスローする。
	 */
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		assert (email != null);
//		Optional<Member> memberOpt = memberRepository.findByEmail(email);
		Optional<MemberEntity> memberOpt = memberRepository.findByEmailMember(email);
		if (memberOpt.isEmpty()) {
			throw new UsernameNotFoundException("User not found for email: " + email);
		} else {
			Member member = new Member(memberOpt.get());
			if (member.getStatus().equals("unapproved")) {
				throw new UsernameNotFoundException("Unauthorized user.: " + email);
			} else {
				return new LoginMemberDetails(member);
			}
		}
	}
}
