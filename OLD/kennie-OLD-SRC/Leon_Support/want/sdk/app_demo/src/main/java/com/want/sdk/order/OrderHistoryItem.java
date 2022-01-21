package com.want.sdk.order;

import java.io.Serializable;

/**
 * The OrderHistoryItem class contains information about one of user's past orders.
 */
public class OrderHistoryItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;

    private String orderNumber;

    private String orderDate;

    private String shippingAddress;

    private String orderTotal;

    private String orderStatus;

    /**
     * Returns ID of the order.
     *
     * @return ID of the order.
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets ID of the order.
     *
     * @param orderId the order ID to set.
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * Returns order number.
     *
     * @return  order number.
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets order number.
     *
     * @param orderNumber the order number to set.
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Returns order date.
     *
     * @return order date.
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * Sets order date.
     *
     * @param orderDate the order date to set
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Returns order's shipping address.
     *
     * @return order's shipping address.
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Sets order's shipping address.
     *
     * @param shippingAddress the shipping address to set.
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * Returns the total price of the order.
     *
     * @return the total price of the order.
     */
    public String getOrderTotal() {
        return orderTotal;
    }

    /**
     * Sets the total price of the order.
     *
     * @param orderTotal the price to set.
     */
    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    /**
     * Returns current order status.
     *
     * @return current order status.
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets current order status.
     *
     * @param orderStatus the order status to set.
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}