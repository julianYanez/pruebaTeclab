package ar.edu.teclab.desafioback.service;


import ar.edu.teclab.desafioback.entity.Ticket;
import ar.edu.teclab.desafioback.exceptions.TicketException;
import ar.edu.teclab.desafioback.integration.TicketCommentIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    TicketCommentIntegration ticketCommentIntegration;

    public Optional<String> getCommentsTicket (String ticket_id) throws TicketException {
            if (ticket_id.isEmpty()) {
                throw new TicketException(HttpStatus.BAD_REQUEST, "-1", "EL campo ticket_id no debe ser vacio");
            }
            Optional<String> comments = ticketCommentIntegration.getCommentsIntegration(ticket_id);
            return comments;
    }

    public Optional<String> putCommentsTicket (String ticket_id, Ticket ticket) throws TicketException {
        if (ticket_id.isEmpty()) {
            throw new TicketException(HttpStatus.BAD_REQUEST, "-1", "EL campo ticket_id no debe ser vacio");
        }
        Optional<String> response = ticketCommentIntegration.putCommentsIntegration(ticket_id, ticket);
        return response;
    }
}
