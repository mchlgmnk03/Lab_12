package Decorator;

public class Main {
    public static void main(String[] args) {
        Document document = new SmartDocument("gs://mykhailo_hum/Screenshot 2022-12-01 at 12.54.00 PM.png");
        document = new TimedDocument(document);
        document = new CashedDocument(document);
        System.out.println(document.parse());


    }
}
