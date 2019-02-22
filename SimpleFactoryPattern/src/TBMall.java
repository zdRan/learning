import model.Goods;
import model.Order;
import util.HttpClientUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 具体的产品类，封装淘宝API
 * Create by ranzd on 2019/2/21
 *
 * @author cm.zdran@gmail.com
 */
public class TBMall implements Mall {
    @Override
    public Goods getGoodsInfo(String sku) {
        //获取对应供应商需要的参数
        Map<String, String> extend = new HashMap<>();

        String result = HttpClientUtil.getMethod("tb/url/getGoodsInfo", extend);
        return this.getGoodsByResult(result);
    }

    @Override
    public Order submitOrder(List<Goods> goods) {
        //获取对应供应商需要的参数
        Map<String, String> extend = new HashMap<>();

        String result = HttpClientUtil.getMethod("tb/url/submitOrder", extend);
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
