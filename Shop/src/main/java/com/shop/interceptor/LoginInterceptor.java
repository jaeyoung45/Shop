package com.shop.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

//	@Inject
//	MemberService service;
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		HttpSession session = request.getSession();
//		if(session.getAttribute("member") != null) {
//			System.out.println("세션에 정보가 존재합니다.");
//			session.removeAttribute("member");
//			session.invalidate();
//		}
//		return true;
//	}
//	
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//		HttpSession session = request.getSession();
//		ModelMap obj = modelAndView.getModelMap();
//		LoginDto dto = (LoginDto)obj.get("loginDto");
//
//		String joindate = dto.getJoindate();
//		String username = dto.getUsername();
//		String password = dto.getPassword();
//
//		final String hash = joindate + "/" + username + "/" + password;
//		
//		final String passwordHash =  service.getPasswordHashByDto(dto);
//		dto.setPassword(passwordHash);
//		
//		MemberVO vo = service.Login(dto);
//		
//		if (vo != null && BCrypt.checkpw(hash, passwordHash)) {
//			session.setAttribute("member", vo);
//			
//			if(dto.isUseCookie()) {
//				Cookie cookie = new Cookie("LoginCookie", String.valueOf(vo.getUsername()));
//				cookie.setPath("/");
//				cookie.setMaxAge(60 * 60 * 24 * 7);
//				response.addCookie(cookie);
//				
//				System.out.println("Username : " + vo.getUsername());
//				System.out.println("Cookie : " + cookie.getValue());
//				System.out.println("쿠키생성 완료");
//			}
//		} else { modelAndView.addObject("message", "로그인에 실패 하셨습니다."); modelAndView.setViewName("/member/login"); }
//	}

}
