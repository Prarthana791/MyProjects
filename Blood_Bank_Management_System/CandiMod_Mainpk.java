package candidate;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import donor.DonorFormpk;
import donordatabase.JTableDonor;
import recipientdatabase.JTableRecipient;
import queries.Queries_Mainpk;
import java.awt.event.*;
import java.util.Vector;



public class CandiMod_Mainpk extends JFrame implements ActionListener
{
JButton donor,recipient,newDonor,query;
JLabel jl1,jl2;
JComboBox jcb1,jcb2;
Font f;
public CandiMod_Mainpk(String s)
{ super(s);
  f=new Font("Lucida Bright",Font.BOLD,15);
  newDonor=new JButton("NEW DONOR");
   donor=new JButton("DONOR DATABASE");
  recipient=new JButton("RECIPIENT DATABASE");
  query=new JButton("QUERIES");
  jl1=new JLabel("COUNT");
   jl2=new JLabel("DETAILS");
  jcb1=new JComboBox();
  jcb2=new JComboBox();
setLayout(null);
newDonor.setBounds(50,50,200,50);
donor.setBounds(350,50,200,50);
recipient.setBounds(650,50,200,50);
query.setBounds(950,50,200,50);

add(newDonor);
add(donor);
add(recipient);add(query);
newDonor.addActionListener(this);donor.addActionListener(this);recipient.addActionListener(this);
query.addActionListener(this);
}

public void actionPerformed(ActionEvent ae)
{ 

String s=ae.getActionCommand();

if(s.equals("QUERIES"))
{
Queries_Mainpk q= new Queries_Mainpk("QUERIES OUTPUT");
q.setSize(1500,800);
q.setVisible(true);
q.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
} 

if(s.equals("NEW DONOR"))
{ 
DonorFormpk d=new DonorFormpk("DONOR & RECIPIENT FORM");
d.setSize(1500,800);
d.setVisible(true);
d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
}

if(s.equals("DONOR DATABASE"))
{
    JTableDonor frame=new JTableDonor("DONOR DATABASE");
    frame.setSize(800,500);
    frame.setVisible(true);
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    
 
}

if(s.equals("RECIPIENT DATABASE"))
{
    JTableRecipient frame1=new JTableRecipient("RECIPIENT DATABASE");
    frame1.setSize(800,500);
    frame1.setVisible(true);
frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
}
}