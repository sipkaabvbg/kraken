package com.test.kraken.websocket.client;

import javax.websocket.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This class represent Client websocket endpoint
 */
@javax.websocket.ClientEndpoint
public class ClientEndpoint {
    private static final Logger LOGGER = Logger.getLogger(ClientEndpoint.class.getName());
    private String initMessage;
    private ContextFactory context;

    public ClientEndpoint(String initMessage) {
        setInitMessage(initMessage);
    }

    public String getInitMessage() {
        return initMessage;
    }

    public void setInitMessage(String initMessage) {
        this.initMessage = initMessage;
    }

    @OnOpen
    public void onOpen(Session session) {
        context = new ContextFactory();
        System.out.println("Connected to endpoint: " + session.getBasicRemote());

        try {
            session.getBasicRemote().sendText(getInitMessage());
        } catch (IOException ex) {
            Logger.getLogger(ClientEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OnMessage
    public void processMessage(String message) throws DecodeException {
        context.processBook(message);
        System.out.println(message);
        context.getOrderBook().printBook();
    }

    @OnError
    public void processError(Throwable t) {
        t.printStackTrace();
    }

    @OnClose
    public void onClose(CloseReason reason) {
        System.out.println("Closing connection");
        LOGGER.warning("closing connection {}" + reason);
    }
}


