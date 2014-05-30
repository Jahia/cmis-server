package org.jahia.services.content.impl.cmis;

import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.commons.vfs.FileContent;
import org.apache.commons.vfs.FileSystemException;

import javax.jcr.Binary;
import javax.jcr.RepositoryException;
import java.io.IOException;
import java.io.InputStream;

/**
 * OpenCMIS repository binary implementation
 */
public class CMISBinaryImpl implements Binary {
    ContentStream contentStream = null;

    public CMISBinaryImpl(ContentStream contentStream) {
        this.contentStream = contentStream;
    }

    public InputStream getStream() throws RepositoryException {
        return contentStream.getStream();
    }

    public int read(byte[] b, long position) throws IOException, RepositoryException {
        return contentStream.getStream().read(b, (int) position, b.length);
    }

    public long getSize() throws RepositoryException {
        return contentStream.getLength();
    }

    public void dispose() {
    }
}
