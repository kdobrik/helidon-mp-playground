package com.example.helidonmp.patterns;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class SimpleConfigTest {

    @Test
    void testSimpleConfig() {
        SimpleConfig config = SimpleConfig.builder()
                .name("MyConfig")
                .timeout(1000)
                .enabled(true)
                .build();

        assertEquals("MyConfig", config.name());
        assertEquals(1000, config.timeout());
        assertTrue(config.enabled());
    }

    // Behavior 1: Builder should set provided values
    @Test
    void testBuilderSetsProvidedValues() {
        SimpleConfig config = SimpleConfig.builder()
                .name("custom")
                .timeout(5000)
                .enabled(false)
                .build();

        assertThat(config.name(), is("custom"));
        assertThat(config.timeout(), is(5000));
        assertThat(config.enabled(), is(false));
    }

    // Behavior 2: Builder should use default values when not specified
    @Test
    void testBuilderUsesDefaultsWhenNotSpecified() {
        SimpleConfig config = SimpleConfig.builder().build();

        assertThat(config.name(), is("default"));
        assertThat(config.timeout(), is(1000));
        assertThat(config.enabled(), is(true));
    }

    // Behavior 3: toString should include all fields and values
    @Test
    void testToStringIncludesAllFields() {
        SimpleConfig config = SimpleConfig.builder()
                .name("alpha")
                .timeout(42)
                .enabled(true)
                .build();

        String text = config.toString();
        assertThat(text.contains("name='alpha'"), is(true));
        assertThat(text.contains("timeout=42"), is(true));
        assertThat(text.contains("enabled=true"), is(true));
    }

    // Behavior 4: Builder is reusable for different combinations of setters
    @Test
    void testBuilderSupportsPartialSetters() {
        SimpleConfig onlyName = SimpleConfig.builder()
                .name("onlyName")
                .build();
        assertThat(onlyName.name(), is("onlyName"));
        assertThat(onlyName.timeout(), is(1000));
        assertThat(onlyName.enabled(), is(true));

        SimpleConfig onlyTimeout = SimpleConfig.builder()
                .timeout(77)
                .build();
        assertThat(onlyTimeout.name(), is("default"));
        assertThat(onlyTimeout.timeout(), is(77));
        assertThat(onlyTimeout.enabled(), is(true));

        SimpleConfig onlyEnabled = SimpleConfig.builder()
                .enabled(false)
                .build();
        assertThat(onlyEnabled.name(), is("default"));
        assertThat(onlyEnabled.timeout(), is(1000));
        assertThat(onlyEnabled.enabled(), is(false));
    }

    // Behavior 5: Builder returns non-null config instance
    @Test
    void testBuilderReturnsInstance() {
        SimpleConfig config = SimpleConfig.builder().build();
        assertThat(config, is(notNullValue()));
    }
}
