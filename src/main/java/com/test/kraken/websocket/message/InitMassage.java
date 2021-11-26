package com.test.kraken.websocket.message;

import java.math.BigInteger;

/**
 * This class represent initial properties ot order book
 */
public class InitMassage implements Message {
    //connection ID
    private BigInteger connectionID;
    //channel ID
    private int channelID;
    //pair
    private String pair;
    //channel Name
    private String channelName;

    /**
     * @return
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * @param channelName
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * @return
     */
    public int getChannelID() {
        return channelID;
    }

    /**
     * @param channelID
     */
    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    /**
     * @return
     */
    public String getPair() {
        return pair;
    }

    /**
     * @param pair
     */
    public void setPair(String pair) {
        this.pair = pair;
    }

    /**
     * @return
     */
    public BigInteger getConnectionID() {
        return connectionID;
    }

    /**
     * @param connectionID
     */
    public void setConnectionID(BigInteger connectionID) {
        this.connectionID = connectionID;
    }

}
