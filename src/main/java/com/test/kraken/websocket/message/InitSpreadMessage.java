package com.test.kraken.websocket.message;

import java.util.List;

/**
 * This class represent initial spred ot order book
 */
public class InitSpreadMessage  implements Message {
    //ask price
    private List<List<String>> as;
    // bid price
    private List<List<String>> bs;

    /**
     *
     * @return
     */
    public List<List<String>> getAs() {
        return as;
    }

    /**
     *
     * @param as
     */
    public void setAs(List<List<String>> as) {
        this.as = as;
    }

    /**
     *
     * @return
     */
    public List<List<String>> getBs() {
        return bs;
    }

    /**
     *
     * @param bs
     */
    public void setBs(List<List<String>> bs) {
        this.bs = bs;
    }
}
