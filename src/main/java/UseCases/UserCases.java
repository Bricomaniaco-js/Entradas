package UseCases;

import bdd.MongoController;
import model.*;

public class UserCases {
    User u;
    MongoController controller = new MongoController(null);

    public UserCases(User u, MongoController controller){
        this.controller = controller;
        this.u = u;
    }
    public void buysTicket(Event e){
        TicketCases.generateTicket(u, e);

    }
}
