package bbs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import bbs.entity.UserEntity;

@Controller
public class LoginFilter implements Filter {


	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		UserEntity loginUser = (UserEntity) ((HttpServletRequest) request).getSession().getAttribute("loginUser");
		System.out.println("bbs.filter.LoginFilter#doFilter running.");
//		boolean check = false;
//		if (loginUser != null) {
//			try {
//				check = new UserChecker().isValidUser(loginUser.getId());
//			} catch (Exception e) {
//				System.out.println("Exception in bbs.filter.LoignFilter#doFilter.");
//				e.printStackTrace();
//			}
//		}
		if (loginUser == null) {

			System.out.println("loginUser is null.");
			HttpSession session = ((HttpServletRequest) request).getSession();
			session.removeAttribute("message");
			session.setAttribute("errorMessages", "ログインしてください");
			((HttpServletResponse) response).sendRedirect("/Spring_BBS/login/");
			return;
		}
		System.out.println("loginUser.getId() is :" + loginUser.getId());
		chain.doFilter(request, response);
	}

	public void destroy() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
