package com.jay.cmbc.filter;

import com.jay.cmbc.servlet.CmbcHttpServletRequestWrapper;
import com.jay.cmbc.servlet.CmbcHttpServletResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解密加密cmbc
 */
@WebFilter(displayName = "SignEncryptDncryptSignChkFilter",urlPatterns = "/cmbc/*")
public class SignEncryptDncryptSignChkFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        CmbcHttpServletRequestWrapper requestWrapper=new CmbcHttpServletRequestWrapper(request);
        CmbcHttpServletResponseWrapper responseWrapper=new CmbcHttpServletResponseWrapper(response);
        filterChain.doFilter(requestWrapper,responseWrapper);

    }

    @Override
    public void destroy() {

    }
}
