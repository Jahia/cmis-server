package org.jahia.services.content.impl.cmis;

import org.apache.chemistry.opencmis.client.api.FileableCmisObject;
import org.apache.commons.collections.IteratorUtils;
import org.jahia.services.content.RangeIteratorImpl;
import org.jahia.services.content.nodetypes.Name;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import java.util.Arrays;

/**
 * OpenCMIS repository node iterator implementation
 */
public class CMISNodeIteratorImpl extends RangeIteratorImpl implements NodeIterator {

    public static CMISNodeIteratorImpl EMPTY = new CMISNodeIteratorImpl(null);

    private CMISSessionImpl cmisSessionImpl;

    public CMISNodeIteratorImpl(CMISSessionImpl cmisSessionImpl, FileableCmisObject... items) {
        super(items != null && items.length > 0 ? Arrays.asList(items).iterator() : IteratorUtils.EMPTY_ITERATOR,
                items != null && items.length > 0 ? items.length : 0);
        this.cmisSessionImpl = cmisSessionImpl;
    }

    public Node nextNode() {
        FileableCmisObject object = (FileableCmisObject) next();
        return new CMISNodeImpl(new Name(object.getName(), "", ""), object, cmisSessionImpl);
    }
}
