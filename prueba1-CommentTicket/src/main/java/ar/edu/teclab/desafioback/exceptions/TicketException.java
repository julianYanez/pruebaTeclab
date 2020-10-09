package ar.edu.teclab.desafioback.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class TicketException extends Exception{
    HttpStatus status;
    String code;
    String message;

}
