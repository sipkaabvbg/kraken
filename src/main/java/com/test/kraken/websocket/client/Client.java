package com.test.kraken.websocket.client;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the main starting class to launch the application
 */
public class Client {

    private final static CountDownLatch messageLatch = new CountDownLatch(1);
    private final static String STREAM_URL = "wss://ws.kraken.com";
    private final static int TIMEOUT_IN_SECS = 2;
    private final static String initMessage= "{\"event\":\"subscribe\", \"subscription\":{\"name\":\"book\"}, \"pair\":[\"BTC/USD\",\"ETH/USD\"]}";

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args)  {

        WebSocketContainer container;
        ClientEndpoint clientEndpoint;
        try {
            container = ContainerProvider.getWebSocketContainer();
            System.out.println("Connecting to " + STREAM_URL);
            clientEndpoint=new ClientEndpoint(initMessage);
            container.connectToServer(clientEndpoint, URI.create(STREAM_URL));
            messageLatch.await(TIMEOUT_IN_SECS, TimeUnit.SECONDS);

        } catch (InterruptedException | DeploymentException | IOException ex ) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
