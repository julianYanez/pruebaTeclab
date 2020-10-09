package ar.edu.teclab.desafioback.controller;

import ar.edu.teclab.desafioback.entity.Ticket;
import ar.edu.teclab.desafioback.exceptions.TicketException;
import ar.edu.teclab.desafioback.service.CommentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/desafio")
public class Controller {
    @Autowired
    private CommentService commentService;

    private static final Log LOG = LogFactory.getLog(Controller.class);

    @GetMapping(value="/getcomments/{ticket_id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getComments (@PathVariable String ticket_id){
        try {
            Optional<String> response = commentService.getCommentsTicket(ticket_id);
            return ResponseEntity
                    .ok()
                    .body(response);
        } catch (TicketException e){
            return ResponseEntity.
                    status(e.getStatus()).
                    build();
        }
    }

    @PutMapping(value="/putcomment/{ticket_id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> putComment(@PathVariable String ticket_id, @RequestBody Ticket ticket){
        try {
            Optional<String> response = commentService.putCommentsTicket(ticket_id, ticket);
            return ResponseEntity.ok().body(response.get());
        } catch (TicketException e) {
            LOG.error("Mensaje de error: [" + e.getStatus() + "] Codigo de error: [" + e.getCode() + "]");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(ex.getMessage());
    }

}
