package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class AddPurchaseViewAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User user = new User();
		
		Purchase purchase = new Purchase();
		purchase.setPaymentOption(request.getParameter("payment_option"));
//		purchase.setBuyer(request.getParameter("user_name"));
		
		System.out.println("AddPurchaseAction ::"+purchase);
		
		PurchaseService purchaseService=new PurchaseServiceImpl();
		purchaseService.addPurchase(purchase);
		
	
		return "redirect:/purchase/addPurchaseView.jsp";
	}
}	
