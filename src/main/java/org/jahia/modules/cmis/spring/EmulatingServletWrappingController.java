package org.jahia.modules.cmis.spring;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ServletWrappingController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * An extension of Spring's ServletWrappingController that can setup servlet wrappers
 */
public class EmulatingServletWrappingController extends ServletWrappingController {

    private String servletPart;

    public String getServletPart() {
        return servletPart;
    }

    public void setServletPart(String servletPart) {
        this.servletPart = servletPart;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.handleRequestInternal(new HttpServletRequestWrapper(request) {

            @Override
            public String getServletPath() {
                return super.getServletPath() + servletPart;
            }

            @Override
            public String getPathInfo() {
                String requestURI = super.getRequestURI();
                String newServletPart = super.getContextPath() + super.getServletPath() + servletPart;
                if (requestURI.startsWith(newServletPart)) {
                    String newPathInfo = requestURI.substring(newServletPart.length());
                    return newPathInfo;
                } else {
                return super.getPathInfo();
                }
            }
        }, response);
    }
}
