package org.jahia.services.content.impl.cmis;

import org.apache.jackrabbit.rmi.server.ServerAdapterFactory;
import org.jahia.services.content.JCRStoreProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Workspace;
import java.rmi.Naming;

/**
 * CMIS Content Store Provider implementation
 */
public class CMISContentStoreProvider extends JCRStoreProvider {

    private static final Logger logger = LoggerFactory.getLogger(CMISContentStoreProvider.class);

    private CMISAccessControlManager accessControlManager;

    private boolean readOnly;

    private Repository repo;

    private String root;

    public Repository getRepository(){
        if (repo == null) {
            synchronized (CMISContentStoreProvider.class) {
                if (repo == null) {
                    accessControlManager = new CMISAccessControlManager(readOnly);
                    repo = new CMISRepositoryImpl(root, accessControlManager);
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
