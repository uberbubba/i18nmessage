package com.netscout.pfsone.i18n;

/**
 * This class enumerates all internationalized (i18n) messages used by PFS One.
 * <p>
 *      The enumerated value names are keys into the <code>i18n/messages_{locale}.xml</code>
 *      resource bundle.  Note that the values are just the ordinal value of the enum item
 *      (zero-relative, in the order it was declared).
 * </p>
 * Created by jparnell on 2016-05-14.
 */
public enum I18nMessageType {
    /**
     * No error
     */
    OK(),
    /**
     * System exception
     */
    SYSTEMEXCEPTION(),
    /**
     * Database access exception
     */
    DBACCESSEXCEPTION(),
    /**
     * An unknown error
     */
    UNKNOWNERROR();

    /**
     * Constructor for an enumerated value.
     */
    I18nMessageType() {}
}
