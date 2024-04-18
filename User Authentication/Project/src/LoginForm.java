import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class LoginForm  extends JFrame{

    final private Font mainFont = new Font("Times New Roman",Font.BOLD,18); 
    JTextField tfEmail;
    JPasswordField pfPassword;
    public void initialize(){

        JLabel lbLoginForm = new JLabel("Login Form",SwingConstants.CENTER);
        lbLoginForm.setFont(mainFont);

        JLabel lbEmail= new JLabel("Email");
        lbEmail.setFont(mainFont);

        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);

        JLabel lbPassword= new JLabel("Password");
        lbPassword.setFont(mainFont);

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);


        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0,1,10,10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
        formPanel.add(lbLoginForm);
        formPanel.add(lbEmail);
        formPanel.add(tfEmail);
        formPanel.add(lbPassword);
        formPanel.add(pfPassword);

        /* Buttons Panel */

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(mainFont);

        btnLogin.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){
                 String email=tfEmail.getText();
                 String password = String.valueOf(pfPassword.getPassword());

                User user = getAuthenticatedUser(email,password);

                if(user != null){
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.initialize(user);
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(LoginForm.this,"Email or Password Invalid","Try again",JOptionPane.ERROR_MESSAGE);
                }

            }
        });


        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(mainFont);

        btnCancel.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });

        JButton btnRegister = new JButton("New Registeration");
        btnRegister.setFont(mainFont);
         
        btnRegister.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){
                RegistrationForm form=new RegistrationForm();
                form.initialize();
                dispose();
            }
        });


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(btnLogin);
        buttonsPanel.add(btnCancel);
        
        
        JPanel buttonsPanel1 = new JPanel();
        buttonsPanel1.setLayout(new GridLayout(1,2,10,0));
        buttonsPanel1.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
        buttonsPanel1.add(btnRegister);

        /* Initialize the frame */
        add(formPanel,BorderLayout.NORTH);
        add(buttonsPanel,BorderLayout.CENTER);
        add(buttonsPanel1,BorderLayout.SOUTH);
         

        setTitle("LoginForm");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(450,500);
        setMinimumSize(new Dimension(350,450));
        setLocationRelativeTo(null);
        setVisible(true);

    }


    private User getAuthenticatedUser(String email, String password)
    {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/datamandb";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            //Connected to database successfully.......
            if (conn != null) {
                System.out.println("Connected to the database!");
            }
            String sql = "SELECT * FROM candidate WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                user = new User();
                user.name = resultSet.getString("name");
                user.phone = resultSet.getString("phone");
                user.email = resultSet.getString("email");
                user.password = resultSet.getString("password");
                user.gender= resultSet.getString("gender");
                user.date_of_birth = resultSet.getString("dob");
                user.address = resultSet.getString("address");
               
            }

            preparedStatement.close();
            conn.close();
        }catch(Exception e){
            System.out.println("Database Connection Failed!!");
        }
        return user;
     
    }

    public static void main(String args[])
    {
        LoginForm loginForm = new LoginForm();
        loginForm.initialize();
    } 
}
