package org.jahia.services.content.impl.cmis;

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
    InputStream inputStream = null;

    public CMISBinaryImpl(InputStream inputStream) {
        // here we should copy the content of the inputstream, but where ??? Keeping it in memory is a bad idea.
        this.inputStream = inputStream;
    }

    public InputStream getStream() throws RepositoryException {
        return inputStream;
    }

    public int read(byte[] b, long position) throws IOException, RepositoryException {
        if (inputStream == null) {
            getStream();
        }
        return inputStream.read(b, (int) position, b.length);
    }

    public long getSize() throws RepositoryException {
        return -1;
    }

    public void dispose() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
