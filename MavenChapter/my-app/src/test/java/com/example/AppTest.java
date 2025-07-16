package com.example;

import junit.framework.TestCase;

public class AppTest extends TestCase {

    public void testEnvironmentPropertyIsSet() {
        String env = System.getProperty("env");
        assertNotNull("Property 'env' should not be null", env);
    }
}
