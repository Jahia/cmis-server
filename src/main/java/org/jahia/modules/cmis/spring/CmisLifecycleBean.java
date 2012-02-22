package org.jahia.modules.cmis.spring;

import org.apache.chemistry.opencmis.commons.server.CmisServiceFactory;
import org.apache.chemistry.opencmis.server.impl.CmisRepositoryContextListener;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

public class CmisLifecycleBean implements ServletContextAware,
        InitializingBean, DisposableBean
{
    private ServletContext servletContext;
    private CmisServiceFactory factory;
    private Map<String,String> parameters = new HashMap<String,String>();

    public void setServletContext(ServletContext servletContext)
    {
        this.servletContext = servletContext;
    }

    public void setCmisServiceFactory(CmisServiceFactory factory)
    {
        this.factory = factory;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public void afterPropertiesSet() throws Exception
    {
        if (factory != null)
        {
            factory.init(parameters);
            servletContext.setAttribute(CmisRepositoryContextListener.SERVICES_FACTORY, factory);
        }
    }

    public void destroy() throws Exception
    {
        if (factory != null)
        {
            factory.destroy();
        }
    }
}