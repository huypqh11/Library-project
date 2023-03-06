
import java.awt.*;
import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class App {
    static void UserLogin() {
        JFrame frame = new JFrame("View");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1195, 650));
        frame.getContentPane().setBackground(Color.decode("#FAF9F6"));
        frame.pack();
        Container c = frame.getContentPane();
        c.setLayout(null);
        Font font = new Font("Arial", Font.BOLD, 27);

        JLabel welcom = new JLabel("WELCOME BACK!");
        welcom.setBounds(40, 50, 250, 30);
        welcom.setFont(font);
        welcom.setOpaque(true);
        welcom.setBackground(Color.decode("#FAF9F6"));
        c.add(welcom);

        JLabel dh = new JLabel("Don't have account");
        dh.setBounds(60, 550, 160, 26);
        dh.setFont(new Font("Arial", Font.PLAIN, 17));
        dh.setOpaque(true);
        dh.setBackground(Color.decode("#FAF9F6"));
        c.add(dh);

        JButton SignIn = new JButton("Sign Up");
        SignIn.setBounds(230, 554, 100, 20);
        SignIn.setBackground(Color.decode("#FAF9F6"));
        SignIn.setForeground(Color.decode("#9CC3E3"));
        c.add(SignIn);

        JLabel username = new JLabel("Username:");
        username.setBounds(60, 200, 150, 30);
        username.setFont(new Font("Arial", Font.BOLD, 25));
        username.setOpaque(true);
        username.setBackground(Color.decode("#FAF9F6"));
        c.add(username);

        JTextField SDT1 = new JTextField();
        SDT1.setBounds(65, 240, 320, 50);
        SDT1.setFont(new Font("Arial", Font.BOLD, 28));
        c.add(SDT1);

        JLabel Pass = new JLabel("Password:");
        Pass.setBounds(60, 320, 150, 30);
        Pass.setFont(new Font("Arial", Font.BOLD, 25));
        Pass.setOpaque(true);
        Pass.setBackground(Color.decode("#FAF9F6"));
        c.add(Pass);

        JPasswordField SDT = new JPasswordField();
        SDT.setBounds(65, 360, 320, 50);
        SDT.setFont(new Font("Arial", Font.BOLD, 28));
        c.add(SDT);

        JButton Sign = new JButton("Sign In");
        Sign.setBounds(70, 450, 320, 50);
        Sign.setBackground(Color.decode("#ADDCF4"));
        Sign.setForeground(Color.decode("#FAF9F6"));
        c.add(Sign);

        JRadioButton Reme = new JRadioButton("Remember me");
        Reme.setBounds(70, 415, 120, 20);
        Reme.setBackground(Color.decode("#FAF9F6"));
        Reme.setForeground(Color.decode("#86C6EE"));
        c.add(Reme);

        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("a.png"));
        Dimension size = label.getPreferredSize();
        label.setBounds(500, 50, size.width, size.height);
        c.add(label);

    }

    public static void main(String[] args) {
        UserLogin();

    }
}
