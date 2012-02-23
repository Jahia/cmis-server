package org.jahia.services.content.impl.cmis;

import org.apache.chemistry.opencmis.client.api.Document;
import org.jahia.api.Constants;
import org.jahia.services.content.nodetypes.ExtendedNodeDefinition;
import org.jahia.services.content.nodetypes.ExtendedNodeType;
import org.jahia.services.content.nodetypes.Name;
import org.jahia.services.content.nodetypes.NodeTypeRegistry;

import javax.activation.MimetypesFileTypeMap;
import javax.jcr.*;
import javax.jcr.lock.Lock;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.*;
import javax.jcr.version.Version;
import javax.jcr.version.VersionException;
import javax.jcr.version.VersionHistory;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Content node for CMIS document
 */
public class CMISContentNodeImpl extends CMISItemImpl implements Node {

    private Document cmisDocument;

    public CMISContentNodeImpl(Name nodeName, CMISSessionImpl cmisSessionImpl, Document cmisDocument) {
        super(nodeName, cmisDocument, cmisSessionImpl);
        this.cmisDocument = cmisDocument;
    }

    public Node addNode(String s) throws ItemExistsException, PathNotFoundException, VersionException, ConstraintViolationException, LockException, RepositoryException {
        return null;
    }

    public Node addNode(String s, String s1) throws ItemExistsException, PathNotFoundException, NoSuchNodeTypeException, LockException, VersionException, ConstraintViolationException, RepositoryException {
        return null;
    }

