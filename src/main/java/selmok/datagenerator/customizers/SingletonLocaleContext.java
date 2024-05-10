package selmok.datagenerator.customizers;

import java.util.HashMap;

/**
 * This is the Singleton implementation for LocaleContext.class.
 * It ensures that only one SingletonLocaleContext exists per
 * LocaleContext. Therefore, same LocaleContext is always used
 * in the program without being re-initialized and re-created.
 */
public class SingletonLocaleContext {
    private LocaleContext locale = null;

    private static  final HashMap<LocaleContext, SingletonLocaleContext> EXISTENT_LOCALES = new HashMap<>();

    /**
     * Private constructor to implement Singleton pattern correspondingly.
     */
    private SingletonLocaleContext(LocaleContext locale){
        this.locale = locale;
    }

    /**
     * Retrieves or creates a SingletonLocaleContext instance associated with the provided LocaleContext.
     *
     * @param locale The LocaleContext for which the SingletonLocaleContext instance is to be retrieved or created.
     * @return The SingletonLocaleContext instance associated with the provided LocaleContext.
     */
    public static SingletonLocaleContext get(LocaleContext locale){

        if(locale == null){
            return null;
        }

         SingletonLocaleContext localeService = EXISTENT_LOCALES.get(locale);

         if(localeService == null){
             localeService = new SingletonLocaleContext(locale);
             EXISTENT_LOCALES.put(locale, localeService);
         }

         return localeService;
    }

    public LocaleContext getLocale() {
        return locale;
    }
}
