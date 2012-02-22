package org.jahia.modules.cmis.spring;

import org.apache.chemistry.opencmis.jcr.JcrServiceFactory;

import javax.jcr.Repository;
import java.util.Map;

/**
 * OpenCMIS service factory to Jahia JCR repository mapper
 */
public class JahiaJcrServiceFactory extends JcrServiceFactory {

    private Repository repository;

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @Override
    protected Repository acquireJcrRepository(Map<String, String> jcrConfig) {
        return repository;
    }
}
