<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Shop</title>
</head>
<body>
<div id="root">
	<header id="header">
		<div id="header_box">
			<%@ include file="../include/header.jsp" %>
		</div>
	</header>
	<nav id="nav">
		<div id="nav_box">
			<%@ include file="../include/nav.jsp" %>
		</div>
	</nav>
	<section id="container">
		<div id="container_box">
			<section id="content">
				<form role="form" method="post" autocomplete="off">
					<div class="input_area">
						<label for="userId">이메일</label>
						<input type="email" id="userId" name="userId" placeholder="example@email.com" required />
					</div>
					<div class="input_area">
						<label for="userPass">비밀번호</label>
						<input type="password" id="userPass" name="userPass" required  />
					</div>
					<div class="input_area">
						<label for="userName">닉네임</label>
						<input type="text" id="userName" name="userName" placeholder="닉네임을 입력해주세요" required  />
					</div>
					<div class="input_area">
						<label for="userPhon">연락처</label>
						<input type="text" id="userPhon" name="userPhon" placeholder="연락처를 입력해주세요" required />
					</div>
					<div class="input_area">
						<label for="userAddr1">주소1</label>
						<input type="text" id="userAddr1" name="userAddr1" placeholder="주소1를 입력해주세요" required />
					</div>
					<div class="input_area">
						<label for="userAddr2">주소2</label>
						<input type="text" id="userAddr2" name="userAddr2" placeholder="주소2를 입력해주세요" required />
					</div>
					<div class="input_area">
						<label for="userAddr3">주소3</label>
						<input type="text" id="userAddr3" name="userAddr3" placeholder="주소3을 입력해주세요" required />
					</div>
					<button type="submit" id="signup_btn" name="signup_btn">회원가입</button>
				</form>
			</section>
		</div>
	</section>
	<footer id="footer">
		<div id="footer_box">
			<%@ include file="../include/footer.jsp" %>
		</div>
	</footer>
</div>
</body>
</html>