package com.library.libManagement;

import com.library.model.*;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class LibManagement{
    public static final String DATABASE_NAME = "libmanagement";
    private static JFrame currFrame;

    public LibManagement(){
        currFrame = login();
        currFrame.setVisible(true);
    }

    public static JFrame getCurrFrame(){
        return currFrame;
    }

    public static void setFrame(JFrame frame){
        currFrame = frame;
    }

    public JFrame login(){
        JFrame frame = new JFrame("View");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1195, 650));
        frame.getContentPane().setBackground(Color.decode("#FFFFFF"));
        frame.pack();
        Container c = frame.getContentPane();
        c.setLayout(null);
        // Font font = new Font("Arial", Font.BOLD, 27);

        // JButton ButtonAbout = new JButton("About");
        // ButtonAbout.setBounds(65, 74, 75, 30);
        // ButtonAbout.setBackground(Color.decode("#FFFDF6"));
        // c.add(ButtonAbout);

        JLabel LabelSignIn = new JLabel("Sign In");
        LabelSignIn.setBounds(180, 74, 85, 30);
        LabelSignIn.setBackground(Color.decode("#FFFDF6"));
        LabelSignIn.setForeground(Color.decode("#FF0000"));
        c.add(LabelSignIn);

        JButton ButtonRegister = new JButton("Register");
        ButtonRegister.setBounds(265, 74, 85, 30);
        ButtonRegister.setBackground(Color.decode("#FFFDF6"));
        ButtonRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                getCurrFrame().dispose();
                setFrame(register());
                getCurrFrame().setVisible(true);
            }
        });
        c.add(ButtonRegister);

        JTextField TextFieldUsername = new JTextField("Enter Username");
        TextFieldUsername.setBounds(64, 253, 400, 57);
        TextFieldUsername.setBackground(Color.decode("#D4FAFA"));
        TextFieldUsername.setFont(new Font("Arial", Font.BOLD, 25));
        TextFieldUsername.setForeground(Color.decode("#ADAEB3"));
        TextFieldUsername.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (TextFieldUsername.getText().equals("Enter Username")) {
                    TextFieldUsername.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (TextFieldUsername.getText().equals("")) {
                    TextFieldUsername.setText("Enter Username");
                }
            }
        });
        c.add(TextFieldUsername);

        JPasswordField TextFieldPassWord = new JPasswordField("PassWord");
        TextFieldPassWord.setBounds(64, 343, 400, 57);
        TextFieldPassWord.setBackground(Color.decode("#D4FAFA"));
        TextFieldPassWord.setForeground(Color.decode("#ADAEB3"));
        TextFieldPassWord.setFont(new Font("Arial", Font.BOLD, 25));
        TextFieldPassWord.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (TextFieldPassWord.getText().equals("PassWord")) {
                    TextFieldPassWord.setText("");
                }
            }

            // @Override
            public void focusLost(FocusEvent e) {
                if (TextFieldPassWord.getText().equals("")) {
                    TextFieldPassWord.setText("PassWord");
                }
            }
        });
        c.add(TextFieldPassWord);

        // JButton ButtonForgetPassWord = new JButton("Forget Password?");
        // ButtonForgetPassWord.setBounds(303, 410, 142, 35);
        // ButtonForgetPassWord.setBackground(Color.decode("#FFFDF6"));
        // ButtonForgetPassWord.setForeground(Color.decode("#C7C8C9"));
        // c.add(ButtonForgetPassWord);

        JButton ButtonSignIn = new JButton("Sign In");
        ButtonSignIn.setBounds(64, 490, 400, 55);
        ButtonSignIn.setBackground(Color.decode("#000000"));
        ButtonSignIn.setForeground(Color.decode("#F8F6F7"));
        ButtonSignIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String strUsername = TextFieldUsername.getText();
                String strPassword = String.valueOf(TextFieldPassWord.getPassword());

                if (strUsername.equals("")){ //If have no username
                    JOptionPane.showMessageDialog(null, "Enter the username");
                } else if (strPassword.equals("")){ //If have no password
                    JOptionPane.showMessageDialog(null, "Enter the password");
                } else {
                    Connection connection = connect();
                    ResultSet resultSet = null;
                    Statement stmt = null;

                    if (connection == null){
                        return;
                    }
                    try{
                        stmt = connection.createStatement();
                    
                        String strQuery = "SELECT * FROM USER WHERE Username = '" + strUsername + "' AND Password = '" + strPassword + "'";

                        resultSet = stmt.executeQuery(strQuery);

                        if (resultSet.next() == false){
                            System.out.println("User does not exist");
                            JOptionPane.showMessageDialog(null, "Wrong username or password!");
                        } else {
                            //Check error lowercase in database
                            if (resultSet.getString("Username").equals(strUsername) == false){
                                System.out.println("User does not exist");
                                JOptionPane.showMessageDialog(null, "Wrong username or password!");
                                return;
                            }

                            int iTypeUser = resultSet.getInt("TypeUser");
                            User user = null;

                            if (iTypeUser == 0){ //This is a librarian
                                strQuery = "SELECT * FROM LIBRARIAN WHERE Username = '" + strUsername + "';";
                                resultSet = stmt.executeQuery(strQuery);
                                resultSet.next();

                                //Get infos from database
                                String strID = resultSet.getString(1);
                                String strFullname = resultSet.getString(2);
                                String strPhoneNum = resultSet.getString(3);
                                String strEmail = resultSet.getString(4);
                                String strGender = resultSet.getString(5);
                                String strDepartment = resultSet.getString(6);
                                java.util.Date dateCreatedDate = new java.util.Date(resultSet.getDate(7).getTime());
                                resultSet.close();
                                //Set infos to object
                                user = new Librarian(strID, strFullname, strPhoneNum, strEmail, strGender, dateCreatedDate, strDepartment);
                                
                                getCurrFrame().dispose();
                                setFrame(user.getDashBoard());
                                getCurrFrame().setVisible(true);
                            } else { //This is a client
                                strQuery = "SELECT * FROM CLIENT WHERE Username = '" + strUsername + "';";
                                resultSet = stmt.executeQuery(strQuery);
                                resultSet.next();

                                //Get infos from database
                                String strID = resultSet.getString(1);
                                String strFullname = resultSet.getString(2);
                                String strPhoneNum = resultSet.getString(3);
                                String strEmail = resultSet.getString(4);
                                String strGender = resultSet.getString(5);
                                String strAddress = resultSet.getString(6);
                                String strBankAccount = resultSet.getString(7);
                                String strBankName = resultSet.getString(8);
                                java.util.Date dateCreatedDate = new java.util.Date(resultSet.getDate(9).getTime());
                                //Set infos to object
                                user = new Client(strID, strFullname, strPhoneNum, strEmail, strGender, dateCreatedDate,
                                                    strAddress, strBankAccount, strBankName);
                                
                                getCurrFrame().dispose();
                                setFrame(user.getDashBoard());
                                getCurrFrame().setVisible(true);
                            }
                        }
                         
                    } catch (Exception ex){
                        ex.printStackTrace();
                    } finally {
                        try {
                            resultSet.close();
                        } catch (Exception ex){
                            ex.printStackTrace();
                        }

                        try {
                            stmt.close();
                        } catch (Exception ex){
                            ex.printStackTrace();
                        }

                        try {
                            connection.close();
                        } catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                    
                }
            }
        });
        c.add(ButtonSignIn);

        JLabel LabelImage = new JLabel();
        String strImagePath = "a.png"; //The path of image
        LabelImage.setIcon(new ImageIcon(strImagePath));
        Dimension size = LabelImage.getPreferredSize();
        LabelImage.setBounds(593, 179, size.width, size.height);
        c.add(LabelImage);

        JLabel LabelSignInto = new JLabel("Sign In to");
        LabelSignInto.setBounds(545, 80, 260, 45);
        LabelSignInto.setFont(new Font("Arial", Font.BOLD, 35));
        LabelSignInto.setBackground(Color.decode("#FFFFFF"));
        c.add(LabelSignInto);

        JLabel LabelLibLibrary = new JLabel("Lib Management");
        LabelLibLibrary.setBounds(555, 128, 500, 45);
        LabelLibLibrary.setFont(new Font("Arial", Font.BOLD, 35));
        LabelLibLibrary.setBackground(Color.decode("#FFFFFF"));
        c.add(LabelLibLibrary);

        frame.setLocationRelativeTo(null);

        return frame;
    }

    public JFrame register(){
        JFrame frame = new JFrame("Library register");
        String strBackgroundPath = "a3.png";
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage(strBackgroundPath);
        JPanel PanelBackground = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        PanelBackground.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(995, 650));

        JLabel labelLibMangement = new JLabel("Lib Management");
        labelLibMangement.setBounds(175, 45, 300, 26);
        labelLibMangement.setFont(new Font("Arial", Font.BOLD, 25));
        PanelBackground.add(labelLibMangement);

        JPanel PanelRegister = new JPanel();
        PanelRegister.setBounds(250, 100, 620, 500);
        PanelRegister.setBackground(Color.decode("#FFFFFF"));
        PanelRegister.setLayout(null);
        PanelBackground.add(PanelRegister);

        JLabel ImageLogo = new JLabel();
        String strLogoPath = "LOGO 1.png";
        ImageLogo.setIcon(new ImageIcon(strLogoPath));
        Dimension size = ImageLogo.getPreferredSize();
        ImageLogo.setBounds(3, 10, size.width, size.height);
        PanelBackground.add(ImageLogo);

        JLabel LabelRegister = new JLabel("Register");
        LabelRegister.setBounds(250, 17, 110, 30);
        LabelRegister.setBackground(Color.decode("#D4FAFA"));
        LabelRegister.setFont(new Font("Arial", Font.BOLD, 25));
        LabelRegister.setForeground(Color.decode("#7C8BFF"));
        PanelRegister.add(LabelRegister);

        JLabel LabelUsername = new JLabel("Username*");
        LabelUsername.setFont(new Font("Arial", Font.PLAIN, 12));
        LabelUsername.setBackground(Color.decode("#D4FAFA"));
        LabelUsername.setBounds(14, 50, 120, 15);
        PanelRegister.add(LabelUsername);

        JTextField TextFieldUserName = new JTextField();
        TextFieldUserName.setBounds(10, 67, 260, 35);
        TextFieldUserName.setBackground(Color.decode("#F0F2E4"));
        PanelRegister.add(TextFieldUserName);

        JLabel LabelEmail = new JLabel("Email");
        LabelEmail.setFont(new Font("Arial", Font.PLAIN, 12));
        LabelEmail.setBackground(Color.decode("#D4FAFA"));
        LabelEmail.setBounds(324, 50, 120, 15);
        PanelRegister.add(LabelEmail);

        JTextField TextFieldEmail = new JTextField();
        TextFieldEmail.setBounds(320, 67, 260, 35);
        TextFieldEmail.setBackground(Color.decode("#F0F2E4"));
        PanelRegister.add(TextFieldEmail);

        JLabel LabelPassWord = new JLabel("Password*");
        LabelPassWord.setFont(new Font("Arial", Font.PLAIN, 12));
        LabelPassWord.setBackground(Color.decode("#D4FAFA"));
        LabelPassWord.setBounds(14, 108, 120, 15);
        PanelRegister.add(LabelPassWord);

        JPasswordField PasswordFieldPassWord = new JPasswordField();
        PasswordFieldPassWord.setBounds(10, 128, 260, 35);
        PasswordFieldPassWord.setBackground(Color.decode("#F0F2E4"));
        PanelRegister.add(PasswordFieldPassWord);

        JLabel LabelComfirmPassWord = new JLabel("Confirm Password*");
        LabelComfirmPassWord.setFont(new Font("Arial", Font.PLAIN, 12));
        LabelComfirmPassWord.setBackground(Color.decode("#D4FAFA"));
        LabelComfirmPassWord.setBounds(324, 108, 220, 15);
        PanelRegister.add(LabelComfirmPassWord);

        JPasswordField passwordFieldConfirmPasswordField = new JPasswordField();
        passwordFieldConfirmPasswordField.setBounds(320, 128, 260, 35);
        passwordFieldConfirmPasswordField.setBackground(Color.decode("#F0F2E4"));
        PanelRegister.add(passwordFieldConfirmPasswordField);

        JLabel LabelPhone = new JLabel("Phone Number*");
        LabelPhone.setFont(new Font("Arial", Font.PLAIN, 12));
        LabelPhone.setBackground(Color.decode("#D4FAFA"));
        LabelPhone.setBounds(14, 164, 120, 15);
        PanelRegister.add(LabelPhone);

        JTextField TextFieldPhone = new JTextField();
        TextFieldPhone.setBounds(10, 183, 260, 35);
        TextFieldPhone.setBackground(Color.decode("#F0F2E4"));
        PanelRegister.add(TextFieldPhone);

        JLabel LabelGender = new JLabel("Gender*");
        LabelGender.setFont(new Font("Arial", Font.PLAIN, 12));
        LabelGender.setBackground(Color.decode("#D4FAFA"));
        LabelGender.setBounds(324, 164, 120, 15);
        PanelRegister.add(LabelGender);

        ButtonGroup buttongroup = new ButtonGroup();
        JRadioButton RadioButtonMale = new JRadioButton("Male");
        RadioButtonMale.setBackground(Color.decode("#FFFFFF"));
        RadioButtonMale.setBounds(334, 177, 50, 40);
        RadioButtonMale.setHorizontalTextPosition(SwingConstants.CENTER);
        RadioButtonMale.setVerticalTextPosition(SwingConstants.BOTTOM);
        buttongroup.add(RadioButtonMale);
        PanelRegister.add(RadioButtonMale);

        JRadioButton RadioButtonFemale = new JRadioButton("Female");
        RadioButtonFemale.setBackground(Color.decode("#FFFFFF"));
        RadioButtonFemale.setBounds(434, 177, 52, 40);
        RadioButtonFemale.setHorizontalTextPosition(SwingConstants.CENTER);
        RadioButtonFemale.setVerticalTextPosition(SwingConstants.BOTTOM);
        buttongroup.add(RadioButtonFemale);
        PanelRegister.add(RadioButtonFemale);

        JRadioButton RadioButtonOther = new JRadioButton("Other");
        RadioButtonOther.setBackground(Color.decode("#FFFFFF"));
        RadioButtonOther.setSelected(true);
        RadioButtonOther.setBounds(534, 177, 50, 40);
        RadioButtonOther.setHorizontalTextPosition(SwingConstants.CENTER);
        RadioButtonOther.setVerticalTextPosition(SwingConstants.BOTTOM);
        buttongroup.add(RadioButtonOther);
        PanelRegister.add(RadioButtonOther);

        JLabel LabelFullName = new JLabel("Full name*");
        LabelFullName.setFont(new Font("Arial", Font.PLAIN, 12));
        LabelFullName.setBackground(Color.decode("#D4FAFA"));
        LabelFullName.setBounds(14, 220, 120, 15);
        PanelRegister.add(LabelFullName);

        JTextField TextFieldFullName = new JTextField();
        TextFieldFullName.setBounds(10, 243, 570, 35);
        TextFieldFullName.setBackground(Color.decode("#F0F2E4"));
        PanelRegister.add(TextFieldFullName);

        JLabel LabelBankAccount = new JLabel("Bank Account");
        LabelBankAccount.setFont(new Font("Arial", Font.PLAIN, 12));
        LabelBankAccount.setBackground(Color.decode("#D4FAFA"));
        LabelBankAccount.setBounds(14, 280, 120, 15);
        PanelRegister.add(LabelBankAccount);

        JTextField TextFieldBankAccount = new JTextField();
        TextFieldBankAccount.setBounds(10, 303, 260, 35);
        TextFieldBankAccount.setBackground(Color.decode("#F0F2E4"));
        PanelRegister.add(TextFieldBankAccount);

        JLabel LabelBankName = new JLabel("Bank name");
        LabelBankName.setFont(new Font("Arial", Font.PLAIN, 12));
        LabelBankName.setBackground(Color.decode("#D4FAFA"));
        LabelBankName.setBounds(324, 280, 120, 15);
        PanelRegister.add(LabelBankName);

        JTextField TextFieldBankName = new JTextField();
        TextFieldBankName.setBounds(320, 303, 260, 35);
        TextFieldBankName.setBackground(Color.decode("#F0F2E4"));
        PanelRegister.add(TextFieldBankName);

        JLabel LabelAddress = new JLabel("Address");
        LabelAddress.setFont(new Font("Arial", Font.PLAIN, 12));
        LabelAddress.setBackground(Color.decode("#D4FAFA"));
        LabelAddress.setBounds(14, 340, 120, 15);
        PanelRegister.add(LabelAddress);

        JTextField TextFieldAddress = new JTextField();
        TextFieldAddress.setBounds(10, 365, 570, 35);
        TextFieldAddress.setBackground(Color.decode("#F0F2E4"));
        PanelRegister.add(TextFieldAddress);

        JButton ButtonRegister = new JButton("Register");
        ButtonRegister.setBackground(Color.decode("#87C7F1"));
        ButtonRegister.setForeground(Color.decode("#FFFFFF"));
        ButtonRegister.setBounds(450, 420, 90, 50);
        ButtonRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String strUsername = TextFieldUserName.getText();
                if (strUsername.equals("")){
                    JOptionPane.showMessageDialog(null, "Has no username!");
                    return;
                }

                String strPassword = String.valueOf(PasswordFieldPassWord.getPassword());
                if (strPassword.equals("")){
                    JOptionPane.showMessageDialog(null, "Has no password!");
                    return;
                }

                String strConfirmedPassword = String.valueOf(passwordFieldConfirmPasswordField.getPassword());
                if (!strConfirmedPassword.equals(strPassword)){
                    JOptionPane.showMessageDialog(null, "Not correct password!");
                    return;
                }

                String strPhoneNumber = TextFieldPhone.getText();
                if (strPhoneNumber.equals("")){
                    JOptionPane.showMessageDialog(null, "Has no phone number!");
                    return;
                }
                // Check if the text is not numeric
                try {
                    int num = Integer.parseInt(strPhoneNumber);
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Not correct phone number!");
                    return;
                }

                String strFullName = TextFieldFullName.getText();
                if (strFullName.equals("")){
                    JOptionPane.showMessageDialog(null, "Has no full name!");
                    return;
                }

                String strBankAccount = TextFieldBankAccount.getText();
                String strBankName = TextFieldBankName.getText();
                String strEmail = TextFieldEmail.getText();
                String strAddress = TextFieldAddress.getText();
                String strGender = RadioButtonMale.isSelected() ? "Male" : (
                                RadioButtonFemale.isSelected() ? "Female" : "Other");

                Connection connection = null;
                PreparedStatement pstmt = null;
                Statement stmt = null;
                ResultSet rs = null;

                try {
                    connection = connect();

                    //Check if Username existed
                    String strQuerry = "SELECT * FROM User WHERE Username = '" + strUsername + "';";
                    stmt = connection.createStatement();
                    rs = stmt.executeQuery(strQuerry);
                    
                    if (rs.next() == true){
                        JOptionPane.showMessageDialog(null, "Existed Username!");
                        try {
                            rs.close();
                        } catch (SQLException ex){
                            ex.printStackTrace();
                        }
    
                        try {
                            stmt.close();
                        } catch (SQLException ex){
                            ex.printStackTrace();
                        }

                        try {
                            connection.close();
                        } catch (SQLException ex){
                            ex.printStackTrace();
                        }

                        return;
                    }
                } catch (SQLException ex){
                    ex.printStackTrace();
                } finally {
                    try {
                        rs.close();
                    } catch (SQLException e1){
                        e1.printStackTrace();
                    }

                    try {
                        stmt.close();
                    } catch (SQLException e1){
                        e1.printStackTrace();
                    }

                    try {
                        connection.close();
                    } catch (SQLException e1){
                        e1.printStackTrace();
                    }
                }

                try {
                    connection = connect();
                    connection.setAutoCommit(false);

                    //Insert data into User Table
                    String strQuerry = "INSERT INTO User (Username, Password) VALUES (?, ?)";
                    pstmt = connection.prepareStatement(strQuerry);
                    pstmt.setString(1, strUsername);
                    pstmt.setString(2, strPassword);
                    pstmt.executeUpdate();

                    //Insert data into Client Table
                    strQuerry = "INSERT INTO Client (Fullname, PhoneNum, Email, Gender, Address, BankAccount, BankName, Username) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    pstmt = connection.prepareStatement(strQuerry);
                    pstmt.setString(1, strFullName);
                    pstmt.setString(2, strPhoneNumber);
                    pstmt.setString(3, strEmail);
                    pstmt.setString(4, strGender);
                    pstmt.setString(5, strAddress);
                    pstmt.setString(6, strBankAccount);
                    pstmt.setString(7, strBankName);
                    pstmt.setString(8, strUsername);
                    pstmt.executeUpdate();

                    connection.commit();  
                    
                    //Back to login
                    JOptionPane.showMessageDialog(null, "Signup Success! Back to Login");
                    getCurrFrame().dispose();
                    setFrame(login());
                    getCurrFrame().setVisible(true);
                } catch (SQLException ex) {
                    try {
                        connection.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Registration failed!");
                } finally{
                    try {
                        stmt.close();
                    } catch (SQLException ex){
                        ex.printStackTrace();
                    }

                    try {
                        pstmt.close();
                    } catch (SQLException ex){
                        ex.printStackTrace();
                    }

                    try {
                        connection.close();
                    } catch (SQLException ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        PanelRegister.add(ButtonRegister);

        JButton ButtonBackToLogin = new JButton("Back to login");
        ButtonBackToLogin.setBackground(Color.decode("#87C7F1"));
        ButtonBackToLogin.setForeground(Color.decode("#FFFFFF"));
        ButtonBackToLogin.setBounds(110, 420, 130, 50);
        ButtonBackToLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                getCurrFrame().dispose();
                setFrame(login());
                getCurrFrame().setVisible(true);
            }
        });
        PanelRegister.add(ButtonBackToLogin);

        frame.setContentPane(PanelBackground);
        frame.pack();
        frame.setLocationRelativeTo(null);

        return frame;
    }

    public static Connection connect()
    {
        try {
            String user = "root";
            String pw = "root";
            String path = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;

            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("Loaded driver");
            Connection con = DriverManager.getConnection(path, user, pw);
            //System.out.println("Connected to MySQL");
            return con;
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createDB() throws SQLException{
        Connection connection = null;
        ResultSet resultSet = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=root&password=root");
            resultSet = connection.getMetaData().getCatalogs();

            while(resultSet.next()){
                //Get database name
                String dbname = resultSet.getString(1);
                
                //If database existed, return this
                if (dbname.equals(DATABASE_NAME)){
                    try {
                        resultSet.close();
                    } catch(Exception ex){
                        ex.printStackTrace();
                    }

                    try {
                        connection.close();
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                    return;
                }
                
            }
            connection.setAutoCommit(false);
            stmt = connection.createStatement();

            //Create database
            stmt.executeUpdate("CREATE DATABASE " + DATABASE_NAME);
            //Use database
            stmt.executeUpdate("USE " + DATABASE_NAME);

            //Set commands
            String strCreateTableUser = "CREATE TABLE USER(" +
                                    "Username VARCHAR(255) NOT NULL, " + 
                                    "Password VARCHAR(255) NOT NULL, " +
                                    "TypeUser int NOT NULL DEFAULT 1, " +
                                    "PRIMARY KEY (Username));";
            stmt.executeUpdate(strCreateTableUser);

            String strCreateTableLibrarian = "CREATE TABLE LIBRARIAN(" + 
                                    "LID VARCHAR(5) NOT NULL, " + 
                                    "Fullname NVARCHAR(50), " +
                                    "PhoneNum VARCHAR(10), " + 
                                    "Email VARCHAR(255), " +
                                    "Gender NVARCHAR(10) CHECK (Gender = 'Male' OR Gender = 'Female' OR Gender = 'Other'), " +
                                    "Department VARCHAR(255), " +
                                    "CreatedDate DATE DEFAULT (CURRENT_DATE), " +
                                    "Username VARCHAR(255), " +
                                    "PRIMARY KEY (LID));";
            stmt.executeUpdate(strCreateTableLibrarian);

            String strCreateTableClient = "CREATE TABLE CLIENT(" + 
                                    "CID VARCHAR(5) NOT NULL, " +
                                    "Fullname VARCHAR(50), " +
                                    "PhoneNum VARCHAR(10), " +
                                    "Email VARCHAR(255), " + 
                                    "Gender VARCHAR(10) CHECK (Gender = 'Male' OR Gender = 'Female' OR Gender = 'Other'), " +
                                    "Address VARCHAR(100), " +
                                    "BankAccount VARCHAR(100), " +
                                    "BankName VARCHAR(100), " +
                                    "CreatedDate DATE DEFAULT (CURRENT_DATE), " +
                                    "Username VARCHAR(255), " +
                                    "PRIMARY KEY (CID));";
            stmt.executeUpdate(strCreateTableClient);

            String strCreateTableBook = "CREATE TABLE BOOK(" +
                                    "BID VARCHAR(255) NOT NULL, " + 
                                    "Tittle VARCHAR(50), " +
                                    "Author VARCHAR(50), " +
                                    "PublishingHouse VARCHAR(50), " +
                                    "PostedYear INT, " +
                                    "Genre VARCHAR(50), " +
                                    "HasLeft int DEFAULT 0, " +
                                    "Place VARCHAR(50), " +
                                    "PathImage VARCHAR(30), " +
                                    "PRIMARY KEY (BID));";
            stmt.executeUpdate(strCreateTableBook);
            
            String strCreateTableReview = "CREATE TABLE REVIEW(" +
                                    "BID VARCHAR(255) NOT NULL, " +
                                    "CID VARCHAR(5) NOT NULL, " +
                                    "Rate int DEFAULT 0 CHECK (0 <= Rate && Rate <= 5), " +
                                    "PRIMARY KEY (BID, CID));";
            stmt.executeUpdate(strCreateTableReview);

            String strCreateTableInteresting = "CREATE TABLE INTERESTING(" +
                                    "CID VARCHAR(5) NOT NULL, " +
                                    "BID VARCHAR(255) NOT NULL, " +
                                    "PRIMARY KEY (CID, BID));";
            stmt.executeUpdate(strCreateTableInteresting);
                                    
            String strCreateTableBorrowing = "CREATE TABLE BORROWING(" +
                                    "BID VARCHAR(255) NOT NULL, " +
                                    "Price int NOT NULL DEFAULT 25000, " +
                                    "Routine int NOT NULL DEFAULT 7, " +
                                    "PRIMARY KEY (BID));";
            stmt.executeUpdate(strCreateTableBorrowing);
                                
            String strCreateTableTransaction = "CREATE TABLE TRANSACTION(" +
                                    "TID VARCHAR(255) NOT NULL, " +
                                    "CID VARCHAR(5) NOT NULL, " +
                                    "BorrowedDate DATE, " +
                                    "ReturnDate DATE, " + 
                                    "TotalPrice int, " +
                                    "TotalRoutine int, " +
                                    "Quantity int, " +
                                    "Status varchar(10) CHECK (Status = 'Borrowing' OR Status = 'Returned' OR Status = 'Waiting' OR Status = 'Cancel'), " +
                                    "PRIMARY KEY (TID));";
            stmt.executeUpdate(strCreateTableTransaction);

            String strCreateTableDetailTrans = "CREATE TABLE DETAILTRANS(" +
                                    "TID VARCHAR(255) NOT NULL, " +
                                    "BID VARCHAR(255) NOT NULL, " +
                                    "PRIMARY KEY (TID, BID));";
            stmt.executeUpdate(strCreateTableDetailTrans);

            //Generate triggers to create auto ID which is customized
            String strTrigger_CustomID = "CREATE TRIGGER librarian_insert_trigger BEFORE INSERT ON LIBRARIAN " +
                                    "FOR EACH ROW SET new.LID = CONCAT('L', LPAD((SELECT count(*) from LIBRARIAN), 4, '0'));";
            stmt.executeUpdate(strTrigger_CustomID);

            strTrigger_CustomID = "CREATE TRIGGER client_insert_trigger BEFORE INSERT ON CLIENT " +
                                    "FOR EACH ROW SET new.CID = CONCAT('C', LPAD((SELECT count(*) from CLIENT), 4, '0'));";
            stmt.executeUpdate(strTrigger_CustomID);

            strTrigger_CustomID = "CREATE TRIGGER trans_insert_trigger BEFORE INSERT ON TRANSACTION " +
                                    "FOR EACH ROW SET new.TID = CONCAT('T', LPAD((SELECT count(*) from TRANSACTION), 4, '0'));";
            stmt.executeUpdate(strTrigger_CustomID);            

            //Generate UNIQUE fields
            String strUC_Username = "ALTER TABLE LIBRARIAN ADD CONSTRAINT UC_Username UNIQUE (Username);";
            stmt.executeUpdate(strUC_Username);

            strUC_Username = "ALTER TABLE CLIENT ADD CONSTRAINT UC_Username UNIQUE (Username);";
            stmt.executeUpdate(strUC_Username);

            //Generate foreign keys
            String strFK_Librarian_User = "ALTER TABLE LIBRARIAN " + 
                                    "ADD CONSTRAINT FK_LIBRARIAN_USER " +
                                    "FOREIGN KEY (Username) REFERENCES USER(Username);";
            stmt.executeUpdate(strFK_Librarian_User);

            String strFK_Client_User = "ALTER TABLE CLIENT " +
                                    "ADD CONSTRAINT FK_CLIENT_USER " +
                                    "FOREIGN KEY (Username) REFERENCES USER(Username);";
            stmt.executeUpdate(strFK_Client_User);

            String strFK_Review_Book = "ALTER TABLE REVIEW " +
                                    "ADD CONSTRAINT FK_REVIEW_BOOK " +
                                    "FOREIGN KEY (BID) REFERENCES BOOK(BID);";
            stmt.executeUpdate(strFK_Review_Book);

            String strFK_Review_Client = "ALTER TABLE REVIEW " +
                                    "ADD CONSTRAINT FK_REVIEW_CLIENT " +
                                    "FOREIGN KEY (CID) REFERENCES CLIENT(CID);";
            stmt.executeUpdate(strFK_Review_Client);

            String strFK_Interesting_Client = "ALTER TABLE INTERESTING " +
                                    "ADD CONSTRAINT FK_INTERESTING_CLIENT " +
                                    "FOREIGN KEY (CID) REFERENCES CLIENT(CID);";
            stmt.executeUpdate(strFK_Interesting_Client);

            String strFK_Interesting_Book = "ALTER TABLE INTERESTING " +
                                    "ADD CONSTRAINT FK_INTERESTING_BOOK " +
                                    "FOREIGN KEY (BID) REFERENCES BOOK(BID);";
            stmt.executeUpdate(strFK_Interesting_Book);

            String strFK_Borrowing_Book = "ALTER TABLE BORROWING " +
                                    "ADD CONSTRAINT FK_BORROWING_BOOK " +
                                    "FOREIGN KEY (BID) REFERENCES BOOK(BID);";
            stmt.executeUpdate(strFK_Borrowing_Book);

            String strFK_Transaction_Client = "ALTER TABLE TRANSACTION " +
                                    "ADD CONSTRAINT FK_BORROWING_CLIENT " +
                                    "FOREIGN KEY (CID) REFERENCES CLIENT(CID);";
            stmt.executeUpdate(strFK_Transaction_Client);

            String strFK_DetailTrans_Book = "ALTER TABLE DETAILTRANS " +
                                    "ADD CONSTRAINT FK_DETAILTRANS_BOOK " +
                                    "FOREIGN KEY (BID) REFERENCES BORROWING(BID);";
            stmt.executeUpdate(strFK_DetailTrans_Book);

            String strFK_DetailTrans_Transaction = "ALTER TABLE DETAILTRANS " +
                                    "ADD CONSTRAINT FK_DETAILTRANS_TRANSACTION " +
                                    "FOREIGN KEY (TID) REFERENCES TRANSACTION(TID);";
            stmt.executeUpdate(strFK_DetailTrans_Transaction);

            //Insert the first account of librarian into the database
            String strSave_LibAccount = "INSERT INTO USER VALUES ('librarian1', '12345', 0);";
            stmt.executeUpdate(strSave_LibAccount);

            String strSave_AccountInfo = "INSERT INTO LIBRARIAN (Fullname, PhoneNum, Email, Gender, Department, CreatedDate, Username) " +
                                    "VALUES ('Ma Cao', '0981236782', 'macao@gmail.com', 'Male', 'A', '2019-04-30', 'librarian1')";
            stmt.executeUpdate(strSave_AccountInfo);

            //Insert some books into the database
            String strBook = "INSERT INTO BOOK VALUES " +
                                "('AA00000', 'Old Path White Clouds', 'Thich Nhat Hanh', 'Phuong Nam', 2010, 'Biography', 2, 'Department A - Shelf A00', 'ImageBooks\\\\AA00000.png')," +
                                "('BA01099', 'Vingt mille lieues sous les mers', 'Jules Verne', 'Hong Duc', 2009, 'Adventure Novels', 5, 'Department B - Shelf A01', 'ImageBooks\\\\BA01099.png')," +
                                "('AC05014', 'JUSTICE What is the right thing to do?', 'Michael Sandel', 'Tre', 2016, 'Encyclopedia', 10, 'Department A - Shelf C05', 'ImageBooks\\\\AC05014.png')," +
                                "('DB03003', 'The Shining', 'Stephen King', 'Doubleday', 1977, 'Horror', 6, 'Department D - Shelf B03', 'ImageBooks\\\\DB03003.png')," +
                                "('CA02008', 'Pet Sematary', 'Stephen King', 'Doubleday', 1983, 'Horror', 7, 'Department C - Shelf A02', 'ImageBooks\\\\CA02008.png')," +
                                "('AD00038', 'Outlander', 'Diana Gabaldon', 'Delacorte Press', 1991, 'Romance', 12, 'Department A - Shelf D00', 'ImageBooks\\\\AD00038.png')," +
                                "('BD03015', 'The Notebook', 'Nicholas Sparks', 'Warner Books', 1996, 'Romance', 3, 'Department B - Shelf D03', 'ImageBooks\\\\BD03015.png')," +
                                "('CD03039', 'Dune', 'Frank Herbert', 'Chilton Books', 1965, 'Science Fiction', 19, 'Department C - Shelf D03', 'ImageBooks\\\\CD03039.png')," +
                                "('DD08062', 'Neuromancer', 'William Gibson', 'Ace Books', 1984, 'Science Fiction', 12, 'Department D - Shelf D08', 'ImageBooks\\\\DD08062.png')," +
                                "('AB07072', 'The Happiness Project', 'Gretchen Rubin', 'HarperCollins', 2009, 'Self-help', 3, 'Department A - Shelf B07', 'ImageBooks\\\\AB07072.png')," +
                                "('BC08010', 'Jade City', 'Fonda Lee', 'Orbit', 2017, 'Fantasy', 7, 'Department B - Shelf C08', 'ImageBooks\\\\BC08010.png')," +
                                "('CC00000', 'The Bone Shard Daughter', 'Andrea Stewart', 'Orbit', 2020, 'Fantasy', 12, 'Department C - Shelf C00', 'ImageBooks\\\\CC00000.png')," +
                                "('DC04002', 'The Dutch House', 'Ann Patchett', 'Harper', 2019, 'Drama', 21, 'Department D - Shelf C04', 'ImageBooks\\\\DC04002.png')," +
                                "('AC04032', 'A Little Life', 'Hanya Yanagihara', 'Doubleday', 2015, 'Drama', 19, 'Department A - Shelf C04', 'ImageBooks\\\\AC04032.png')," +
                                "('BA02047', 'Homie', 'Danez Smith', 'Graywolf Press', 2020, 'Poetry', 5, 'Department B - Shelf A02', 'ImageBooks\\\\BA02047.png')," +
                                "('CA01091', 'The Black Maria', 'Aracelis Girmay', 'BOA Editions', 2016, 'Poetry', 8, 'Department C - Shelf A01', 'ImageBooks\\\\CA01091.png')," +
                                "('DA07023', 'The Body: A Guide for Occupants', 'Bill Bryson', 'Doubleday', 2019, 'Science', 8, 'Department D - Shelf A07', 'ImageBooks\\\\DA07023.png')," +
                                "('AB07043', 'Brief Answers to the Big Questions', 'Stephen Hawking', 'Bantam Press', 2018, 'Science', 8, 'Department A - Shelf B07', 'ImageBooks\\\\AB07043.png')," +
                                "('BB03050', 'The Family Upstairs', 'Lisa Jewell', 'Atria Books', 2019, 'Mystery', 15, 'Department B - Shelf B03', 'ImageBooks\\\\BB03050.png')," +
                                "('CA03055', 'The Lost Man', 'Jane Harper', 'Flatiron Books', 2018, 'Mystery', 2, 'Department C - Shelf A03', 'ImageBooks\\\\CA03055.png')," +
                                "('DD05077', 'The Water Dancer', 'Ta-Nehisi Coates', 'One World', 2019, 'Novel', 6, 'Department D - Shelf D05', 'ImageBooks\\\\DD05077.png')," +
                                "('AA05017', 'The Underground Railroad', 'Colson Whitehead', 'Doubleday', 2016, 'Novel', 9, 'Department A - Shelf A05', 'ImageBooks\\\\AA05017.png')," +
                                "('BC09026', 'The Cold War: A World History', 'Odd Arne Westad', 'Basic Books', 2017, 'History', 17, 'Department B - Shelf C09', 'ImageBooks\\\\BC09026.png')," +
                                "('CC09087', 'Stalin: Waiting for Hitler, 1929-1941', 'Stephen Kotkin', 'Penguin Press', 2017, 'History', 10, 'Department C - Shelf C09', 'ImageBooks\\\\CC09087.png')," +
                                "('DA03000', 'Educated', 'Tara Westover', 'Random House', 2018, 'Memoir', 3, 'Department D - Shelf A03', 'ImageBooks\\\\DA03000.png');";
            stmt.executeUpdate(strBook);

            String strBorrowing = "INSERT INTO BORROWING(BID, Price, Routine) VALUES " + 
                                "('AA00000', 15000, 10)," +
                                "('BA01099', 14000, 4)," +
                                "('AC05014', 6000, 6)," +
                                "('DB03003', 21000, 12)," +
                                "('CA02008', 5000, 7)," +
                                "('AD00038', 23000, 3)," +
                                "('BD03015', 18000, 8)," +
                                "('CD03039', 26000, 6)," +
                                "('DD08062', 10000, 10)," +
                                "('AB07072', 7000, 7)," +
                                "('BC08010', 20000, 5)," +
                                "('CC00000', 25000, 7)," +
                                "('DC04002', 15000, 9)," +
                                "('AC04032', 5000, 3)," +
                                "('BA02047', 20000, 5)," +
                                "('CA01091', 10000, 5)," +
                                "('DA07023', 21000, 9)," +
                                "('AB07043', 7000, 6)," +
                                "('BB03050', 12000, 4)," +
                                "('CA03055', 23000, 7)," +
                                "('DD05077', 25000, 12)," +
                                "('AA05017', 5000, 3)," +
                                "('BC09026', 7000, 4)," +
                                "('CC09087', 17000, 7)," +
                                "('DA03000', 12000, 10);";
            stmt.executeUpdate(strBorrowing);

            connection.commit();
        } catch (SQLException ex){
            connection.rollback();
            ex.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            try {
                stmt.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }

            try {
                connection.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}