package com.indra.selecaoJava.util;

import static java.text.MessageFormat.format;

import java.util.Objects;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ExceptionUtil {

    public static String getMessage(String key) {

        return getMessage(key, new Object[] {});

    }

    public static String getMessage(String key, Object[] args) {

        HttpServletRequest request = null;
        String msg = null;
        try {

            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        } catch (Exception e) {
        }

        msg = getMessage(key, request, args);

        return msg;
    }

    public static String getMessage(String key, HttpServletRequest request, Object[] args) {

        try {

            ResourceBundle bundle = ResourceBundle.getBundle("exceptionMessages");

            String msg = bundle.getString(tratarKey(key));
            if (Objects.nonNull(msg) && args != null && args.length != 0) {
                msg = format(msg, args);
            }

            if (Objects.nonNull(msg)) {
                return new String(msg.getBytes());
            }

        } catch (Exception e) {
            return e.getMessage();
        }

        return null;

    }

    public static String tratarKey(String key) {

        if (Objects.nonNull(key)) {
            key = key.trim();
            key = key.replaceAll("\\{\\{\\{", "");
            key = key.replaceAll("\\}\\}\\}", "");
        }

        return key;

    }

}