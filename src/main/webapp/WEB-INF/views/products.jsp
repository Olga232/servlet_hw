<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@include file="header.jsp" %>

<table>
<c:forEach var="product" items="${listOfProducts}">
	<tr><td colspan="2">${product.name}</td><td></td></tr>
	<tr><td><a href="products?id=${product.id}"><img src="static/images/${product.id}.jpg" width="120" height="100%"/></a></td><td>${product.description}</td></tr>
	<tr><td width=140>Цена за 1 шт: ${product.price} грн</td><td>
			<img onclick="minus('qnt${product.id}')" src="static/images/mi.jpg" width="15" height="15"/>
				<input type="text" id="qnt${product.id}" size="2" name="qnt" value="1"/>
			<img onclick="plus('qnt${product.id}')" src="static/images/pl.jpg" width="15" height="15"/>
			<input onclick="addProductIdAndQntToCart('${product.id}', 'qnt${product.id}')" type="button" value="Добавить в корзину" hspace="5"/>
			</td></tr>
    <tr><td><br/><br/></td><td></td></tr>
</c:forEach>
</table>

<%@include file="footer.jsp" %>

