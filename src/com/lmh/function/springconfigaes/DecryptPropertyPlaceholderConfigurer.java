package com.lmh.function.springconfigaes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;

public class DecryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer
{
    private Resource[]                 locations;
    private DecryptPropertiesPersister propertiesPersister    = new DecryptPropertiesPersister();
    private String                     fileEncoding = "utf-8";
    private boolean                    ignoreResourceNotFound = false;
    
    @Override
    public void setLocations(Resource[] locations) {
        this.locations = locations;
    }

    @Override
    public void setFileEncoding(String encoding) {
        this.fileEncoding = encoding;
    }

    @Override
    public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound) {
        this.ignoreResourceNotFound = ignoreResourceNotFound;
    }

    @Override
    public void loadProperties(Properties props) throws IOException {
        if (this.locations != null) {
            for (int i = 0; i < this.locations.length; i++) {
                Resource location = this.locations[i];
                InputStream is = null;
                try {
                    is = location.getInputStream();
                    if (location.getFilename().endsWith(XML_FILE_EXTENSION)) {
                        this.propertiesPersister.loadFromXml(props, is);
                    } else {
                        this.propertiesPersister.doLoad(props, new InputStreamReader(is,
                            this.fileEncoding));
                    }
                } catch (IOException ex) {
                    if (this.ignoreResourceNotFound) {
                        if (logger.isWarnEnabled()) {
                            logger.warn("Could not load properties from " + location + ": "
                                + ex.getMessage());
                        }
                    } else {
                        throw ex;
                    }
                } finally {
                    if (is != null) {
                        is.close();
                    }
                }
            }
        }
    }
}