package com.want.sdk.order;

import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * The InternalOrder object contains information about one of user's past orders.
 *
 * <pre>
 * {@code
    <item>
        <entity_id>7</entity_id>
        <number>200000007</number>
        <date>12/10/13</date>
        <ship_to>Expected First Expected Last</ship_to>
        <total>$5.10</total>
        <status>Complete</status>
   </item>
 * }
 * </pre>
 */
public class InternalOrderHistoryItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Element(name = "entity_id")
    private String id;

    @Element(name = "number")
    private String number;

    @Element(name = "date")
    private String date;

    @Element(name = "ship_to", required = false)
    private String shipTo;

    @Element(name = "total")
    private String total;

    @Element(name = "status")
    private String status;

    public Object convertToPublicModel() {
        OrderHistoryItem order = new OrderHistoryItem();
        order.setOrderId(id);
        order.setOrderNumber(number);
        order.setOrderDate(date);
        order.setShippingAddress(shipTo);
        order.setOrderTotal(total);
        order.setOrderStatus(status);
        return order;
    }

}