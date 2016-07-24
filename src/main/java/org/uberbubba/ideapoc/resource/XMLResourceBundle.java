package org.uberbubba.ideapoc.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * This class loads the XML resource bundle and provides access to the properties it contains.
 * It is called by <code>XMLResourceBundleControl</code>.
 * <p>This reference implementation is taken almost verbatim from the Oracle Docs.
 * See: <a href="http://docs.oracle.com/javase/8/docs/api/java/util/ResourceBundle.Control.html">
 *     ResourceBundle.Control</a>
 * </p>
 * Created by jparnell on 2016-05-14.
 */
class XMLResourceBundle extends ResourceBundle {
    /** Private copy of the properties loaded from the bundle. */
    private Properties props;

    /**
     * This constructor loads the bundle from the given stream.
     * @param stream  Resource file input stream.
     * @throws IOException
     */
    XMLResourceBundle(InputStream stream) throws IOException {
        props = new Properties();
        props.loadFromXML(stream);
    }

    /**
     * Return the object in the resource bundle having the given key.
     * @param key  Key into the properties map of the resource bundle.
     * @return
     */
    @Override
    protected Object handleGetObject(String key) {
        return props.getProperty(key);
    }

    /**
     * Returns all the property keys.
     * @return  Property keys as list of Enumerations.
     */
    @Override
    public Enumeration<String> getKeys() {
        Set<String> handleKeys = props.stringPropertyNames();
        return Collections.enumeration(handleKeys);
    }
}