package com.pani.birrachallenge.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * We use this class to ensure the user using the endpoint is logged
 * 
 * @author mariano.paniagua
 *
 */
@SuppressWarnings("deprecation")
public class RestAuthInterceptor extends HandlerInterceptorAdapter {

	String UNIQUE_TOKEN_HEADER = "unique-token";

	@Autowired
	UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (handler instanceof HandlerMethod) {
			if (((HandlerMethod) handler).getMethod().getAnnotation(RestAdminAuth.class) != null) {
				String uniqueToken = request.getHeader(UNIQUE_TOKEN_HEADER);
				User user = userService.getUser(uniqueToken);
				if (user != null && user.isAdmin()) {
					return true;
				} else {
					response.reset();
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					return false;
				}
			}
			if (((HandlerMethod) handler).getMethod().getAnnotation(RestAuth.class) != null) {
				String uniqueToken = request.getHeader(UNIQUE_TOKEN_HEADER);
				User user = userService.getUser(uniqueToken);
				if (user != null) {
					return true;
				} else {
					response.reset();
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					return false;
				}
			}
		}
		return true;

	}
}
