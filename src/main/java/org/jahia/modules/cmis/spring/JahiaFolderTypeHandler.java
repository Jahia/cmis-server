package org.jahia.modules.cmis.spring;

import org.apache.chemistry.opencmis.jcr.impl.DefaultFolderTypeHandler;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

/**
 * An extension of the OpenCMIS default folder type handler that can handle more node types
 */
public class JahiaFolderTypeHandler extends DefaultFolderTypeHandler {

    String[] nodeTypes;

    public JahiaFolderTypeHandler(String[] nodeTypes) {
        this.nodeTypes = nodeTypes;
    }

    @Override
    public boolean canHandle(Node node) throws RepositoryException {
        for (String nodeType : nodeTypes) {
            if (node.isNodeType(nodeType)) {
                return true;
            }
        }
        if (node.getDepth() == 0) { // the case of the root node.
            return true;
        }
        return false;
    }
}
