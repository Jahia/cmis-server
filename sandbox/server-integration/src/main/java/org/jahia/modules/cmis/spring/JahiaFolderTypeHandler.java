package org.jahia.modules.cmis.spring;

import org.apache.chemistry.opencmis.commons.data.Properties;
import org.apache.chemistry.opencmis.commons.exceptions.CmisStorageException;
import org.apache.chemistry.opencmis.jcr.JcrFolder;
import org.apache.chemistry.opencmis.jcr.impl.DefaultFolderTypeHandler;
import org.jahia.api.Constants;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.nodetype.NodeType;

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

    @Override
    public JcrFolder createFolder(JcrFolder parentFolder, String name, Properties properties) {
        try {
            Node node = parentFolder.getNode().addNode(name, Constants.JAHIANT_FOLDER);
            node.addMixin(NodeType.MIX_CREATED);
            node.addMixin(NodeType.MIX_LAST_MODIFIED);

            // compile the properties
            JcrFolder.setProperties(node, getTypeDefinition(), properties);

            node.getSession().save();
            return getJcrNode(node);
        }
        catch (RepositoryException e) {
            throw new CmisStorageException(e.getMessage(), e);
        }
    }
}
