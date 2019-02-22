import model.Goods;
import model.Order;

import java.util.List;

/**
 * 平台模块入口
 * Create by ranzd on 2019/2/21
 *
 * @author cm.zdran@gmail.com
 */
public class MallController {

    public Goods getGoodsInfo(String sku, String channel) {
        Mall mall = MallFactory.getMall(channel);
        if (mall == null) {
            return null;
        }
        return mall.getGoodsInfo(sku);
    }

    public Order submitOrder(List<Goods> goods, String channel) {
        Mall mall = MallFactory.getMall(channel);
        if (mall == null) {
            return null;
        }
        return mall.submitOrder(goods);
    }

    //其他接口
}
