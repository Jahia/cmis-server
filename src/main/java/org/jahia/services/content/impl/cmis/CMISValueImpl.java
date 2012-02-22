package org.jahia.services.content.impl.cmis;

import javax.jcr.*;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * OpenCMIS repository value implementation
 */
public class CMISValueImpl implements Value {

    Object value;
    int type;

    public CMISValueImpl(String value) {
        this(value, PropertyType.STRING);
    }

    public CMISValueImpl(Binary value) {
        this(value, PropertyType.BINARY);
    }

    public CMISValueImpl(long value) {
        this(new Long(value), PropertyType.LONG);
    }

    public CMISValueImpl(double value) {
        this(new Double(value), PropertyType.DOUBLE);
    }

    public CMISValueImpl(BigDecimal value) {
        this(value, PropertyType.DECIMAL);
    }

    public CMISValueImpl(Calendar value) {
        this(value, PropertyType.DATE);
    }

    public CMISValueImpl(boolean value) {
        this(new Boolean(value), PropertyType.BOOLEAN);
    }

    public CMISValueImpl(Node value, boolean weakReference) throws RepositoryException {
        if (weakReference) {
            this.value = value.getIdentifier();
            this.type = PropertyType.WEAKREFERENCE;
        } else {
            this.value = value.getIdentifier();
            this.type = PropertyType.REFERENCE;
        }
    }

    public CMISValueImpl(Object value, int type) {
        this.value = value;
        this.type = type;
    }

    public String getString() throws ValueFormatException, IllegalStateException, RepositoryException {
        return value.toString();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public InputStream getStream() throws RepositoryException {
        return (InputStream) value;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Binary getBinary() throws RepositoryException {
        return (Binary) value;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long getLong() throws ValueFormatException, RepositoryException {
        return ((Long) value).longValue();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public double getDouble() throws ValueFormatException, RepositoryException {
        return ((Double) value).doubleValue();
    }

    public BigDecimal getDecimal() throws ValueFormatException, RepositoryException {
        return (BigDecimal) value;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Calendar getDate() throws ValueFormatException, RepositoryException {
        return (Calendar) value;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean getBoolean() throws ValueFormatException, RepositoryException {
        return ((Boolean) value).booleanValue();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getType() {
        return type;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
