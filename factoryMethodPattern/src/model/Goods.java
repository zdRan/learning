package model;

/**
 * 商品类
 * Create by ranzd on 2019/2/20
 *
 * @author cm.zdran@gmail.com
 */
public class Goods {

    private String sku;
    private String name;
    private String price;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
