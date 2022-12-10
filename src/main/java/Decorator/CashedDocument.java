package Decorator;

import java.sql.*;

public class CashedDocument implements Document {
    public Document document;
    public String gcsPath;
    public String data;

    public CashedDocument(Document document) {
        this.document = document;
    }
    private String find() {
        Connection c = null;
        String sql = "SELECT data "
                + "FROM docs WHERE path = ?";
        try {
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, gcsPath);
            ResultSet doc = pstmt.executeQuery();
            return doc.getString("data");
        } catch ( Exception e) {
            return  null;
        }
    }
    private void insert(){
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            Statement stmt = c.createStatement();
            String sql = "INSERT INTO docs (path, data) " +
                    "VALUES ('"+gcsPath+"', '"+data+"');";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    @Override
    public String parse() {
        if (document instanceof SmartDocument) {
            TimedDocument newDoc = new TimedDocument(document);
            gcsPath = ((SmartDocument) document).getGcsPath();
            data = newDoc.parse();
        } else {
            gcsPath = ((TimedDocument) document).getGcsPath();
            data = document.parse();
        }
        if(this.find() == null){
            this.insert();
            return this.find() + "This document was added to database.\n";
        } else {
           return this.find() + "This document was already added to database.\n";
        }
    }

}
