package blockChainService;

import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Main extends Thread {
    @Override
    public void run() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Dubbo.xml");
        context.start();
        while (true) {

        }
    }
}