package com.test.kraken.websocket.model.factory;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;

public interface Operation {
    public void doOperation(TreeMap<BigDecimal,BigDecimal> treeMap, List<List<String>>list, BigDecimal... a);
}
