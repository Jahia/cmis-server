package org.jahia.services.content.impl.cmis;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.jahia.services.content.nodetypes.Name;

import javax.jcr.*;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;

/**
 * OpenCMIS repository item implementation
 */
public class CMISItemImpl implements Item {

    protected CMISSessionImpl cmisSessionImpl;
    protected CmisObject cmisObject;
    protected Name name;

    public CMISItemImpl(Name name, CmisObject cmisObject, CMISSessionImpl cmisSessionImpl) {
        this.cmisSessionImpl = cmisSessionImpl;
        this.cmisObject = cmisObject;
        this.name = name;
    }


    public String getPath() throws RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getName() throws RepositoryException {
        return name.getLocalName();
    }

    public Item getAncestor(int depth) throws ItemNotFoundException, AccessDeniedException, RepositoryException {
        throw new ItemNotFoundException("No valid ancestor for " + getName());
    }

    public Node getParent() throws ItemNotFoundException, AccessDeniedException, RepositoryException {
        throw new ItemNotFoundException("No valid parent for " + getName());
    }

    public int getDepth() throws RepositoryException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Session getSession() throws RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isNode() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isNew() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isModified() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isSame(Item otherItem) throws RepositoryException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void accept(ItemVisitor visitor) throws RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void save() throws AccessDeniedException, ItemExistsException, ConstraintViolationException, InvalidItemStateException, ReferentialIntegrityException, VersionException, LockException, NoSuchNodeTypeException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void refresh(boolean keepChanges) throws InvalidItemStateException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void remove() throws VersionException, LockException, ConstraintViolationException, AccessDeniedException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
