package io.github.mavenreposs.royalcms.component.facades;

import io.github.mavenreposs.royalcms.component.gettext.GettextResource;
import org.xnap.commons.i18n.I18n;

import java.text.MessageFormat;
import java.util.Locale;

public class RC_Gettext {

    private I18n i18n;

    private volatile static RC_Gettext singleton;

    public static RC_Gettext getSingleton() {
        synchronized (RC_Gettext.class) {
            if (singleton == null) {
                singleton = new RC_Gettext();
            }
        }
        return singleton;
    }

    private RC_Gettext() {}

    public I18n getCurrentGettext() {
        return i18n;
    }

    /**
     *
     * @param basename my.package.Message
     * @param locale zh_CN
     */
    public void setCurrentGettext(String basename, Locale locale)
    {
        i18n = new I18n(basename, locale, GettextResource.class.getClassLoader());
    }

    /**
     * Returns <code>text</code> translated into the currently selected
     * language. Every user-visible string in the program must be wrapped into
     * this function.
     *
     * @param text text to translate
     * @return the translation
     * @since 0.9
     */
    public static String tr(String text) {
        return getSingleton().i18n.tr(text);
    }

    /**
     * Returns <code>text</code> translated into the currently selected
     * language.
     * <p>
     * Occurrences of {number} placeholders in text are replaced by
     * <code>objects</code>.
     * <p>
     * Invokes
     * {@link MessageFormat#format(java.lang.String, java.lang.Object[])}.
     *
     * @param text    text to translate
     * @param objects arguments to <code>MessageFormat.format()</code>
     * @return the translated text
     * @since 0.9
     */
    public static String tr(String text, Object[] objects) {
        return getSingleton().i18n.tr(text, objects);
    }


    /**
     * Convenience method that invokes {@link #tr(String, Object[])}.
     *
     * @since 0.9
     */
    public static String tr(String text, Object o1)
    {
        return getSingleton().i18n.tr(text, o1);
    }


    /**
     * Convenience method that invokes {@link #tr(String, Object[])}.
     *
     * @since 0.9
     */
    public static String tr(String text, Object o1, Object o2) {
        return getSingleton().i18n.tr(text, o1, o2);
    }

    /**
     * Convenience method that invokes {@link #tr(String, Object[])}.
     *
     * @since 0.9
     */
    public static String tr(String text, Object o1, Object o2, Object o3)
    {
        return getSingleton().i18n.tr(text, o1, o2, o3);
    }

    /**
     * Convenience method that invokes {@link #tr(String, Object[])}.
     *
     * @since 0.9
     */
    public static String tr(String text, Object o1, Object o2, Object o3, Object o4)
    {
        return getSingleton().i18n.tr(text, o1, o2, o3, o4);
    }

    /**
     * Returns the plural form for <code>n</code> of the translation of
     * <code>text</code>.
     *
     * @param text
     *            the key string to be translated.
     * @param pluralText
     *            the plural form of <code>text</code>.
     * @param n
     *            value that determines the plural form
     * @return the translated text
     * @since 0.9
     */
    public static String trn(String text, String pluralText, long n)
    {
        return getSingleton().i18n.trn(text, pluralText, n);
    }

    /**
     * Returns the plural form for <code>n</code> of the translation of
     * <code>text</code>.
     *
     * @param text
     *            the key string to be translated.
     * @param pluralText
     *            the plural form of <code>text</code>.
     * @param n
     *            value that determines the plural form
     * @param objects
     *            object args to be formatted and substituted.
     * @return the translated text
     * @since 0.9
     */
    public static String trn(String text, String pluralText, long n, Object[] objects)
    {
        return getSingleton().i18n.trn(text, pluralText, n, objects);
    }

    /**
     * Overloaded method that invokes
     * {@link #trn(String, String, long, Object[])} passing <code>Object</code>
     * arguments as an array.
     *
     * @since 0.9
     */
    public static String trn(String text, String pluralText, long n, Object o1)
    {
        return getSingleton().i18n.trn(text, pluralText, n, o1);
    }

    /**
     * Overloaded method that invokes
     * {@link #trn(String, String, long, Object[])} passing <code>Object</code>
     * arguments as an array.
     *
     * @since 0.9
     */
    public static String trn(String text, String pluralText, long n, Object o1, Object o2)
    {
        return getSingleton().i18n.trn(text, pluralText, n, o1, o2);
    }

    /**
     * Overloaded method that invokes
     * {@link #trn(String, String, long, Object[])} passing <code>Object</code>
     * arguments as an array.
     *
     * @since 0.9
     */
    public static String trn(String text, String pluralText, long n, Object o1, Object o2, Object o3)
    {
        return getSingleton().i18n.trn(text, pluralText, n, o1, o2, o3);
    }

    /**
     * Overloaded method that invokes
     * {@link #trn(String, String, long, Object[])} passing <code>Object</code>
     * arguments as an array.
     *
     * @since 0.9
     */
    public static String trn(String text, String pluralText, long n, Object o1, Object o2, Object o3, Object o4)
    {
        return getSingleton().i18n.trn(text, pluralText, n, o1, o2, o3, o4);
    }



}



