package ar.edu.teclab.desafioback.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ConfigurationApp {
    @Value("${basePath}")
    private String basePath;

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;

}
