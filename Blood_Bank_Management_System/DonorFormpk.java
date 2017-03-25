package donor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.JOptionPane;
import storagedatabase.BloodStockpk;


public class DonorFormpk extends JFrame implements ActionListener{

JLabel reg,donor_id,name,bld_grp, dtype,wt,bp, hb,age,sex,ldt, cnt,u1,u2,u3,pid,rid,rdt,rna,rwd,hos,doc,drf;
JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8,jtf11,jtf12,jtf13,jtf14,jtf15,jtf16,jtf17;
JRadioButton vol,rep,m,f;
ButtonGroup bg1,bg2; 
JComboBox bg;
JPanel jp1,jp2;
JButton ok,submit,back,cancel,rec_cancel,get_source,blood_stock;
String s1,s2,s3,s4,s5,s6,s7,s8,s11,s12,s13,s14,s15,s16,s17;
Font fo;

public DonorFormpk(String s)
{
super(s);
jp1 = new JPanel();
jp2=new JPanel();
jp1.setLayout(null);
jp1.setBounds(0,0,1500,400);
jp2.setVisible(false);
fo=new Font("Lucida Bright",Font.BOLD,25);
drf=new JLabel("DONOR REGISTRATION FORM");
donor_id= new JLabel("DONOR ID*");
name= new JLabel("NAME");
bld_grp= new JLabel("BLOOD GROUP");
dtype= new JLabel("TYPE OF DONOR");
wt= new JLabel("WEIGHT");
bp= new JLabel("BLOOD PRESSURE");
hb= new JLabel("HEMOGLOBIN CONTENT");
age= new JLabel("AGE");
sex= new JLabel("SEX");
ldt= new JLabel("LAST DONATION DATE");
cnt= new JLabel("MOBILE NUMBER");
u1=new JLabel("Kg");
u2=new JLabel("mm Hg");
u3=new JLabel("gm/dL");
bg=new JComboBox();
vol=new JRadioButton("VOLUNTARY"); 
rep=new JRadioButton("REPLACEMENT");
m=new JRadioButton("MALE");
f=new JRadioButton("FEMALE");
back=new JButton("BACK");
ok=new JButton("OK");
cancel=new JButton("CANCEL");
rec_cancel=new JButton("CANCEL");
blood_stock=new JButton("BLOOD STOCK");
bg1= new ButtonGroup();
bg2= new ButtonGroup();


jtf1=new JTextField(25);
jtf2=new JTextField(25);
jtf3=new JTextField(25);
jtf4=new JTextField(25);
jtf5=new JTextField(25);
jtf6=new JTextField(25);
jtf7=new JTextField(25);
jtf8=new JTextField(25);

drf.setFont(fo);
jp1.add(drf);
bg.addItem("NULL");
bg.addItem("A+");bg.addItem("A-");bg.addItem("B+");bg.addItem("B-");bg.addItem("AB+");bg.addItem("AB-");bg.addItem("O+");bg.addItem("O-");
jp1.add(donor_id);jp1.add(jtf1);
jp1.add(name); jp1.add(jtf2);
jp1.add(bld_grp);jp1.add(bg);
jp1.add(dtype); jp1.add(vol); jp1.add(rep); bg1.add(vol); bg1.add(rep);
jp1.add(wt);jp1.add(jtf3);jp1.add(u1);
jp1.add(bp);jp1.add(jtf4);jp1.add(u2);
jp1.add(hb);jp1.add(jtf5);jp1.add(u3);
jp1.add(age);jp1.add(jtf6);
jp1.add(sex);jp1.add(m); jp1.add(f); bg2.add(m); bg2.add(f); 
jp1.add(ldt);jp1.add(jtf7);
jp1.add(cnt);jp1.add(jtf8);jp1.add(ok);ok.addActionListener(this);get_source=(JButton) jp1.add(cancel);get_source.addActionListener(this);jp1.add(back);back.addActionListener(this);

add(jp1);

drf.setBounds(300,10,500,25);
donor_id.setBounds(50,50,100,20);
jtf1.setBounds(150,50,200,20);
name.setBounds(395,50,100,20);
jtf2.setBounds(450,50,200,20);
bld_grp.setBounds(50,110,100,20);
bg.setBounds(150,110,100,20);
dtype.setBounds(395,110,125,20);
vol.setBounds(500,110,100,20);
rep.setBounds(600,110,125,20);
age.setBounds(50,170,100,20);
jtf6.setBounds(150,170,100,20);
sex.setBounds(395,170,100,20);
m.setBounds(500,170,100,20);
f.setBounds(600,170,100,20);

wt.setBounds(50,230,100,20);
jtf3.setBounds(150,230,100,20);
u1.setBounds(250,230,30,20);
bp.setBounds(310,230,170,20);
jtf4.setBounds(440,230,100,20);
u2.setBounds(540,230,50,20);
hb.setBounds(620,230,190,20);
jtf5.setBounds(770,230,100,20);
u3.setBounds(870,230,50,20);
ldt.setBounds(50,290,150,20);
jtf7.setBounds(200,290,200,20);
cnt.setBounds(580,290,200,20);
jtf8.setBounds(720,290,200,20);
back.setBounds(850,10,75,35);
cancel.setBounds(750,330,90,35);
ok.setBounds(850,330,70,35);
//recipient

jp2.setLayout(null);
jp2.setBounds(0,400,1500,400);

pid=new JLabel("POUCH ID*");
rid=new JLabel("RECIPIENT ID*");  
rdt=new JLabel("RECEIVING DATE");
rna=new JLabel("RECIPIENT NAME");
rwd=new JLabel("RELATION WITH DONOR");
hos=new JLabel("HOSPITAL");
doc=new JLabel("DOCTOR");
submit=new JButton("SUBMIT");


jtf11=new JTextField(25);
jtf12=new JTextField(25);
jtf13=new JTextField(25);
jtf14=new JTextField(25);
jtf15=new JTextField(25);
jtf16=new JTextField(25);
jtf17=new JTextField(25);
 
jp2.add(pid);jp2.add(jtf11);jp2.add(rid);jp2.add(jtf12);
jp2.add(rdt);jp2.add(jtf13);jp2.add(rna);jp2.add(jtf14);
jp2.add(rwd);jp2.add(jtf15);jp2.add(hos);jp2.add(jtf16);
jp2.add(doc);jp2.add(jtf17);jp2.add(blood_stock);blood_stock.addActionListener(this);jp2.add(rec_cancel);rec_cancel.addActionListener(this);jp2.add(submit);submit.addActionListener(this);
add(jp2);

pid.setBounds(50,450,100,20);
jtf11.setBounds(150,450,200,20);
rid.setBounds(395,450,175,20);
jtf12.setBounds(500,450,100,20);
rdt.setBounds(670,450,100,20);
jtf13.setBounds(800,450,100,20);
rna.setBounds(50,510,125,20);
jtf14.setBounds(150,510,200,20);
rwd.setBounds(395,510,175,20);
jtf15.setBounds(550,510,100,20);
hos.setBounds(50,570,100,20);
jtf16.setBounds(150,570,200,20);
doc.setBounds(395,570,100,20);
jtf17.setBounds(500,570,200,20);
submit.setBounds(850,600,120,35);
blood_stock.setBounds(1100,500,200,50);
rec_cancel.setBounds(750,600,90,35);
                                                                                  
}

public void actionPerformed(ActionEvent ae)
{ 
String x="";
char y='\0';
String s=ae.getActionCommand();

if(s.equals("BLOOD STOCK"))
{
BloodStockpk t2=new BloodStockpk("STORAGE DATABASE");
t2.setSize(700,500);
t2.setVisible(true);
t2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}


if(ae.getSource()==get_source)
{
jtf1.setText("");
jtf2.setText("");
jtf3.setText("");
jtf4.setText("");
jtf5.setText("");
jtf6.setText("");
jtf7.setText("");
jtf8.setText("");

}
if(s.equals("CANCEL"))
{
jtf11.setText("");
jtf12.setText("");
jtf13.setText("");
jtf14.setText("");
jtf15.setText("");
jtf16.setText("");
jtf17.setText("");
}

if(s.equals("BACK"))
{
setVisible(false);
}


  if(s.equals("OK"))
   {
s1=jtf1.getText();
s2=jtf2.getText();
s3=jtf3.getText();
s4=jtf4.getText();
s5=jtf5.getText();
s6=jtf6.getText();
s7=jtf7.getText();
s8=jtf8.getText();

if(m.isSelected())
y='M';
if(f.isSelected())
y='F';

     if(rep.isSelected())
     { jp2.setVisible(true);
x="REPLACEMENT"; }
     else if(vol.isSelected())
     {  jp2.setVisible(false);
x="VOLUNTARY";}



try
{
Connection con;
Statement st;
Class.forName("oracle.jdbc.driver.OracleDriver");
con=(Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","shivani","chotu");
st=con.createStatement();
st.executeQuery("insert into Donor (donor_id,donor_name,blood_group,type_of_donor,weight,blood_pressure,hb_content,age,sex,last_donation_date,contact_no) values('"+s1+"','"+s2+"','"+bg.getSelectedItem()+"','"+x+"',"+s3+",'"+s4+"',"+s5+","+s6+",'"+y+"','"+s7+"','"+s8+"')");
JOptionPane.showMessageDialog(null, "Record of "+x+" Donor Inserted");
if(vol.isSelected()){
jtf1.setText("");
jtf2.setText("");
jtf3.setText("");
jtf4.setText("");
jtf5.setText("");
jtf6.setText("");
jtf7.setText("");jtf8.setText("");bg.setSelectedIndex(0);}
st.close();
con.close();

}

catch(SQLException se)
{

/*if(se.getErrorCode()==1403)
{ JOptionPane.showMessageDialog(null, "Please Enter Details Marked With *");
}*/


if(se.getErrorCode()==1)
        { JOptionPane.showMessageDialog(null, "Record of Donor Already Exists");
jtf1.setText("");


      }

 if(se.getErrorCode()==2290)
{JOptionPane.showMessageDialog(null, "Enter A 10 Digit Contact Number");
jtf8.setText("");}


}

catch(Exception e)
{
System.out.println(" "+e.getMessage());
}
}   
  
    else if(s.equals("SUBMIT"))
     { 

      jp2.setVisible(true);

s11=jtf11.getText();
s12=jtf12.getText();
s13=jtf13.getText();
s14=jtf14.getText();
s15=jtf15.getText();
s16=jtf16.getText();
s17=jtf17.getText();


try
{
Connection con;
Statement st;
Class.forName("oracle.jdbc.driver.OracleDriver");
con=(Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","shivani","chotu");
st=con.createStatement();
st.executeQuery("insert into Recipient (recipient_id,pouch_id,recipient_name,hospital_name,doctor_name,receiving_date,relation_with_donor) values('"+s12+"','"+s11+"','"+s14+"','"+s16+"','"+s17+"','"+s13+"','"+s15+"')");
JOptionPane.showMessageDialog(null,"Record of Recipient Inserted");
jtf1.setText("");
jtf2.setText("");
jtf3.setText("");
jtf4.setText("");
jtf5.setText("");
jtf6.setText("");
jtf7.setText("");
jtf8.setText("");bg.setSelectedIndex(0);
jtf11.setText("");
jtf12.setText("");
jtf13.setText("");
jtf14.setText("");
jtf15.setText("");
jtf16.setText("");
jtf17.setText("");
jp2.setVisible(false);
 
st.close();
con.close();
}

catch(SQLException se)
{
System.out.println(" "+se.getMessage());
if(se.getErrorCode()==2291)
{JOptionPane.showMessageDialog(null, "Pouch ID Does Not Exist");
jtf11.setText("");}

if(se.getErrorCode()==1)
{ JOptionPane.showMessageDialog(null, "Pouch ID Has Been Already Issued");
jtf11.setText("");
}

if(se.getErrorCode()==20001)
{ JOptionPane.showMessageDialog(null, "Pouch Has Expired. Cannot Be Issued");
jtf13.setText("");
}

if(se.getErrorCode()==1403)
{ JOptionPane.showMessageDialog(null, "Please Enter Details Marked With *");
}


}



catch(Exception e)
{
System.out.println(" "+e.getMessage());
}
   }

}

}
 

 
  









