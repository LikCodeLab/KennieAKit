package com.want.sdk.order;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The InternalOrderHistory object contains information about user's past orders.
 *
 * <pre>
 * {@code
    <orders orders_count="7" offset="0">
        <item>
            <entity_id>7</entity_id>
            <number>200000007</number>
            <ship_to>Expected First Expected Last</ship_to>
            <date>12/10/13</date>
            <total>$5.10</total>
            <status>Complete</status>
       </item>
    </orders>
 * }
 * </pre>
 */
@Root(name = "orders")
public class InternalOrderHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(name = "orders_count")
    private int ordersCount;

    @Attribute(name = "offset")
    private int offset;

    @ElementList(entry = "item", inline = true, required = false)
    private List<InternalOrderHistoryItem> orders = new ArrayList<InternalOrderHistoryItem>();

    public Object convertToPublicModel() {
        List<OrderHistoryItem> items = convertModelsList(orders);
        return new OrdersHistory(items, ordersCount);
    }


    public <T> List<T> convertModelsList(List<InternalOrderHistoryItem> inputDataList) {
        List<T> outputDataList = new ArrayList<T>();
        for (InternalOrderHistoryItem inputListItem : inputDataList) {
            if (inputListItem != null) {
                T outputListItem = (T)(inputListItem).convertToPublicModel();
                if (outputListItem != null) {
                    outputDataList.add(outputListItem);
                }
            }
        }
        return outputDataList;
    }

    @Override
    public String toString() {
        return "InternalOrderHistory{" +
                "offset=" + offset +
                ", ordersCount=" + ordersCount +
                ", orders=" + orders +
                '}';
    }
}