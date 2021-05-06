package com.wzl.filter;

import com.wzl.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.wzl.util.Constants;
public class AdminFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User)request.getSession().getAttribute(Constants.USER_SESSION);
        if (user == null || user.getIsAdmin() == 0){
            response.sendRedirect("/error.jsp");
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {

    }
}
