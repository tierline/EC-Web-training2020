package com.example.training.common.domain.value.address;

import com.example.training.common.domain.value.Assertion;

import lombok.Getter;

/**
 * 郵便番号を表す値オブジェクト
 */
public class Postcode {
	@Getter
	private String value;

	/*
	 * ハイフンなしの郵便番号の長さ
	 */
	private final Integer LENGTH = 7;

	/**
	 * 正規表現
	 */
	private final String REGEXP = "[0-9]";

	/*
	 * 基本コンストラクタ
	 */
	public Postcode(String value) {
		Assertion.isNull(value);
		Assertion.length(value, LENGTH, LENGTH);
		Assertion.matches(value, REGEXP);

		this.value = value;
	}

	/**
	 * デフォルトコンストラクタ
	 */
	public Postcode() {
	}

}
