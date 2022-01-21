package com.want.sdk.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The OrdersHistory class contains information about a set of user's previous orders.
 */
public class OrdersHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<OrderHistoryItem> orders = new ArrayList<OrderHistoryItem>();

    private int ordersTotalCount;

    public OrdersHistory(List<OrderHistoryItem> orders, int ordersTotalCount) {
        if (orders != null && !orders.isEmpty()) {
            this.orders.addAll(orders);
        }
        this.ordersTotalCount = ordersTotalCount;
    }

    /**
     * Returns the list of order history items.
     *
     * @return the list of order history items.
     */
    public List<OrderHistoryItem> getOrders() {
        return orders;
    }

    /**
     * Returns the number of available orders.
     *
     * @return the number of available orders.
     */
    public int getOrdersTotalCount() {
        return ordersTotalCount;
    }

}