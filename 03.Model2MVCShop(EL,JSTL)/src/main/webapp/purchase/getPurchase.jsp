<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
<%@ page import="com.model2.mvc.service.domain.*" %>

<% Purchase purchase = (Purchase)request.getAttribute("productVO"); %>
<% User user = (User)session.getAttribute("user"); %>
 --%>
 
<html>
<head>
<title>Insert title here</title>
</head>

<body>
<!-- 구매 버튼 누른 후, 렌더링되는 구매완료 동적 페이지 -->

다음과 같이 구매가 되었습니다.

<table border=1>

<!-- purchaseVO 에 User buyer, Product purchaseProd 존재 -->
	<tr>
		<td>물품번호</td>
		<td>${purchase.purchaseProd.prodNo}</td>
		<td></td>
	</tr>
	
	<!-- 구매자 아이디 가져올시, 
		1. requestScope에 저장된 purchase.buyer.userId
		2. sessionScope에 저장된 user.userId 
		모두 가능  -->
	<tr>
		<td>구매자아이디</td>
		<td>${user.userId}</td>	
		<!--<td>${requestScope.purchase.buyer.userId}</td> -->	
		<td></td>
	</tr>
	
	
<!--  -->	
	<tr>
		<td>구매방법</td>
		<td>${purchase.paymentOption}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td>${purchase.receiverName}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td>${purchase.receiverPhone}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td>${purchase.divyAddr}</td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td>${purchase.divyRequest}</td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td>${purchase.divyDate}</td>
		<td></td>
	</tr>
	<tr>
		<td>주문일</td>
		<td>${purchase.orderDate}</td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>