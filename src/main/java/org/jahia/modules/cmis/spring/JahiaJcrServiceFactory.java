package org.jahia.modules.cmis.spring;

import org.apache.chemistry.opencmis.jcr.JcrServiceFactory;
import org.apache.chemistry.opencmis.jcr.JcrTypeManager;
import org.apache.chemistry.opencmis.jcr.PathManager;
import org.apache.chemistry.opencmis.jcr.impl.DefaultDocumentTypeHandler;
import org.apache.chemistry.opencmis.jcr.impl.DefaultFolderTypeHandler;
import org.apache.chemistry.opencmis.jcr.impl.DefaultUnversionedDocumentTypeHandler;
import org.apache.chemistry.opencmis.jcr.type.JcrTypeHandlerManager;
import org.jahia.services.search.jcr.JahiaJCRSearchProvider;

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

    // We override the type handler manager to be able to expose more types.
    protected JcrTypeHandlerManager createTypeHandlerManager(PathManager pathManager, JcrTypeManager typeManager) {
        JcrTypeHandlerManager typeHandlerManager = new JcrTypeHandlerManager(pathManager, typeManager);
        // typeHandlerManager.addHandler(new DefaultFolderTypeHandler());
        String[] nodeTypes = new String[] {
                "nt:folder",
                "jnt:virtualsitesFolder",
                "jnt:virtualsite",
        };
        typeHandlerManager.addHandler(new JahiaFolderTypeHandler(nodeTypes));
        typeHandlerManager.addHandler(new DefaultDocumentTypeHandler());
        typeHandlerManager.addHandler(new DefaultUnversionedDocumentTypeHandler());
        return typeHandlerManager;
    }

}
