package com.test.kraken.websocket.message.decoder;

import com.google.gson.Gson;
import com.test.kraken.websocket.message.InitSpreadMessage;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * This class represent InitSpreadMessage decoder
 */
public class MessageDecoderInitSpread implements Decoder.Text<InitSpreadMessage> {

    private static Gson gson = new Gson();

    @Override
    public InitSpreadMessage decode(String s) throws DecodeException {
        InitSpreadMessage message = gson.fromJson(s, InitSpreadMessage.class);
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
