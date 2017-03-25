package testing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.JOptionPane;
import blooddatabase.TestStatuspk;
import storagedatabase.BloodStockpk;
import testdatabase.JTableTest;

public class TestMod_Mainpk extends JFrame implements ActionListener{

JLabel pid,eid,h1,h2,vd,mal,hb,hc,tr;
JTextField jtf1,jtf2;
JPanel jp1;
JButton submit,test_status,test_result;
String s1,s2;Font fo;
JComboBox jcb1,jcb2,jcb3,jcb4,jcb5,jcb6;
JTableTest tt;
String r="";

    public TestMod_Mainpk(String s)
{
super(s);

jp1 = new JPanel();
jp1.setLayout(null);
jp1.setBounds(0,0,750,800);
fo=new Font("Lucida Bright",Font.BOLD,25);
tr=new JLabel("TEST RESULT");
pid= new JLabel("POUCH ID*");
eid= new JLabel("EMPLOYEE ID");
h1= new JLabel("HIV I");
h2= new JLabel("HIV II");
vd= new JLabel("VDRL");
mal= new JLabel("MALARIA");
hb= new JLabel("HEPATITIS B");
hc= new JLabel("HEPATITIS C");
jtf1=new JTextField(25);
jtf2=new JTextField(25);
jcb1=new JComboBox();
jcb2=new JComboBox();
jcb3=new JComboBox();
jcb4=new JComboBox();
jcb5=new JComboBox();
jcb6=new JComboBox();


submit=new JButton("SUBMIT");
test_status=new JButton("TEST STATUS");
test_result=new JButton("TEST RESULT");
tr.setFont(fo);
add(tr);
jp1.add(eid);jp1.add(jtf1);
jp1.add(pid); jp1.add(jtf2);
jp1.add(h1);
jp1.add(h2);jp1.add(jcb2);
jp1.add(vd); jp1.add(jcb3);
jp1.add(mal);jp1.add(jcb4);
jp1.add(hb);jp1.add(jcb5);
jp1.add(hc);jp1.add(jcb6);jp1.add(submit) ;submit.addActionListener(this);
jcb1.addItem("Positive");
jcb1.addItem("Negative");
jp1.add(jcb1);
jp1.add(test_status);
jp1.add(test_result);
test_status.setBounds(800,500,200,50);
test_result.setBounds(1100,500,200,50);

test_status.addActionListener(this);
test_result.addActionListener(this);
add(jp1);

jcb2.addItem("Positive");

jcb2.addItem("Negative");

jcb3.addItem("Positive");
jcb3.addItem("Negative");
jcb4.addItem("Positive");
jcb4.addItem("Negative");
jcb5.addItem("Positive");
jcb5.addItem("Negative");
jcb6.addItem("Positive");
jcb6.addItem("Negative");

tr.setBounds(250,10,500,35);
eid.setBounds(50,50,100,30);
jtf1.setBounds(150,50,200,30);
pid.setBounds(50,110,100,30);
jtf2.setBounds(150,110,200,30);
h1.setBounds(50,170,200,30);
jcb1.setBounds(150,170,200,30);
h2.setBounds(50,250,200,30);
jcb2.setBounds(150,250,200,30);
vd.setBounds(50,330,200,30);
jcb3.setBounds(150,330,200,30);
mal.setBounds(50,410,200,30);
jcb4.setBounds(150,410,200,30);
hb.setBounds(50,490,200,30);
jcb5.setBounds(150,490,200,30);
hc.setBounds(50,570,200,30);
jcb6.setBounds(150,570,200,30);
submit.setBounds(300,650,100,35);


tt=new JTableTest("TEST DATABASE");
tt.setSize(800,500);

 }
public void actionPerformed(ActionEvent ae)
{
String s;

s=ae.getActionCommand();
if(s.equals("TEST STATUS"))
{
TestStatuspk t1=new TestStatuspk("BLOOD DATABASE");
t1.setSize(800,500);
t1.setVisible(true);
t1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}

if(s.equals("TEST RESULT"))
{
tt.setVisible(true);
tt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}

else if(s.equals("SUBMIT"))
{

s1=jtf1.getText();
s2=jtf2.getText();

if(jcb1.getSelectedItem()=="Positive" || jcb2.getSelectedItem()=="Positive"|| jcb3.getSelectedItem()=="Positive"|| jcb4.getSelectedItem()=="Positive"||jcb5.getSelectedItem()=="Positive"||jcb6.getSelectedItem()=="Positive")
{
r="IMPURE BLOOD";
}
else
{
r="PURE BLOOD";
}

try
{
Connection con;
Statement st;
Class.forName("oracle.jdbc.driver.OracleDriver");
con=(Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","shivani","chotu");
st=con.createStatement();
st.executeQuery("insert into Test (e_id,pouch_id,HIV_I,HIV_II,VDRL,Malaria,Hepatitis_B,Hepatitis_C,result) values('"+s1+"','"+s2+"','"+jcb1.getSelectedItem()+"','"+jcb2.getSelectedItem()+"','"+jcb3.getSelectedItem()+"','"+jcb4.getSelectedItem()+"','"+jcb5.getSelectedItem()+"','"+jcb6.getSelectedItem()+"','"+r+"')");
JOptionPane.showMessageDialog(null, "Test Record Inserted");
jtf1.setText("");
jtf2.setText("");

st.close();
con.close();
}


catch(SQLException se){
      
      if(se.getErrorCode()==1)
        { JOptionPane.showMessageDialog(null, "Record Already Exists");}
      
else if(se.getErrorCode()==2291)
{ JOptionPane.showMessageDialog(null, "Pouch ID or Employee ID NOT FOUND");
}
      

   }

catch(Exception e)
{
System.out.println(" "+e.getMessage());
}
}
}
}

