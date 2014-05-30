package org.jahia.services.content.impl.cmis;

import org.apache.commons.lang.ArrayUtils;
import org.apache.jackrabbit.commons.iterator.AccessControlPolicyIteratorAdapter;
import org.apache.jackrabbit.core.security.JahiaPrivilegeRegistry;
import org.jahia.exceptions.JahiaRuntimeException;
import org.jahia.services.content.JCRSessionFactory;

import javax.jcr.AccessDeniedException;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.lock.LockException;
import javax.jcr.security.*;
import javax.jcr.version.VersionException;
import java.util.HashMap;
import java.util.Map;

import static javax.jcr.security.Privilege.*;
import static javax.jcr.security.Privilege.JCR_ADD_CHILD_NODES;
import static javax.jcr.security.Privilege.JCR_REMOVE_CHILD_NODES;
import static org.jahia.api.Constants.EDIT_WORKSPACE;
import static org.jahia.api.Constants.LIVE_WORKSPACE;

/**
 * CMIS client access manager
 * @todo most of this code was copied from the VFS implementation, but it might be better to integrate with the
 * OpenCMIS client API for access control.
 */
public class CMISAccessControlManager implements AccessControlManager {

    private static final AccessControlPolicy[] POLICIES = new AccessControlPolicy[0];

    private Privilege[] registeredPrivileges;

    private Privilege[] rootNodePrivileges;

    private Privilege[] privileges;

    private Map<String, Privilege> registeredPrivilegeMap;

    private boolean readOnly;

    public CMISAccessControlManager(boolean readOnly) {
        super();
        this.readOnly = readOnly;
        try {
            init();
        } catch (RepositoryException e) {
            throw new JahiaRuntimeException(e);
        }
    }

    private void init() throws RepositoryException {
        JahiaPrivilegeRegistry registry = new JahiaPrivilegeRegistry(JCRSessionFactory.getInstance().getNamespaceRegistry());
        registeredPrivileges = registry.getRegisteredPrivileges();
        registeredPrivilegeMap = new HashMap<String, Privilege>();
        for (Privilege priv : registeredPrivileges) {
            registeredPrivilegeMap.put(priv.getName(), priv);
        }
        if (readOnly) {
            rootNodePrivileges = new Privilege[] {
                    privilegeFromName(JCR_READ + "_" + EDIT_WORKSPACE), privilegeFromName(JCR_READ + "_" + LIVE_WORKSPACE),
                    };
            privileges = rootNodePrivileges;
        } else {
            rootNodePrivileges = new Privilege[] {
                    privilegeFromName(JCR_READ + "_" + EDIT_WORKSPACE), privilegeFromName(JCR_READ + "_" + LIVE_WORKSPACE),
                    privilegeFromName(JCR_WRITE + "_" + EDIT_WORKSPACE), privilegeFromName(JCR_WRITE + "_" + LIVE_WORKSPACE),
                    privilegeFromName(JCR_ADD_CHILD_NODES + "_" + EDIT_WORKSPACE), privilegeFromName(JCR_ADD_CHILD_NODES + "_" + LIVE_WORKSPACE),
                    privilegeFromName(JCR_REMOVE_CHILD_NODES + "_" + EDIT_WORKSPACE), privilegeFromName(JCR_REMOVE_CHILD_NODES + "_" + LIVE_WORKSPACE),
                    };
            privileges = new Privilege[] {
                    privilegeFromName(JCR_READ + "_" + EDIT_WORKSPACE), privilegeFromName(JCR_READ + "_" + LIVE_WORKSPACE),
                    privilegeFromName(JCR_WRITE + "_" + EDIT_WORKSPACE), privilegeFromName(JCR_WRITE + "_" + LIVE_WORKSPACE),
                    privilegeFromName(JCR_REMOVE_NODE + "_" + EDIT_WORKSPACE), privilegeFromName(JCR_REMOVE_NODE + "_" + LIVE_WORKSPACE),
                    privilegeFromName(JCR_ADD_CHILD_NODES + "_" + EDIT_WORKSPACE), privilegeFromName(JCR_ADD_CHILD_NODES + "_" + LIVE_WORKSPACE),
                    privilegeFromName(JCR_REMOVE_CHILD_NODES + "_" + EDIT_WORKSPACE), privilegeFromName(JCR_REMOVE_CHILD_NODES + "_" + LIVE_WORKSPACE),
                    };
        }
    }

    public AccessControlPolicyIterator getApplicablePolicies(String absPath)
            throws PathNotFoundException, AccessDeniedException, RepositoryException {
        return AccessControlPolicyIteratorAdapter.EMPTY;
    }

    public AccessControlPolicy[] getEffectivePolicies(String absPath) throws PathNotFoundException,
            AccessDeniedException, RepositoryException {
        return POLICIES;
    }

    public AccessControlPolicy[] getPolicies(String absPath) throws PathNotFoundException,
            AccessDeniedException, RepositoryException {
        return POLICIES;
    }

    public Privilege[] getPrivileges(String absPath) throws PathNotFoundException,
            RepositoryException {
        return absPath.length() == 1 && "/".equals(absPath) ? rootNodePrivileges : privileges;
    }

    public Privilege[] getSupportedPrivileges(String absPath) throws PathNotFoundException,
            RepositoryException {
        return registeredPrivileges;
    }

    public boolean hasPrivileges(String absPath, Privilege[] privileges)
            throws PathNotFoundException, RepositoryException {
        if (privileges == null || privileges.length == 0) {
            return true;
        }
        boolean allowed = true;
        Privilege[] granted = getPrivileges(absPath);
        for (Privilege toCheck : privileges) {
            if (toCheck != null && !ArrayUtils.contains(granted, toCheck)) {
                allowed = false;
                break;
            }
        }

        return allowed;
    }

    public Privilege privilegeFromName(String privilegeName) throws AccessControlException,
            RepositoryException {
        return registeredPrivilegeMap.get(privilegeName);
    }

    public void removePolicy(String absPath, AccessControlPolicy policy)
            throws PathNotFoundException, AccessControlException, AccessDeniedException,
            LockException, VersionException, RepositoryException {
        // not supported
    }

    public void setPolicy(String absPath, AccessControlPolicy policy) throws PathNotFoundException,
            AccessControlException, AccessDeniedException, LockException, VersionException,
            RepositoryException {
        // not supported
    }

}
