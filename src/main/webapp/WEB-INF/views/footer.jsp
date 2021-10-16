<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

			    </div>
            
				<div id="sidebar">
					<table border=1>
                    <tr>
                    <td width="252" align="left">
                    <font color=white>
                    <c:choose>
                    	<c:when test="${sessionScope.authorized != null}">Вы авторизировались как ${sessionScope.authorized}</c:when>
                    	<c:otherwise>Вы не авторизованы.</c:otherwise>
                    </c:choose>
                    <br />
                    Количество товаров в корзине: 
                    	<span id="currentQnt">${(sessionScope.totalProductsInCartQnt == null ? "0" : ""+sessionScope.totalProductsInCartQnt)}</span>
                    </font>
                    </td>
                    </tr>
                    </table>
                    <h2></h2>
					<ul>
						<li><a href="products?category=1">для Котов</a></li>
						<li><a href="products?category=2">для Собак</a></li>
						<li><a href="products?category=3">для Попугаев</a></li>
						<li>
							<c:choose>
                    			<c:when test="${sessionScope.authorized != null}"><a href="user?update">Кабинет</a></c:when>
                    			<c:otherwise><a href="user?registration">Регистрация</a></c:otherwise>
                			</c:choose>
						</li>
						<li><a href="authorization">
							<c:choose>
                    			<c:when test="${sessionScope.authorized != null}">Выход</c:when>
                    			<c:otherwise>Вход</c:otherwise>
                			</c:choose></a></li>
						<li><a href="cart">Корзина</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">
	<p>Copyright (c) by Могильная Ольга</p>
</div>
<!-- end #footer -->
</body>

<script type="text/javascript">
	function plus(qntId) {
		var qnt = document.getElementById(qntId).value;
		document.getElementById(qntId).value = +qnt + +1;
	}
	
	function minus(qntId) {
		var qnt = document.getElementById(qntId).value;
		if (qnt > 1) {
			document.getElementById(qntId).value = +qnt - +1; 
		}
	}
	
	function addProductIdAndQntToCart(productId, qntId) {
		var qnt = document.getElementById(qntId).value;
		$.ajax({
	 		url: 'cart',
	  		method: 'post',
	  		data: "id=" + productId + "&qnt=" + +qnt,
	  		success: function(data){
	    				document.getElementById("currentQnt").innerHTML=data;
	  				}
		})
	}
</script>

</html>
