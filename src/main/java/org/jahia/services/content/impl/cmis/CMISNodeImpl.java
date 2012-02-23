package org.jahia.services.content.impl.cmis;

import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.commons.data.*;
import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;
import org.jahia.api.Constants;
import org.jahia.services.content.nodetypes.ExtendedNodeType;
import org.jahia.services.content.nodetypes.Name;
import org.jahia.services.content.nodetypes.NodeTypeRegistry;

import javax.jcr.*;
import javax.jcr.Property;
import javax.jcr.lock.Lock;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.nodetype.NodeDefinition;
import javax.jcr.nodetype.NodeType;
import javax.jcr.version.ActivityViolationException;
import javax.jcr.version.Version;
import javax.jcr.version.VersionException;
import javax.jcr.version.VersionHistory;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * OpenCMIS repository node implementation
 */
public class CMISNodeImpl extends CMISItemImpl implements Node {

    private FileableCmisObject fileableCmisObject;

    public CMISNodeImpl(Name nodeName, FileableCmisObject fileableCmisObject, CMISSessionImpl cmisSessionImpl) {
        super(nodeName, fileableCmisObject, cmisSessionImpl);
        this.fileableCmisObject = fileableCmisObject;
    }

    @Override
    public String getPath() throws RepositoryException {
        List<String> paths = fileableCmisObject.getPaths();
        if (paths == null || paths.size() == 0) {
            return null;
        }
        String s = paths.get(0).substring(((CMISRepositoryImpl) cmisSessionImpl.getRepository()).getRootPath().length());
        if (!s.startsWith("/")) {
            s = "/" + s;
        }
        return s;
    }

