package practice3;

import java.math.BigDecimal;
import java.util.List;

public class PriceCaculator {
    private BigDecimal mSubtotal;

    public PriceCaculator(int value) {
        mSubtotal = new BigDecimal(value);
    }

    public BigDecimal totalUpLineItems(BigDecimal subtotal, List<OrderLineItem> orderLineItemList) {
        for (OrderLineItem lineItem : orderLineItemList) {
            subtotal = subtotal.add(lineItem.getPrice());
        }
        return subtotal;
    }

    public BigDecimal subtractDiscounts(BigDecimal subtotal, List<BigDecimal> discounts) {
        for (BigDecimal discount : discounts) {
            subtotal = subtotal.subtract(discount);
        }
        return subtotal;
    }

    public BigDecimal calculateTax(BigDecimal subtotal, BigDecimal tax) {
        return subtotal.multiply(tax);
    }

    public BigDecimal calculateGrandTotal(BigDecimal subtotal, BigDecimal tax) {
        return subtotal.add(tax);
    }


    public BigDecimal getSubtotal() {
        return mSubtotal;
    }
}
