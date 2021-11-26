package com.test.kraken.websocket.message.decoder;

import com.google.gson.Gson;
import com.test.kraken.websocket.message.InitMassage;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * This class represent InitMessage decoder
 */
public class MessageDecoderInit implements Decoder.Text<InitMassage> {

    private static Gson gson = new Gson();

    @Override
    public InitMassage decode(String s) throws DecodeException {
        InitMassage message = gson.fromJson(s, InitMassage.class);
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
