package com.example.training.web.controller.order;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.example.training.common.domain.Cart;
import com.example.training.common.domain.Member;
import com.example.training.common.domain.Order;
import com.example.training.common.domain.value.Email;
import com.example.training.common.entity.MemberEntity;
import com.example.training.common.http.MemberSession;
import com.example.training.common.repository.MemberRepository;
import com.example.training.common.service.OrderService;
import com.example.training.web.controller.member.MemberDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注文のコントローラ
 */
@Controller
@RequestMapping("/member/order")
public class OrderController {

	@Autowired
	private HttpSession session;

	@Autowired
	private OrderService orderService;

	@Autowired
	private MemberRepository memberRepository;

	/**
	 * お届け先入力フォームを表示する。
	 *
	 * @param orderForm
	 * @param model
	 * @return お届け先入力フォーム画面
	 */
	@GetMapping("/form")
	public String form(OrderForm orderForm, Model model) {
		MemberSession memberSession = (MemberSession) session.getAttribute(Member.SESSION_NAME);
		Email email = memberSession.getEmail();
		MemberEntity memberEntity = memberRepository.findByEmail(email).orElseThrow();
		Member member = new Member(memberEntity);
		OrderForm sessionOrderForm = (OrderForm) session.getAttribute(OrderForm.SESSION_NAME);
		if (sessionOrderForm != null) {
			model.addAttribute("orderForm", sessionOrderForm);
		} else {
			MemberDto memberDto = new MemberDto(member);
			orderForm.setMemberInfo(memberDto);
			session.setAttribute(OrderForm.SESSION_NAME, orderForm);
		}
		return "member/order/form";
	}

	/**
	 * 注文確認画面を表示する。
	 *
	 * @param orderForm
	 * @param result
	 * @param model
	 * @return 注文確認画面
	 */
	@PostMapping("/confirmation")
	public String confirmation(@Valid OrderForm orderForm, BindingResult result, Model model) {
		session.setAttribute(OrderForm.SESSION_NAME, orderForm);
		if (result.hasErrors()) {
			return form(orderForm, model);
		} else {
			Cart cart = (Cart) session.getAttribute(Cart.SESSION_NAME);
			Order order = new Order(orderForm, cart);
			model.addAttribute("cart", cart);
			model.addAttribute("order", order);
			return "member/order/confirmation";
		}
	}

	/**
	 * 注文処理する。
	 *
	 * @return 注文完了画面
	 */
	@PostMapping("/save")
	public String save() {
		Cart cart = (Cart) session.getAttribute(Cart.SESSION_NAME);
		OrderForm orderForm = (OrderForm) session.getAttribute(OrderForm.SESSION_NAME);
		Order order = orderForm.createOrderFrom(cart);
		Order ordered = orderService.order(order, cart);
		session.setAttribute(Cart.SESSION_NAME, new Cart());
		session.removeAttribute(OrderForm.SESSION_NAME);
		return "redirect:/member/order/complete/" + ordered.getId();
	}

	/**
	 * 注文完了画面を表示する。
	 *
	 * @param orderId
	 * @param model
	 * @return 注文完了画面
	 */
	@GetMapping("/complete/{orderId}")
	public String complete(@PathVariable int orderId, Model model) {
		model.addAttribute("orderId", orderId);
		return "member/order/complete";
	}
}