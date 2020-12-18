package com.example.training.restApi.member.order;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.training.common.domain.Cart;
import com.example.training.common.domain.Order;
import com.example.training.common.domain.OrderForm;
import com.example.training.common.domain.OrderItem;
import com.example.training.common.domain.OrderService;
import com.example.training.common.repository.OrderRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/member/order")
public class ApiOrderController {

	@Autowired
	private HttpSession session;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderService orderService;

	/**
	 * 注文処理を行う
	 */
	@PostMapping("/save")
	public Integer save(@RequestBody LinkedHashMap<String, String> order) {
		String lastName = order.get("lastName");
		String firstName = order.get("firstName");
		String email = order.get("email");
		String phone = "00000000000";
		String address1 = "xx";
		String address2 = "zz";
		Date dateNow = new Date();

		OrderForm orderForm = new OrderForm(lastName, firstName, email, phone, address1, address2, dateNow);

		Cart cart = (Cart) session.getAttribute(Cart.SESSION_NAME);
		int orderId = orderService.order(orderForm, cart);
		session.setAttribute(Cart.SESSION_NAME, new Cart());
		return orderId;
	}

	/**
	 * 注文番号から注文明細を返す
	 */
	@GetMapping("/orderDetails/{id}")
	public Order orderDetails(@PathVariable Integer id) {
		Order order = orderRepository.findById(id);
		// List<String> orderedList = new ArrayList<String>();
		// orderedList.

		return order;
	}

	/**
	 * 注文番号から注文明細を返す
	 */
	@GetMapping("/itemDetails/{id}")
	public List<OrderItem> itemDetails(@PathVariable Integer id) {
		Order order = orderRepository.findById(id);
		List<OrderItem> items = orderRepository.findItemsByOrder(order);

		return items;

	}
}