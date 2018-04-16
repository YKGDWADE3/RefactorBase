package practice3;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    public static final double TAX_VAL = 0.1;
    public static final int SUBTOTAL_VALUE = 0;

    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;

    public Order(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts) {
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = new BigDecimal(TAX_VAL);
    }

    public BigDecimal calculate() {
        PriceCaculator priceCaculator = new PriceCaculator(SUBTOTAL_VALUE);
        BigDecimal sub = priceCaculator.getSubtotal();

        // Total up line items
        BigDecimal lineItem = priceCaculator.totalUpLineItems(sub, orderLineItemList);

        // Subtract discounts
        BigDecimal subTotal = priceCaculator.subtractDiscounts(lineItem, discounts);

        // calculate tax
        BigDecimal taxTotal = priceCaculator.calculateTax(subTotal, tax);

        // calculate GrandTotal
        BigDecimal grandTotal = priceCaculator.calculateGrandTotal(subTotal, taxTotal);

        return grandTotal;
    }
}
