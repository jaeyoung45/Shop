package com.shop.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CheckCookieInterceptor extends HandlerInterceptorAdapter {

//	@Inject
//	MemberService service;
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		HttpSession session = request.getSession();
//		if (session.getAttribute("member") != null) return true;
//		
//		Cookie loginCookie = WebUtils.getCookie(request, "LoginCookie");
//		if (loginCookie != null) {
//			String username = loginCookie.getValue();
//			System.out.println("check Cookie username : " + username);
//			MemberVO vo = service.profilesGetMethod(username); // Since - 2019/03/29, Content - 유저이름으로 정보 가져오기
//			if (vo != null) session.setAttribute("member", vo);
//		}
//		return true;
//	}

}
