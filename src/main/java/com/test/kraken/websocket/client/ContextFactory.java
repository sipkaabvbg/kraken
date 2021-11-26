package com.test.kraken.websocket.client;

import com.test.kraken.websocket.constants.Constants;
import com.test.kraken.websocket.message.InitMassage;
import com.test.kraken.websocket.message.InitSpreadMessage;
import com.test.kraken.websocket.message.UpdateMessage;
import com.test.kraken.websocket.message.decoder.MessageDecoderInit;
import com.test.kraken.websocket.message.decoder.MessageDecoderInitSpread;
import com.test.kraken.websocket.message.decoder.MessageDecoderUpdate;
import com.test.kraken.websocket.model.Asset;
import com.test.kraken.websocket.model.AssetImpl;
import com.test.kraken.websocket.model.OrderBookSnapshot;
import com.test.kraken.websocket.model.OrderBookSnapshotImpl;
import com.test.kraken.websocket.model.factory.Operation;

import javax.websocket.DecodeException;
import java.math.BigDecimal;
import java.util.*;

/**
 *Factory class to generate object of concrete operation based on given information from websocket
 */
public class ContextFactory {
    private Operation strategy;
    private OrderBookSnapshot orderBook;
    private MessageDecoderInit messageDecoderInit;
    private MessageDecoderUpdate messageDecoderUpdate;
    private MessageDecoderInitSpread messageDecoderInitSpread;
    private Map<Integer, Asset> assetMap;

    /**
     *Default constructor
     */
    public ContextFactory(){
        orderBook = new OrderBookSnapshotImpl();
        messageDecoderInit = new MessageDecoderInit();
        messageDecoderInitSpread = new MessageDecoderInitSpread();
        assetMap = new HashMap<>();
        orderBook.setOrderBook(assetMap);
        messageDecoderUpdate = new MessageDecoderUpdate();
    }

    public OrderBookSnapshot getOrderBook(){
        return orderBook;
    }

    /**
     * This method process incoming messages
     * @param message
     * @throws DecodeException
     */
    public void processBook(String message) throws DecodeException {
        String pair;
        Integer intChannelID;
        Asset asset;
        StringBuilder sb;
        UpdateMessage updateMessage;
        InitSpreadMessage spreadMessage;
        InitMassage initMassage;
        List<List<String>> asList;
        List<List<String>> bsList;
        //update operations  delete,remove,insert
        if (message.contains(Constants.ASK) || message.contains(Constants.BID)) {

            sb = new StringBuilder(message);

            assetMap = orderBook.getOrderBook();
            asset = assetMap.get(getPairID(sb));

            updateMessage = messageDecoderUpdate.decode(getJson(sb));
            asList = updateMessage.getA();
            bsList = updateMessage.getB();
            if (asList != null) {
                updateSpread(asList,asset.getAs());
            }else if (bsList != null) {
                updateSpread(bsList,asset.getBt());
            }
            asset.setTime(new Date());

            //operation   init book spread
        } else if (message.contains(Constants.ASKS_INIT) || message.contains(Constants.BIDS_INIT)) {

            sb=new StringBuilder(message);
            asset = assetMap.get(getPairID(sb));
            spreadMessage = messageDecoderInitSpread.decode(getJson(sb));
            asList = spreadMessage.getAs();
            bsList = spreadMessage.getBs();
            asset.setAs(initPrices(asList));
            asset.setBt(initPrices(bsList));
            asset.setTime(new Date());
            assetMap = orderBook.getOrderBook();
            assetMap.put(getPairID(sb), asset);
            //operation init pairs
        } else  if (message.contains(Constants.CHANNEL_ID)) {

            initMassage = messageDecoderInit.decode(message);

            pair = initMassage.getPair();
            intChannelID = initMassage.getChannelID();
            assetMap = orderBook.getOrderBook();
            asset = assetMap.get(intChannelID);
            if (asset == null) {
                asset = new AssetImpl();
                asset.setPair(pair);
                asset.setChannelID(intChannelID);
                asset.setChannelName(initMassage.getChannelName());
                assetMap.put(intChannelID, asset);
            }
            //init book connectionID
        } else if (message.contains(Constants.CONNECTION_ID)) {
            InitMassage initMessage = messageDecoderInit.decode(message);
            orderBook.setConnectionID(initMessage.getConnectionID());
        }
    }

    /**
     * prices initialisation
     * @param inList
     * @return
     */
    private TreeMap<BigDecimal,BigDecimal> initPrices(List<List<String>> inList){
        BigDecimal price;
        BigDecimal amount;
        TreeMap<BigDecimal,BigDecimal>  result = new TreeMap<>(Collections.reverseOrder());

        for (List<String> list : inList) {
            price = new BigDecimal(list.get(0));
            amount = new BigDecimal(list.get(1));
            result.put(price, amount);
        }
        return result;
    }
    /**
     *This method extract Jsom string object as String
     * @param sb
     * @return
     */
    public String getJson(StringBuilder sb){
        int startJson;
        int endJson;

        startJson = sb.indexOf(Constants.JSON_STRING_START);
        endJson = sb.indexOf(Constants.JSON_STRING_END);
        return sb.substring(startJson, endJson + 1);
    }
    /**
     * get pair id
     * @param sb
     * @return
     */
    public Integer getPairID(StringBuilder sb){
        int idStart;
        int idEnd;
        String idString;

        idStart = sb.indexOf(Constants.PAIR_ID_START);
        idEnd = sb.indexOf(Constants.PAIR_ID_END);
        idString = sb.substring(idStart + 1, idEnd);
        return Integer.valueOf(idString);
    }

    /**
     * This method executes real logic in order to process message.
     * it can be treated as factory method to get object of type Operation
     *  as remove, update, insert operation
     * @param priceList
     * @param treeMap
     */
    public void  updateSpread( List<List<String>> priceList,TreeMap<BigDecimal,BigDecimal>treeMap){
        List<String>bs1;
        BigDecimal price;
        BigDecimal amount;

        //remove
        if (priceList.size() == 2) {
            remove(treeMap,priceList);
            System.out.println(Constants.BOOK_OPERATION_REMOVE);
        } else {
            bs1=priceList.get(0);
            price = new BigDecimal(bs1.get(0));
            amount = new BigDecimal(bs1.get(1));
            //insert

            if (treeMap.get(price)==null){
                //there is possible to have price book with les then 10 elements
                if(treeMap.size()>=10) {
                    treeMap.pollLastEntry();
                }
                treeMap.put(price,amount);
                System.out.println(Constants.BOOK_OPERATION_INSERT);
            }else if (treeMap.get(price)!=null){
                //update
                treeMap.put(price,amount);
                System.out.println(Constants.BOOK_OPERATION_UPDATE);
            }
        }
    }
    /**
     * This method removes price
     * @param treeMap
     * @param list
     */
    public void remove(Map<BigDecimal,BigDecimal> treeMap, List<List<String>>list, BigDecimal... a){
        BigDecimal amount;
        BigDecimal price;
        List<String> innerList;

        price = new BigDecimal(list.get(0).get(0));
        if(treeMap.remove(price)!=null){
            innerList = list.get(1);
            price = new BigDecimal(innerList.get(0));
            amount = new BigDecimal(innerList.get(1));
            treeMap.put(price, amount);

        }
    }
}
