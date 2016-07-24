package com.netscout.pfsone.i18n

import spock.lang.*

/**
 * Created by jparnell on 2016-05-14.
 */
class I18nMessageTest extends Specification {

    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {}     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method

    def "testing toString() in default locale"() {
        when:
        def i18n = new I18nMessage(I18nMessageType.SYSTEMEXCEPTION, "Some error occurred here.")
        def ret = i18n.toString()
        System.out.println("ret: "+ret)
        then:
        ret.length() > 0
    }
    def "testing toString() in de_DE locale"() {
        when:
        def i18n = new I18nMessage(I18nMessageType.SYSTEMEXCEPTION, "Some error occurred here.")
        def ret = i18n.format(new Locale("de_DE"))
        System.out.println("ret: "+ret)
        then:
        ret.length() > 0
    }
    def "testing missing exception type"() {
        when:
        def i18n = new I18nMessage(I18nMessageType.UNKNOWNERROR, "File not found.")
        def ret = i18n.toString()
        System.out.println("ret: "+ret)
        then:
        ret.length() > 0
    }

    def "testing type getter"(I18nMessageType type, I18nMessageType expected,
                              boolean result) {
        expect:
        def i18n = new I18nMessage(type)
        result == (i18n.getType() == expected)
        where:
        type | expected | result
        I18nMessageType.SYSTEMEXCEPTION | I18nMessageType.SYSTEMEXCEPTION | true
        I18nMessageType.DBACCESSEXCEPTION | I18nMessageType.DBACCESSEXCEPTION | true
        I18nMessageType.DBACCESSEXCEPTION | I18nMessageType.UNKNOWNERROR | false
    }

    def "testing type setter"(I18nMessageType type, I18nMessageType newtype,
                              boolean result) {
        expect:
        def i18n = new I18nMessage(type)
        i18n.setType(newtype)
        result == (i18n.getType() == newtype)
        where:
        type | newtype | result
        I18nMessageType.SYSTEMEXCEPTION | I18nMessageType.DBACCESSEXCEPTION | true
        I18nMessageType.SYSTEMEXCEPTION | I18nMessageType.UNKNOWNERROR | true
    }

    def "testing args getter"(I18nMessageType type, Object [] args,
                              boolean result) {
        expect:
        def i18n = new I18nMessage(type, args)
        def oldargs = i18n.getArgs()
        result == Arrays.equals(args, oldargs)
        where:
        type | args | result
        I18nMessageType.SYSTEMEXCEPTION | [ "howdy", "doody" ] | true
        I18nMessageType.SYSTEMEXCEPTION | [ "howdy", new Integer(1) ] | true
        I18nMessageType.SYSTEMEXCEPTION | [ "howdy", new Boolean(false) ] | true
    }

    def "testing args setter"(I18nMessageType type, Object [] args,
                              boolean result) {
        expect:
        def i18n = new I18nMessage(type)
        i18n.setArgs(args)
        def oldargs = i18n.getArgs()
        result == Arrays.equals(args, oldargs)
        System.out.println("Type: " + type.ordinal() + " = " + type)
        where:
        type | args | result
        I18nMessageType.OK | [ ] | true
        I18nMessageType.SYSTEMEXCEPTION | [ "howdy", "doody" ] | true
        I18nMessageType.DBACCESSEXCEPTION | [ "howdy", new Integer(1) ] | true
        I18nMessageType.UNKNOWNERROR | [ "howdy", new Boolean(false) ] | true
    }

}
