package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class AddPurchaseAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Purchase purchase = new Purchase();
		User user = new User();
		
		user.setUserId(request.getParameter("buyerId"));
		purchase.setPaymentOption(request.getParameter("payment_option"));
		purchase.setReceiverName(request.getParameter("receiver_name"));
		purchase.setReceiverPhone(request.getParameter("receiver_phone"));
		purchase.setDivyAddr(request.getParameter("dlvy_addr"));
		purchase.setDivyRequest(request.getParameter("dlvy_request"));
		purchase.setDivyDate(request.getParameter("dlvy_date"));
		purchase.setBuyer(user);
		System.out.println(purchase);
		
		PurchaseService purchaseService = new PurchaseServiceImpl();
		purchaseService.addPurchase(purchase);
		
		return "redirect:/purchase/getPurchase.jsp";
	}
}
