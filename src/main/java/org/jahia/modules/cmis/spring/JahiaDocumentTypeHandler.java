package org.jahia.modules.cmis.spring;

import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.data.Properties;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisStorageException;
import org.apache.chemistry.opencmis.jcr.JcrBinary;
import org.apache.chemistry.opencmis.jcr.JcrFolder;
import org.apache.chemistry.opencmis.jcr.JcrNode;
import org.apache.chemistry.opencmis.jcr.JcrVersionBase;
import org.apache.chemistry.opencmis.jcr.impl.DefaultDocumentTypeHandler;
import org.jahia.api.Constants;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.nodetype.NodeType;
import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * A subclass of the DefaultDocumentTypeHandler to create jnt:file nodes instead of nt:file
 */
public class JahiaDocumentTypeHandler extends DefaultDocumentTypeHandler {

    @Override
    public JcrNode createDocument(JcrFolder parentFolder, String name, Properties properties, ContentStream contentStream, VersioningState versioningState) {
        try {
            Node fileNode = parentFolder.getNode().addNode(name, Constants.JAHIANT_FILE);
            if (versioningState != VersioningState.NONE) {
                fileNode.addMixin(NodeType.MIX_SIMPLE_VERSIONABLE);
            }

            Node contentNode = fileNode.addNode(Node.JCR_CONTENT, Constants.JAHIANT_RESOURCE);
            contentNode.addMixin(NodeType.MIX_CREATED);

            // compile the properties
            JcrFolder.setProperties(contentNode, getTypeDefinition(), properties);

            // write content, if available
            Binary binary = contentStream == null || contentStream.getStream() == null
                    ? JcrBinary.EMPTY
                    : new JcrBinary(new BufferedInputStream(contentStream.getStream()));
            try {
                contentNode.setProperty(Property.JCR_DATA, binary);
                if (contentStream != null && contentStream.getMimeType() != null) {
                    contentNode.setProperty(Property.JCR_MIMETYPE, contentStream.getMimeType());
                }
            }
            finally {
                binary.dispose();
            }

            fileNode.getSession().save();
            JcrNode jcrFileNode = getJcrNode(fileNode);
            if (versioningState == VersioningState.NONE) {
                return jcrFileNode;
            }

            JcrVersionBase jcrVersion = jcrFileNode.asVersion();
            if (versioningState == VersioningState.MINOR || versioningState == VersioningState.MAJOR) {
                return jcrVersion.checkin(null, null, "auto checkin");
            } else {
                return jcrVersion.getPwc();
            }
        }
        catch (RepositoryException e) {
            throw new CmisStorageException(e.getMessage(), e);
        }
        catch (IOException e) {
            throw new CmisStorageException(e.getMessage(), e);
        }
    }
}
