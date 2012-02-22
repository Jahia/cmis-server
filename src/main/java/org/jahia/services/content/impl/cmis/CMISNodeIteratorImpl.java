package org.jahia.services.content.impl.cmis;

import org.jahia.services.content.RangeIteratorImpl;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import java.util.Iterator;

/**
 * OpenCMIS repository node iterator implementation
 */
public class CMISNodeIteratorImpl extends RangeIteratorImpl implements NodeIterator {
    public CMISNodeIteratorImpl(Iterator<?> iterator, long size) {
        super(iterator, size);
    }

    public Node nextNode() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
