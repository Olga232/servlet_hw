<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@include file="header.jsp" %>

<c:choose>
	<c:when test="${sessionScope.authorized == null}">
		<center>
			<h4><font color="#C0C0C0">Форма авторизации</font></h4><br/>
		</center>
		<center>
		<form action="authorization" method="post">
		<table>
		    <tr><td>Login (email):</td><td><input type="text" name="login"
				value="<%=(request.getParameter("login") != null) ? request.getParameter("login") : ""%>"/></td></tr>
		    <tr><td>Password:</td><td><input type="password" name="password"/></td></tr>
		    <tr><td> </td><td align="right"><input type="submit" value="SEND"/></td></tr>
		</table>
		</form>
		</center>	
		
		<center>
			<h4>${result}</h4>
		</center>
	</c:when>

	<c:otherwise>
		<a href='?logout'>logout</a>
	</c:otherwise>

</c:choose>

<%@include file="footer.jsp" %>

      