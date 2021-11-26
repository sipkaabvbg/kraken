package com.test.kraken.websocket.message.decoder;

import com.google.gson.Gson;
import com.test.kraken.websocket.message.UpdateMessage;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * This class represent UpdateMessage decoder
 *
 */
public class MessageDecoderUpdate implements Decoder.Text<UpdateMessage> {

    private static Gson gson = new Gson();

    @Override
    public UpdateMessage decode(String s) throws DecodeException {
        UpdateMessage message = gson.fromJson(s, UpdateMessage.class);
        return message;
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}
