import Decorator.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class DecoratorTest {
    @Test
    public void parsing() {
        Document document = new SmartDocument("gs://mykhailo_hum/Screenshot 2022-12-01 at 12.54.00 PM.png");
        assertEquals("iPad Pro\n" +
                "Supercharged by\n" +
                "Learn more > Buy >\n" +
                "M2\n", document.parse());
    }
}
