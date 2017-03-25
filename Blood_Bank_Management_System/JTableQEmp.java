package qemp;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import java.awt.event.*;
import java.util.Vector;

public class JTableQEmp extends JFrame{
    ResultSet rs;
String a;
    public JTableQEmp(String s,String s1){
    super(s);
a=s1;
    final Vector columnNames = new Vector();
        final Vector data = new Vector();
    try{
    Class.forName("oracle.jdbc.driver.OracleDriver");
     Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "shivani", "chotu");
        Statement st = con.createStatement();
        ResultSet rs= st.executeQuery("SELECT pouch_id,hiv_I,hiv_II,vdrl,malaria,hepatitis_b,hepatitis_c,result FROM Test WHERE e_id='"+a+"'");
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
    table.getColumnModel().getColumn(1).setPreferredWidth(100);
    table.getColumnModel().getColumn(2).setPreferredWidth(100);
    table.getColumnModel().getColumn(3).setPreferredWidth(100);
    table.getColumnModel().getColumn(4).setPreferredWidth(100);
    table.getColumnModel().getColumn(5).setPreferredWidth(100);
    table.getColumnModel().getColumn(6).setPreferredWidth(100);
    table.getColumnModel().getColumn(7).setPreferredWidth(100);

    



JScrollPane scrollPane = new JScrollPane(table);
add(scrollPane);
}
}