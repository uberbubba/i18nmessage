package org.uberbubba.ideapoc.i18n

import spock.lang.*

/**
 * Created by jparnell on 2016-05-15.
 */
class I18nSystemExceptionTest extends Specification {

    // Print the exception
    def printException(String tag, I18nSystemException e) {
        def msg = e.getI18nMessage()
        System.out.println(
                String.format(
                        "\n===== %s\n\texception:\t%s\n\tstring:\t%s\n\ti18n:\t%s\n" +
                        "\tlocalized:\t%s\n" +
                        "\tby locale:\t%s\n",
                        tag, e, e.getMessage(), msg, e.getLocalizedMessage(),
                        e.getLocalizedMessageByLocale(new Locale("de_DE"))
                ))
    }

    // Test the default constructor
    def "GetI18nMessage_Default"() {
    when:
    def e = new I18nSystemException()
    def msg = e.getI18nMessage()
    printException("GetI18nMessage_Default", e)

    then:
    msg.getType() == I18nMessageType.SYSTEMEXCEPTION && msg.getArgs().length == 0
    }

    // Test the string constructor
    def "GetI18nMessage_String"() {
        when:
        def str = "OMG WTF happened?"
        def e = new I18nSystemException(str)
        def msg = e.getI18nMessage()
        printException("GetI18nMessage_String", e)

        then:
        msg.getType() == I18nMessageType.SYSTEMEXCEPTION &&
                msg.getArgs().length == 0 &&
                e.getMessage().equals(str)
    }

    // Test the type constructor
    def "GetI18nMessage_Type"() {
        when:
        def type = I18nMessageType.DBACCESSEXCEPTION
        def e = new I18nSystemException(type)
        def msg = e.getI18nMessage()
        printException("GetI18nMessage_Type", e)

        then:
        msg.getType() == I18nMessageType.DBACCESSEXCEPTION &&
                msg.getArgs().length == 0
    }


    // Test the string plus type constructor
    def "GetI18nMessage_String_Type"() {
        when:
        def str = "OMG WTF happened?"
        def type = I18nMessageType.DBACCESSEXCEPTION
        def e = new I18nSystemException(str, type)
        def msg = e.getI18nMessage()
        printException("GetI18nMessage_String_Type", e)

        then:
        msg.getType() == I18nMessageType.DBACCESSEXCEPTION &&
                msg.getArgs().length == 0 &&
                e.getMessage().equals(str)
    }

    // Test the type plus args constructor
    def "GetI18nMessage_Type_Args"() {
        when:
        def type = I18nMessageType.DBACCESSEXCEPTION
        def e = new I18nSystemException(type, "No DB for you")
        def msg = e.getI18nMessage()
        printException("GetI18nMessage_Type_Args", e)

        then:
        msg.getType() == I18nMessageType.DBACCESSEXCEPTION &&
                msg.getArgs().length == 1
    }

    // Test the string constructor
    def "GetI18nMessage_String_Type_Args"() {
        when:
        def str = "OMG WTF happened?"
        def type = I18nMessageType.DBACCESSEXCEPTION
        def e = new I18nSystemException(str, type, "You do not deserve", "to look at this data")
        def msg = e.getI18nMessage()
        printException("GetI18nMessage_String_Type_Args", e)

        then:
        msg.getType() == I18nMessageType.DBACCESSEXCEPTION &&
                msg.getArgs().length == 2 &&
                e.getMessage().equals(str)
    }

    def "GetLocalizedMessage"() {
        when:
        def str = "OMG WTF happened?"
        def type = I18nMessageType.DBACCESSEXCEPTION
        def e = new I18nSystemException(str, type, "You do not deserve", "to look at this data")
        def msg = e.getI18nMessage()
        printException("GetLocalizedMessage", e)

        then:
        msg.getType() == I18nMessageType.DBACCESSEXCEPTION &&
                msg.getArgs().length == 2 &&
                e.getLocalizedMessage().equals(msg.toString())
    }

    def "GetLocalizedMessageByLocale"() {
        when:
        def str = "OMG WTF happened?"
        def type = I18nMessageType.DBACCESSEXCEPTION
        def e = new I18nSystemException(str, type, "You do not deserve", "to look at this data")
        def msg = e.getI18nMessage()
        printException("GetLocalizedMessage", e)

        then:
        msg.getType() == I18nMessageType.DBACCESSEXCEPTION &&
                msg.getArgs().length == 2 &&
                e.getLocalizedMessageByLocale(new Locale("de_DE"))
                        .equals(msg.format(new Locale("de_DE")))

    }
}