    public void orderBefore(String s, String s1) throws UnsupportedRepositoryOperationException, VersionException, ConstraintViolationException, ItemNotFoundException, LockException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String s, Value value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String s, Value value, int i) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String s, Value[] values) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String s, Value[] values, int i) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String s, String[] strings) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String s, String[] strings, int i) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String s, String s1) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        if (s.equals(Constants.JCR_MIMETYPE)) {
//            try {
//                content.setAttribute();
//            } catch (FileSystemException e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
            return null;
        }
        throw new ConstraintViolationException("Unknown type");
    }

    public Property setProperty(String s, String s1, int i) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String s, InputStream inputStream) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        /*
        if (s.equals(Constants.JCR_DATA)) {
            try {
                cmisDocument.deleteContentStream();
                cmisDocument.
                IOUtils.copy(inputStream, outputStream);
                outputStream.close();
                CMISValueFactoryImpl valueFactory = (CMISValueFactoryImpl) cmisSessionImpl.getValueFactory();
                Value value = valueFactory.createValue(new CMISBinaryImpl(content));
                return new DataPropertyImpl(new Name(s, "", ""), this, session, value);
            } catch (IOException e) {
                throw new RepositoryException("Cannot write to stream", e);
            }
        }
        throw new ConstraintViolationException("Unknown type");
        */
        return null;
    }

    public Property setProperty(String s, boolean b) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String s, double v) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String s, long l) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String s, Calendar calendar) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        /*
        if (s.equals(Constants.JCR_LASTMODIFIED)) {
            try {
                content.setLastModifiedTime(calendar.getTime().getTime());
            } catch (FileSystemException e) {

            }
            return null;
        }
        throw new ConstraintViolationException("Unknown type");
        */
        return null;
    }

    public Property setProperty(String s, Node node) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Node getNode(String s) throws PathNotFoundException, RepositoryException {
        return null;
    }

    public NodeIterator getNodes() throws RepositoryException {
        return CMISNodeIteratorImpl.EMPTY;
    }

    public NodeIterator getNodes(String s) throws RepositoryException {
        return CMISNodeIteratorImpl.EMPTY;
    }

    public Property getProperty(String s) throws PathNotFoundException, RepositoryException {
        CMISValueFactoryImpl valueFactory = (CMISValueFactoryImpl) cmisSessionImpl.getValueFactory();
        if (s.equals(Constants.JCR_DATA)) {
            Value value = null;
            value = valueFactory.createValue(new CMISBinaryImpl(cmisDocument.getContentStream()));
            return new CMISPropertyImpl(new Name(s, "", ""), cmisDocument, cmisSessionImpl, value) {
                public long getLength() throws ValueFormatException, RepositoryException {
                    return cmisDocument.getContentStream().getLength();
                }

                public InputStream getStream() throws ValueFormatException, RepositoryException {
                    return cmisDocument.getContentStream().getStream();
                }

                public String getName() throws RepositoryException {
                    return Constants.JCR_DATA;
                }

                public PropertyDefinition getDefinition() throws RepositoryException {
                    return NodeTypeRegistry.getInstance().getNodeType(Constants.NT_RESOURCE).getPropertyDefinition(Constants.JCR_DATA);
                }
            };
        } else if (s.equals(Constants.JCR_MIMETYPE)) {
            String s1 = null;
            s1 = cmisDocument.getContentStreamMimeType();
            if (s1 == null) {
                int extensionPos = cmisDocument.getContentStreamFileName().lastIndexOf(".");
                if (extensionPos > -1) {
                    s1 = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType("."+ cmisDocument.getContentStreamFileName().substring(extensionPos+1));
                }
            }
            Value value = valueFactory.createValue(s1);
            return new CMISPropertyImpl(new Name(s, "", ""), cmisDocument, cmisSessionImpl, value) {
                public String getString() throws ValueFormatException, RepositoryException {
                    String s1 = cmisDocument.getContentStreamMimeType();
                    if (s1 == null) {
                        int extensionPos = cmisDocument.getContentStreamFileName().lastIndexOf(".");
                        if (extensionPos > -1) {
                            s1 = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType("."+ cmisDocument.getContentStreamFileName().substring(extensionPos+1));
                        }
                    }
                    return s1;
                }

                public String getName() throws RepositoryException {
                    return Constants.JCR_MIMETYPE;
                }

                public PropertyDefinition getDefinition() throws RepositoryException {
                    return NodeTypeRegistry.getInstance().getNodeType("mix:mimeType").getPropertyDefinition(Constants.JCR_MIMETYPE);
                }

            };
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PropertyIterator getProperties() throws RepositoryException {
        List l = new ArrayList(2);
        l.add(getProperty(Constants.JCR_DATA));
        l.add(getProperty(Constants.JCR_MIMETYPE));

        return new CMISPropertyIteratorImpl(l.iterator(), l.size());
    }

    public PropertyIterator getProperties(String s) throws RepositoryException {
        return CMISPropertyIteratorImpl.EMPTY;
    }

    public Item getPrimaryItem() throws ItemNotFoundException, RepositoryException {
        return null;
    }

    public String getUUID() throws UnsupportedRepositoryOperationException, RepositoryException {
        return getIdentifier();
    }

    public int getIndex() throws RepositoryException {
        return 0;
    }

    public PropertyIterator getReferences() throws RepositoryException {
        return CMISPropertyIteratorImpl.EMPTY;
    }

    public boolean hasNode(String s) throws RepositoryException {
        return false;
    }

    public boolean hasProperty(String s) throws RepositoryException {
        return s.equals("jcr:data") || s.equals("jcr:mimeType");
    }

    public boolean hasNodes() throws RepositoryException {
        return false;
    }

    public boolean hasProperties() throws RepositoryException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public NodeType getPrimaryNodeType() throws RepositoryException {
        return NodeTypeRegistry.getInstance().getNodeType("jnt:resource");
    }

    public NodeType[] getMixinNodeTypes() throws RepositoryException {
        return new NodeType[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isNodeType(String s) throws RepositoryException {
        return getPrimaryNodeType().isNodeType(s);
    }

    public void addMixin(String s) throws NoSuchNodeTypeException, VersionException, ConstraintViolationException, LockException, RepositoryException {
    }

    public void removeMixin(String s) throws NoSuchNodeTypeException, VersionException, ConstraintViolationException, LockException, RepositoryException {
    }

    public boolean canAddMixin(String s) throws NoSuchNodeTypeException, RepositoryException {
        return false;
    }

    public NodeDefinition getDefinition() throws RepositoryException {
        CMISNodeImpl parentNode = (CMISNodeImpl) getParent();
        ExtendedNodeType parentNodeType = parentNode.getExtendedPrimaryNodeType();
        ExtendedNodeDefinition nodeDefinition = parentNodeType.getNodeDefinition("jcr:content");
        if (nodeDefinition != null) {
            return nodeDefinition;
        }
        for (ExtendedNodeDefinition extendedNodeDefinition : parentNodeType.getUnstructuredChildNodeDefinitions().values()) {
            return extendedNodeDefinition;
        }
        return null;
    }

    public Version checkin() throws VersionException, UnsupportedRepositoryOperationException, InvalidItemStateException, LockException, RepositoryException {
        return null;
    }

    public void checkout() throws UnsupportedRepositoryOperationException, LockException, RepositoryException {
    }

    public void doneMerge(Version version) throws VersionException, InvalidItemStateException, UnsupportedRepositoryOperationException, RepositoryException {
    }

    public void cancelMerge(Version version) throws VersionException, InvalidItemStateException, UnsupportedRepositoryOperationException, RepositoryException {
    }

    public void update(String s) throws NoSuchWorkspaceException, AccessDeniedException, LockException, InvalidItemStateException, RepositoryException {
    }

    public NodeIterator merge(String s, boolean b) throws NoSuchWorkspaceException, AccessDeniedException, MergeException, LockException, InvalidItemStateException, RepositoryException {
        return null;
    }

    public String getCorrespondingNodePath(String s) throws ItemNotFoundException, NoSuchWorkspaceException, AccessDeniedException, RepositoryException {
        return null;
    }

    public boolean isCheckedOut() throws RepositoryException {
        return false;
    }

    public void restore(String s, boolean b) throws VersionException, ItemExistsException, UnsupportedRepositoryOperationException, LockException, InvalidItemStateException, RepositoryException {
    }

    public void restore(Version version, boolean b) throws VersionException, ItemExistsException, UnsupportedRepositoryOperationException, LockException, RepositoryException {
    }

    public void restore(Version version, String s, boolean b) throws PathNotFoundException, ItemExistsException, VersionException, ConstraintViolationException, UnsupportedRepositoryOperationException, LockException, InvalidItemStateException, RepositoryException {
    }

    public void restoreByLabel(String s, boolean b) throws VersionException, ItemExistsException, UnsupportedRepositoryOperationException, LockException, InvalidItemStateException, RepositoryException {
    }

    public VersionHistory getVersionHistory() throws UnsupportedRepositoryOperationException, RepositoryException {
        return null;
    }

    public Version getBaseVersion() throws UnsupportedRepositoryOperationException, RepositoryException {
        return null;
    }

    public Lock lock(boolean b, boolean b1) throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, InvalidItemStateException, RepositoryException {
        return null;
    }

    public Lock getLock() throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, RepositoryException {
        return null;
    }

    public void unlock() throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, InvalidItemStateException, RepositoryException {
    }

    public boolean holdsLock() throws RepositoryException {
        return false;
    }

    public boolean isLocked() throws RepositoryException {
        return false;
    }

    public String getPath() throws RepositoryException {
        String s = cmisDocument.getPaths().get(0).substring(((CMISRepositoryImpl) cmisSessionImpl.getRepository()).getRootPath().length());
        if (!s.startsWith("/")) {
            s = "/"+s;
        }
        return s+"/"+Constants.JCR_CONTENT;
    }

    public String getName() throws RepositoryException {
        return Constants.JCR_CONTENT;
    }

    public Node getParent() throws ItemNotFoundException, AccessDeniedException, RepositoryException {
        return new CMISNodeImpl(new Name(cmisDocument.getName(), "", ""), cmisDocument, cmisSessionImpl);
    }


    public Property setProperty(String name, Binary value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return setProperty(name, value.getStream());
    }

    public Property setProperty(String name, BigDecimal value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public NodeIterator getNodes(String[] nameGlobs) throws RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PropertyIterator getProperties(String[] strings) throws RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getIdentifier() throws RepositoryException {
        return cmisDocument.getId() + Constants.JCR_CONTENT;
    }

    public PropertyIterator getReferences(String name) throws RepositoryException {
        return CMISPropertyIteratorImpl.EMPTY;
    }

    public PropertyIterator getWeakReferences() throws RepositoryException {
        return CMISPropertyIteratorImpl.EMPTY;
    }

    public PropertyIterator getWeakReferences(String name) throws RepositoryException {
        return CMISPropertyIteratorImpl.EMPTY;
    }

    public void setPrimaryType(String nodeTypeName) throws NoSuchNodeTypeException, VersionException, ConstraintViolationException, LockException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public NodeIterator getSharedSet() throws RepositoryException {
        return CMISNodeIteratorImpl.EMPTY;
    }

    public void removeSharedSet() throws VersionException, LockException, ConstraintViolationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeShare() throws VersionException, LockException, ConstraintViolationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void followLifecycleTransition(String transition) throws UnsupportedRepositoryOperationException, InvalidLifecycleTransitionException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public String[] getAllowedLifecycleTransistions() throws UnsupportedRepositoryOperationException, RepositoryException {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    class DataPropertyImpl extends CMISPropertyImpl {

        public DataPropertyImpl(Name name, Document cmisDocument, CMISSessionImpl session, Value value) throws RepositoryException {
            super(name, cmisDocument, session, value);
        }

        public InputStream getStream() throws ValueFormatException, RepositoryException {
            return cmisDocument.getContentStream().getStream();
        }
    }

}
