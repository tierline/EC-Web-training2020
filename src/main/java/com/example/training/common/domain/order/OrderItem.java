package com.example.training.common.domain.order;

import com.example.training.common.domain.Quantity;
import com.example.training.common.domain.cart.CartItem;

import lombok.Data;

@Data
public class OrderItem {
	private String name;
	private int price;
	private Quantity quantity;
	private String imagePath;

	public OrderItem(CartItem item) {
		this.name = item.getProductName();
		this.price = item.getProductPrice();
		this.quantity = item.getQuantity();
		this.imagePath = item.getProductImage();
	}

	public OrderItem() {
	}

}
