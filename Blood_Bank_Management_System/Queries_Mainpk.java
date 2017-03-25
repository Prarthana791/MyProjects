package queries;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.Vector;
import qdonordatabase.JTableQDonor;
import qpouchdatabase.JTableQPouch;
import qyear.JTableQYear;
import qemp.JTableQEmp;
public class Queries_Mainpk extends JFrame implements ActionListener
{

JButton bg,year,fridge,pouch,donor,ok2,emp,ok1;
JPanel jp1,jp2,jpb,jpy,jpf,jpe;
JComboBox cbg,cyr,cbe;
CardLayout card;
JLabel lbg,lyr,lemp;long i;
ResultSet rs;
ResultSetMetaData md;

 Vector columnNames;
 Vector data;JTable table;JScrollPane scrollPane;


public Queries_Mainpk(String s)
{
super(s);
setLayout(null);
jp1=new JPanel();jp2=new JPanel();
jpb=new JPanel();
jpy=new JPanel();
jpf=new JPanel();
jpe=new JPanel();
pouch=new JButton("SEARCH POUCHES");
donor=new JButton("SEARCH DONORS");
ok2=new JButton("DONE");
emp=new JButton("TESTED BY");
ok1=new JButton("SEARCH RESULT");
bg=new JButton("BLOOD GROUP");
year=new JButton("DONATION YEAR");
fridge=new JButton("FRIDGE DETAILS");
 lbg=new JLabel("Blood Group : ");
lyr=new JLabel("Year");
lemp=new JLabel("EMPLOYEE ID");
 card=new CardLayout();
cbg=new JComboBox();
cyr=new JComboBox();
cbe=new JComboBox();
jp1.setBounds(0,0,400,800);
jp2.setBounds(400,0,1100,800);
jp2.setLayout(card);


cbg.addItem("NULL");
cbg.addItem("A+");cbg.addItem("A-");cbg.addItem("B+");cbg.addItem("B-");cbg.addItem("AB+");cbg.addItem("AB-");cbg.addItem("O+");cbg.addItem("O-");

cyr.addItem("NULL");
for(i=1990;i<=2020;i++)
{ cyr.addItem(i); }
cbe.addItem("NULL");
cbe.addItem("E1");cbe.addItem("E2");cbe.addItem("E3");cbe.addItem("E4");cbe.addItem("E5");

jpe.add(lemp);jpe.add(cbe);jpe.add(ok1);
jpb.add(lbg); jpb.add(cbg);jpb.add(pouch);jpb.add(donor);
jpy.add(lyr); jpy.add(cyr);jpy.add(ok2);
jp1.add(bg);
jp1.add(year);
jp1.add(fridge);jp1.add(emp);
jp2.add(jpb,"BLOOD GROUP"); jp2.add(jpy,"DONATION YEAR"); jp2.add(jpf,"FRIDGE DETAILS");jp2.add(jpe,"TESTED BY");
add(jp1); add(jp2);
bg.addActionListener(this);
year.addActionListener(this);
fridge.addActionListener(this);
pouch.addActionListener(this);
donor.addActionListener(this);
ok2.addActionListener(this);
ok1.addActionListener(this);
emp.addActionListener(this);

 columnNames = new Vector();
   data = new Vector();
     try{
    Class.forName("oracle.jdbc.driver.OracleDriver");
     Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "shivani", "chotu");
        Statement st = con.createStatement();
        ResultSet rs= st.executeQuery("SELECT * FROM Fridge");
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

 table = new JTable(data, columnNames);
table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    table.getColumnModel().getColumn(0).setPreferredWidth(150);
    table.getColumnModel().getColumn(1).setPreferredWidth(200);
    table.getColumnModel().getColumn(2).setPreferredWidth(170);
   

 scrollPane = new JScrollPane(table);
jpf.add(scrollPane);

}


public void actionPerformed(ActionEvent ae)
{ String s=ae.getActionCommand();

if(s.equals("BLOOD GROUP"))
{ 
card.show(jp2,"BLOOD GROUP");
}

else if(s.equals("DONATION YEAR"))
{ card.show(jp2,"DONATION YEAR");}
else if(s.equals("FRIDGE DETAILS"))
{ card.show(jp2,"FRIDGE DETAILS");}
else if(s.equals("TESTED BY"))
{ card.show(jp2,"TESTED BY");}
if(s.equals("SEARCH DONORS"))
{
String s1=(String)cbg.getSelectedItem();
JTableQDonor jtq=new JTableQDonor("DONORS",s1);
jtq.setSize(540,500);
jtq.setVisible(true);
jtq.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}

if(s.equals("SEARCH POUCHES"))
{
String s1=(String)cbg.getSelectedItem();
JTableQPouch jtq=new JTableQPouch("POUCHES",s1);
jtq.setSize(330,500);
jtq.setVisible(true);
jtq.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}

if(s.equals("DONE"))
{
String s1=(cyr.getSelectedItem()).toString();
JTableQYear jtq=new JTableQYear("YEAR",s1);
jtq.setSize(480,500);
jtq.setVisible(true);
jtq.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}

if(s.equals("SEARCH RESULT"))
{
String s1=(String)cbe.getSelectedItem();
JTableQEmp jtq=new JTableQEmp("RESULT",s1);
jtq.setSize(800,500);
jtq.setVisible(true);
jtq.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}


}
}

  