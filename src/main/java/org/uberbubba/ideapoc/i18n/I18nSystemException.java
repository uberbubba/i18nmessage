
package org.uberbubba.ideapoc.i18n;

import java.util.Locale;

/**
 * Internationalized (i18n) system exception class.
 * <p>
 *     Use this class to internationalize an exception message.
 * </p>
 * Created by jparnell on 2016-05-14.
 */
public class I18nSystemException extends Exception {

    /** The i18n message associated with this exception. */
    private I18nMessage message;

    /**
     * Default constructor.  Just build it with SYSTEMEXCEPTION.
     */
    I18nSystemException() {
        super();
        this. message = new I18nMessage(I18nMessageType.SYSTEMEXCEPTION);
    }

    /**
     * String constructor.  Just build it with SYSTEMEXCEPTION and message.
     *
     * @param message  Exception message (not i18n)
     */
    I18nSystemException(String message) {
        super(message);
        this.message = new I18nMessage(I18nMessageType.SYSTEMEXCEPTION);
    }

    /**
     * Type constructor.  Build an exception with the given i18n type.
     * @param type  Type to use in the i18n message.
     */
    I18nSystemException(I18nMessageType type) {
        super();
        this.message = new I18nMessage(type);
    }

    /**
     * String plus type constructor.  Build an exception with the given
     * string and i18n type.
     * @param message  Default (non-i18n) message string.
     * @param type  Type to use in the i18n message.
     */
    I18nSystemException(String message, I18nMessageType type) {
        super(message);
        this.message = new I18nMessage(type);
    }

    /**
     * Type plus format args constructor.  Build an exception with the given i18n type
     * and format arguments.
     * @param type  Type to use in the i18n message.
     * @param args  Format arguments used to format the i18n message.
     */
    I18nSystemException(I18nMessageType type, Object ... args) {
        super();
        this.message = new I18nMessage(type, args);
    }

    /**
     * String plus type plus format args constructor.
     * Build an exception with the given i18n type and format arguments.  In addition,
     * set the non-i18n exception string to the given message.
     *
     * @param message  Default (non-i18n) message string.
     * @param type  Type to use in the i18n message.
     * @param args  Format arguments used to format the i18n message.
     */
    I18nSystemException(String message, I18nMessageType type, Object ... args) {
        super(message);
        this.message = new I18nMessage(type, args);
    }

    /**
     * Return the i18n message object associated with this exception.
     * @return  The i18n message.
     */
    public I18nMessage getI18nMessage() { return message; };

    /**
     * Get the localized message from the exception's i18n message.
     * @return  A formatted string, using the server default locale.
     */
    @Override
    public String getLocalizedMessage() {
        return message.toString();
    }

    /**
     * Get the localized message from the exception's i18n message.
     * @param locale  Locale to use when formatting message.
     * @return  A formatted string, using the given locale.
     */
    public String getLocalizedMessageByLocale(Locale locale) {
        return message.format(locale);
    }

}
