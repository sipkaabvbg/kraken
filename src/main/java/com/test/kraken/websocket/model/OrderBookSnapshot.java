package com.test.kraken.websocket.model;

import java.math.BigInteger;
import java.util.Map;

/**
 * This interface represent Order Book Snapshot
 */
public interface OrderBookSnapshot {
    /**
     * @param connectionID
     */
    void setConnectionID(BigInteger connectionID);

    /**
     * @param endpoint
     * @return
     */
    String setEndpoint(String endpoint);

    /**
     * @return
     */
    Map<Integer, Asset> getOrderBook();

    /**
     * @param orderBook
     */
    void setOrderBook(Map<Integer, Asset> orderBook);

    /**
     *
     */
    void printBook();
}
