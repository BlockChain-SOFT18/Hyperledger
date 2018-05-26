import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class Main extends Thread {
    @Override
    public void run() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        context.start();
        while (true) {

        }
    }
}