package ChainOfResposibility;

public class ATM {
    public static void main(String[] args) {
        Handler5 handler5 = new Handler5();
        Handler20 handler20 = new Handler20();
        Handler50 handler50 = new Handler50();
        handler50.setNext(handler20);
        handler20.setNext(handler5);
        handler50.process(125);
    }
}
