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

import bbs.entity.UserEntity;

public class NgWordFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("bbs.filter.NgWordFilter#doFilter running.");
		UserEntity user = (UserEntity) ((HttpServletRequest) request).getSession().getAttribute("loginUser");
		if (user.getDepartmentId() != 2) {
			HttpSession session = ((HttpServletRequest) request).getSession();
			session.setAttribute("errorMessages", "この操作に対する権限がありません");

			((HttpServletResponse) response).sendRedirect("/Spring_BBS/top/");
			System.out.println("No permission.");
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
