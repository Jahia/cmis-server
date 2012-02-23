package org.jahia.services.content.impl.cmis;

import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;

import javax.jcr.*;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * OpenCMIS repository value factory implementation
 */
public class CMISValueFactoryImpl implements ValueFactory {

    public Value createValue(String value) {
         return new CMISValueImpl(value);
     }

     public Value createValue(String value, int type) throws ValueFormatException {
         switch (type) {
             case PropertyType.BINARY :
                 throw new ValueFormatException("Not allowed to convert string ["+value+"] to binary value");
             case PropertyType.BOOLEAN :
                 return createValue(Boolean.parseBoolean(value));
             case PropertyType.DATE :
                 Date date = null;
                 try {
                     date = DateFormat.getInstance().parse(value);
                     Calendar calendar = Calendar.getInstance();
                     calendar.setTime(date);
                     return createValue(calendar);
                 } catch (ParseException e) {
                     throw new ValueFormatException("Error converting value [" + value +"] to calendar value");
                 }                
             case PropertyType.DECIMAL :
                 return createValue(new BigDecimal(value));
             case PropertyType.DOUBLE :
                 return createValue(Double.parseDouble(value));
             case PropertyType.LONG :
                 return createValue(Long.parseLong(value));
             case PropertyType.REFERENCE :
                 return new CMISValueImpl(value, PropertyType.REFERENCE);
             case PropertyType.STRING :
                 return createValue(value);
             case PropertyType.WEAKREFERENCE :
                 return new CMISValueImpl(value, PropertyType.WEAKREFERENCE);
         }
         throw new ValueFormatException("Unsupported value type " + type);
     }

     public Value createValue(long value) {
         return new CMISValueImpl(value);
     }

     public Value createValue(double value) {
         return new CMISValueImpl(value);
     }

     public Value createValue(BigDecimal value) {
         return new CMISValueImpl(value);
     }

     public Value createValue(boolean value) {
         return new CMISValueImpl(value);
     }

     public Value createValue(Calendar value) {
         return new CMISValueImpl(value);
     }

     public Value createValue(InputStream value) {
         ContentStreamImpl contentStreamImpl = new ContentStreamImpl();
         contentStreamImpl.setStream(value);
         contentStreamImpl.setLength(BigInteger.valueOf(-1));
         return new CMISValueImpl(new CMISBinaryImpl(contentStreamImpl));
     }

     public Value createValue(InputStream value, long size) {
         ContentStreamImpl contentStreamImpl = new ContentStreamImpl();
         contentStreamImpl.setStream(value);
         contentStreamImpl.setLength(BigInteger.valueOf(size));
         return new CMISValueImpl(new CMISBinaryImpl(contentStreamImpl));
     }

     public Value createValue(Binary value) {
         return new CMISValueImpl(value);
     }

     public Value createValue(Node value) throws RepositoryException {
         return new CMISValueImpl(value, false);
     }

     public Value createValue(Node value, boolean weak) throws RepositoryException {
         return new CMISValueImpl(value, weak);
     }

     public Binary createBinary(InputStream stream) throws RepositoryException {
         ContentStreamImpl contentStreamImpl = new ContentStreamImpl();
         contentStreamImpl.setStream(stream);
         contentStreamImpl.setLength(BigInteger.valueOf(-1));
         return new CMISBinaryImpl(contentStreamImpl);  //To change body of implemented methods use File | Settings | File Templates.
     }
    
}
