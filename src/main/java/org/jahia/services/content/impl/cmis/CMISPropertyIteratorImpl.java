package org.jahia.services.content.impl.cmis;

import org.apache.commons.collections.IteratorUtils;
import org.jahia.services.content.JCRPropertyWrapperImpl;
import org.jahia.services.content.RangeIteratorImpl;

import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import java.util.*;

/**
 * OpenCMIS repository property iterator implementation
 */
public class CMISPropertyIteratorImpl extends RangeIteratorImpl implements PropertyIterator, Map {

    public static final CMISPropertyIteratorImpl EMPTY = new CMISPropertyIteratorImpl(IteratorUtils.EMPTY_ITERATOR, 0);
    private Map map = null;

    public CMISPropertyIteratorImpl(List<Property> list, long size) {
        super(list.iterator(), size);
        map = new HashMap();
        for (Property pi : list) {
            try {
                if (pi.isMultiple()) {
                    map.put(pi.getName(),pi.getValues());
                }
                else {
                    map.put(pi.getName(),pi.getValue());
                }
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
        }
    }

    public CMISPropertyIteratorImpl(Iterator iterator, long size) {
        super(iterator, size);
    }

    public Property nextProperty() {
        return (Property) next();
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean containsKey(Object o) {
        return map.containsKey(o);
    }

    public boolean containsValue(Object o) {
        return map.containsValue(o);
    }

    public Object get(Object o) {
        return map.get(o);
    }

    public Object put(Object o, Object o1) {
        return map.put(o, o1);
    }

    public Object remove(Object o) {
        return map.remove(o);
    }

    public void putAll(Map map) {
        this.map.putAll(map);
    }

    public void clear() {
        map.clear();
    }

    public Set keySet() {
        return map.keySet();
    }

    public Collection values() {
        return map.values();
    }

    public Set entrySet() {
        return map.entrySet();
    }

    public boolean equals(Object o) {
        return map.equals(o);
    }

    public int hashCode() {
        return map.hashCode();
    }
}
