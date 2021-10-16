<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@include file="header.jsp" %>

<c:choose>
	<c:when test="${sessionScope.productsInCart == null || sessionScope.totalProductsInCartQnt == 0}">
		<center>Ваша корзина пуста</center>
	</c:when>
	
	<c:otherwise>
		<center><h4>Ваша корзина</h4></center>
			<c:forEach var="entry" items="${sessionScope.productsInCart}">
				  <table>
				  <tr><td colspan="2">${entry.key.name}</td><td></td></tr>
				  <tr><td width="90"><a href="products?id=${entry.key.id}"><img src="static/images/${entry.key.id}.jpg" width="70" height="100%"/></a></td>
				      <td>${entry.value} шт</td></tr>
				  <tr><td></td><td>
					  <form action="cart" method="post">
						<input type="number" size="2" name="qnt" min="1" max=${entry.value} value="1"/>
						<input type="hidden" name="id" value="${entry.key.id}"/>
						<input type="hidden" name="remove" value="true"/>
						<input type="submit" value="Удалить из корзины"/>
					  </form>
				  </td></tr>
		  		  <tr><td><br/></td><td></td></tr>
		  		  </table>
			</c:forEach>
		<table>
			<tr><td>Общая стоимость товаров в корзине:</td><td>${sessionScope.totalProductsCost} грн</td></tr>
			<tr><td><br/></td><td></td></tr>
			<tr><td><a href="order">Оформить заказ</a></td><td></td></tr>
		</table>
	</c:otherwise>
</c:choose>


<%@include file="footer.jsp" %>

