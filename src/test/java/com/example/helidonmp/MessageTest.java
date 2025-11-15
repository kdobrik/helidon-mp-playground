package com.example.helidonmp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.Test;

public class MessageTest {


    @Test
    void testBuilder() {
        Message message = Message.builder()
            .message("Hello, World!")
            .greeting("Hello")
            .build();

        assertThat(message.getMessage(), is("Hello, World!"));
        assertThat(message.getGreeting(), is("Hello"));
    }

    // 1) Default constructor should initialize fields to null
    @Test
    void testDefaultConstructorInitializesNulls() {
        Message message = new Message();
        assertThat(message.getMessage(), is(nullValue()));
        assertThat(message.getGreeting(), is(nullValue()));
    }

    // 2) Parameterized constructor should set message and leave greeting null
    @Test
    void testParameterizedConstructorSetsMessageOnly() {
        Message message = new Message("Hi there");
        assertThat(message.getMessage(), is("Hi there"));
        assertThat(message.getGreeting(), is(nullValue()));
    }

    // 3) Setters should update values
    @Test
    void testSettersUpdateValues() {
        Message message = new Message();
        message.setMessage("New message");
        message.setGreeting("Howdy");
        assertThat(message.getMessage(), is("New message"));
        assertThat(message.getGreeting(), is("Howdy"));
    }

    // 4) Builder should allow setting only message
    @Test
    void testBuilderOnlyMessage() {
        Message message = Message.builder()
                .message("Only message")
                .build();
        assertThat(message.getMessage(), is("Only message"));
        assertThat(message.getGreeting(), is(nullValue()));
    }

    // 5) Builder should allow setting only greeting
    @Test
    void testBuilderOnlyGreeting() {
        Message message = Message.builder()
                .greeting("Only greeting")
                .build();
        assertThat(message.getMessage(), is(nullValue()));
        assertThat(message.getGreeting(), is("Only greeting"));
    }
}
