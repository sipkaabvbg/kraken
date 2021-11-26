package com.test.kraken.websocket.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TreeMap;

/**
 * This interface represents the object currency pair for trade
 */
public interface Asset {
    /**
     * @param channelID
     */
    void setChannelID(int channelID);

    /**
     * @param pairName
     */
    void setPair(String pairName);

    /**
     * @param channelName
     */
    void setChannelName(String channelName);

    /**
     * @param time
     */
    void setTime(Date time);

    /**
     * @return
     */
    Date getTime();

    /**
     * @return
     */
    String getPairName();

    /**
     * @param as
     */
    void setAs(TreeMap<BigDecimal, BigDecimal> as);

    /**
     * @param bt
     */
    void setBt(TreeMap<BigDecimal, BigDecimal> bt);

    /**
     * @return
     */
    TreeMap<BigDecimal, BigDecimal> getAs();

    /**
     * @return
     */
    TreeMap<BigDecimal, BigDecimal> getBt();
}
