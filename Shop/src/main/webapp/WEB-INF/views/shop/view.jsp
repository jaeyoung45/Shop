<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Shop</title>
	<script src="/resources/jquery/jquery-3.3.1.min.js"></script>
	<script src="/resources/bootstrap/bootstrap.min.js"></script>
	<script src="/resources/ckeditor/ckeditor.js"></script>
	<link rel="stylesheet" href="/resources/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="/resources/bootstrap/bootstrap-theme.min.css">

	<style>
		body { margin:0; padding:0; font-family:'맑은 고딕', verdana; }
		a { color:#05f; text-decoration:none; }
		a:hover { text-decoration:underline; }
		h1, h2, h3, h4, h5, h6 { margin:0; padding:0; }
		ul, lo, li { margin:0; padding:0; list-style:none; }
		div#root { width:900px; margin:0 auto; }
		header#header { }
		nav#nav { }
		section#container { }
		section#content { float:right; width:700px; }
		aside#aside { float:left; width:180px; }
		section#container::after { content:""; display:block; clear:both; } 
		footer#footer { background:#eee; padding:20px; }
		header#header div#header_box { text-align:center; padding:30px 0; }
		header#header div#header_box h1 { font-size:50px; }
		header#header div#header_box h1 a { color:#000; }
		nav#nav div#nav_box { font-size:14px; padding:10px; text-align:right; }
		nav#nav div#nav_box li { display:inline-block; margin:0 10px; }
		nav#nav div#nav_box li a { color:#333; }
		section#container { }
		aside#aside h3 { font-size:22px; margin-bottom:20px; text-align:center; }
		aside#aside li { font-size:16px; text-align:center; }
		aside#aside li a { color:#000; display:block; padding:10px 0; }
		aside#aside li a:hover { text-decoration:none; background:#eee; }
		aside#aside li { position:relative; }
		aside#aside li:hover { background:#eee; }   
		aside#aside li > ul.low { display:none; position:absolute; top:0; left:180px;  }
		aside#aside li:hover > ul.low { display:block; }
		aside#aside li:hover > ul.low li a { background:#eee; border:1px solid #eee; }
		aside#aside li:hover > ul.low li a:hover { background:#fff;}
		aside#aside li > ul.low li { width:180px; }
		footer#footer { margin-top:100px; border-radius:50px 50px 0 0; }
		footer#footer div#footer_box { padding:0 20px; }
		section#content ul li { display:inline-block; margin:10px; }
		section#content div.goodsThumb img { width:200px; height:200px; }
		section#content div.goodsName { padding:10px 0; text-align:center; }
		section#content div.goodsName a { color:#000; }
		div.goods div.goodsImg { float:left; width:350px; }
		div.goods div.goodsImg img { width:350px; height:auto; }
		div.goods div.goodsInfo { float:right; width:330px; font-size:22px; }
		div.goods div.goodsInfo p { margin:0 0 20px 0; }
		div.goods div.goodsInfo p span { display:inline-block; width:100px; margin-right:15px; }
		div.goods div.goodsInfo p.cartStock input { font-size:22px; width:50px; padding:5px; margin:0; border:1px solid #eee; }
		div.goods div.goodsInfo p.cartStock button { font-size:26px; border:none; background:none; }
		div.goods div.goodsInfo p.addToCart { text-align:right; }
		div.goods div.gdsDes { font-size:18px; clear:both; padding-top:30px; }
		section.replyForm { padding:30px 0; }
		section.replyForm div.input_area { margin:10px 0; }
		section.replyForm textarea { font-size:16px; font-family:'맑은 고딕', verdana; padding:10px; width:500px;; height:150px; }
		section.replyForm button { font-size:20px; padding:5px 10px; margin:10px 0; background:#fff; border:1px solid #ccc; }
		section.replyList { padding:30px 0; }
		section.replyList ol { padding:0; margin:0; }
		section.replyList ol li { padding:10px 0; border-bottom:2px solid #eee; }
		section.replyList div.userInfo { }
		section.replyList div.userInfo .userName { font-size:24px; font-weight:bold; }
		section.replyList div.userInfo .date { color:#999; display:inline-block; margin-left:10px; }
		section.replyList div.replyContent { padding:10px; margin:20px 0; }
		section.replyList div.replyFooter button { font-size:14px; border: 1px solid #999; background:none; margin-right:10px; }
	</style>
	<style>
	 	div.replyModal { position:relative; z-index:1; display:none; }
	 	div.modalBackground { position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0, 0, 0, 0.8); z-index:-1; }
	 	div.modalContent { position:fixed; top:20%; left:calc(50% - 250px); width:500px; height:250px; padding:20px 10px; background:#fff; border:2px solid #666; }
	 	div.modalContent textarea { font-size:16px; font-family:'맑은 고딕', verdana; padding:10px; width:500px; height:200px; }
	 	div.modalContent button { font-size:20px; padding:5px 10px; margin:10px 0; background:#fff; border:1px solid #ccc; }
	 	div.modalContent button.modal_cancel { margin-left:20px; }
	</style>
	
</head>
<body>
	<div id="root">
		<header id="header">
			<div id="header_box">
				<%@ include file="../include/header.jsp"%>
			</div>
		</header>
		<nav id="nav">
			<div id="nav_box">
				<%@ include file="../include/nav.jsp"%>
			</div>
		</nav>
		<section id="container">
			<div id="container_box">
				<section id="content">
					<%-- <form action="" role="form" method="post">
						<input type="hidden" name="gdsNum" value="${view.gdsNum}">
					</form>
					<p>
						<img src="/resources/imgUpload${gdsImg}" />
					</p>
					<p>
						상품 이름 : ${view.gdsName}
					</p>
					<p>
						상품 가격 : ${view.gdsPrice}
					</p>
					<p>
						상품 수량 : ${view.gdsStock}
					</p>
					<p>
						상품 설명 : ${view.gdsDes}
					</p> --%>
					<form role="form" method="post">
						<input type="hidden" name="gdsNum" value="${view.gdsNum}" />
					</form>
					<div class="goods">
						<div class="goodsImg">
							<c:choose>
								<c:when test="${view.gdsImg != null}">
									<!-- 이미지 있을때 출력 -->
									<img alt="${view.gdsNum}번 상품 썸네일" src="/resources/imgUpload${gdsImg}">
								</c:when>
								<c:otherwise>
									<!-- 이미지 없을때 출력 -->
									<img alt="${view.gdsNum}번 상품 썸네일" src="/resources/imgUpload/none.png">
								</c:otherwise>
							</c:choose>
						</div>
	
						<div class="goodsInfo">
							<p class="gdsName">
								<span>상품명</span> ${view.gdsName}
							</p>
							<p class="cateName">
								<span>카테고리</span> ${view.cateName}
							</p>
							<p class="gdsPrice">
								<span>가격 </span>
								<fmt:formatNumber pattern="###,###,###" value="${view.gdsPrice}" /> 원
							</p>
							<p class="gdsStock">
								<span>재고 </span>
								<fmt:formatNumber pattern="###,###,###" value="${view.gdsStock}" /> EA
							</p>
							<c:if test="${view.gdsStock != 0}">
							<p class="cartStock">
								<span>구입 수량</span> <input type="number" class="numBox" cmin="1" max="${view.gdsStock}" value="1" />
							</p>
							<p class="addToCart">
								<button type="button" class="addCart_btn">카트에 담기</button>
								<script>
									$(".addCart_btn").click(function() {
										var gdsNum = $("#gdsNum").val();
										var cartStock = $(".numBox").val();
									    
										var data = {
											gdsNum : gdsNum,
											cartStock : cartStock
										};
										
										$.ajax({
											url : "/shop/view/addCart.json",
											type : "post",
											data : data,
											success : function(result) {
												if (result == 1) {
													alert("카트 담기 성공");
													$(".numBox").val("1");
												} else {
													alert("회원만 사용할 수 있습니다.");
													$(".numBox").val("1");
												}
											},
											error : function(){
												alert("카트 담기 실패");
											}
										});
									});
	 							</script>
							</p>
							</c:if>
							<c:if test="${view.gdsStock == 0 }">
								<p>상품 수량이 부족합니다.</p>
							</c:if>
						</div>
						<div class="gdsDes">${view.gdsDes}</div>
					</div>
					<div id="reply">
					<c:if test="${member == null}">
						<p>
							소감을 남기시려면<a href="/member/signin">로그인</a>해주세요
						</p>
					</c:if>
					<c:if test="${member != null}">
						<section class="replyForm">
							<form role="from" method="post" autocomplete="off">
								<input type="hidden" name="gdsNum" id="gdsNum" value="${view.gdsNum}">
								<div class="input_area">
									<textarea name="repCon" id="repCon"></textarea>
								</div>
								<div class="input_area">
									<button type="button" id="reply_btn">소감 남기기</button>
									<script>
										$("#reply_btn").click(function() {
											var formObj = $(".replyForm form[role='form']");
											var gdsNum = $("#gdsNum").val();
											var repCon = $("#repCon").val();
											
											var data = {
													gdsNum : gdsNum,
													repCon : repCon
												};
											
											$.ajax({
												url : "/shop/view/registReply",
												type : "post",
												data : data,
												success : function() {
													replyList();
													$('#repCon').val('');
												}
											});
										});
									</script>
								</div>
							</form>
						</section>
					</c:if>
						<section class="replyList">
							<ol>
								<script>
									function replyList() { 
										var gdsNum = ${view.gdsNum};
										
										$.getJSON("/shop/view/replyList?n=" + gdsNum, function(data) {
											var str = "";
											$(data).each(function() {
											console.log(data);
											
											var repDate = new Date(this.repDate);
											repDate = repDate.toLocaleDateString("ko-KR");
											
											str += "<li data-repNum='" + this.gdsNum + "'>"
												+ "<div class='userInfo'>"
												+ "<span class='userName'>" + this.userName + "</span>"
												+ "</div>"
												+ "<div class='replyContent'>" + this.repCon + "</div>"
												+ "<c:if test='${member != null}'>"
												+ "<div class='replyFooter'>"
												+ "<button type='button' class='modify' data-repNum='" + this.repNum + "'>M</button>"
												+ "<button type='button' class='delete' data-repNum='" + this.repNum + "'>D</button>"
												+ "</div>"
												+ "</c:if>"
												+ "</li>";
											});
											$("section.replyList ol").html(str);
										});
									}
								</script>
							<%-- <c:forEach items="${reply}" var="reply">
								<li>
									<div class="userInfo">
										<span class="userName">${reply.userName}</span>
										<span class="date"><fmt:formatDate value="${reply.repDate}" pattern="yyyy-MM-dd" /></span>
									</div>
									<div class="replyContent">${reply.repCon}</div>
								</li>
							</c:forEach> --%>
							</ol>
							<script>
								replyList();
							</script>
							
						</section>
						<aside id="aside">
							<%@ include file="../include/aside.jsp"%>
						</aside>
					</div>
				</section>
				<footer id="footer">
					<div id="footer_box">
						<%@ include file="../include/footer.jsp"%>
					</div>
				</footer>
			</div>
		</section>
	</div>
	<div class="replyModal">
		<div class="modalContent">
			<div>
				<textarea id="model_repCon" class="model_repCon" name="model_repCon"></textarea>
			</div>
			<div>
				<button type="button" class="model_modify_btn">수정</button>
				<button type="button" class="model_cancel_btn">취소</button>
			</div>
		</div>
		<div class="modalBackground"></div>
	</div>

	<script>
		$(".model_cancel_btn").click(function() {
			//$(".replyModal").attr("style", "display:none;");
			$(".replyModal").fadeOut(200);
		});
		
		$(".model_modify_btn").click(function() {
			var modifyConfirm = confirm("정말로 수정하시겠습니까?");
			
			if(modifyConfirm) {
				var data = {
					repNum : $(this).attr("data-repNum"),
					repCon : $("#model_repCon").val()
				};
				
				$.ajax({
					url : "/shop/view/modifyReply.json",
					type : "post",
					data : data,
					success : function(result) {
						// result의 값에 따라 동작
						if(result == 1) {
							replyList(); // 리스트 새로고침
							$(".replyModal").fadeOut(200);
						} else alert("작성자 본인만 할 수 있습니다."); // 본인이 아닌 경우
					},
					error : function() {
						// 로그인 하지 않아서 에러가 발생한 경우
						alert("로그인 하셔야 합니다.");
					}
				});
			}
		});
		
		$(document).on("click", ".modify", function() {
			//$(".replyModal").attr("style", "display:block;");
			$(".replyModal").fadeIn(200);
			
			var repNum = $(this).attr("data-repNum");
			var repCon = $(this).parent().parent().children(".replyContent").text();
			
			$(".model_repCon").val(repCon);
			$(".model_modify_btn").attr("data-repNum", repNum);
		});
		
		$(document).on("click", ".delete", function() {
			
			var data = { repNum : $(this).attr("data-repNum") };
			
			$.ajax({
				url : "/shop/view/deleteReply.json",
				type : "post",
				data : data,
				success : function(result) {
					alert(result)
					if(result == 1) replyList();
					else alert("작성자 본인만 할 수 있습니다.");
				},
				error : function() {
					alert("로그인하셔야합니다.");
				}
			});
		});
	</script>
</body>
</html>