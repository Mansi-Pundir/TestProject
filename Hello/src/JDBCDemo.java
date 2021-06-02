import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class Demo1 implements ActionListener
{
   JFrame frame=new JFrame("JDBC Demo");
   Container con=frame.getContentPane();
   JLabel Imainhead,Iname,Iemail,Imobile,Igender,Idob,Ipassword,Iregis,Isearch,Idelete;
   JTextField tname,temail,tmobile,tsearch,tdelete;
   JPasswordField pass;
   JRadioButton r1,r2;
   ButtonGroup gender=new ButtonGroup();
   JComboBox day,month,year;
   JCheckBox term;
   JButton submit,reset,showall,search,delete;
   JTextArea display;
   Demo1()
   {
      frame.setBounds(150,90,1000,600);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      con.setLayout(null);
      con.setBackground(Color.YELLOW);
      
      Font f=new Font("This New Roman",Font.BOLD,30);
      Imainhead=new JLabel("JDBC Demo");
      Imainhead.setBounds(395,5,400,35);
      Imainhead.setFont(f);
      con.add(Imainhead);

      Iname=new JLabel("Name");
      Iname.setBounds(50,50,60,30);
      con.add(Iname);
    
      Iemail=new JLabel("Email");
      Iemail.setBounds(50,250,60,30);
      con.add(Iemail);

      Imobile=new JLabel("Mobile No");
      Imobile.setBounds(50,250,60,30);
      con.add(Imobile);

      Igender=new JLabel("Gender");
      Igender.setBounds(50,100,60,30);
      con.add(Igender);

      Idob=new JLabel("DOB");
      Idob.setBounds(50,150,60,30);
      con.add(Idob);

      Ipassword=new JLabel("Password");
      Ipassword.setBounds(50,300,60,30);
      con.add(Ipassword);

      tname=new JTextField();
      tname.setBounds(130,53,180,20);
      con.add(tname);

      r1=new JRadioButton("Male");
      r1.setBounds(130,103,80,30);
      r1.setBackground(Color.YELLOW);
      con.add(r1);

      r2=new JRadioButton("Female");
      r2.setBounds(230,103,80,30);
      r2.setBackground(Color.YELLOW);
      con.add(r2);
   
      gender.add(r1);
      gender.add(r2);
  
      temail=new JTextField();
      temail.setBounds(130,253,180,20);
      con.add(temail);
  
      tmobile=new JTextField();
      tmobile.setBounds(130,203,180,20);
      con.add(tmobile);

      pass=new JPasswordField();
      pass.setBounds(130,303,180,20);
      con.add(pass);
  
      String[] daylist=new String[31];
      for(int i=1;i<=31;i++)
      {
        daylist[i-1]=Integer.toString(i);
      }
      day=new JComboBox(daylist);
      day.setBounds(130,153,40,25);
      day.setEditable(true);
      con.add(month);

      String[] monthlist={"jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};
      month=new JComboBox(monthlist);
      month.setBounds(185,153,50,25);
      month.setEditable(true);
      con.add(month);

      String[] yearlist=new String[100];
      for(int i=1951;i<=2050;i++)
      {
        yearlist[i-1951]=Integer.toString(i);
      }
      year=new JComboBox(yearlist);
      year.setBounds(250,153,60,25);
      year.setEditable(true);
      con.add(year);

      term=new JCheckBox("I accept terms and conditions");
      term.setBounds(70,340,250,25);
      term.setBackground(Color.YELLOW);
      con.add(term);

      submit=new JButton("SUBMIT");
      submit.setBounds(100,380,80,25);
      con.add(submit);

      reset=new JButton("RESET");
      reset.setBounds(200,380,80,25);
      con.add(reset);

      Iregis=new JLabel("REGISTRAION");
      Iregis.setBounds(90,420,200,50);
      Iregis.setFont(f);
      con.add(Iregis);

      Color cc=new Color(170,170,170);
      Font ff=new Font("Times New Roman",Font.BOLD,15);
      display.setBounds(660,50,300,450);
      con.add(display);
      display.setFont(ff);
      display.setBackground(cc);
      display.setForeground(Color.WHITE);
      display.setEditable(false);
  
      showall=new JButton("SHOW DATA");
      showall.setBounds(370,80,200,30);
      con.add(showall);

      Isearch=new JLabel("Search Name");
      Isearch.setBounds(390,160,250,30);
      Isearch.setFont(f);
      con.add(Isearch);

      tsearch=new JTextField();
      tsearch.setBounds(370,200,200,20);
      con.add(tsearch);

      search=new JButton("SEARCH");
      search.setBounds(390,240,250,30);
      con.add(search);

      Idelete=new JLabel("Delete Name");
      Idelete.setBounds(390,320,250,30);
      Idelete.setFont(f);
      con.add(Idelete);

      tdelete=new JTextField();
      tdelete.setBounds(370,360,200,20);
      con.add(tdelete);

      delete=new JButton("DELETE");
      delete.setBounds(390,400,140,30);
      con.add(delete);

      submit.addActionListener(this);
      reset.addActionListener(this);
      showall.addActionListener(this);
      search.addActionListener(this);
      delete.addActionListener(this);
}
public void actionPerformed(ActionEvent e)
{
   String ch=e.getActionCommand();
   if(ch=="SUBMIT")
   {
     if(term.isSelected())
     {
       String name=tname.getText();
       String email=temail.getText();
       String mobile=tmobile.getText();
       String password=pass.getText();
       String gen="Male";
       if(r2.isSelected())
       {
         gen="Female";
       }
       String date=(String)day.getSelectedItem();
       String mon=(String)month.getSelectedItem();
       String yr=(String)year.getSelectedItem();
      try
        {
        try
        {
          Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException ce)
         {
           display.setText("Loding Driver....");
         }
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/d?useSSL=false","root","root");
         String sql="insert into data(name,gender,day,month,year,mobile,email,pass) values(?,?,?,?,?,?,?,?)";
         PreparedStatement st=con.prepareStatement(sql);
         st.setString(1,name);
         st.setString(2,gen);
         st.setString(3,date);
         st.setString(4,mon);
         st.setString(5,yr);
         st.setString(6,mobile);
         st.setString(7,email);
         st.setString(8,password);
         
         st.executeUpdate();
         
         display.setText("registration successfull");
        }catch(Exception ae) 
       {
       display.setText("Something might be.....");
       }
     }
    else
   {  
    display.setText("Please agree "); 
   }
   }
   else if(ch=="SHOW DATA")
   {
     
   }
   else if(ch=="SEARCH")
   {
     
   }
   else if(ch=="DELETE")
   {
     
   }
  else{
   tname.setText(null);
   temail.setText(null);
   tmobile.setText(null);
   pass.setText(null);
   tsearch.setText(null);
   tdelete.setText(null);
   display.setText(null);

   gender.clearSelection();
   term.setSelected(false);
   day.setSelectedIndex(0);
   month.setSelectedIndex(0);
   year.setSelectedIndex(0);
  }
}
}
class JDBCDemo    
{
  public static void main(String args[])
  {
    Demo1 j=new Demo1();
  }
} 