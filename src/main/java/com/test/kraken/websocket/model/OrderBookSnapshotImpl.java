package com.test.kraken.websocket.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

/**
 * This class implements Order Book Snapshot
 */
public class OrderBookSnapshotImpl implements OrderBookSnapshot {
    private static final Logger LOGGER = Logger.getLogger(OrderBookSnapshotImpl.class.getName());

    //connection ID
    private BigInteger connectionID;
    //endpoint
    private String endpoint;
    //Order Book collection of pairs
    private Map<Integer, Asset> orderBook;


    /**
     * Default constructor
     */
    public OrderBookSnapshotImpl() {
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

    /**
     * @return
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @param orderBook
     */
    public OrderBookSnapshotImpl(Map<Integer, Asset> orderBook) {
        this.orderBook = orderBook;
    }

    /**
     * @return
     */
    public Map<Integer, Asset> getOrderBook() {
        return orderBook;
    }

    /**
     * @param orderBook
     */
    public void setOrderBook(Map<Integer, Asset> orderBook) {
        this.orderBook = orderBook;
    }

    /**
     * @param endpoint
     * @return
     */
    @Override
    public String setEndpoint(String endpoint) {
        return null;
    }

    /**
     * Prints Order Book
     */
    public void printBook() {

        for (Map.Entry<Integer, Asset> entry : orderBook.entrySet()) {
            System.out.println("<------------------------------------>");
            Asset asset = entry.getValue();
            TreeMap<BigDecimal, BigDecimal> as = asset.getAs();
            if (as != null) {
                System.out.println("asks:");
                System.out.println("[" + printTree(as) + "]");
                TreeMap<BigDecimal, BigDecimal> bd = asset.getBt();
                System.out.println("best bid: [" + bd.firstKey() + "," + bd.firstEntry().getValue() + "]");
                System.out.println("best ask: [" + as.lastKey() + "," + as.lastEntry().getValue() + "]");
                System.out.println("bids:");
                System.out.println("[" + printTree(bd) + "]");
                if (asset.getTime() != null) {//, Locale BG
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getAvailableLocales()[3]).format(asset.getTime()));
                }
                System.out.println(asset.getPairName());
            }
            System.out.println(">--------------------------------------<");
        }
    }

    /**
     * Converts tree as String
     *
     * @param tempTree
     * @return tree as String
     */
    private String printTree(TreeMap<BigDecimal, BigDecimal> tempTree) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<BigDecimal, BigDecimal> entryBd : tempTree.entrySet()) {
            sb.append("\n");
            sb.append("[");
            sb.append(entryBd.getKey());
            sb.append(",");
            sb.append(entryBd.getValue());
            sb.append("]");
            sb.append(",");
        }
        int g = sb.lastIndexOf(",");
        return sb.substring(0, g).substring(1);
    }

    @Override
    public String toString() {
        return "OrderBookSnapshotImpl{" +
                "connectionID=" + connectionID +
                ", endpoint='" + endpoint + '\'' +
                ", orderBook=" + orderBook +
                '}';
    }
}
