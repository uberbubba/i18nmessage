package org.uberbubba.ideapoc.i18n;

import org.uberbubba.ideapoc.resource.XMLResourceBundleControl;
import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Internationalized (i18n) message class.
 * <p>
 *     Produce a message backed by an XML resource bundle.  Each message has a type and
 *     optional format arguments.  The caller can specify the locale for formatting or use the
 *     default locale of the server.
 * </p>
 * <p>
 *     The base name for the resource bundle has the following format:<br><br>
 *         <code>i18n/messages_{locale}.xml</code>
 * </p>
 * Created by jparnell on 2016-05-14.
 */
public class I18nMessage {

    /** Message type enumeration value for this message. */
    private I18nMessageType type;
    /** Message formatting arguments. */
    private Object[] args;
    /** Base name for the resource bundle. */
    private final String resourcebase = "i18n/messages";
    private final Logger logger = Logger.getLogger(I18nMessage.class);

    /**
     * Constructor for a message with no arguments
     * @param type  Message type
     */
    I18nMessage(I18nMessageType type) {
        this.type = type;
        this.args = new Object[]{};
    }

    /**
     * Constructor for a message with arguments.
     * @param type  Message type.
     * @param args  Message formatting arguments.
     */
    I18nMessage(I18nMessageType type, Object ... args){
        this.type = type;
        this.args = args;
    }

    // ===================
    // Getters and setters
    // ===================

    /**
     * Get the message type
     * @return  The message type
     */
    public I18nMessageType getType() {
        return type;
    }

    /**
     * Set the message type.
     * @param type  The message type to use on this message.
     */
    public void setType(I18nMessageType type) {
        this.type = type;
    }

    /**
     * Get the arguments attached to this message.
     * @return  An array of Object containing the arguments.
     */
    public Object[] getArgs() {
        return args;
    }

    /**
     * Set the format arguments for this message.
     * @param args  List of arguments to be used when formatting this message.
     */
    public void setArgs(Object ... args) {
        this.args = args;
    }

    /**
     * The venerable toString() method.  Use the default server locale for formatting
     * the message.
     * @return  A formatted string.
     */
    @Override
    public String toString() {
        return format(Locale.getDefault());
    }

    /**
     * Format the message using the given locale.
     * @param locale  Locale to use when formatting the message.
     * @return  A formatted string.
     */
    public String format(Locale locale) {
        return format(type, locale, args);
    }

    /**
     * Format a message using the given message type, locale, and arguments.
     *
     * @param type    Message type
     * @param locale  Target locale
     * @param args    Optional arguments to formatter.
     * @return  A formatted string.
     */
    public String format(I18nMessageType type, Locale locale, Object ... args) {
        try {
            ResourceBundle i18n_bundle =
                    ResourceBundle.getBundle(resourcebase, locale, new XMLResourceBundleControl());
            return MessageFormat.format(i18n_bundle.getString(type.toString()), args);
        } catch (Exception e) {
            logger.error("Error processing message: "+type+" exception: "+e);
            e.printStackTrace();
            return "Message type: " + type;
        }
    }
}
