package org.jahia.services.content.impl.cmis;

import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.*;
import java.util.*;

/**
 * CMIS OpenCMIS client repository implementation
 */
public class CMISRepositoryImpl implements Repository {

    private static final transient Logger logger = LoggerFactory.getLogger(CMISRepositoryImpl.class);

    private String root;
    private String rootPath = "";
    private Map<String, String> parameters;

    private Map<String, Object> repositoryDescriptors = new HashMap<String, Object>();

    private CMISAccessControlManager accessControlManager;

    private static final Set<String> STANDARD_KEYS = new HashSet<String>() {{
        add(Repository.QUERY_FULL_TEXT_SEARCH_SUPPORTED);
        add(Repository.QUERY_JOINS);
        add(Repository.QUERY_LANGUAGES);
        add(Repository.QUERY_STORED_QUERIES_SUPPORTED);
        add(Repository.QUERY_XPATH_DOC_ORDER);
        add(Repository.QUERY_XPATH_POS_INDEX);
        add(Repository.REP_NAME_DESC);
        add(Repository.REP_VENDOR_DESC);
        add(Repository.REP_VENDOR_URL_DESC);
        add(Repository.SPEC_NAME_DESC);
        add(Repository.SPEC_VERSION_DESC);
        add(Repository.WRITE_SUPPORTED);
        add(Repository.IDENTIFIER_STABILITY);
        add(Repository.LEVEL_1_SUPPORTED);
        add(Repository.LEVEL_2_SUPPORTED);

        add(Repository.OPTION_NODE_TYPE_MANAGEMENT_SUPPORTED);
        add(Repository.NODE_TYPE_MANAGEMENT_AUTOCREATED_DEFINITIONS_SUPPORTED);
        add(Repository.NODE_TYPE_MANAGEMENT_INHERITANCE);
        add(Repository.NODE_TYPE_MANAGEMENT_MULTIPLE_BINARY_PROPERTIES_SUPPORTED);
        add(Repository.NODE_TYPE_MANAGEMENT_MULTIVALUED_PROPERTIES_SUPPORTED);
        add(Repository.NODE_TYPE_MANAGEMENT_ORDERABLE_CHILD_NODES_SUPPORTED);
        add(Repository.NODE_TYPE_MANAGEMENT_OVERRIDES_SUPPORTED);
        add(Repository.NODE_TYPE_MANAGEMENT_PRIMARY_ITEM_NAME_SUPPORTED);
        add(Repository.NODE_TYPE_MANAGEMENT_PROPERTY_TYPES);
        add(Repository.NODE_TYPE_MANAGEMENT_RESIDUAL_DEFINITIONS_SUPPORTED);
        add(Repository.NODE_TYPE_MANAGEMENT_SAME_NAME_SIBLINGS_SUPPORTED);
        add(Repository.NODE_TYPE_MANAGEMENT_VALUE_CONSTRAINTS_SUPPORTED);
        add(Repository.NODE_TYPE_MANAGEMENT_UPDATE_IN_USE_SUPORTED);
        add(Repository.OPTION_ACCESS_CONTROL_SUPPORTED);
        add(Repository.OPTION_JOURNALED_OBSERVATION_SUPPORTED);
        add(Repository.OPTION_LIFECYCLE_SUPPORTED);
        add(Repository.OPTION_LOCKING_SUPPORTED);
        add(Repository.OPTION_OBSERVATION_SUPPORTED);
        add(Repository.OPTION_NODE_AND_PROPERTY_WITH_SAME_NAME_SUPPORTED);
        add(Repository.OPTION_QUERY_SQL_SUPPORTED);
        add(Repository.OPTION_RETENTION_SUPPORTED);
        add(Repository.OPTION_SHAREABLE_NODES_SUPPORTED);
        add(Repository.OPTION_SIMPLE_VERSIONING_SUPPORTED);
        add(Repository.OPTION_TRANSACTIONS_SUPPORTED);
        add(Repository.OPTION_UNFILED_CONTENT_SUPPORTED);
        add(Repository.OPTION_UPDATE_MIXIN_NODE_TYPES_SUPPORTED);
        add(Repository.OPTION_UPDATE_PRIMARY_NODE_TYPE_SUPPORTED);
        add(Repository.OPTION_VERSIONING_SUPPORTED);
        add(Repository.OPTION_WORKSPACE_MANAGEMENT_SUPPORTED);
        add(Repository.OPTION_XML_EXPORT_SUPPORTED);
        add(Repository.OPTION_XML_IMPORT_SUPPORTED);
        add(Repository.OPTION_ACTIVITIES_SUPPORTED);
        add(Repository.OPTION_BASELINES_SUPPORTED);

    }};

