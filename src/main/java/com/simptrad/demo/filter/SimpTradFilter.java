package com.simptrad.demo.filter;


import com.simptrad.demo.util.SimpTradUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class SimpTradFilter implements Filter {

    @Resource
    SimpTradUtil simpTradUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----------------filter init");
    }
   //过滤功能
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("----------------filter doFilter begin");

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        //得到参数，如果有istrad则处理
        String istrad = servletRequest.getParameter("istrad");

        if (istrad != null && istrad.equals("1")) {
            ResponseWrapper wrapperResponse = new ResponseWrapper(response);
            filterChain.doFilter(request, wrapperResponse);
            byte[] bytes = wrapperResponse.getBytes();
            String val = new String(bytes, "UTF-8");

            System.out.println("content:"+val);

            String tradCont = simpTradUtil.convert2Trad(val);
            servletResponse.getOutputStream().write(tradCont.getBytes());
            servletResponse.getOutputStream().flush();
            servletResponse.getOutputStream().close();
        } else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        System.out.println("----------------filter destroy");
    }
}
