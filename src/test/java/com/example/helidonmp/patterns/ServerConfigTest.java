package com.example.helidonmp.patterns;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ServerConfigTest {

    @Test
    void testServerConfig() {
        ServerConfig config = ServerConfig.builder()
                .name("MyServer")
                .port(8080)
                .timeout(5000)
                .host("localhost")
                .build();

        assertEquals("MyServer", config.name());
        assertEquals(8080, config.port());
        assertEquals(5000, config.timeout());
        assertEquals("localhost", config.host());
    }

    // Behavior 1: Builder should set provided values
    @Test
    void testBuilderSetsProvidedValues() {
        ServerConfig cfg = ServerConfig.builder()
                .name("api-server")
                .port(9090)
                .host("0.0.0.0")
                .timeout(120000)
                .build();

        assertThat(cfg.name(), is("api-server"));
        assertThat(cfg.port(), is(9090));
        assertThat(cfg.host(), is("0.0.0.0"));
        assertThat(cfg.timeout(), is(120000));
    }

    // Behavior 2: Builder should use default values when not specified
    @Test
    void testBuilderUsesDefaultsWhenNotSpecified() {
        ServerConfig cfg = ServerConfig.builder().build();

        assertThat(cfg.name(), is("default-server"));
        assertThat(cfg.port(), is(8080));
        assertThat(cfg.host(), is("localhost"));
        assertThat(cfg.timeout(), is(30000));
    }

    // Behavior 3: toString should contain all fields and values
    @Test
    void testToStringContainsAllFields() {
        ServerConfig cfg = ServerConfig.builder()
                .name("svc")
                .port(8001)
                .host("example.com")
                .timeout(45000)
                .build();

        String s = cfg.toString();
        assertThat(s, containsString("name='svc'"));
        assertThat(s, containsString("port=8001"));
        assertThat(s, containsString("host='example.com'"));
        assertThat(s, containsString("timeout=45000"));
    }

    // Behavior 4: Builder supports partial setters and preserves defaults for others
    @Test
    void testBuilderPartialSetters() {
        ServerConfig onlyName = ServerConfig.builder().name("only-name").build();
        assertThat(onlyName.name(), is("only-name"));
        assertThat(onlyName.port(), is(8080));
        assertThat(onlyName.host(), is("localhost"));
        assertThat(onlyName.timeout(), is(30000));

        ServerConfig onlyPort = ServerConfig.builder().port(8181).build();
        assertThat(onlyPort.name(), is("default-server"));
        assertThat(onlyPort.port(), is(8181));
        assertThat(onlyPort.host(), is("localhost"));
        assertThat(onlyPort.timeout(), is(30000));

        ServerConfig onlyHost = ServerConfig.builder().host("srv").build();
        assertThat(onlyHost.name(), is("default-server"));
        assertThat(onlyHost.port(), is(8080));
        assertThat(onlyHost.host(), is("srv"));
        assertThat(onlyHost.timeout(), is(30000));

        ServerConfig onlyTimeout = ServerConfig.builder().timeout(10).build();
        assertThat(onlyTimeout.name(), is("default-server"));
        assertThat(onlyTimeout.port(), is(8080));
        assertThat(onlyTimeout.host(), is("localhost"));
        assertThat(onlyTimeout.timeout(), is(10));
    }

    // Behavior 5: Builder returns a non-null instance and is immutable after build
    @Test
    void testBuilderReturnsInstanceAndImmutability() {
        ServerConfig.Builder builder = ServerConfig.builder()
                .name("svc")
                .port(8082)
                .host("h")
                .timeout(111);

        ServerConfig cfg1 = builder.build();
        assertThat(cfg1, is(notNullValue()));

        // mutate builder after first build and build again; previous built instance should keep its values
        ServerConfig cfg2 = builder.name("svc2").port(8083).build();

        // cfg1 retains original values
        assertThat(cfg1.name(), is("svc"));
        assertThat(cfg1.port(), is(8082));
        assertThat(cfg1.host(), is("h"));
        assertThat(cfg1.timeout(), is(111));

        // cfg2 reflects updated builder values
        assertThat(cfg2.name(), is("svc2"));
        assertThat(cfg2.port(), is(8083));
        assertThat(cfg2.host(), is("h"));
        assertThat(cfg2.timeout(), is(111));
    }
}
