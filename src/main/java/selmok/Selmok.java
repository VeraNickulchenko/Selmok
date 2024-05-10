package selmok;

import selmok.datagenerator.DataGenerator;
import selmok.webdriver_wrapper.Browser;

public class Selmok {
    private Browser browser;
    private DataGenerator dataGenerator;

    public Selmok(SelmokBuilder selmokCompiler) {
        this.browser = selmokCompiler.getBrowser();
        this.dataGenerator = selmokCompiler.getDataGenerator();
    }

    public static SelmokBuilder compiler(){
        return new SelmokBuilder();
    }

    public Browser browser() {
        return browser;
    }

    public DataGenerator generator() {
        return dataGenerator;
    }

    public static class SelmokBuilder{
        private Browser browser;
        private DataGenerator dataGenerator;

        public SelmokBuilder browser(Browser browser){
            this.browser = browser;
            return this;
        }

        public SelmokBuilder generator(DataGenerator generator){
            this.dataGenerator = generator;
            return this;
        }

        public Selmok compile(){
            return new Selmok(this);
        }

        public Browser getBrowser() {
            return browser;
        }

        public DataGenerator getDataGenerator() {
            return dataGenerator;
        }
    }
}
