package com.guo.config;

import com.sun.corba.se.spi.orbutil.closure.Closure;
import com.sun.corba.se.spi.resolver.LocalResolver;
import org.omg.CORBA.Object;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class MyLocaleResolver implements LocalResolver {

    //解析请求
    public Locale resolverLocale(HttpServletRequest request) {
        //获取语言中的语言参数
        String language =  request.getParameter("l");

        Locale locale = Locale.getDefault();//如果没有就使用默认的
        //如果请求的连接携带了国际化的参数
        if(!StringUtils.isEmpty(language)){
            String[] split = language.split("_");
            //国家，地区
            locale = new Locale(split[0], split[1]);
        }

        return locale;
    }

    @Override
    public void register(String s, Closure closure) {

    }

    @Override
    public Object resolve(String s) {
        return null;
    }

    @Override
    public Set list() {
        return null;
    }

}