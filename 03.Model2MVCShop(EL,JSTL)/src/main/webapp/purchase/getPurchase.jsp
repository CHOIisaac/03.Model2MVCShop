<%@page import="com.model2.mvc.service.domain.Product"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
Product vo = (Product)request.getAttribute("vo");
%>
<!DOCTYPE html>














<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
		<td>10102</td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td>user21</td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>
		
			���ݱ���
		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td>SCOTT</td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td>null</td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td>null</td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>