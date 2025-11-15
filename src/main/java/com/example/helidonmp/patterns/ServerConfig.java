package com.example.helidonmp.patterns;

/**
 * Server configuration demonstrating F-bounded polymorphism (self-type pattern).
 * 
 * Phase 3: F-Bounded Polymorphism
 */
public class ServerConfig {
    private final String name;
    private final int port;
    private final String host;
    private final int timeout;

    /**
     * Protected constructor - use builder() to create instances.
     * Protected to allow subclassing.
     * 
     * @param builder the builder containing field values
     */
    protected ServerConfig(BuilderBase<?, ?> builder) {
        this.name = builder.name;
        this.port = builder.port;
        this.host = builder.host;
        this.timeout = builder.timeout;
    }

    /**
     * Creates a new builder for ServerConfig.
     * 
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    public abstract static class BuilderBase<
            BUILDER extends ServerConfig.BuilderBase<BUILDER, PROTOTYPE>, 
            PROTOTYPE extends ServerConfig> {
        
        private String name = "default-server";
        private int port = 8080;
        private String host = "localhost";
        private int timeout = 30000;

        protected BuilderBase() {
        }
        
        /**
         * Returns this builder as its concrete type.
         * 
         * This is the magic method that enables fluent chaining!
         * By returning BUILDER, methods can return the concrete
         * builder type instead of BuilderBase.
         * 
         * @return this builder as BUILDER type
         */
        @SuppressWarnings("unchecked")
        protected BUILDER self() {
            return (BUILDER) this;    
        }

        public BUILDER name(String name) {
            this.name = name;
            return self();
        }

        public BUILDER port(int port) {
            this.port = port;
            return self();
        }

        public BUILDER host(String host) {
            this.host = host;
            return self();
        }
        
        public BUILDER timeout(int timeout) {
            this.timeout = timeout;
            return self();
        }

        /**
         * Builds the configuration object.
         * 
         * Subclasses must implement this to return their specific PROTOTYPE type.
         * 
         * @return the built configuration of type PROTOTYPE
         */
        public abstract PROTOTYPE build();
    }

    /**
     * Concrete builder for ServerConfig.
     */
    public static class Builder extends BuilderBase<Builder, ServerConfig> {

        /**
         * Private constructor - use ServerConfig.builder() instead.
         */
        private Builder() {
        }

        /**
         * Builds a ServerConfig instance.
         * 
         * @return a new ServerConfig with the configured values
         */
        @Override
        public ServerConfig build() {
            return new ServerConfig(this);
        }
    }
    

    public String name() {
        return name;
    }

    public int port() {
        return port;
    }

    public int timeout() {
        return timeout;
    }

    public String host() {
        return host;
    }

    @Override
    public String toString() {
        return "ServerConfig{" +
                "name='" + name + '\'' +
                ", port=" + port +
                ", timeout=" + timeout +
                ", host='" + host + '\'' +
                '}';
    }
        
    
}
