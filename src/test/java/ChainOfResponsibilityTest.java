import ChainOfResposibility.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



public class ChainOfResponsibilityTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void test125() {
        Handler5 handler5 = new Handler5();
        Handler20 handler20 = new Handler20();
        Handler50 handler50 = new Handler50();
        handler50.setNext(handler20);
        handler20.setNext(handler5);
        handler50.process(125);
        assertEquals("1 * 5\n" +
                "1 * 20\n" +
                "2 * 50\n", outContent.toString());
    }

    @Test
    public void test150() {
        Handler5 handler5 = new Handler5();
        Handler20 handler20 = new Handler20();
        Handler50 handler50 = new Handler50();
        handler50.setNext(handler20);
        handler20.setNext(handler5);
        handler50.process(150);
        assertEquals("0 * 5\n" +
                "0 * 20\n" +
                "3 * 50\n", outContent.toString());
    }

    @Test
    public void test175() {
        Handler5 handler5 = new Handler5();
        Handler20 handler20 = new Handler20();
        Handler50 handler50 = new Handler50();
        handler50.setNext(handler20);
        handler20.setNext(handler5);
        handler50.process(175);
        assertEquals("1 * 5\n" +
                "1 * 20\n" +
                "3 * 50\n", outContent.toString());
    }
    @Test(expected = IllegalArgumentException.class)
    public void test126() {
        Handler5 handler5 = new Handler5();
        Handler20 handler20 = new Handler20();
        Handler50 handler50 = new Handler50();
        handler50.setNext(handler20);
        handler20.setNext(handler5);
        handler50.process(126);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test151() {
        Handler5 handler5 = new Handler5();
        Handler20 handler20 = new Handler20();
        Handler50 handler50 = new Handler50();
        handler50.setNext(handler20);
        handler20.setNext(handler5);
        handler50.process(151);
    }
}
