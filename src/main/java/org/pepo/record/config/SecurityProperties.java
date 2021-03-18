package org.pepo.record.config;

import lombok.Getter;
import lombok.Setter;
import org.pepo.record.config.props.JwtProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("security")
public class SecurityProperties {

    private JwtProperties jwt;

}
