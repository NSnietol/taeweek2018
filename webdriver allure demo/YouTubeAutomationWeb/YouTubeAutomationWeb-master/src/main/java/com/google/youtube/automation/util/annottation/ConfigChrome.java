package com.google.youtube.automation.util.annottation;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@Component
@Profile("Chrome")
public @interface ConfigChrome {
}
