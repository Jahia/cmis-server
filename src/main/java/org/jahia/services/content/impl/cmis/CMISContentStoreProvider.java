package org.jahia.services.content.impl.cmis;

import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.jackrabbit.rmi.server.ServerAdapterFactory;
import org.jahia.services.content.JCRStoreProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Workspace;
import java.rmi.Naming;
import java.util.HashMap;
import java.util.Map;

/**
 * CMIS Content Store Provider implementation
 */
public class CMISContentStoreProvider extends JCRStoreProvider {

    private static final Logger logger = LoggerFactory.getLogger(CMISContentStoreProvider.class);

    private CMISAccessControlManager accessControlManager;

    private boolean readOnly;

    private Repository repo;

    private String root;

    private Map<String,String> parameters = new HashMap<String,String>();

    private Map<String,String> propertyMapping = new HashMap<String,String>();

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public void setPropertyMapping(Map<String, String> propertyMapping) {
        this.propertyMapping = propertyMapping;
    }

    public Repository getRepository(){
        if (repo == null) {
            synchronized (CMISContentStoreProvider.class) {
                if (repo == null) {
                    accessControlManager = new CMISAccessControlManager(readOnly);
                    repo = new CMISRepositoryImpl(root, accessControlManager, parameters);
                    if (rmibind != null) {
                        try {
                            Naming.rebind(rmibind, new ServerAdapterFactory().getRemoteRepository(repo));
                        } catch (Exception e) {
                            logger.warn("Unable to bind remote JCR repository to RMI using "
                                    + rmibind, e);
                        }
                    }
                }
            }
        }
        return repo;
    }

    public String getRoot() {
        return root;
    }

    public boolean isExportable() {
        return false;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    protected void registerNamespaces(Workspace workspace) throws RepositoryException {
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public void setRoot(String root) {
        this.root = root;
    }

}
