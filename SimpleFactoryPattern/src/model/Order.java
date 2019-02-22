package model;

import java.util.List;

/**
 * Create by ranzd on 2019/2/20
 *
 * @author cm.zdran@gmail.com
 */
public class Order {
    private String ordrerId;

    private List<Goods> goodsList;

    public String getOrdrerId() {
        return ordrerId;
    }

    public void setOrdrerId(String ordrerId) {
        this.ordrerId = ordrerId;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
