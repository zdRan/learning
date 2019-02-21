/**
 * 工厂类，产生具体的产品
 * Create by ranzd on 2019/2/21
 *
 * @author cm.zdran@gmail.com
 */
public class MallFactory {

    public static Mall getMall(String name) {
        if ("JD".equals(name)) {
            return new JDMall();
        }
        if ("TM".equals(name)) {
            return new TBMall();
        }
        return null;
    }
}
