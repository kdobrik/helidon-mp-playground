package com.example.helidonmp.patterns;

/**
 * Simple configuration class demonstrating generic builder foundation.
 * 
 * Phase 2: Generic Builder Foundation
 * 
 * SimpleConfig config = SimpleConfig.builder()
                .name("MyConfig")
                .timeout(1000)
                .enabled(true)
                .build();
 */
public class SimpleConfig {

    private final String name;
    private final int timeout;
    private final boolean enabled;

    public String name() {
        return name;
    }

    public int timeout() {
        return timeout;
    }

    public boolean enabled() {
        return enabled;
    }

    /**
     * Protected constructor - use builder() to create instances.
     * Protected (not private) to allow subclassing in future examples.
     * 
     * @param builder the builder containing field values
     */
    protected SimpleConfig(BuilderBase<?> builder) {
        this.name = builder.name;
        this.timeout = builder.timeout;
        this.enabled = builder.enabled;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Concrete builder for SimpleConfig.
     */
    public static class Builder extends BuilderBase<SimpleConfig>{

        private Builder() {
        }

        @Override
        public SimpleConfig build() {
            return new SimpleConfig(this);
        }
    }

    /**
     * Generic builder base class.
     * 
     * Key concepts:
     * <T> is a type parameter - it represents "whatever type this builder builds"
     * Methods return BuilderBase<T> (not just this anymore)
     * build() is abstract - subclasses must implement it
     * This base can be reused for different config types!
     * 
     * @param <T> the type of configuration object this builder creates
     */
    public static abstract class BuilderBase<T> {

        private String name = "default";
        private int timeout = 1000;
        private boolean enabled = true;

        /**
         * Protected constructor - subclasses only.
         */
        protected BuilderBase() {
        }

        public BuilderBase<T> name(String name) {
            this.name = name;
            return this;
        }

        public BuilderBase<T> timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public BuilderBase<T> enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        /**
         * Builds the configuration object.
         * 
         * @return the built configuration of type T
         */
        public abstract T build();
    }

    @Override
    public String toString() {
        return "SimpleConfig{" +
                "name='" + name + '\'' +
                ", timeout=" + timeout +
                ", enabled=" + enabled +
                '}';
    }

}