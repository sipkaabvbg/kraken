package com.test.kraken.websocket.constants;

/**
 * Constant utility class
 */
public class Constants {

    // Prevents instantiation
    private Constants(){}

    public static final String ASK="\"a\"";
    public static final String BID="\"b\"";
    public static final String ASKS_INIT="\"as\"";
    public static final String BIDS_INIT="\"bs\"";
    public static final String CHANNEL_ID="channelID";
    public static final String CONNECTION_ID="connectionID";
    public static final String JSON_STRING_START="{";
    public static final String JSON_STRING_END="}";
    public static final String PAIR_ID_START="[";
    public static final String PAIR_ID_END=",";
    public static final String BOOK_OPERATION_REMOVE="remove";
    public static final String BOOK_OPERATION_INSERT="insert";
    public static final String BOOK_OPERATION_UPDATE="update";
}
