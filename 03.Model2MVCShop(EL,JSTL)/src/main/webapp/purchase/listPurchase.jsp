<%@ page contentType="text/html; charset=EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <%@ page import="java.util.ArrayList"  %>
<%@ page import="java.util.Map"%>
<%@ page import="com.model2.mvc.service.domain.Purchase" %>
<%@ page import="com.model2.mvc.common.Search" %>
 --%>
<%-- <%
   System.out.println("list.jsp 시작");

   Map<String,Object> map=(Map<String,Object>)request.getAttribute("map");
   Search search=(Search)request.getAttribute("search");
   
   int total=0;
   ArrayList<Purchase> list=null;
   if(map != null){
      total=((Integer)map.get("count"));
      list=(ArrayList<Purchase>)map.get("list");
   }
   
   int currentPage=search.getCurrentPage();
   System.out.println(map);
   int totalPage=0;
   
   if(total > 0) {
      totalPage= total / search.getPageSize() ;
      if(total%search.getPageSize() >0){
         totalPage += 1;
      }
         
   }
%> --%>

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetUserList(currentPage) {
   document.getElementById("currentPage").value = currentPage;
      document.detailForm.submit();      
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"   cellspacing="0">
   <tr>
      <td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
      <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
         <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
               <td width="93%" class="ct_ttl01">구매 목록조회</td>
               
            </tr>
         </table>
      </td>
      <td width="12" height="37"><img src="/images/ct_ttl_img03.gif"   width="12" height="37"></td>
   </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"   style="margin-top: 10px;">
   <tr>
      <%-- <td colspan="11">전체<%= totalPage %>건수, 현재 <%=currentPage %>페이지</td> --%>
      <td colspan="11">전체${resultPage.totalCount }건수, 현재 ${resultPage.currentPage}페이지</td>
   </tr>
   <tr>
      <td class="ct_list_b" width="100">No</td>
      <td class="ct_line02"></td>
      <td class="ct_list_b" width="150">회원ID</td>
      <td class="ct_line02"></td>
      <td class="ct_list_b" width="150">회원명</td>
      <td class="ct_line02"></td>
      <td class="ct_list_b">전화번호</td>
      <td class="ct_line02"></td>
      <td class="ct_list_b">배송현황</td>
      <td class="ct_line02"></td>
      <td class="ct_list_b">정보수정</td>
   </tr>
   <tr>
      <td colspan="11" bgcolor="808285" height="1"></td>
   </tr>
      
   <%-- <%    int no=list.size();
      for(int i=0; i<list.size(); i++) {
         Purchase purchase = (Purchase)list.get(i);
   %>   
   
   
   <tr class="ct_list_pop">
      <td align="center">
         <a href="/getPurchase.do?tranNo=<%=purchase.getTranNo()%>"><%=no-- %></a>
      </td>
      <td></td>
      <td align="left">
         <a href="/getUser.do?userId=<%=purchase.getBuyerId().getUserId()%>"><%=purchase.getBuyerId().getUserId()%></a>
      </td>
      <td></td>
      <td align="left"><%= purchase.getReceiverName() %></td>
      <td></td>
      <td align="left"><%= purchase.getReceiverPhone() %></td>
      <td></td>
      <td align="left">현재
         
            <%if(purchase.getTranCode().equals("1")){ %>
               구매완료
            <%}else if(purchase.getTranCode().equals("2")){ %>
               배송중   
            <%}else{ %>
                배송완료
            <%} %>
            
               
            상태 입니다.</td>
      <td></td>
      <td align="left">
         <%if(purchase.getTranCode().equals("2")) { %>
         <a href="/updateTranCode.do?prodNo=<%=purchase.getPurchaseProd().getProdNo() %>&tranCode=3">물건도착</a>
         <%} %>
      </td>
   </tr>
   <tr>
      <td colspan="11" bgcolor="D6D7D6" height="1"></td>
   </tr>
   <% } %> --%>
   <c:set var="i" value="0"/>
   <c:forEach var="purchase" items="${list}">
      <c:set var="i" value="${i+1}"/>
      <tr class="ct_list_pop">
      <td align="center"><a href="/getPurchase.do?tranNo=${purchase.tranNo}">${ i }</td>
      <td></td>
      <td align="left"><a href="/getUser.do?userId=${purchase.buyer.userId}">${purchase.buyer.userId}</a></td>
      <td></td>
      <td align="left">${purchase.receiverName}</td>
      <td></td>
      <td align="left">${purchase.receiverPhone}</td>
      <td></td>
      <td align="left">현재 
      <c:choose>
            <c:when test="${purchase.tranCode eq '0'}">판매중</c:when>
            <c:when test="${purchase.tranCode eq '1'}">구매완료</c:when>
            <c:when test="${purchase.tranCode eq '2'}">배송중</c:when>
            <c:otherwise>배송완료</c:otherwise>   
         </c:choose>
          상태 입니다.</td>
      <td align="left">
         <c:if test="${purchase.tranCode eq '2'}">
            <a href="/updateTranCode.do?prodNo=${purchase.purchaseProd.prodNo}&tranCode=3">물건도착</a>
         </c:if>
      </td>
      <tr>
   </tr>
   <tr>
      <td colspan="11" bgcolor="D6D7D6" height="1"></td>
   </tr>
   </c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
   <tr>
      <td align="center">
      <input type="hidden" id="currentPage" name="currentPage" value=""/>
      <%-- <%
         for(int i=1;i<=totalPage;i++){
      %>
         <a href="/listPurchase.do?page=<%=i%>"><%=i %></a> 
      <%
         }
      %>    --%>
     <%--  <c:forEach var="i" begin="1" end="${totalPage}">
      <a href="/listPurchase.do?page=${i }">${i }</a> 
      </c:forEach>
      --%>
        <jsp:include page="../common/pageNavigator.jsp"/>    
      </td>
   </tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>
<% System.out.println("list.jsp 종료");%>