package org.jahia.services.content.impl.cmis;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import javax.jcr.*;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.retention.RetentionManager;
import javax.jcr.security.AccessControlManager;
import javax.jcr.version.VersionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.AccessControlException;
import java.util.Map;
import java.util.UUID;

/**
 * OpenCMIS repository session implementation
 */
public class CMISSessionImpl implements Session {
    private CMISRepositoryImpl repository;
    private CMISWorkspaceImpl workspace;
    private Credentials credentials;
    private Map<String,String> parameters;
    private org.apache.chemistry.opencmis.client.api.Session cmisSession;

    public CMISSessionImpl(CMISRepositoryImpl repository, Credentials credentials, Map<String,String> parameters, org.apache.chemistry.opencmis.client.api.Session cmisSession) {
        this.repository = repository;
        this.workspace = new CMISWorkspaceImpl(this);
        this.credentials = credentials;
        this.parameters = parameters;
        this.cmisSession = cmisSession;
    }

    public Repository getRepository() {
        return repository;
    }

    public String getUserID() {
        return ((SimpleCredentials) credentials).getUserID();
    }

    public Object getAttribute(String s) {
        return null;
    }

    public String[] getAttributeNames() {
        return new String[0];
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public Session impersonate(Credentials credentials) throws LoginException, RepositoryException {
        return this;
    }

    public Node getRootNode() throws RepositoryException {
        Folder rootFolder = cmisSession.getRootFolder();
        return new CMISNodeImpl(rootFolder, this);
    }

    public Node getNodeByUUID(String uuid) throws ItemNotFoundException, RepositoryException {
        try {
            UUID testUUID = UUID.fromString(uuid);
            throw new ItemNotFoundException("This repository does not support UUID as identifiers");
        } catch (IllegalArgumentException iae) {
            // this is expected, we should not be using UUIDs
        }
        return null;
    }

    public Item getItem(String path) throws PathNotFoundException, RepositoryException {
        CmisObject cmisObject = null;
        if ("/".equals(path)) {
            throw new PathNotFoundException("Can't request / on this provider");
        }
        try {
            cmisObject = cmisSession.getObjectByPath(path);
        } catch (Throwable t) {
            throw new PathNotFoundException("Couldn't find path " + path, t);
        }
        if (cmisObject instanceof Folder) {
            return new CMISNodeImpl((Folder)cmisObject, this);
        }
        if (cmisObject instanceof Document) {
            return new CMISNodeImpl((Document)cmisObject, this);
        }
        return new CMISItemImpl(cmisObject, this);
    }

    public boolean itemExists(String path) throws RepositoryException {
        CmisObject cmisObject = null;
        try {
            cmisObject = cmisSession.getObjectByPath(path);
        } catch (Throwable t) {
            return false;
        }
        return (cmisObject != null);
    }

    public void move(String source, String dest) throws ItemExistsException, PathNotFoundException, VersionException, ConstraintViolationException, LockException, RepositoryException {
    }

    public void save() throws AccessDeniedException, ItemExistsException, ConstraintViolationException, InvalidItemStateException, VersionException, LockException, NoSuchNodeTypeException, RepositoryException {

    }

    public void refresh(boolean b) throws RepositoryException {

    }

    public boolean hasPendingChanges() throws RepositoryException {
        return false;
    }

    public ValueFactory getValueFactory() throws UnsupportedRepositoryOperationException, RepositoryException {
        return new CMISValueFactoryImpl();
    }

    public void checkPermission(String s, String s1) throws AccessControlException, RepositoryException {

    }

    public ContentHandler getImportContentHandler(String s, int i) throws PathNotFoundException, ConstraintViolationException, VersionException, LockException, RepositoryException {
        return null;
    }

    public void importXML(String s, InputStream inputStream, int i) throws IOException, PathNotFoundException, ItemExistsException, ConstraintViolationException, VersionException, InvalidSerializedDataException, LockException, RepositoryException {

    }

    public void exportSystemView(String s, ContentHandler contentHandler, boolean b, boolean b1) throws PathNotFoundException, SAXException, RepositoryException {

    }

    public void exportSystemView(String s, OutputStream outputStream, boolean b, boolean b1) throws IOException, PathNotFoundException, RepositoryException {

    }

    public void exportDocumentView(String s, ContentHandler contentHandler, boolean b, boolean b1) throws PathNotFoundException, SAXException, RepositoryException {

    }

    public void exportDocumentView(String s, OutputStream outputStream, boolean b, boolean b1) throws IOException, PathNotFoundException, RepositoryException {

    }

    public void setNamespacePrefix(String s, String s1) throws NamespaceException, RepositoryException {

    }

    public String[] getNamespacePrefixes() throws RepositoryException {
        return new String[0];
    }

    public String getNamespaceURI(String s) throws NamespaceException, RepositoryException {
        return null;
    }

    public String getNamespacePrefix(String s) throws NamespaceException, RepositoryException {
        return null;
    }

    public void logout() {

    }

    public boolean isLive() {
        return false;
    }

    public void addLockToken(String s) {

    }

    public String[] getLockTokens() {
        return new String[0];
    }

    public void removeLockToken(String s) {

    }

    public Node getNodeByIdentifier(String id) throws ItemNotFoundException, RepositoryException {
        return getNodeByUUID(id);
    }

    public Node getNode(String absPath) throws PathNotFoundException, RepositoryException {
        return (Node) getItem(absPath);
    }

    public Property getProperty(String absPath) throws PathNotFoundException, RepositoryException {
        return (Property) getItem(absPath);
    }

    public boolean nodeExists(String absPath) throws RepositoryException {
        return itemExists(absPath);
    }

    public boolean propertyExists(String absPath) throws RepositoryException {
        return itemExists(absPath);
    }

    public void removeItem(String absPath) throws VersionException, LockException, ConstraintViolationException, AccessDeniedException, RepositoryException {
        getItem(absPath).remove();
    }

    public boolean hasPermission(String absPath, String actions) throws RepositoryException {
        // TODO implement me
        return false;
    }

    public boolean hasCapability(String s, Object o, Object[] objects) throws RepositoryException {
        // TODO implement me
        return false;
    }

    public AccessControlManager getAccessControlManager() throws UnsupportedRepositoryOperationException, RepositoryException {
        return repository.getAccessControlManager();
    }

    public RetentionManager getRetentionManager() throws UnsupportedRepositoryOperationException, RepositoryException {
        return null;
    }

}
