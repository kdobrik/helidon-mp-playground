package com.example.helidonmp;

public class Message {

    private String message;
    private String greeting;

    public Message() {
    }

    private Message(Builder builder) {
        this.message = builder.message;
        this.greeting = builder.greeting;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String message;
        private String greeting;

        private Builder() {
        }

        public Builder message(String message) {
            this.message = message;
            return this; // return 'this' for fluent chaining
        }

        public Builder greeting(String greeting) {
            this.greeting = greeting;
            return this;
        }

        public Message build() {
            return new Message(this);
        }

    }

    public Message(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return this.greeting;
    }
}