    public boolean isStandardDescriptor(String key) {
        return STANDARD_KEYS.contains(key);
    }

    private void initDescriptors() {

        repositoryDescriptors.put(Repository.SPEC_VERSION_DESC, new CMISValueImpl("2.0"));
        repositoryDescriptors.put(Repository.SPEC_NAME_DESC, new CMISValueImpl("Content Repository for Java Technology API"));
        repositoryDescriptors.put(Repository.REP_VENDOR_DESC, new CMISValueImpl("Jahia"));
        repositoryDescriptors.put(Repository.REP_VENDOR_URL_DESC, new CMISValueImpl("http://www.jahia.org"));
        repositoryDescriptors.put(Repository.REP_NAME_DESC, new CMISValueImpl("The Web Integration Software"));
        repositoryDescriptors.put(Repository.REP_VERSION_DESC, new CMISValueImpl("1.0"));
        repositoryDescriptors.put(Repository.WRITE_SUPPORTED, new CMISValueImpl(true));
        repositoryDescriptors.put(Repository.IDENTIFIER_STABILITY, new CMISValueImpl(Repository.IDENTIFIER_STABILITY_SESSION_DURATION));
        repositoryDescriptors.put(Repository.NODE_TYPE_MANAGEMENT_INHERITANCE, new CMISValueImpl(Repository.NODE_TYPE_MANAGEMENT_INHERITANCE_MINIMAL));
        repositoryDescriptors.put(Repository.NODE_TYPE_MANAGEMENT_OVERRIDES_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.NODE_TYPE_MANAGEMENT_PRIMARY_ITEM_NAME_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.NODE_TYPE_MANAGEMENT_ORDERABLE_CHILD_NODES_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.NODE_TYPE_MANAGEMENT_RESIDUAL_DEFINITIONS_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.NODE_TYPE_MANAGEMENT_AUTOCREATED_DEFINITIONS_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.NODE_TYPE_MANAGEMENT_SAME_NAME_SIBLINGS_SUPPORTED, new CMISValueImpl(false));
        List<CMISValueImpl> propertyTypes = new ArrayList<CMISValueImpl>();
        propertyTypes.add(new CMISValueImpl(PropertyType.BINARY));
        propertyTypes.add(new CMISValueImpl(PropertyType.NAME));
        propertyTypes.add(new CMISValueImpl(PropertyType.PATH));
        repositoryDescriptors.put(Repository.NODE_TYPE_MANAGEMENT_PROPERTY_TYPES, propertyTypes.toArray(new CMISValueImpl[propertyTypes.size()]));
        repositoryDescriptors.put(Repository.NODE_TYPE_MANAGEMENT_MULTIVALUED_PROPERTIES_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.NODE_TYPE_MANAGEMENT_MULTIPLE_BINARY_PROPERTIES_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.NODE_TYPE_MANAGEMENT_VALUE_CONSTRAINTS_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.NODE_TYPE_MANAGEMENT_UPDATE_IN_USE_SUPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.QUERY_LANGUAGES, new CMISValueImpl[0]);
        repositoryDescriptors.put(Repository.QUERY_STORED_QUERIES_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.QUERY_FULL_TEXT_SEARCH_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.QUERY_JOINS, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.LEVEL_1_SUPPORTED, new CMISValueImpl(true));
        repositoryDescriptors.put(Repository.LEVEL_2_SUPPORTED, new CMISValueImpl(true));
        repositoryDescriptors.put(Repository.QUERY_XPATH_POS_INDEX, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.QUERY_XPATH_DOC_ORDER, new CMISValueImpl(false));

        repositoryDescriptors.put(Repository.OPTION_ACCESS_CONTROL_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_ACTIVITIES_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_BASELINES_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_JOURNALED_OBSERVATION_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_LIFECYCLE_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_LOCKING_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_NODE_AND_PROPERTY_WITH_SAME_NAME_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_NODE_TYPE_MANAGEMENT_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_OBSERVATION_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_QUERY_SQL_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_RETENTION_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_SHAREABLE_NODES_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_SIMPLE_VERSIONING_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_TRANSACTIONS_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_UNFILED_CONTENT_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_UPDATE_MIXIN_NODE_TYPES_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_UPDATE_PRIMARY_NODE_TYPE_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_VERSIONING_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_WORKSPACE_MANAGEMENT_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_XML_EXPORT_SUPPORTED, new CMISValueImpl(false));
        repositoryDescriptors.put(Repository.OPTION_XML_IMPORT_SUPPORTED, new CMISValueImpl(false));
    }

