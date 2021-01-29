package com.example.training.mobile.controller.cart;

import java.util.ArrayList;
import java.util.List;

import com.example.training.common.domain.Cart;
import com.example.training.common.domain.CartItem;

import lombok.Getter;

/**
 * カートのデータ転送用オブジェクト fix :まだ未使用です...。
 */
@Getter
public class CartDTO {

  private List<CartItemDTO> items = new ArrayList<CartItemDTO>();
  private int totalPrice;

  /**
   * 基本コンストラクタ
   */
  public CartDTO(Cart cart) {
    List<CartItemDTO> list = new ArrayList<CartItemDTO>();
    List<CartItem> items = cart.getItems();
    for (CartItem item : items) {
      list.add(new CartItemDTO(item));
    }
    this.items = list;
    this.totalPrice = cart.getTotalPrice().getValue();
  }

}