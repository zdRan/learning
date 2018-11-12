import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Create by ranzd on 2018/11/12
 *
 * @author cm.zdran@gmail.com
 */
public class ApplicationMain {
    public static void main(String[] args) throws Exception {
        System.out.println("user start");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]
                {"dubbo/dubbo-server.xml"});
        context.start();
        System.out.println("user start 1");
        System.in.read();
    }
}
