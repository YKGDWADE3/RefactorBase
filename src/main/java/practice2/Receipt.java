package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {


    public static final double TAX_VAL = 0.1;
    public static final int NEW_SCALE = 2;

    private BigDecimal tax;

    public Receipt() {
        tax = new BigDecimal(TAX_VAL);
        tax = setNewScale(tax);
    }

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = calculateSubtotal(products, items);
        BigDecimal grandTotal = subTotal.add(subTotal.multiply(tax));
        return setNewScale(grandTotal).doubleValue();
    }

    private BigDecimal setNewScale(BigDecimal bigDecimal) {
        return bigDecimal.setScale(NEW_SCALE, BigDecimal.ROUND_HALF_UP);
    }


    private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = new BigDecimal(0);
        for (Product product : products) {
            OrderItem item = findOrderItemByProduct(items, product);
            BigDecimal itemTotal = getItemTotal(product, item);
            BigDecimal reducePrice = getReducePrice(product, item);
            subTotal = subTotal.add(itemTotal).subtract(reducePrice);
        }
        return subTotal;
    }

    private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {
        OrderItem curItem = null;
        for (OrderItem item : items) {
            if (item.getCode() == product.getCode()) {
                curItem = item;
                break;
            }
        }
        return curItem;
    }

    private BigDecimal getReducePrice(Product product, OrderItem orderItem) {
        return getItemTotal(product, orderItem).multiply(product.getDiscountRate());
    }

    private BigDecimal getItemTotal(Product product, OrderItem orderItem) {
        return product .getPrice()
                .multiply(new BigDecimal(orderItem.getCount()));

    }
}