    public CMISRepositoryImpl(String root, CMISAccessControlManager accessControlManager, Map<String, String> parameters) {
        initDescriptors();
        this.root = root;
        this.accessControlManager = accessControlManager;
        this.parameters = parameters;
    }

    public String getRoot() {
        return root;
    }

    public String getRootPath() {
        return rootPath;
    }

    public String[] getDescriptorKeys() {
        return repositoryDescriptors.keySet().toArray(new String[repositoryDescriptors.size()]);
    }

    public String getDescriptor(String s) {
        Object descriptorObject = repositoryDescriptors.get(s);
        if (descriptorObject == null) {
            return null;
        }
        if (descriptorObject instanceof Value) {
            return ((Value) descriptorObject).toString();
        } else {
            throw new RuntimeException("Expected single-value value but found multi-valued property instead !");
        }
    }

    private org.apache.chemistry.opencmis.client.api.Session connect(Credentials credentials) {
        SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
        if ((credentials != null) && (!parameters.containsKey(SessionParameter.USER))) {
            if (credentials instanceof SimpleCredentials) {
                SimpleCredentials simpleCredentials = (SimpleCredentials) credentials;
                parameters.put(SessionParameter.USER, simpleCredentials.getUserID());
                parameters.put(SessionParameter.PASSWORD, new String(simpleCredentials.getPassword()));
            }
        }

        if (parameters.get(SessionParameter.REPOSITORY_ID) == null) {
            List<org.apache.chemistry.opencmis.client.api.Repository> repositories = sessionFactory.getRepositories(parameters);
            if (repositories == null || repositories.size() == 0) {
                return null;
            }
            org.apache.chemistry.opencmis.client.api.Repository repository = repositories.get(0);
            parameters.put(SessionParameter.REPOSITORY_ID, repository.getId());

        }
        org.apache.chemistry.opencmis.client.api.Session session = sessionFactory.createSession(parameters);
        return session;
    }

    public Session login(Credentials credentials, String s) throws LoginException, NoSuchWorkspaceException, RepositoryException {
        return new CMISSessionImpl(this, credentials, parameters, connect(credentials));
    }

    public Session login(Credentials credentials) throws LoginException, RepositoryException {
        return new CMISSessionImpl(this, credentials, parameters, connect(credentials));
    }

    public Session login(String s) throws LoginException, NoSuchWorkspaceException, RepositoryException {
        return new CMISSessionImpl(this, null, parameters, connect(null));
    }

    public Session login() throws LoginException, RepositoryException {
        return new CMISSessionImpl(this, null, parameters, connect(null));
    }

    public boolean isSingleValueDescriptor(String key) {
        Object repositoryDescriptor = repositoryDescriptors.get(key);
        if (repositoryDescriptor instanceof Value) {
            return true;
        } else {
            return false;
        }
    }

    public Value getDescriptorValue(String key) {
        return (Value) repositoryDescriptors.get(key);
    }

    public Value[] getDescriptorValues(String key) {
        return (Value[]) repositoryDescriptors.get(key);
    }

    protected CMISAccessControlManager getAccessControlManager() {
        return accessControlManager;
    }
}
