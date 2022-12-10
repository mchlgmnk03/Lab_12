package Decorator;

import lombok.Getter;

public class TimedDocument implements Document {
    public Document document;
    @Getter
    public String gcsPath;
    public TimedDocument(Document document) {
        this.document = document;
        this.gcsPath = ((SmartDocument) document).getGcsPath();
    }

    @Override
    public String parse() {
        long start = System.currentTimeMillis();
        String result = document.parse();
        long end = System.currentTimeMillis();
        return result + "Parsing time: " + (end - start) + "ms.\n";
    }
}

