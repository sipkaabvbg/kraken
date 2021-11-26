package com.test.kraken.websocket.model.factory;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;

public class OperationInsert implements Operation {
    @Override
    public void doOperation(TreeMap<BigDecimal,BigDecimal> treeMap, List<List<String>>list,BigDecimal... a) {
        //there is possible to have price book with les then 10 elements
        if(treeMap.size()>=10) {
            treeMap.pollLastEntry();
        }
        treeMap.put(a[0],a[1]);
        System.out.println("insert");
    }

}
