package com.example.training.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
	public static final String SESSION_NAME = "CART";
	private List<CartItem> items = new ArrayList<CartItem>();

	/**
	 * 商品をカートに追加する
	 * 
	 * @param item 商品
	 */
	public void add(Product product) {
		this.add(product, 1);
	}

	/**
	 * 商品をカートに追加する
	 * 
	 * @param item     商品
	 * @param quantity
	 */
	public void add(Product product, int quantity) {
		Optional<CartItem> itemOpt = getItem(product);
		if (itemOpt.isPresent()) {
			itemOpt.get().addQuantity(quantity);
		} else {
			items.add(new CartItem(product));
		}
	}

	/**
	 * 商品をカートから削除する
	 * 
	 * @param product
	 */
	public void remove(Product product) {
		Optional<CartItem> itemOpt = getItem(product);
		if (itemOpt.isPresent()) {
			CartItem item = itemOpt.get();
			item.removeQuantity(1);
			if (item.isEmpty()) {
				this.items.remove(item);
			}
		}
	}

	public int getSize() {
		return items.size();
	}

	/**
	 * カートの中身の取得
	 * 
	 * @return
	 */
	public List<CartItem> getItems() {
		return items;
	}

	/**
	 * 商品が存在確認
	 * 
	 * @param product
	 * @return
	 */
	public Optional<CartItem> getItem(Product product) {
		for (CartItem item : items) {
			int id = item.getProductId();
			if (id == product.getId()) {
				return Optional.of(item);
			}
		}
		return Optional.empty();
	}

	/**
	 * 合計金額の取得
	 * 
	 * @return
	 */
	public int getTotalAmount() {
		int totalAmount = 0;
		List<CartItem> items = this.getItems();
		for (CartItem item : items) {
			totalAmount += item.getTotalAmount();
		}
		return totalAmount;
	}
}