    @Override
    public Item getAncestor(int depth) throws ItemNotFoundException, AccessDeniedException, RepositoryException {
        return super.getAncestor(depth);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public Node getParent() throws ItemNotFoundException, AccessDeniedException, RepositoryException {
        List<Folder> parentFolders = fileableCmisObject.getParents();
        if (parentFolders == null || parentFolders.size() == 0) {
            throw new ItemNotFoundException("Couldn't find parent for " + getName());
        }
        Folder parentFolder = parentFolders.get(0);
        return new CMISNodeImpl(new Name(parentFolder.getName(), "", ""), parentFolder, cmisSessionImpl);
    }

    @Override
    public int getDepth() throws RepositoryException {
        return super.getDepth();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public Node addNode(String relPath) throws ItemExistsException, PathNotFoundException, VersionException, ConstraintViolationException, LockException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Node addNode(String relPath, String primaryNodeTypeName) throws ItemExistsException, PathNotFoundException, NoSuchNodeTypeException, LockException, VersionException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void orderBefore(String srcChildRelPath, String destChildRelPath) throws UnsupportedRepositoryOperationException, VersionException, ConstraintViolationException, ItemNotFoundException, LockException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, Value value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, Value value, int type) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, Value[] values) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, Value[] values, int type) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, String[] values) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, String[] values, int type) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, String value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, String value, int type) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, InputStream value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, Binary value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, boolean value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, double value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, BigDecimal value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, long value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, Calendar value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property setProperty(String name, Node value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Node getNode(String relPath) throws PathNotFoundException, RepositoryException {
        List<FileableCmisObject> cmisObjects = new ArrayList<FileableCmisObject>();
        if (fileableCmisObject instanceof Folder) {
            Folder folder = (Folder) fileableCmisObject;
            ItemIterable<CmisObject> children = folder.getChildren();
            for (CmisObject child : children) {
                if (child.getName().equals(relPath)) {
                    return new CMISNodeImpl(new Name(child.getName(), "",""), (FileableCmisObject) child, cmisSessionImpl);
                }
            }
        } else if (fileableCmisObject instanceof Document) {
            // here we need to return the content node.
            Document document = (Document) fileableCmisObject;
            if (relPath.equals(Constants.JCR_CONTENT)) {
                return new CMISContentNodeImpl(new Name(Constants.JCR_CONTENT, "", ""), cmisSessionImpl, document);
            } else {
                throw new PathNotFoundException("Couldn't find sub node " + relPath + " on document node !");
            }
        }
        throw new PathNotFoundException("Couldn't find child node " + relPath + " on node " + getName() + "!");
    }

    public NodeIterator getNodes() throws RepositoryException {
        List<FileableCmisObject> cmisObjects = new ArrayList<FileableCmisObject>();
        if (fileableCmisObject instanceof Folder) {
            Folder folder = (Folder) fileableCmisObject;
            ItemIterable<CmisObject> children = folder.getChildren();
            for (CmisObject child : children) {
                if (child instanceof FileableCmisObject) {
                    cmisObjects.add((FileableCmisObject) child);
                }
            }
        } else if (fileableCmisObject instanceof Document) {
            // here we need to return the content node.
            Document document = (Document) fileableCmisObject;
        }
        return new CMISNodeIteratorImpl(cmisSessionImpl, cmisObjects.toArray(new FileableCmisObject[cmisObjects.size()]));
    }

    public NodeIterator getNodes(String namePattern) throws RepositoryException {
        return CMISNodeIteratorImpl.EMPTY;
    }

    public NodeIterator getNodes(String[] nameGlobs) throws RepositoryException {
        return CMISNodeIteratorImpl.EMPTY;
    }

    public Property getProperty(String relPath) throws PathNotFoundException, RepositoryException {
        org.apache.chemistry.opencmis.client.api.Property<?> cmisProperty = fileableCmisObject.getProperty(relPath);
        if (cmisProperty != null) {
            return cmisToJCRProperty(new Name(cmisProperty.getLocalName(),"",""), cmisProperty);
        } else {
            throw new PathNotFoundException("Property " + relPath + " could not be found !");
        }
    }

    private Property cmisToJCRProperty(Name propertyName, org.apache.chemistry.opencmis.client.api.Property<?> cmisProperty) throws RepositoryException {
        CMISPropertyImpl jcrProperty = new CMISPropertyImpl(propertyName, fileableCmisObject, cmisSessionImpl);
        if (cmisProperty.isMultiValued()) {
            List<?> cmisValues = cmisProperty.getValues();
            Value[] jcrValues = new Value[cmisValues.size()];
            int i=0;
            for (Object cmisValue : cmisValues) {
                Value jcrValue = cmisToJCRValue(cmisValue);
                jcrValues[i] = jcrValue;
                i++;
            }
            jcrProperty.setValue(jcrValues);
        } else {
            jcrProperty.setValue(cmisToJCRValue(cmisProperty.getFirstValue()));
        }
        return jcrProperty;
    }

    private Value cmisToJCRValue(Object cmisValue) {
        Value jcrValue = null;
        if (cmisValue instanceof Boolean) {
            jcrValue = new CMISValueImpl((Boolean) cmisValue);
        } else if (cmisValue instanceof Calendar) {
            jcrValue = new CMISValueImpl((Calendar) cmisValue);
        } else if (cmisValue instanceof PropertyDecimal) {
            jcrValue = new CMISValueImpl(((PropertyDecimal) cmisValue).getFirstValue());
        } else if (cmisValue instanceof PropertyHtml) {
            jcrValue = new CMISValueImpl(((PropertyHtml) cmisValue).getFirstValue());
        } else if (cmisValue instanceof PropertyInteger) {
            jcrValue = new CMISValueImpl(new BigDecimal(((PropertyInteger) cmisValue).getFirstValue()));
        } else if (cmisValue instanceof PropertyString) {
            jcrValue = new CMISValueImpl(((PropertyString) cmisValue).getFirstValue());
        } else if (cmisValue instanceof PropertyUri) {
            jcrValue = new CMISValueImpl(((PropertyUri) cmisValue).getFirstValue());
        } else {
            if (cmisValue != null) {
                jcrValue = new CMISValueImpl(cmisValue.toString());
            } else {
                jcrValue = null;
            }
        }
        return jcrValue;
    }

    public PropertyIterator getProperties() throws RepositoryException {
        List<org.apache.chemistry.opencmis.client.api.Property<?>> properties = fileableCmisObject.getProperties();
        List<Property> jcrProperties = new ArrayList<Property>();
        for (org.apache.chemistry.opencmis.client.api.Property<?> property : properties) {
            jcrProperties.add(cmisToJCRProperty(new Name(property.getLocalName(), "", ""), property));
        }
        return new CMISPropertyIteratorImpl(jcrProperties, jcrProperties.size());
    }

    public PropertyIterator getProperties(String namePattern) throws RepositoryException {
        return CMISPropertyIteratorImpl.EMPTY;
    }

    public PropertyIterator getProperties(String[] nameGlobs) throws RepositoryException {
        return CMISPropertyIteratorImpl.EMPTY;
    }

    public Item getPrimaryItem() throws ItemNotFoundException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getUUID() throws UnsupportedRepositoryOperationException, RepositoryException {
        return getIdentifier();
    }

    public String getIdentifier() throws RepositoryException {
        return fileableCmisObject.getId();
    }

    public int getIndex() throws RepositoryException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PropertyIterator getReferences() throws RepositoryException {
        return CMISPropertyIteratorImpl.EMPTY;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PropertyIterator getReferences(String name) throws RepositoryException {
        return CMISPropertyIteratorImpl.EMPTY;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PropertyIterator getWeakReferences() throws RepositoryException {
        return CMISPropertyIteratorImpl.EMPTY;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PropertyIterator getWeakReferences(String name) throws RepositoryException {
        return CMISPropertyIteratorImpl.EMPTY;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean hasNode(String relPath) throws RepositoryException {
        if (fileableCmisObject instanceof Folder) {
            Folder folder = (Folder) fileableCmisObject;
            ItemIterable<CmisObject> children = folder.getChildren();
            if (children != null && children.getHasMoreItems()) {
                Iterator<CmisObject> childIterator = children.iterator();
                while (childIterator.hasNext()) {
                    CmisObject child = childIterator.next();
                    if (child.getName().equals(relPath)) {
                        return true;
                    }
                }
                return false;
            } else {
                return false;
            }
        } else if (fileableCmisObject instanceof Document) {
            // here we need to return the content node.
            Document document = (Document) fileableCmisObject;
            if (relPath.equals(Constants.JCR_CONTENT)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean hasProperty(String relPath) throws RepositoryException {
        org.apache.chemistry.opencmis.client.api.Property<?> property = fileableCmisObject.getProperty(relPath);
        if (property != null) {
            return true;
        }
        return false;
    }

    public boolean hasNodes() throws RepositoryException {
        if (fileableCmisObject instanceof Folder) {
            Folder folder = (Folder) fileableCmisObject;
            ItemIterable<CmisObject> children = folder.getChildren();
            if (children != null && children.getHasMoreItems()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean hasProperties() throws RepositoryException {
        List<org.apache.chemistry.opencmis.client.api.Property<?>> properties = fileableCmisObject.getProperties();
        if (properties != null && properties.size() > 0) {
            return true;
        }
        return false;
    }

    public NodeType getPrimaryNodeType() throws RepositoryException {
        return getExtendedPrimaryNodeType();
    }

    public ExtendedNodeType getExtendedPrimaryNodeType() throws RepositoryException {
        ObjectType fileType = fileableCmisObject.getType();
        if (fileType.getBaseTypeId().equals(BaseTypeId.CMIS_DOCUMENT)) {
            return NodeTypeRegistry.getInstance().getNodeType(Constants.JAHIANT_FILE);
        } else if (fileType.getBaseTypeId().equals(BaseTypeId.CMIS_FOLDER)) {
            return NodeTypeRegistry.getInstance().getNodeType(Constants.JAHIANT_FOLDER);
        }
        return NodeTypeRegistry.getInstance().getNodeType(Constants.JAHIANT_FILE);
    }

    public NodeType[] getMixinNodeTypes() throws RepositoryException {
        return new NodeType[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isNodeType(String nodeTypeName) throws RepositoryException {
        return getPrimaryNodeType().isNodeType(nodeTypeName);
    }

    public void setPrimaryType(String nodeTypeName) throws NoSuchNodeTypeException, VersionException, ConstraintViolationException, LockException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addMixin(String mixinName) throws NoSuchNodeTypeException, VersionException, ConstraintViolationException, LockException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeMixin(String mixinName) throws NoSuchNodeTypeException, VersionException, ConstraintViolationException, LockException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean canAddMixin(String mixinName) throws NoSuchNodeTypeException, RepositoryException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public NodeDefinition getDefinition() throws RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Version checkin() throws VersionException, UnsupportedRepositoryOperationException, InvalidItemStateException, LockException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void checkout() throws UnsupportedRepositoryOperationException, LockException, ActivityViolationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void doneMerge(Version version) throws VersionException, InvalidItemStateException, UnsupportedRepositoryOperationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void cancelMerge(Version version) throws VersionException, InvalidItemStateException, UnsupportedRepositoryOperationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void update(String srcWorkspace) throws NoSuchWorkspaceException, AccessDeniedException, LockException, InvalidItemStateException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public NodeIterator merge(String srcWorkspace, boolean bestEffort) throws NoSuchWorkspaceException, AccessDeniedException, MergeException, LockException, InvalidItemStateException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getCorrespondingNodePath(String workspaceName) throws ItemNotFoundException, NoSuchWorkspaceException, AccessDeniedException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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

    public boolean isCheckedOut() throws RepositoryException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void restore(String versionName, boolean removeExisting) throws VersionException, ItemExistsException, UnsupportedRepositoryOperationException, LockException, InvalidItemStateException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void restore(Version version, boolean removeExisting) throws VersionException, ItemExistsException, InvalidItemStateException, UnsupportedRepositoryOperationException, LockException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void restore(Version version, String relPath, boolean removeExisting) throws PathNotFoundException, ItemExistsException, VersionException, ConstraintViolationException, UnsupportedRepositoryOperationException, LockException, InvalidItemStateException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void restoreByLabel(String versionLabel, boolean removeExisting) throws VersionException, ItemExistsException, UnsupportedRepositoryOperationException, LockException, InvalidItemStateException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public VersionHistory getVersionHistory() throws UnsupportedRepositoryOperationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Version getBaseVersion() throws UnsupportedRepositoryOperationException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Lock lock(boolean isDeep, boolean isSessionScoped) throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, InvalidItemStateException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Lock getLock() throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void unlock() throws UnsupportedRepositoryOperationException, LockException, AccessDeniedException, InvalidItemStateException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean holdsLock() throws RepositoryException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isLocked() throws RepositoryException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void followLifecycleTransition(String transition) throws UnsupportedRepositoryOperationException, InvalidLifecycleTransitionException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public String[] getAllowedLifecycleTransistions() throws UnsupportedRepositoryOperationException, RepositoryException {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isNode() {
        return true;
    }

}
