import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationForm extends JFrame implements ActionListener{

    JLabel label1, label2,label3,label4,label5,label6,label7,label8;
    JTextField t1,t2,t3,t4,t5,t6;
    JRadioButton male,female;
    JTextArea ta1;
    JCheckBox terms;
    JButton submit;
    JLabel msg,msg1;
    JTextArea screen;

    final private Font mainFont = new Font("Times New Roman",Font.BOLD,16); 

    void initialize()
    {
        setTitle("Registration Form");
        setSize(700,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
         
        Container c=getContentPane();
        c.setLayout(null);

        label1=new JLabel("Name: ");
        label1.setBounds(20,50,100,20);
        label1.setFont(mainFont);
        c.add(label1);
        
        t1=new JTextField();
        t1.setBounds(150,50,140,20);
        t1.setFont(mainFont);
        c.add(t1);
        
        label2=new JLabel("Mobile Number: ");
        label2.setBounds(20,100,120,20);
        label2.setFont(mainFont);
        c.add(label2);
        
        t2=new JTextField();
        t2.setBounds(150,100,140,20);
        t2.setFont(mainFont);
        c.add(t2);

        label6=new JLabel("Email: ");
        label6.setBounds(350,50,100,20);
        label6.setFont(mainFont);
        c.add(label6);

        t3=new JTextField();
        t3.setBounds(490,50,140,20);
        t3.setFont(mainFont);
        c.add(t3);

        label7=new JLabel("Password: ");
        label7.setBounds(350,100,100,20);
        label7.setFont(mainFont);
        c.add(label7);

        t4=new JTextField();
        t4.setBounds(490,100,140,20);
        t4.setFont(mainFont);
        c.add(t4);
        

        label8=new JLabel(" Confirm Password: ");
        label8.setBounds(350,150,140,20);
        label8.setFont(mainFont);
        c.add(label8);

        t5=new JTextField();
        t5.setBounds(490,150,140,20);
        t5.setFont(mainFont);
        c.add(t5);

        
        label3=new JLabel("Gender: ");
        label3.setBounds(20,150,100,20);
        label3.setFont(mainFont);
        c.add(label3);

        male=new JRadioButton("Male");
        female=new JRadioButton("Female");
        male.setBounds(130,150,80,20);
        female.setBounds(220,150,80,20);
        
        c.add(male);
        c.add(female);

        ButtonGroup gen= new ButtonGroup();
        gen.add(male);
        gen.add(female);
        
        label4=new JLabel("Date of Birth: ");
        label4.setBounds(20,200,100,20);
        label4.setFont(mainFont);
        c.add(label4);
        

        t6=new JTextField();
        t6.setBounds(150,200,140,20);
        t6.setFont(mainFont);
        c.add(t6);
        
        label5=new JLabel("Address");
        label5.setBounds(20,250,100,20);
        label5.setFont(mainFont);
        c.add(label5);

        ta1=new JTextArea();
        ta1.setBounds(130,240,200,50);
        ta1.setFont(mainFont);
        c.add(ta1);
       
        terms=new JCheckBox("Please accept terms and conditions.");
        terms.setBounds(150,330,280,20);
        terms.setFont(mainFont);
        c.add(terms);

      submit=new JButton("SUBMIT");
      submit.setBounds(290,370,110,20);
      submit.setFont(mainFont);
      c.add(submit);
      
      submit.addActionListener(this);
      
      msg=new JLabel();
      msg.setBounds(20,400,400,20);
      msg.setFont(mainFont);
      c.add(msg);

      msg1=new JLabel();
      msg1.setBounds(20,420,350,20);
      msg1.setFont(mainFont);
      c.add(msg);
       
      c.setBackground(Color.YELLOW);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
         
        final String DB_URL = "jdbc:mysql://localhost:3306/datamandb";
        final String DB_USER = "root";
        final String DB_PASSWORD = "";

        if(terms.isSelected())
        {
            
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); 
                String name = t1.getText();
                String number = t2.getText();
                String address = ta1.getText();
                String email = t3.getText();
                String gender="";
                String dob=t6.getText();
                if(male.isSelected())
                {
                    gender="Male";
                }
                else{
                    gender="Female";
                }
                int password = Integer.parseInt(t4.getText());
    
                String query = "INSERT INTO candidate (name,phone,gender,dob,address,email,password) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, number);
                preparedStatement.setString(3, gender);
                preparedStatement.setString(4, dob);
                preparedStatement.setString(5, address);
                preparedStatement.setString(6, email);
                preparedStatement.setInt(7, password);
                preparedStatement.executeUpdate();
    
                JOptionPane.showMessageDialog(msg, "Data saved successfully!");
                msg.setText("Registration Successfull !!.. Please Go To LOGIN PAGE");

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(msg, "Error occurred while saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
             catch (NumberFormatException ex)
                {
                   JOptionPane.showMessageDialog(msg, "Please enter a valid date of birth!", "Error", JOptionPane.ERROR_MESSAGE);
                } 
             catch (ClassNotFoundException e1) 
               {
                   e1.printStackTrace();
               }
        }
        else
        {
            msg.setText("Please tick the check box of terms and conditions.");
        }
    }
}