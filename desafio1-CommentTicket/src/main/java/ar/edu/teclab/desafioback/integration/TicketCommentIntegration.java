package ar.edu.teclab.desafioback.integration;

import ar.edu.teclab.desafioback.configuration.ConfigurationApp;
import ar.edu.teclab.desafioback.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
public class TicketCommentIntegration {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ConfigurationApp configurationApp;

    private static final String TICKET_PATH = "/api/v2/tickets/";
    private static final String GET_PATH = "/comments.json";
    private static final String PUT_PATH = ".json";

    public Optional<String> getCommentsIntegration (final String ticket_id) throws HttpClientErrorException {
            String url = configurationApp.getBasePath() + TICKET_PATH + ticket_id + GET_PATH;
            restTemplate.getInterceptors().add(
                    new BasicAuthenticationInterceptor(configurationApp.getUser(),configurationApp.getPassword()));

            String response = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<String>() {})
                    .getBody();
            Optional<String> optionalResponse = Optional.ofNullable(response);
            if (!optionalResponse.isPresent()){
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
            }
            return optionalResponse;
    }

    public Optional<String> putCommentsIntegration (final String ticket_id, Ticket ticket) throws HttpClientErrorException {
        String url = configurationApp.getBasePath() + TICKET_PATH + ticket_id + PUT_PATH;
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor(configurationApp.getUser(),configurationApp.getPassword()));
        HttpEntity<Ticket> httpEntity = new HttpEntity<>(ticket);
        String response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity,
                new ParameterizedTypeReference<String>() {})
                .getBody();
        Optional<String> optionalResponse = Optional.ofNullable(response);
        if (!optionalResponse.isPresent()){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return optionalResponse;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
