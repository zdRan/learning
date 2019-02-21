import model.Goods;
import model.Order;
import util.HttpClientUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Create by ranzd on 2019/2/21
 *
 * @author cm.zdran@gmail.com
 */
public class TBMall implements Mall {
    @Override
    public Goods getGoodsInfo(String sku) {
        String result = HttpClientUtil.getMethod("tb/url/getGoodsInfo", new HashMap<>());
        return this.getGoodsByResult(result);
    }

    @Override
    public Order submitOrder(List<Goods> goods) {
        String result = HttpClientUtil.getMethod("tb/url/submitOrder", new HashMap<>());
        return this.getOrderByResult(result);
    }

    private Goods getGoodsByResult(String result) {
        return new Goods();
    }

    private Order getOrderByResult(String result) {
        return new Order();
    }
}
