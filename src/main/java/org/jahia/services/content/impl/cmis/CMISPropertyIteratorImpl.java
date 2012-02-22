package org.jahia.services.content.impl.cmis;

import org.jahia.services.content.RangeIteratorImpl;

import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import java.util.Iterator;

/**
 * OpenCMIS repository property iterator implementation
 */
public class CMISPropertyIteratorImpl extends RangeIteratorImpl implements PropertyIterator {
    public CMISPropertyIteratorImpl(Iterator<?> iterator, long size) {
        super(iterator, size);
    }

    public Property nextProperty() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
