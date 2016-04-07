package se.tedro.bootstrap;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.tedro.bootstrap.api.Brewer;

public class CoffeeMakerSpring {
    public static void main(String[] argv) {
        final AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        CoffeeMaker.run(() -> context.getBean(Brewer.class));

        context.destroy();
    }
}
