package org.uberbubba.ideapoc.resource

import org.uberbubba.ideapoc.i18n.I18nMessage
import org.uberbubba.ideapoc.i18n.I18nMessageType
import spock.lang.*

/**
 * Created by jparnell on 2016-05-15.
 */
class XMLResourceBundleTest extends Specification {
    def resourcebase = "i18n/messages"

    // Print the message
    def printMessage(String tag, String lang, String msg) {
        System.out.println(
                String.format(
                        "\n===== %s\n\tlang:\t%s\n\tmsg:\t%s\n",
                        tag, lang, msg))
    }

    def "HandleGetObject_I18nMessage"(String lang, I18nMessageType type, boolean valid) {
        expect:
        def msg = new I18nMessage(type, "Bogus error")
        def locale = new Locale(lang)
        def str =  msg.format(locale)
        // printMessage("HandleGetObject_I18nMessage", lang, str)
        // Causes failures^ why?
        System.out.println(
                String.format(
                        "\n===== %s\n\tlang:\t%s\n\tmsg:\t%s\n",
                        "HandleGetObject_I18nMessage", lang, msg))
        valid == (str.length() > 0)
        where:
        lang | type | valid
        "en_US" | I18nMessageType.SYSTEMEXCEPTION | true
        "de_DE" | I18nMessageType.SYSTEMEXCEPTION | true
    }

    def "GetKeys"() {

    }
}
