package io.httpdoc.springmvc;

import io.httpdoc.core.interpretation.DefaultInterpreter;
import io.httpdoc.core.interpretation.Interpreter;
import io.httpdoc.core.interpretation.SourceInterpreter;
import io.httpdoc.core.provider.Provider;
import io.httpdoc.core.provider.SystemProvider;
import org.springframework.stereotype.Component;

/**
 * @author 钟宝林
 * @date 2018-05-13 10:50
 **/
@Component
public class SpringmvcTranslation {

    private String httpdoc;
    private String protocol;
    private String hostname;
    private Integer port;
    private String context;
    private String version;
    private Provider provider = new SystemProvider();
    private Interpreter interpreter = new SourceInterpreter();

    public SpringmvcTranslation() {

    }

    private SpringmvcTranslation(Builder builder) {
        httpdoc = builder.httpdoc;
        protocol = builder.protocol;
        hostname = builder.hostname;
        port = builder.port;
        context = builder.context;
        version = builder.version;
        provider = builder.provider;
        interpreter = builder.interpreter;
    }

    public String getHttpdoc() {
        return httpdoc;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHostname() {
        return hostname;
    }

    public Integer getPort() {
        return port;
    }

    public String getContext() {
        return context;
    }

    public String getVersion() {
        return version;
    }

    public Provider getProvider() {
        return provider;
    }

    public Interpreter getInterpreter() {
        return interpreter;
    }

    public static class Builder {
        private String httpdoc;
        private String protocol;
        private String hostname;
        private Integer port;
        private String context;
        private String version;
        private Provider provider = new SystemProvider();
        private Interpreter interpreter = new DefaultInterpreter();

        public Builder httpDoc(String httpdoc) {
            this.httpdoc = httpdoc;
            return this;
        }

        public Builder protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public Builder hostname(String hostname) {
            this.hostname = hostname;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder context(String context) {
            this.context = context;
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public Builder provider(Provider provider) {
            this.provider = provider;
            return this;
        }

        public Builder interpreter(Interpreter interpreter) {
            this.interpreter = interpreter;
            return this;
        }

        public SpringmvcTranslation build() {
            return new SpringmvcTranslation(this);
        }
    }

}
