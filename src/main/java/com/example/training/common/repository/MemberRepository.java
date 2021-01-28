package com.example.training.common.repository;

import java.util.List;
import java.util.Optional;

import com.example.training.web.domain.member.Email;
import com.example.training.web.domain.member.Member;
import com.example.training.web.domain.member.MemberEntity;
import com.example.training.web.domain.member.MemberId;

import org.apache.ibatis.annotations.Mapper;

/**
 * 会員のリポジトリ
 */
@Mapper
public interface MemberRepository {

	/**
	 * Eメールで取得する。
	 */
	public Optional<MemberEntity> findByEmail(Email email);

	/**
	 * IDで取得する。
	 */
	public MemberEntity findById(MemberId memberId);

	/**
	 * 全ての会員を取得する。
	 */
	public List<MemberEntity> findAll();

	/**
	 * 会員を作る。
	 *
	 * @param member
	 */
	public void save(Member member);

	/**
	 * フォーム内容で会員情報を更新する。
	 *
	 * @param memberEditForm
	 * @param lastUpdatedBy
	 */
	public void updateByAdmin(Member member);

	/**
	 * 注文内容で会員情報を更新する。
	 *
	 * @param member 会員
	 */
	public void updateAtOrder(Member member);

}
