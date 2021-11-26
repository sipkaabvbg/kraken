package com.test.kraken.websocket.model.factory;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;

public class OperationDelete implements Operation {
    @Override
    public void doOperation(TreeMap<BigDecimal, BigDecimal> treeMap, List<List<String>> list, BigDecimal... a) {
        BigDecimal amount;
        BigDecimal price;
        List<String> innerList;

        price = new BigDecimal(list.get(0).get(0));
        if (treeMap.remove(price) != null) {
            innerList = list.get(1);
            price = new BigDecimal(innerList.get(0));
            amount = new BigDecimal(innerList.get(1));
            treeMap.put(price, amount);

        }
    }
}
