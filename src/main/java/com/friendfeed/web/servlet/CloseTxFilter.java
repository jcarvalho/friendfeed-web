package com.friendfeed.web.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;

@WebFilter("/*")
public class CloseTxFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CloseTxFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
        logger.info("Initialized {}", this.getClass().getName());
    }

    @Override
    public void destroy() {
        FenixFramework.shutdown();
    }

    @Override
    @Atomic(mode = TxMode.READ)
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        chain.doFilter(request, response);
    }

}
