package com.test.kraken.websocket.message;

import java.math.BigInteger;
import java.util.List;

/**
 *This class represent update message about order book
 */
public class UpdateMessage implements Message {

    //ask price
    private List<List<String>> a;
    // bid price
    private List<List<String>> b;
    //CRC32 checksum value
    private BigInteger c;

    /**
     *
     * @return
     */
    public List<List<String>> getA() {
        return a;
    }

    /**
     *
     * @param a
     */
    public void setA(List<List<String>> a) {
        this.a = a;
    }

    /**
     *
     * @return
     */
    public List<List<String>> getB() {
        return b;
    }

    /**
     *
     * @param b
     */
    public void setB(List<List<String>> b) {
        this.b = b;
    }

    /**
     *
     * @return
     */
    public BigInteger getC() {
        return c;
    }

    /**
     *
     * @param c
     */
    public void setC(BigInteger c) {
        this.c = c;
    }
}
