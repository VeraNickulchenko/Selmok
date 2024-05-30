package selmok;

import selmok.datagenerator.Generator;
import selmok.browser.Browser;

public class Selmok {
    private Browser browser;
    private Generator generator;

    public Selmok(SelmokBuilder selmokCompiler) {
        this.browser = selmokCompiler.getBrowser();
        this.generator = selmokCompiler.getDataGenerator();
    }

    public static SelmokBuilder compiler(){
        return new SelmokBuilder();
    }

    public Browser browser() {
        return browser;
    }

    public Generator generator() {
        return generator;
    }

    public static class SelmokBuilder{
        private Browser browser;
        private Generator generator;

        public SelmokBuilder setBrowser(Browser browser){
            this.browser = browser;
            return this;
        }

        public SelmokBuilder setGenerator(Generator generator){
            this.generator = generator;
            return this;
        }

        public Selmok compile(){
            return new Selmok(this);
        }

        public Browser getBrowser() {
            return browser;
        }

        public Generator getDataGenerator() {
            return generator;
        }
    }
}
