package dev.hisham.universal_clipboard.config.custom;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value="custom")
public record CustomConfigs(String name, Integer age) {
}
