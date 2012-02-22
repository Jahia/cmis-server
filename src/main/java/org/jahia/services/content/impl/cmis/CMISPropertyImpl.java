package org.jahia.services.content.impl.cmis;

import org.apache.chemistry.opencmis.client.api.CmisObject;

import javax.jcr.*;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.PropertyDefinition;
import javax.jcr.version.VersionException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * OpenCMIS repository property implementation
 */
public class CMISPropertyImpl extends CMISItemImpl implements Property {

    public CMISPropertyImpl(CmisObject cmisObject, CMISSessionImpl cmisSessionImpl) {
        super(cmisObject, cmisSessionImpl);
    }

    public void setValue(Value value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setValue(Value[] values) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setValue(String value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setValue(String[] values) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setValue(InputStream value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setValue(Binary value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setValue(long value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setValue(double value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setValue(BigDecimal value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setValue(Calendar value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setValue(boolean value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setValue(Node value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Value getValue() throws ValueFormatException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Value[] getValues() throws ValueFormatException, RepositoryException {
        return new Value[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getString() throws ValueFormatException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public InputStream getStream() throws ValueFormatException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Binary getBinary() throws ValueFormatException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long getLong() throws ValueFormatException, RepositoryException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public double getDouble() throws ValueFormatException, RepositoryException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public BigDecimal getDecimal() throws ValueFormatException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Calendar getDate() throws ValueFormatException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean getBoolean() throws ValueFormatException, RepositoryException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Node getNode() throws ItemNotFoundException, ValueFormatException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Property getProperty() throws ItemNotFoundException, ValueFormatException, RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long getLength() throws ValueFormatException, RepositoryException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long[] getLengths() throws ValueFormatException, RepositoryException {
        return new long[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PropertyDefinition getDefinition() throws RepositoryException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getType() throws RepositoryException {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isMultiple() throws RepositoryException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
