package selmok.datagenerator.dataproviders;

import selmok.datagenerator.customizers.SingletonLocaleContext;
import selmok.datagenerator.services.RandomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Provides a base class for other faker data providers with common functionality.
 *
 * @author Cas
 */
public class BaseProvider {
    private SingletonLocaleContext locale;
    private RandomService random;

    private final Logger ISSUE_LOG = LogManager.getLogger(this);

    /**
     * Common constructor for all faker data providers.
     * Accepts and initializes object with SingletonLocaleContext in order to
     * define country and language for which data will be generated.
     * (e.g. for US + en -> US names in english; for UA + en -> ukrainian names in english)
     * Accepts as well RandomService to define desired Random settings (e.g. specific date seed)
     */
    public BaseProvider(SingletonLocaleContext locale, RandomService random) {
        this.locale = locale;
        this.random = random;
    }

    /**
     * Returns SingletonLocale, initialized for this BaseProvider object
     */
    public SingletonLocaleContext getLocale() {
        return locale;
    }

    /**
     * Returns RandomService, initialized for this BaseProvider object
     */
    public RandomService getRandom() {
        return random;
    }

    /**
     * Returns Logger, initialized for BaseProvider and inherited by other providers.
     */
    public Logger getISSUE_LOG() {
        return ISSUE_LOG;
    }
}
