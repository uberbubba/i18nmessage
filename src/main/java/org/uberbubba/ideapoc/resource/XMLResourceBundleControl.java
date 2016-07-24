package org.uberbubba.ideapoc.resource;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class provides the ResourceBundle.Control support needed to load XML resource bundles.
 * It calls <code>XMLResourceBundleControl</code> to load the properties for the given locale.
 * <p>This reference implementation is taken almost verbatim from the Oracle Docs.
 * See: <a href="http://docs.oracle.com/javase/8/docs/api/java/util/ResourceBundle.Control.html">
 *     ResourceBundle.Control</a>
 * </p>

 * Created by jparnell on 2016-05-14.
 */
public class XMLResourceBundleControl extends ResourceBundle.Control {
    /** Resource type: XML */
    private static String XML = "xml";

    /**
     * Returns only one available format for our XML bundles: XML
     * @param baseName  Bundle base name (not used here)
     * @return  A list of formats for bundles.
     */
    @Override
    public List<String> getFormats(String baseName) {
        return Collections.singletonList(XML);
    }

    /**
     * Instantiate the bundle using the name, locale, format.  Use the loader provided.
     * @param baseName  Bundle basename (name prefix)
     * @param locale    Locale to load
     * @param format    Bundle format
     * @param loader    Bundle loader
     * @param reload    Reload the bundle.
     * @return  A resource bundle with the Properties we want.
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    @Override
    public ResourceBundle newBundle(String baseName, Locale locale, String format,
                                    ClassLoader loader, boolean reload)
            throws IllegalAccessException, InstantiationException, IOException {

        if ((baseName == null) || (locale == null) || (format == null) || (loader == null)) {
            throw new NullPointerException();
        }
        ResourceBundle bundle = null;
        if (!format.equals(XML)) {
            return null;
        }

        String bundleName = toBundleName(baseName, locale);
        String resourceName = toResourceName(bundleName, format);
        URL url = loader.getResource(resourceName);
        if (url == null) {
            return null;
        }
        URLConnection connection = url.openConnection();
        if (connection == null) {
            return null;
        }
        if (reload) {
            connection.setUseCaches(false);
        }
        InputStream stream = connection.getInputStream();
        if (stream == null) {
            return null;
        }
        BufferedInputStream bis = new BufferedInputStream(stream);
        bundle = new XMLResourceBundle(bis);
        bis.close();

        return bundle;
    }
}
