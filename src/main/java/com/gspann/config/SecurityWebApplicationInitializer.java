package com.gspann.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Sets up the Spring Security filter chain
 *
 */
@Order(value=2)
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}

