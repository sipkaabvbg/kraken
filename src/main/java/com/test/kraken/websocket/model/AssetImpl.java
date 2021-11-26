package com.test.kraken.websocket.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TreeMap;

/**
 * This class represents the object currency pair for trade
 */
public class AssetImpl implements Asset {
    //channel ID
    private int channelID;
    //pair name
    private String pairName;
    //channel name
    private String channelName;
    //last update time about pair
    private Date time;
    //asks prices
    private TreeMap<BigDecimal, BigDecimal> as;
    //bids prices
    private TreeMap<BigDecimal, BigDecimal> bt;

    /**
     * @param channelID
     */
    @Override
    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    /**
     * @param pairName
     */
    @Override
    public void setPair(String pairName) {
        this.pairName = pairName;
    }

    /**
     * @param channelName
     */
    @Override
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * @return
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @param as
     */
    @Override
    public void setAs(TreeMap<BigDecimal, BigDecimal> as) {
        this.as = as;
    }

    /**
     * @param bt
     */
    @Override
    public void setBt(TreeMap<BigDecimal, BigDecimal> bt) {
        this.bt = bt;
    }

    /**
     * @return
     */
    public TreeMap<BigDecimal, BigDecimal> getAs() {
        return as;
    }

    /**
     * @return
     */
    public TreeMap<BigDecimal, BigDecimal> getBt() {
        return bt;
    }

    /**
     * @return
     */
    public int getChannelID() {
        return channelID;
    }

    /**
     * @return
     */
    public String getPairName() {
        return pairName;
    }

    /**
     * @return
     */
    public String getChannelName() {
        return channelName;
    }
}
