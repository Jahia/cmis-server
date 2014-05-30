package org.jahia.services.content.impl.cmis;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.jahia.services.content.nodetypes.Name;

import javax.jcr.*;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.PropertyDefinition;
import javax.jcr.version.VersionException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

/**
 * OpenCMIS repository property implementation
 */
public class CMISPropertyImpl extends CMISItemImpl implements Property {

    private Value[] values;
    private boolean multiValued = false;

    public CMISPropertyImpl(Name propertyName, CmisObject cmisObject, CMISSessionImpl cmisSessionImpl) {
        super(propertyName, cmisObject, cmisSessionImpl);
    }

    public CMISPropertyImpl(Name propertyName, CmisObject cmisObject, CMISSessionImpl cmisSessionImpl, Value value) throws RepositoryException {
        super(propertyName, cmisObject, cmisSessionImpl);
        setValue(value);
    }

    public void setValue(Value value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        this.values = new Value[1];
        this.values[0] = value;
        this.multiValued = false;
    }

    public void setValue(Value[] values) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        this.values = values;
        this.multiValued = true;
    }

    public void setValue(String value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        setValue(new CMISValueImpl(value));
    }

    public void setValue(String[] stringValues) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        Value[] values = new Value[stringValues.length];
        int i=0;
        for (String stringValue : stringValues) {
            values[i] = new CMISValueImpl(stringValue);
            i++;
        }
        setValue(values);
    }

    public void setValue(InputStream value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        ContentStreamImpl contentStreamImpl = new ContentStreamImpl();
        contentStreamImpl.setStream(value);
        contentStreamImpl.setLength(BigInteger.valueOf(-1));
        setValue(new CMISValueImpl(new CMISBinaryImpl(contentStreamImpl)));
    }

    public void setValue(Binary value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        setValue(new CMISValueImpl(value));
    }

    public void setValue(long value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        setValue(new CMISValueImpl(value));
    }

    public void setValue(double value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        setValue(new CMISValueImpl(value));
    }

    public void setValue(BigDecimal value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        setValue(new CMISValueImpl(value));
    }

    public void setValue(Calendar value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        setValue(new CMISValueImpl(value));
    }

    public void setValue(boolean value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        setValue(new CMISValueImpl(value));
    }

    public void setValue(Node value) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException {
        setValue(new CMISValueImpl(value, false));
    }

    public Value getValue() throws ValueFormatException, RepositoryException {
        if ((values == null) || (values.length == 0)) {
            throw new ItemNotFoundException("No value found for property " + getName());
        }
        return values[0];
    }

    public Value[] getValues() throws ValueFormatException, RepositoryException {
        return values;
    }

    public String getString() throws ValueFormatException, RepositoryException {
        return getValue().getString();
    }

    public InputStream getStream() throws ValueFormatException, RepositoryException {
        return getValue().getStream();
    }

    public Binary getBinary() throws ValueFormatException, RepositoryException {
        return getValue().getBinary();
    }

    public long getLong() throws ValueFormatException, RepositoryException {
        return getValue().getLong();
    }

    public double getDouble() throws ValueFormatException, RepositoryException {
        return getValue().getDouble();
    }

    public BigDecimal getDecimal() throws ValueFormatException, RepositoryException {
        return getValue().getDecimal();
    }

    public Calendar getDate() throws ValueFormatException, RepositoryException {
        return getValue().getDate();
    }

    public boolean getBoolean() throws ValueFormatException, RepositoryException {
        return getValue().getBoolean();
    }

    public Node getNode() throws ItemNotFoundException, ValueFormatException, RepositoryException {
        return cmisSessionImpl.getNodeByIdentifier(getValue().getString());
    }

    public Property getProperty() throws ItemNotFoundException, ValueFormatException, RepositoryException {
        return this;
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
        return getValue().getType();
    }

    public boolean isMultiple() throws RepositoryException {
        return multiValued;
    }
}
