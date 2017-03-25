package qpouchdatabase;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import java.awt.event.*;
import java.util.Vector;

public class JTableQPouch extends JFrame{
    ResultSet rs;
String a;
    public JTableQPouch(String s,String s1){
    super(s);
a=s1;
    final Vector columnNames = new Vector();
        final Vector data = new Vector();
     try{
    Class.forName("oracle.jdbc.driver.OracleDriver");
     Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "shivani", "chotu");
        Statement st = con.createStatement();
        ResultSet rs= st.executeQuery("SELECT s.pouch_id,s.fridge_id FROM BloodStorage s,Blood b WHERE s.pouch_id=b.pouch_id AND b.blood_group='"+a+"'");

    ResultSetMetaData md = rs.getMetaData();
int columns = md.getColumnCount();
for (int i = 1; i <= columns; i++) {
columnNames.addElement( md.getColumnName(i) );
}
while (rs.next()) {
Vector row = new Vector(columns);
for (int i = 1; i <= columns; i++) {
row.addElement( rs.getObject(i) );
}
data.addElement( row );
}
}
catch(Exception e){}

JTable table = new JTable(data, columnNames);
table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    table.getColumnModel().getColumn(0).setPreferredWidth(100);
    table.getColumnModel().getColumn(1).setPreferredWidth(150);
    
JScrollPane scrollPane = new JScrollPane(table);
add(scrollPane);
}
}