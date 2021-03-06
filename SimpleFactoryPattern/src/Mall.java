import model.Goods;
import model.Order;

import java.util.List;

/**
 * 抽象产品类
 * Create by ranzd on 2019/2/20
 *
 * @author cm.zdran@gmail.com
 */
public interface Mall {
    /**
     * 获取商品信息
     *
     * @param sku 商品 id
     * @return 商品信息
     */
    Goods getGoodsInfo(String sku);

    /**
     * 下单接口
     *
     * @param goods 商品集合
     * @return 订单
     */
    Order submitOrder(List<Goods> goods);

    //其他接口 ... ...

}
