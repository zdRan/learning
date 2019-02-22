import model.Goods;
import model.Order;
import util.HttpClientUtil;

import java.util.HashMap;
import java.util.List;

/**
 * 具体的产品类，封装京东API
 * Create by ranzd on 2019/2/21
 *
 * @author cm.zdran@gmail.com
 */
public class JDMall implements Mall {
    @Override
    public Goods getGoodsInfo(String sku) {
        String result = HttpClientUtil.getMethod("jd/url/getGoodsInfo", new HashMap<>());
        return this.getGoodsByResult(result);
    }

    @Override
    public Order submitOrder(List<Goods> goods) {
        String result = HttpClientUtil.getMethod("jd/url/submitOrder", new HashMap<>());
        return this.getOrderByResult(result);
    }
    //格式化返回值，适配本地模型
    private Goods getGoodsByResult(String result) {
        return new Goods();
    }
    //格式化返回值，适配本地模型
    private Order getOrderByResult(String result) {
        return new Order();
    }
}
