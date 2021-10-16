<%@ page pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Name       : Photoshoot 
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20110926

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Товары для Животне</title>
<style type="text/css">
   TABLE {
    color: #C0C0C0;
   }
</style>
<link href="static/css/style.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="static/scripts/jquery-3.6.0.js"></script>
<script type="text/javascript" src="jquery.poptrox-0.1.js"></script>
</head>
<body>
<div id="header" class="container">
	<div id="logo">
		<h1><a href="#">Животне </a></h1>
		<p>магазин зоотоваров<a href="http://www.freecsstemplates.org"></a></p>
	</div>
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="index">Главная</a></li>
			<li><a href="products">Все товары</a></li>
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
                	</c:choose>
                </a></li>
			<li><a href="cart">Корзина</a></li>
		</ul>
	</div>
</div>
<!-- end #header -->
<div id="poptrox">
	<!-- start -->
	<ul id="gallery">
		<li><a href="static/images/img1_big.jpg"><img src="static/images/img1.jpg" title="Котофей" alt="" /></a></li>
		<li><a href="static/images/img3_big.jpg"><img src="static/images/img3.jpg" title="Балу" alt="" /></a></li>
		<li><a href="static/images/img4_big.jpg"><img src="static/images/img4.jpg" title="Солисты известного попугай-бенда" alt="" /></a></li>
		<li><a href="static/images/img2_big.jpg"><img src="static/images/img2.jpg" title="Няша" alt="" /></a></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
	<br class="clear" />
	<script type="text/javascript">
		$('#gallery').poptrox();
		</script>
	<!-- end -->
</div>
<div id="page">
	<div id="bg1">
		<div id="bg2">
			<div id="bg3">
				<div id="content">
