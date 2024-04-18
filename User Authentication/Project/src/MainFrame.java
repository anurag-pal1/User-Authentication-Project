import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame{

    final private Font mainFont = new Font("Times New Roman",Font.BOLD,18); 
    public void initialize(User user){

       /* Info Panel */
       JPanel infoPanel = new JPanel();
       infoPanel.setLayout(new GridLayout(0,2,5,5));
    infoPanel.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
      
       infoPanel.add(new JLabel("Name: "));
       infoPanel.add(new JLabel(user.name));
       infoPanel.add(new JLabel("Email: "));
       infoPanel.add(new JLabel(user.email));
       infoPanel.add(new JLabel("Mobile Number: "));
       infoPanel.add(new JLabel(user.phone));
       infoPanel.add(new JLabel("Gender: "));
       infoPanel.add(new JLabel(user.gender));
       infoPanel.add(new JLabel("Date of Birth: "));
       infoPanel.add(new JLabel(user.date_of_birth));
       infoPanel.add(new JLabel("Address: "));
       infoPanel.add(new JLabel(user.address));

       Component[] labels = infoPanel.getComponents();
       for(int i=0;i<labels.length;i++)
       {
        labels[i].setFont(new Font("Times New Roman",Font.BOLD,18));
       }
       

       JButton btnDelete=new JButton("DELETE");
       btnDelete.setFont(mainFont);

       JButton btnUpdate=new JButton("UPDATE");
       btnUpdate.setFont(mainFont);

       JPanel buttonsPanel = new JPanel();
       buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
       buttonsPanel.add(btnUpdate);
       buttonsPanel.add(btnDelete);
       
        

       btnDelete.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent e){ 
    
            dispose();
        }

       });

       add(infoPanel, BorderLayout.NORTH);
       add(buttonsPanel,BorderLayout.CENTER);
        
        setTitle("DashBoard");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100,650);

        setLocationRelativeTo(null);
        setVisible(true);
    }  
}
