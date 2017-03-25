import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import candidate.CandiMod_Mainpk;
import testing.TestMod_Mainpk;
import empdatabase.JTableEmp;

class Module extends JFrame implements ActionListener,Runnable{

Thread t;
JButton ad,can,stt;
JLabel ibb,imageLabel,adlabel,canlabel,sttlabel;
Font f,f1;

Module(String s)
{
super(s);
t=new Thread(this);
ad=new JButton("1",new ImageIcon("AD.jpg"));
can=new JButton("2",new ImageIcon("CAN.jpg"));
stt=new JButton("3",new ImageIcon("STT.jpg"));
f=new Font("Adobe Garamond Pro Bold",Font.BOLD,35);
f1=new Font("Myriad Pro",Font.BOLD,20);

adlabel= new JLabel("ADMINISTRATION");
canlabel= new JLabel("CANDIDATE");
sttlabel= new JLabel("BLOOD SCREENING & STORAGE");
ImageIcon image = new ImageIcon("TESTEDLayout.jpg");
imageLabel = new JLabel(image);

ibb= new JLabel("INDU BLOOD BANK");
ibb.setForeground(Color.red);
ibb.setFont(f);ad.addActionListener(this);can.addActionListener(this);stt.addActionListener(this);

adlabel.setFont(f1);
canlabel.setFont(f1);
sttlabel.setFont(f1);

ad.setToolTipText("ADMINISTRATION");
can.setToolTipText("CANDIDATE");
stt.setToolTipText("BLOOD SCREENING & STORAGE");


setLayout(null);
ad.setBounds(160,300,200,200);
can.setBounds(560,300,200,200);
stt.setBounds(960,300,200,200);
ibb.setBounds(200,55,500,35);
adlabel.setBounds(175,540,200,20);
canlabel.setBounds(600,540,200,20);
sttlabel.setBounds(905,540,500,20);

imageLabel.setBounds(0,0,1500,800);
add(ibb);add(ad);add(can);add(stt);add(adlabel);add(canlabel);add(sttlabel);add(imageLabel);
t.start();}

public void actionPerformed(ActionEvent ae)
{
String s=ae.getActionCommand();

if(s.equals("1"))
{
JTableEmp e=new JTableEmp("EMPLOYEES");
e.setSize(1500,800);
e.setVisible(true);
e.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}

if(s.equals("2"))
{
CandiMod_Mainpk c=new CandiMod_Mainpk("CANDIDATE");
c.setSize(1500,800);
c.setVisible(true);
c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}

if(s.equals("3"))
{
TestMod_Mainpk t=new TestMod_Mainpk("TEST RESULTS");
t.setSize(1500,800);
t.setVisible(true);
t.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
}
public void run() {

int column=200;
while(true)
{
if(column>1520)
{
column=200;
}
else
{
column+=5;}
ibb.setLocation(column,55);

try{
Thread.sleep(50);
}
catch(Exception e)
{}

}
}
}


public class MainMod {

public static void main(String args[]) {

Module f=new Module("WELCOME TO INDU BLOOD BANK");
f.setSize(1500,800);
f.setVisible(true);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}

