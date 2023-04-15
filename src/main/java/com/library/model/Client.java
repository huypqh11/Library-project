package com.library.model;

import com.library.libManagement.*;

import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

class ClientUI{
    private User currUser;

    ClientUI(User currUser){
        this.currUser = currUser;
    }

    //This attribute is used to be into getUIDashboard
    private static JPanel PanelListBooks;

    public JFrame getUIDashboard(){
        JFrame frame = new JFrame("Dashboard Client");
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage("pictures\\ImageBackground\\a6.png");
        JPanel PanelBackground = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        PanelBackground.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));

        JLabel labelLibMangement = new JLabel("Lib Management");
        labelLibMangement.setBounds(170, 45, 260, 26);
        labelLibMangement.setFont(new Font("Arial", Font.BOLD, 25));
        PanelBackground.add(labelLibMangement);

        JLabel ImageLogo = new JLabel();
        ImageLogo.setIcon(new ImageIcon("pictures\\ImageBackground\\LOGO 1.png"));
        Dimension size = ImageLogo.getPreferredSize();
        ImageLogo.setBounds(0, 5, size.width, size.height);
        PanelBackground.add(ImageLogo);

        ImageIcon IconDashboard = new ImageIcon("pictures\\ImageBackground\\LOGO_Dashboard.png");
        JLabel LabelDashboard = new JLabel("Dashboard", IconDashboard, JLabel.LEFT);
        LabelDashboard.setHorizontalTextPosition(SwingConstants.RIGHT);
        LabelDashboard.setIconTextGap(10);
        LabelDashboard.setBounds(0, 190, 190, 40);
        LabelDashboard.setBackground(Color.decode("#A1E7FF"));
        LabelDashboard.setForeground(Color.decode("#FFF8F7"));
        LabelDashboard.setOpaque(true);
        PanelBackground.add(LabelDashboard);

        ImageIcon IconBook = new ImageIcon("pictures\\ImageBackground\\LOGO_Book.png");
        JButton ButtonHistory = new JButton("History", IconBook);
        ButtonHistory.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonHistory.setIconTextGap(10);
        ButtonHistory.setHorizontalAlignment(JButton.LEFT);
        ButtonHistory.setBounds(0, 230, 190, 40);
        ButtonHistory.setBackground(Color.decode("#F4FAFB"));
        ButtonHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIHistory());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonHistory);

        ImageIcon IconUser = new ImageIcon("pictures\\ImageBackground\\LOGO_User.png");
        JButton ButtonInteresting = new JButton("Interesting", IconUser);
        ButtonInteresting.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonInteresting.setIconTextGap(10);
        ButtonInteresting.setHorizontalAlignment(JButton.LEFT);
        ButtonInteresting.setBounds(0, 271, 190, 40);
        ButtonInteresting.setBackground(Color.decode("#F4FAFB"));
        ButtonInteresting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIInteresting());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonInteresting);

        ImageIcon IconPayment = new ImageIcon("pictures\\ImageBackground\\LOGO_Payment.png");
        JButton ButtonCart = new JButton("Cart", IconPayment);
        ButtonCart.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonCart.setIconTextGap(10);
        ButtonCart.setHorizontalAlignment(JButton.LEFT);
        ButtonCart.setBounds(0, 311, 190, 40);
        ButtonCart.setBackground(Color.decode("#F4FAFB"));
        ButtonCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUICart());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonCart);

        ImageIcon IconSetting = new ImageIcon("pictures\\ImageBackground\\LOGO_Setting.png");
        JButton ButtonLogout = new JButton("Log out", IconSetting);
        ButtonLogout.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonLogout.setIconTextGap(10);
        ButtonLogout.setHorizontalAlignment(JButton.LEFT);
        ButtonLogout.setBounds(0, 572, 190, 40);
        ButtonLogout.setBackground(Color.decode("#F4FAFB"));
        ButtonLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement lib = new LibManagement();
            }
        });
        PanelBackground.add(ButtonLogout);

        JButton ButtonClient = new JButton(this.currUser.getID());
        ButtonClient.setFont(new Font("Arial", Font.BOLD, 15));
        ButtonClient.setBackground(Color.decode("#FFFFFF"));
        ButtonClient.setBounds(1090, 20, 90, 30);
        ButtonClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIShowInfos());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonClient);

        PanelListBooks = getListBooks("");
        JScrollPane scrollPaneBooks = new JScrollPane(PanelListBooks);
        // JScrollPane scrollPaneBooks = new JScrollPane((new ListBooks(strSearch)).getListItems());
        scrollPaneBooks.setBounds(200, 180, 1060, 450);
        //Speed up the scroll speed
        scrollPaneBooks.getVerticalScrollBar().setUnitIncrement(20);
        PanelBackground.add(scrollPaneBooks);

        //Searching
        JPanel PanelSearch = new JPanel();
        PanelSearch.setBounds(400, 100, 520, 70);
        PanelSearch.setBackground(Color.decode("#FFF7DB"));
        PanelSearch.setLayout(null);

        JLabel LabelSearch = new JLabel("BOOK:");
        LabelSearch.setBounds(10, 10, 100, 40);
        LabelSearch.setFont(new Font("Arial", Font.BOLD, 25));
        PanelSearch.add(LabelSearch);

        JTextField TextFieldEnterSearch = new JTextField();
        TextFieldEnterSearch.setBounds(110, 10, 350, 40);
        TextFieldEnterSearch.setFont(new Font("Arial", Font.PLAIN, 25));
        TextFieldEnterSearch.setBackground(Color.decode("#C9F0FE"));
        PanelSearch.add(TextFieldEnterSearch);

        ImageIcon IconSearch = new ImageIcon("pictures\\ImageBackground\\LOGO_Search.png");
        JButton ButtonSearch = new JButton(IconSearch);
        ButtonSearch.setBounds(470, 10, 40, 40);
        ButtonSearch.setBackground(Color.decode("#FFFFFF"));
        ButtonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                scrollPaneBooks.remove(PanelListBooks);
                String strSearch = TextFieldEnterSearch.getText();

                PanelListBooks = getListBooks(strSearch);
                scrollPaneBooks.setViewportView(PanelListBooks);
                //scrollPaneBooks.setViewportView((new ListBooks(strSearch)).getListItems());
            }
        });
        PanelSearch.add(ButtonSearch);
        PanelBackground.add(PanelSearch);

        frame.setContentPane(PanelBackground);
        frame.pack();
        frame.setLocationRelativeTo(null); //set center the frame on the screen

        return frame;
    }

    public JFrame getUIShowInfos(){
        JFrame frame = new JFrame("Infomation");
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage("pictures\\ImageBackground\\a6.png");
        JPanel PanelBackground = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        PanelBackground.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));

        JLabel labelLibMangement = new JLabel("Lib Management");
        labelLibMangement.setBounds(170, 45, 260, 26);
        labelLibMangement.setFont(new Font("Arial", Font.BOLD, 25));
        PanelBackground.add(labelLibMangement);

        JLabel ImageLogo = new JLabel();
        ImageLogo.setIcon(new ImageIcon("pictures\\ImageBackground\\LOGO 1.png"));
        Dimension size = ImageLogo.getPreferredSize();
        ImageLogo.setBounds(0, 5, size.width, size.height);
        PanelBackground.add(ImageLogo);

        ImageIcon IconDashboard = new ImageIcon("pictures\\ImageBackground\\LOGO_Dashboard.png");
        JButton ButtonDashboard = new JButton("Dashboard", IconDashboard);
        ButtonDashboard.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonDashboard.setIconTextGap(10);
        ButtonDashboard.setHorizontalAlignment(JButton.LEFT);
        ButtonDashboard.setBounds(0, 190, 190, 40);
        ButtonDashboard.setBackground(Color.decode("#F4FAFB"));
        ButtonDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIDashboard());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonDashboard);

        ImageIcon IconBook = new ImageIcon("pictures\\ImageBackground\\LOGO_Book.png");
        JButton ButtonHistory = new JButton("History", IconBook);
        ButtonHistory.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonHistory.setIconTextGap(10);
        ButtonHistory.setHorizontalAlignment(JButton.LEFT);
        ButtonHistory.setBounds(0, 230, 190, 40);
        ButtonHistory.setBackground(Color.decode("#F4FAFB"));
        ButtonHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIHistory());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonHistory);

        ImageIcon IconUser = new ImageIcon("pictures\\ImageBackground\\LOGO_User.png");
        JButton ButtonInteresting = new JButton("Interesting", IconUser);
        ButtonInteresting.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonInteresting.setIconTextGap(10);
        ButtonInteresting.setHorizontalAlignment(JButton.LEFT);
        ButtonInteresting.setBounds(0, 271, 190, 40);
        ButtonInteresting.setBackground(Color.decode("#F4FAFB"));
        ButtonInteresting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIInteresting());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonInteresting);

        ImageIcon IconPayment = new ImageIcon("pictures\\ImageBackground\\LOGO_Payment.png");
        JButton ButtonCart = new JButton("Cart", IconPayment);
        ButtonCart.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonCart.setIconTextGap(10);
        ButtonCart.setHorizontalAlignment(JButton.LEFT);
        ButtonCart.setBounds(0, 311, 190, 40);
        ButtonCart.setBackground(Color.decode("#F4FAFB"));
        ButtonCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUICart());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonCart);

        ImageIcon IconSetting = new ImageIcon("pictures\\ImageBackground\\LOGO_Setting.png");
        JButton ButtonLogout = new JButton("Log out", IconSetting);
        ButtonLogout.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonLogout.setIconTextGap(10);
        ButtonLogout.setHorizontalAlignment(JButton.LEFT);
        ButtonLogout.setBounds(0, 572, 190, 40);
        ButtonLogout.setBackground(Color.decode("#F4FAFB"));
        ButtonLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement lib = new LibManagement();
            }
        });
        PanelBackground.add(ButtonLogout);

        //Informations
        JLabel LabelClient = new JLabel(this.currUser.getID(), SwingConstants.CENTER);
        LabelClient.setFont(new Font("Arial", Font.BOLD, 15));
        LabelClient.setBackground(Color.decode("#A1E7FF"));
        LabelClient.setForeground(Color.decode("#FFF8F7"));
        LabelClient.setBounds(1090, 20, 90, 30);
        LabelClient.setOpaque(true);
        PanelBackground.add(LabelClient);

        JLabel LabelInformationOfUser = new JLabel("Information of User");
        LabelInformationOfUser.setBounds(480, 82, 450, 70);
        LabelInformationOfUser.setBackground(Color.decode("#FFFFFF"));
        LabelInformationOfUser.setFont(new Font("Arial", Font.BOLD, 30));
        LabelInformationOfUser.setHorizontalAlignment(JLabel.CENTER);
        LabelInformationOfUser.setOpaque(true);
        PanelBackground.add(LabelInformationOfUser);

        int iPosX = 500, iWidthSize = 500, iHeightSize = 25, iFontSize = 20;

        JLabel LabelFullName = new JLabel("Full Name: " + this.currUser.getFullname());
        LabelFullName.setBounds(iPosX, 189, iWidthSize, iHeightSize);
        LabelFullName.setFont(new Font("Arial", Font.PLAIN, iFontSize));
        PanelBackground.add(LabelFullName);

        JLabel LabelGender = new JLabel("Gender: " + this.currUser.getGender());
        LabelGender.setBounds(iPosX, 239, iWidthSize, iHeightSize);
        LabelGender.setFont(new Font("Arial", Font.PLAIN, iFontSize));
        PanelBackground.add(LabelGender);

        JLabel LabelEmail = new JLabel("Email: " + this.currUser.getEmailAddress());
        LabelEmail.setBounds(iPosX, 289, iWidthSize, iHeightSize);
        LabelEmail.setFont(new Font("Arial", Font.PLAIN, iFontSize));
        PanelBackground.add(LabelEmail);

        JLabel LabelPhone = new JLabel("Phone Number: " + this.currUser.getPhoneNumber());
        LabelPhone.setBounds(iPosX, 339, iWidthSize, iHeightSize);
        LabelPhone.setFont(new Font("Arial", Font.PLAIN, iFontSize));
        PanelBackground.add(LabelPhone);

        JLabel LabelAddress = new JLabel("Address: " + ((Client)(this.currUser)).getAddress());
        LabelAddress.setBounds(iPosX, 389, iWidthSize, iHeightSize);
        LabelAddress.setFont(new Font("Arial", Font.PLAIN, iFontSize));
        PanelBackground.add(LabelAddress);

        JLabel LabelBankAccount = new JLabel("Bank Account: " + ((Client)(this.currUser)).getBankAccount());
        LabelBankAccount.setBounds(iPosX, 439, iWidthSize, iHeightSize);
        LabelBankAccount.setFont(new Font("Arial", Font.PLAIN, iFontSize));
        PanelBackground.add(LabelBankAccount);

        JLabel LabelBankName = new JLabel("Bank Name: " + ((Client)(this.currUser)).getBankName());
        LabelBankName.setBounds(iPosX, 489, iWidthSize, iHeightSize);
        LabelBankName.setFont(new Font("Arial", Font.PLAIN, iFontSize));
        PanelBackground.add(LabelBankName);

        JButton ButtonUpdate = new JButton("Update");
        ButtonUpdate.setBounds(780, 560, 130, 40);
        ButtonUpdate.setBackground(Color.decode("#85BEF9"));
        ButtonUpdate.setForeground(Color.decode("#F8ECBD"));
        ButtonUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIUpdateInfos());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonUpdate);

        JButton ButtonChangePasswrd = new JButton("Change Password");
        ButtonChangePasswrd.setBounds(480, 560, 150, 40);
        ButtonChangePasswrd.setBackground(Color.decode("#85BEF9"));
        ButtonChangePasswrd.setForeground(Color.decode("#F8ECBD"));
        ButtonChangePasswrd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIChangePassword());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonChangePasswrd);

        frame.setContentPane(PanelBackground);
        frame.pack();
        frame.setLocationRelativeTo(null); //set center the frame on the screen

        return frame;
    }

    public JFrame getUIChangePassword(){
        JFrame frame = new JFrame("Change Password");
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage("pictures\\ImageBackground\\a6.png");
        JPanel PanelBackground = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        PanelBackground.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));

        JLabel labelLibMangement = new JLabel("Lib Management");
        labelLibMangement.setBounds(170, 45, 260, 26);
        labelLibMangement.setFont(new Font("Arial", Font.BOLD, 25));
        PanelBackground.add(labelLibMangement);

        JLabel ImageLogo = new JLabel();
        ImageLogo.setIcon(new ImageIcon("pictures\\ImageBackground\\LOGO 1.png"));
        Dimension size = ImageLogo.getPreferredSize();
        ImageLogo.setBounds(0, 5, size.width, size.height);
        PanelBackground.add(ImageLogo);

        ImageIcon IconDashboard = new ImageIcon("pictures\\ImageBackground\\LOGO_Dashboard.png");
        JButton ButtonDashboard = new JButton("Dashboard", IconDashboard);
        ButtonDashboard.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonDashboard.setIconTextGap(10);
        ButtonDashboard.setHorizontalAlignment(JButton.LEFT);
        ButtonDashboard.setBounds(0, 190, 190, 40);
        ButtonDashboard.setBackground(Color.decode("#F4FAFB"));
        ButtonDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIDashboard());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonDashboard);

        ImageIcon IconBook = new ImageIcon("pictures\\ImageBackground\\LOGO_Book.png");
        JButton ButtonHistory = new JButton("History", IconBook);
        ButtonHistory.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonHistory.setIconTextGap(10);
        ButtonHistory.setHorizontalAlignment(JButton.LEFT);
        ButtonHistory.setBounds(0, 230, 190, 40);
        ButtonHistory.setBackground(Color.decode("#F4FAFB"));
        ButtonHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIHistory());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonHistory);

        ImageIcon IconUser = new ImageIcon("pictures\\ImageBackground\\LOGO_User.png");
        JButton ButtonInteresting = new JButton("Interesting", IconUser);
        ButtonInteresting.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonInteresting.setIconTextGap(10);
        ButtonInteresting.setHorizontalAlignment(JButton.LEFT);
        ButtonInteresting.setBounds(0, 271, 190, 40);
        ButtonInteresting.setBackground(Color.decode("#F4FAFB"));
        ButtonInteresting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIInteresting());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonInteresting);

        ImageIcon IconPayment = new ImageIcon("pictures\\ImageBackground\\LOGO_Payment.png");
        JButton ButtonCart = new JButton("Cart", IconPayment);
        ButtonCart.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonCart.setIconTextGap(10);
        ButtonCart.setHorizontalAlignment(JButton.LEFT);
        ButtonCart.setBounds(0, 311, 190, 40);
        ButtonCart.setBackground(Color.decode("#F4FAFB"));
        ButtonCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUICart());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonCart);

        JLabel LabelClient = new JLabel(this.currUser.getID(), SwingConstants.CENTER);
        LabelClient.setFont(new Font("Arial", Font.BOLD, 15));
        LabelClient.setBackground(Color.decode("#A1E7FF"));
        LabelClient.setForeground(Color.decode("#FFF8F7"));
        LabelClient.setBounds(1090, 20, 90, 30);
        LabelClient.setOpaque(true);
        PanelBackground.add(LabelClient);

        ImageIcon IconSetting = new ImageIcon("pictures\\ImageBackground\\LOGO_Setting.png");
        JButton ButtonLogOut = new JButton("Log out", IconSetting);
        ButtonLogOut.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonLogOut.setIconTextGap(10);
        ButtonLogOut.setHorizontalAlignment(JButton.LEFT);
        ButtonLogOut.setBounds(0, 572, 190, 40);
        ButtonLogOut.setBackground(Color.decode("#F4FAFB"));
        ButtonLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement lib = new LibManagement();
            }
        });
        PanelBackground.add(ButtonLogOut);


        //Change password
        JLabel LabelLibrarianChangePassword = new JLabel("CHANGE PASSSWORD");
        LabelLibrarianChangePassword.setBounds(480, 150, 570, 50);
        LabelLibrarianChangePassword.setBackground(Color.decode("#FFFFFF"));
        LabelLibrarianChangePassword.setForeground(Color.decode("#7C8BFF"));
        LabelLibrarianChangePassword.setFont(new Font("Arial", Font.BOLD, 30));
        LabelLibrarianChangePassword.setHorizontalAlignment(JLabel.CENTER);
        LabelLibrarianChangePassword.setOpaque(true);
        PanelBackground.add(LabelLibrarianChangePassword);

        JLabel LabelOldPassword = new JLabel("Old Password(*)");
        LabelOldPassword.setFont(new Font("Arial", Font.BOLD, 15));
        LabelOldPassword.setBounds(500, 212, 175, 33);
        PanelBackground.add(LabelOldPassword );

        JPasswordField PasswordFieldOldPassword = new JPasswordField();
        PasswordFieldOldPassword.setFont(new Font("Arial", Font.PLAIN, 30));
        PasswordFieldOldPassword.setBounds(685, 212, 270, 33);
        PasswordFieldOldPassword.setBackground(Color.decode("#FFFAF0"));
        PanelBackground.add(PasswordFieldOldPassword);

        JLabel LabelNewPassword = new JLabel("New Password(*)");
        LabelNewPassword.setFont(new Font("Arial", Font.BOLD, 15));
        LabelNewPassword.setBounds(500, 312, 175, 33);
        PanelBackground.add(LabelNewPassword);

        JPasswordField PasswordFiledNewPassword = new JPasswordField();
        PasswordFiledNewPassword.setFont(new Font("Arial", Font.PLAIN, 30));
        PasswordFiledNewPassword.setBounds(685, 312, 270, 33);
        PasswordFiledNewPassword.setBackground(Color.decode("#FFFAF0"));
        PanelBackground.add(PasswordFiledNewPassword);

        JLabel LabelConfirmPassword = new JLabel("Confirm Password(*)");
        LabelConfirmPassword.setFont(new Font("Arial", Font.BOLD, 15));
        LabelConfirmPassword.setBounds(500, 412, 175, 33);
        PanelBackground.add(LabelConfirmPassword);

        JPasswordField PasswordFieldConfirmPassword = new JPasswordField();
        PasswordFieldConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 30));
        PasswordFieldConfirmPassword.setBounds(685, 412, 270, 33);
        PasswordFieldConfirmPassword.setBackground(Color.decode("#FFFAF0"));
        PanelBackground.add(PasswordFieldConfirmPassword);

        JButton ButtonSubmit = new JButton("Submit");
        ButtonSubmit.setBounds(900, 512, 130, 40);
        ButtonSubmit.setBackground(Color.decode("#85BEF9"));
        ButtonSubmit.setForeground(Color.decode("#F8ECBD"));
        ButtonSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String strID = currUser.getID();
                String strOldPassword = String.valueOf(PasswordFieldOldPassword.getPassword());
                String strNewPassword = String.valueOf(PasswordFiledNewPassword.getPassword());
                String strConfirmPassword = String.valueOf(PasswordFieldConfirmPassword.getPassword());

                if (strOldPassword.equals("") || strNewPassword.equals("") || strConfirmPassword.equals("")) {
                    JOptionPane.showMessageDialog(null, "Do not have any empty fields");
                    return;
                }

                Connection connection = null;
                Statement stmt = null;
                ResultSet resultSet = null;

                try {
                    connection = LibManagement.connect();
                    connection.setAutoCommit(false);

                    //Check old password is correct
                    stmt = connection.createStatement();

                    String strQuerry = "SELECT * FROM USER u JOIN CLIENT c ON u.Username = c.Username WHERE c.CID = '" + strID + "'";
                    resultSet = stmt.executeQuery(strQuerry);
                    resultSet.next();

                    String strOP = resultSet.getString("Password");
                    String strUsername = resultSet.getString("Username");

                    if (!strOP.equals(strOldPassword)) {
                        JOptionPane.showMessageDialog(null, "Old Password is incorrect");
                        return;
                    }

                    //Incorrect comfirm password
                    if (!strNewPassword.equals(strConfirmPassword)) {
                        JOptionPane.showMessageDialog(null, "Confirm Password is incorrect");
                        return;
                    }

                    //Save new password
                    strQuerry = "UPDATE USER SET Password = '" + strNewPassword + "' WHERE Username = '" + strUsername + "'";
                    stmt.executeUpdate(strQuerry);

                    connection.commit();
                } catch (SQLException ex) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }

                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();

                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }
                } finally {
                    try {
                        resultSet.close();
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
                }
                
                JOptionPane.showMessageDialog(null, "Update password successfully! Back to login page.");
                LibManagement.getCurrFrame().dispose();
                LibManagement lib = new LibManagement();
            }   
        });
        PanelBackground.add(ButtonSubmit);

        JButton ButtonBack = new JButton("Back");
        ButtonBack.setBounds(500, 512, 130, 40);
        ButtonBack.setBackground(Color.decode("#85BEF9"));
        ButtonBack.setForeground(Color.decode("#F8ECBD"));
        ButtonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIShowInfos());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonBack);

        frame.setContentPane(PanelBackground);
        frame.pack();
        frame.setLocationRelativeTo(null); //set center the frame on the screen

        return frame;
    }

    public JFrame getUIUpdateInfos(){
        JFrame frame = new JFrame("Update Infomation");
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage("pictures\\ImageBackground\\a6.png");
        JPanel PanelBackground = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        PanelBackground.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));

        JLabel labelLibMangement = new JLabel("Lib Management");
        labelLibMangement.setBounds(170, 45, 260, 26);
        labelLibMangement.setFont(new Font("Arial", Font.BOLD, 25));
        PanelBackground.add(labelLibMangement);

        JLabel ImageLogo = new JLabel();
        ImageLogo.setIcon(new ImageIcon("pictures\\ImageBackground\\LOGO 1.png"));
        Dimension size = ImageLogo.getPreferredSize();
        ImageLogo.setBounds(0, 5, size.width, size.height);
        PanelBackground.add(ImageLogo);

        ImageIcon IconDashboard = new ImageIcon("pictures\\ImageBackground\\LOGO_Dashboard.png");
        JButton ButtonDashboard = new JButton("Dashboard", IconDashboard);
        ButtonDashboard.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonDashboard.setIconTextGap(10);
        ButtonDashboard.setHorizontalAlignment(JButton.LEFT);
        ButtonDashboard.setBounds(0, 190, 190, 40);
        ButtonDashboard.setBackground(Color.decode("#F4FAFB"));
        ButtonDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIDashboard());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonDashboard);

        ImageIcon IconBook = new ImageIcon("pictures\\ImageBackground\\LOGO_Book.png");
        JButton ButtonHistory = new JButton("History", IconBook);
        ButtonHistory.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonHistory.setIconTextGap(10);
        ButtonHistory.setHorizontalAlignment(JButton.LEFT);
        ButtonHistory.setBounds(0, 230, 190, 40);
        ButtonHistory.setBackground(Color.decode("#F4FAFB"));
        ButtonHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIHistory());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonHistory);

        ImageIcon IconUser = new ImageIcon("pictures\\ImageBackground\\LOGO_User.png");
        JButton ButtonInteresting = new JButton("Interesting", IconUser);
        ButtonInteresting.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonInteresting.setIconTextGap(10);
        ButtonInteresting.setHorizontalAlignment(JButton.LEFT);
        ButtonInteresting.setBounds(0, 271, 190, 40);
        ButtonInteresting.setBackground(Color.decode("#F4FAFB"));
        ButtonInteresting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIInteresting());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonInteresting);

        ImageIcon IconPayment = new ImageIcon("pictures\\ImageBackground\\LOGO_Payment.png");
        JButton ButtonCart = new JButton("Cart", IconPayment);
        ButtonCart.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonCart.setIconTextGap(10);
        ButtonCart.setHorizontalAlignment(JButton.LEFT);
        ButtonCart.setBounds(0, 311, 190, 40);
        ButtonCart.setBackground(Color.decode("#F4FAFB"));
        ButtonCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUICart());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonCart);

        JLabel LabelClient = new JLabel(this.currUser.getID(), SwingConstants.CENTER);
        LabelClient.setFont(new Font("Arial", Font.BOLD, 15));
        LabelClient.setBackground(Color.decode("#A1E7FF"));
        LabelClient.setForeground(Color.decode("#FFF8F7"));
        LabelClient.setBounds(1090, 20, 90, 30);
        LabelClient.setOpaque(true);
        PanelBackground.add(LabelClient);

        ImageIcon IconSetting = new ImageIcon("pictures\\ImageBackground\\LOGO_Setting.png");
        JButton ButtonLogOut = new JButton("Log out", IconSetting);
        ButtonLogOut.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonLogOut.setIconTextGap(10);
        ButtonLogOut.setHorizontalAlignment(JButton.LEFT);
        ButtonLogOut.setBounds(0, 572, 190, 40);
        ButtonLogOut.setBackground(Color.decode("#F4FAFB"));
        ButtonLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement lib = new LibManagement();
            }
        });
        PanelBackground.add(ButtonLogOut);

        //Update Infos
        JLabel LabelUpdateUser = new JLabel("UPDATE USER");
        LabelUpdateUser.setBounds(458, 77, 570, 70);
        LabelUpdateUser.setBackground(Color.decode("#FFFFFF"));
        LabelUpdateUser.setForeground(Color.decode("#7C8BFF"));
        LabelUpdateUser.setFont(new Font("Arial", Font.BOLD, 30));
        LabelUpdateUser.setHorizontalAlignment(JLabel.CENTER);
        LabelUpdateUser.setOpaque(true);
        PanelBackground.add(LabelUpdateUser);

        JPanel PanelUpdate = new JPanel();
        PanelUpdate.setBounds(458, 152, 570, 412);
        PanelUpdate.setBackground(Color.decode("#FFFFFF"));
        PanelUpdate.setLayout(null);
        PanelBackground.add(PanelUpdate);

        JLabel LabelOldPassword  = new JLabel("Full Name");
        LabelOldPassword .setFont(new Font("Arial", Font.PLAIN, 15));
        LabelOldPassword .setBounds(18, 16, 100, 17);
        PanelUpdate.add(LabelOldPassword );

        JTextField PasswordFieldOldPassword = new JTextField();
        PasswordFieldOldPassword.setFont(new Font("Arial", Font.PLAIN, 30));
        PasswordFieldOldPassword.setBounds(127, 12, 370, 33);
        PasswordFieldOldPassword.setBackground(Color.decode("#FFFAF0"));
        PanelUpdate.add(PasswordFieldOldPassword);

        JLabel LabelGender = new JLabel("Gender");
        LabelGender.setFont(new Font("Arial", Font.PLAIN, 15));
        LabelGender.setBounds(18, 67, 100, 17);
        PanelUpdate.add(LabelGender);

        ButtonGroup buttongroup = new ButtonGroup();
        JRadioButton RadioButtonMale = new JRadioButton("Male");
        RadioButtonMale.setBackground(Color.decode("#FFFFFF"));
        RadioButtonMale.setSelected(this.currUser.getGender().equals("Male"));
        RadioButtonMale.setBounds(195, 65, 50, 40);
        RadioButtonMale.setHorizontalTextPosition(SwingConstants.CENTER);
        RadioButtonMale.setVerticalTextPosition(SwingConstants.BOTTOM);
        buttongroup.add(RadioButtonMale);
        PanelUpdate.add(RadioButtonMale);

        JRadioButton RadioButtonFemale = new JRadioButton("Female");
        RadioButtonFemale.setBackground(Color.decode("#FFFFFF"));
        RadioButtonFemale.setSelected(this.currUser.getGender().equals("Female"));
        RadioButtonFemale.setBounds(296, 65, 52, 40);
        RadioButtonFemale.setHorizontalTextPosition(SwingConstants.CENTER);
        RadioButtonFemale.setVerticalTextPosition(SwingConstants.BOTTOM);
        buttongroup.add(RadioButtonFemale);
        PanelUpdate.add(RadioButtonFemale);

        JRadioButton RadioButtonOther = new JRadioButton("Other");
        RadioButtonOther.setBackground(Color.decode("#FFFFFF"));
        RadioButtonOther.setSelected(this.currUser.getGender().equals("Other"));
        RadioButtonOther.setBounds(396, 65, 50, 40);
        RadioButtonOther.setHorizontalTextPosition(SwingConstants.CENTER);
        RadioButtonOther.setVerticalTextPosition(SwingConstants.BOTTOM);
        buttongroup.add(RadioButtonOther);
        PanelUpdate.add(RadioButtonOther);

        JLabel LabelPhone = new JLabel("Phone Number");
        LabelPhone.setFont(new Font("Arial", Font.PLAIN, 15));
        LabelPhone.setBounds(18, 125, 110, 33);
        PanelUpdate.add(LabelPhone);

        JTextField TextFieldPhone = new JTextField();
        TextFieldPhone.setFont(new Font("Arial", Font.PLAIN, 30));
        TextFieldPhone.setBounds(130, 125, 370, 33);
        TextFieldPhone.setBackground(Color.decode("#FFFAF0"));
        PanelUpdate.add(TextFieldPhone);

        JLabel LabelEmail = new JLabel("Email");
        LabelEmail.setFont(new Font("Arial", Font.PLAIN, 15));
        LabelEmail.setBounds(18, 180, 100, 33);
        PanelUpdate.add(LabelEmail);

        JTextField TextFieldEmail = new JTextField();
        TextFieldEmail.setFont(new Font("Arial", Font.PLAIN, 30));
        TextFieldEmail.setBounds(130, 180, 370, 33);
        TextFieldEmail.setBackground(Color.decode("#FFFAF0"));
        PanelUpdate.add(TextFieldEmail);

        JLabel LabelAddress = new JLabel("Address");
        LabelAddress.setFont(new Font("Arial", Font.PLAIN, 15));
        LabelAddress.setBounds(18, 237, 100, 33);
        PanelUpdate.add(LabelAddress);

        JTextField TextFieldAddress = new JTextField();
        TextFieldAddress.setFont(new Font("Arial", Font.PLAIN, 30));
        TextFieldAddress.setBounds(130, 237, 370, 33);
        TextFieldAddress.setBackground(Color.decode("#FFFAF0"));
        PanelUpdate.add(TextFieldAddress);

        JLabel LabelBankAccount = new JLabel("Bank Account");
        LabelBankAccount.setFont(new Font("Arial", Font.PLAIN, 15));
        LabelBankAccount.setBounds(18, 287, 105, 33);
        PanelUpdate.add(LabelBankAccount);

        JTextField TextfieldBankAccount = new JTextField();
        TextfieldBankAccount.setFont(new Font("Arial", Font.PLAIN, 30));
        TextfieldBankAccount.setBounds(130, 287, 370, 33);
        TextfieldBankAccount.setBackground(Color.decode("#FFFAF0"));
        PanelUpdate.add(TextfieldBankAccount);

        JLabel LabelBankName = new JLabel("Bank Name");
        LabelBankName.setFont(new Font("Arial", Font.PLAIN, 15));
        LabelBankName.setBounds(18, 344, 100, 17);
        PanelUpdate.add(LabelBankName);

        JTextField TextFieldBankName = new JTextField();
        TextFieldBankName.setFont(new Font("Arial", Font.PLAIN, 30));
        TextFieldBankName.setBounds(130, 340, 370, 33);
        TextFieldBankName.setBackground(Color.decode("#FFFAF0"));
        PanelUpdate.add(TextFieldBankName);

        JButton ButtonChange = new JButton("Change");
        ButtonChange.setBounds(900, 570, 100, 40);
        ButtonChange.setBackground(Color.decode("#85BEF9"));
        ButtonChange.setForeground(Color.decode("#F8ECBD"));
        ButtonChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Update infos of user
                Connection connection = null;
                Statement stmt = null;

                try {
                    connection = LibManagement.connect();
                    connection.setAutoCommit(false);
                    stmt = connection.createStatement();

                    String strID = currUser.getID();

                    String strPhoneNumber = TextFieldPhone.getText();
                    if (!strPhoneNumber.equals("")){ //If filling in phone number
                        try {
                            int num = Integer.parseInt(strPhoneNumber);

                            String strQuerry = "UPDATE CLIENT SET PhoneNum = " + strPhoneNumber + " WHERE CID = '" + strID + "';";
                            stmt.executeUpdate(strQuerry);
                        } catch (NumberFormatException ex){
                            JOptionPane.showMessageDialog(null, "Not correct phone number!");
                            return;
                        }
                    }

                    String strFullName = PasswordFieldOldPassword.getText();
                    if (!strFullName.equals("")){ //If filling in full name
                        String strQuerry = "UPDATE CLIENT SET Fullname = '" + strFullName + "' WHERE CID = '" + strID + "';";
                        stmt.executeUpdate(strQuerry);
                    }

                    String strGender = RadioButtonMale.isSelected() ? "Male" : (RadioButtonFemale.isSelected() ? "Female" : "Other");
                    if (!strGender.equals("")){ //If filling in gender
                        String strQuerry = "UPDATE CLIENT SET Gender = '" + strGender + "' WHERE CID = '" + strID + "';";
                        stmt.executeUpdate(strQuerry);
                    }

                    String strEmail = TextFieldEmail.getText();
                    if (!strEmail.equals("")){ //If filling in email
                        String strQuerry = "UPDATE CLIENT SET Email = '" + strEmail + "' WHERE CID = '" + strID + "';";
                        stmt.executeUpdate(strQuerry);
                    }

                    String strAddress = TextFieldAddress.getText();
                    if (!strAddress.equals("")){ //If filling in address
                        String strQuerry = "UPDATE CLIENT SET Address = '" + strAddress + "' WHERE CID = '" + strID + "';";
                        stmt.executeUpdate(strQuerry);
                    }

                    String strBankAccount = TextfieldBankAccount.getText();
                    if (!strBankAccount.equals("")){ //If filling in bank account
                        String strQuerry = "UPDATE CLIENT SET BankAccount = '" + strBankAccount + "' WHERE CID = '" + strID + "';";
                        stmt.executeUpdate(strQuerry);
                    }

                    String strBankName = TextFieldBankName.getText();
                    if (!strBankName.equals("")){ //If filling in bank name
                        String strQuerry = "UPDATE CLIENT SET BankName = '" + strBankName + "' WHERE CID = '" + strID + "';";
                        stmt.executeUpdate(strQuerry);
                    }

                    connection.commit();
                } catch (SQLException ex) {
                    try {
                        connection.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    ex.printStackTrace();
                } catch (Exception ex) {
                    try {
                        connection.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    ex.printStackTrace();
                } finally {
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
                }

                //Set this user to a new user
                ResultSet resultSet = null;
                try {
                    connection = LibManagement.connect();
                    stmt = connection.createStatement();

                    String strQuery = "SELECT * FROM CLIENT WHERE CID = '" + currUser.getID() + "';";
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

                    currUser = new Client(strID, strFullname, strPhoneNum, strEmail, strGender, dateCreatedDate,
                                        strAddress, strBankAccount, strBankName);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        resultSet.close();
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
                }

                JOptionPane.showMessageDialog(null, "Update Success! Back to see your changed infos");
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIShowInfos());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonChange);

        JButton ButtonBackToUser = new JButton("Back");
        ButtonBackToUser.setBounds(480, 570, 100, 40);
        ButtonBackToUser.setBackground(Color.decode("#85BEF9"));
        ButtonBackToUser.setForeground(Color.decode("#F8ECBD"));
        ButtonBackToUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIShowInfos());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonBackToUser);

        frame.setContentPane(PanelBackground);
        frame.pack();
        frame.setLocationRelativeTo(null); //set center the frame on the screen

        return frame;
    }

    public JFrame getUIBookDetail(Book currBook){
        JFrame frame = new JFrame("Details Of Book");
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage("pictures\\ImageBackground\\a6.png");
        JPanel PanelBackground = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        PanelBackground.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));

        JLabel labelLibMangement = new JLabel("Lib Management");
        labelLibMangement.setBounds(170, 45, 260, 26);
        labelLibMangement.setFont(new Font("Arial", Font.BOLD, 25));
        PanelBackground.add(labelLibMangement);

        JLabel ImageLogo = new JLabel();
        ImageLogo.setIcon(new ImageIcon("pictures\\ImageBackground\\LOGO 1.png"));
        Dimension size = ImageLogo.getPreferredSize();
        ImageLogo.setBounds(0, 5, size.width, size.height);
        PanelBackground.add(ImageLogo);

        ImageIcon IconDashboard = new ImageIcon("pictures\\ImageBackground\\LOGO_Dashboard.png");
        JButton ButtonDashboard = new JButton("Dashboard", IconDashboard);
        ButtonDashboard.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonDashboard.setIconTextGap(10);
        ButtonDashboard.setHorizontalAlignment(JButton.LEFT);
        ButtonDashboard.setBounds(0, 190, 190, 40);
        ButtonDashboard.setBackground(Color.decode("#F4FAFB"));
        ButtonDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIDashboard());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonDashboard);

        ImageIcon IconBook = new ImageIcon("pictures\\ImageBackground\\LOGO_Book.png");
        JButton ButtonHistory = new JButton("History", IconBook);
        ButtonHistory.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonHistory.setIconTextGap(10);
        ButtonHistory.setHorizontalAlignment(JButton.LEFT);
        ButtonHistory.setBounds(0, 230, 190, 40);
        ButtonHistory.setBackground(Color.decode("#F4FAFB"));
        ButtonHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIHistory());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonHistory);

        ImageIcon IconUser = new ImageIcon("pictures\\ImageBackground\\LOGO_User.png");
        JButton ButtonInteresting = new JButton("Interesting", IconUser);
        ButtonInteresting.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonInteresting.setIconTextGap(10);
        ButtonInteresting.setHorizontalAlignment(JButton.LEFT);
        ButtonInteresting.setBounds(0, 271, 190, 40);
        ButtonInteresting.setBackground(Color.decode("#F4FAFB"));
        ButtonInteresting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIInteresting());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonInteresting);

        ImageIcon IconPayment = new ImageIcon("pictures\\ImageBackground\\LOGO_Payment.png");
        JButton ButtonCart = new JButton("Cart", IconPayment);
        ButtonCart.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonCart.setIconTextGap(10);
        ButtonCart.setHorizontalAlignment(JButton.LEFT);
        ButtonCart.setBounds(0, 311, 190, 40);
        ButtonCart.setBackground(Color.decode("#F4FAFB"));
        ButtonCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUICart());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonCart);

        JButton ButtonClient = new JButton(this.currUser.getID());
        ButtonClient.setFont(new Font("Arial", Font.BOLD, 15));
        ButtonClient.setBackground(Color.decode("#FFFFFF"));
        ButtonClient.setBounds(1090, 20, 90, 30);
        ButtonClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIShowInfos());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonClient);

        ImageIcon IconSetting = new ImageIcon("pictures\\ImageBackground\\LOGO_Setting.png");
        JButton ButtonLogOut = new JButton("Log out", IconSetting);
        ButtonLogOut.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonLogOut.setIconTextGap(10);
        ButtonLogOut.setHorizontalAlignment(JButton.LEFT);
        ButtonLogOut.setBounds(0, 572, 190, 40);
        ButtonLogOut.setBackground(Color.decode("#F4FAFB"));
        ButtonLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement lib = new LibManagement();
            }
        });
        PanelBackground.add(ButtonLogOut);

        //Show details of book
        ImageIcon ImageIconBook = new ImageIcon(currBook.getPathImage());
        Image ImageScaleBook = ImageIconBook.getImage();    
        ImageIconBook = new ImageIcon(ImageScaleBook.getScaledInstance(200, 250, Image.SCALE_SMOOTH));
        JLabel LabelImageBook = new JLabel(ImageIconBook, SwingConstants.CENTER);
        LabelImageBook.setBounds(360, 145, 200, 250);
        PanelBackground.add(LabelImageBook);

        int iWidth = 500, iHeight = 20, iSize = 20;

        JLabel LabelTitle = new JLabel("Tittle: " + currBook.getTitle());
        LabelTitle.setBounds(720, 145, iWidth, iHeight);
        LabelTitle.setFont(new Font("Arial", Font.BOLD, iSize));
        PanelBackground.add(LabelTitle);

        JLabel LabelAuthor = new JLabel("Author: " + currBook.getAuthor());
        LabelAuthor.setBounds(720, 180, iWidth, iHeight);
        LabelAuthor.setFont(new Font("Arial", Font.PLAIN, iSize));
        PanelBackground.add(LabelAuthor);

        JLabel LabelPublishingHouse = new JLabel("Publishing House: " + currBook.getPublishingHouse());
        LabelPublishingHouse.setBounds(720, 215, iWidth, iHeight);
        LabelPublishingHouse.setFont(new Font("Arial", Font.PLAIN, iSize));
        PanelBackground.add(LabelPublishingHouse);

        JLabel LabelPublishingYear = new JLabel("Publishing Year: " + currBook.getYearPublic());
        LabelPublishingYear.setBounds(720, 250, iWidth, iHeight);
        LabelPublishingYear.setFont(new Font("Arial", Font.PLAIN, iSize));
        PanelBackground.add(LabelPublishingYear);

        JLabel LabelGenre = new JLabel("Genre: " + currBook.getGenre());
        LabelGenre.setBounds(720, 285, iWidth, iHeight);
        LabelGenre.setFont(new Font("Arial", Font.PLAIN, iSize));
        PanelBackground.add(LabelGenre);

        JLabel LabelHasLeft = new JLabel("Has Left: " + currBook.getHasLeft());
        LabelHasLeft.setBounds(720, 320, iWidth, iHeight);
        LabelHasLeft.setFont(new Font("Arial", Font.PLAIN, iSize));
        PanelBackground.add(LabelHasLeft);

        JLabel LabelPlace = new JLabel("Place: " + currBook.getPlace());
        LabelPlace.setBounds(720, 355, iWidth, iHeight);
        LabelPlace.setFont(new Font("Arial", Font.PLAIN, iSize));
        PanelBackground.add(LabelPlace);

        JLabel LabelPrice = new JLabel("Price: " + currBook.getPrice() + "VND");
        LabelPrice.setBounds(720, 390, iWidth, iHeight);
        LabelPrice.setFont(new Font("Arial", Font.PLAIN, iSize));
        PanelBackground.add(LabelPrice);

        JLabel LabelRoutine = new JLabel("Routine: " + currBook.getRoutine() + " days");
        LabelRoutine.setBounds(720, 425, iWidth, iHeight);
        LabelRoutine.setFont(new Font("Arial", Font.PLAIN, iSize));
        PanelBackground.add(LabelRoutine);

        double dRating = (double)Math.ceil(currBook.getRating() * 10) / 10;
        Color colorRating = dRating == 0 ? Color.BLACK : (dRating < 3 ? Color.RED : Color.GREEN);
        JLabel LabelRating = new JLabel(dRating + "/5.0", SwingConstants.CENTER);
        LabelRating.setBounds(410, 410, 100, 30);
        LabelRating.setFont(new Font("Arial", Font.BOLD, 30));
        LabelRating.setForeground(colorRating);
        PanelBackground.add(LabelRating);

        JLabel LabelReview = new JLabel("Rating Book:");
        LabelReview.setBounds(360, 480, 150, 25);
        LabelReview.setFont(new Font("Arial", Font.BOLD, 20));
        PanelBackground.add(LabelReview);

        Integer arrInteger[] = {0, 1, 2, 3, 4, 5};
        int iIndexRatingOfUser = 0;
        JComboBox ComboBoxRating = new JComboBox(arrInteger); 
        ComboBoxRating.setBounds(520, 480, 50, 25);
        if (true) { //What did the user choose?
            Connection connection = null;
            Statement stmt = null;
            ResultSet resultSet = null;

            try {
                connection = LibManagement.connect();
                stmt = connection.createStatement();

                String strQuerry = "SELECT * FROM REVIEW WHERE CID = '" + currUser.getID() + "' AND BID = '" + currBook.getID() + "';";
                resultSet = stmt.executeQuery(strQuerry);
                
                if (resultSet.next()) {
                    iIndexRatingOfUser = resultSet.getInt("RATE"); 
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        ComboBoxRating.setSelectedIndex(iIndexRatingOfUser);
        ComboBoxRating.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int iUserRating = ComboBoxRating.getSelectedIndex();
                Connection connection = null;
                Statement stmt = null;
                ResultSet resultSet = null;

                try {
                    connection = LibManagement.connect();
                    connection.setAutoCommit(false);
                    stmt = connection.createStatement();

                    if (iUserRating == 0) {
                        String strQuerry = "DELETE FROM REVIEW WHERE CID = '" + currUser.getID() + "' AND BID = '" + currBook.getID() + "';";
                        stmt.executeUpdate(strQuerry);

                        connection.commit();
                        //Set a new average rating of book
                        strQuerry = "SELECT AVG(Rate) a FROM REVIEW GROUP BY BID HAVING BID = '" + currBook.getID() + "'";
                        resultSet = stmt.executeQuery(strQuerry);
                        double dNewAvgRating = 0.0;
                        if(resultSet.next()) {
                            dNewAvgRating = (double)Math.ceil(resultSet.getDouble("a") * 10) / 10 ;
                        }
                        
                        LabelRating.setText(dNewAvgRating + "/5.0");
                        Color colorNewRating = dNewAvgRating == 0 ? Color.BLACK : (dNewAvgRating < 3 ? Color.RED : Color.GREEN);
                        LabelRating.setForeground(colorNewRating);
                        currBook.setRating(dNewAvgRating);
                        return;
                    }

                    String strQuerry = "SELECT * FROM REVIEW WHERE CID = '" + currUser.getID() + "' AND BID = '" + currBook.getID() + "';";
                    resultSet = stmt.executeQuery(strQuerry);
                    
                    if (resultSet.next()) { //Update the rating of book on REVIEW table
                        strQuerry = "UPDATE REVIEW SET RATE = " + iUserRating +
                                    " WHERE CID = '" + currUser.getID() + "' AND BID = '" + currBook.getID() + "';";
                        stmt.executeUpdate(strQuerry);
                    } else { //Insert the rating of book into REVIEW table
                        strQuerry = "INSERT INTO REVIEW (CID, BID, RATE) VALUES " +
                                    "('" + currUser.getID() + "', '" + currBook.getID() + "', " + iUserRating + ");";
                        stmt.executeUpdate(strQuerry);
                    }

                    connection.commit();

                    //Set a new average rating of book
                    strQuerry = "SELECT AVG(Rate) a FROM REVIEW GROUP BY BID HAVING BID = '" + currBook.getID() + "'";
                    resultSet = stmt.executeQuery(strQuerry);
                    resultSet.next();

                    double dNewAvgRating = (double)Math.ceil(resultSet.getDouble("a") * 10) / 10 ;
                    LabelRating.setText(dNewAvgRating + "/5.0");
                    Color colorNewRating = dNewAvgRating == 0 ? Color.BLACK : (dNewAvgRating < 3 ? Color.RED : Color.GREEN);
                    LabelRating.setForeground(colorNewRating);
                    currBook.setRating(dNewAvgRating);
                } catch (SQLException ex) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }

                    ex.printStackTrace();
                } catch (Exception ex) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }

                    ex.printStackTrace();
                } finally {
                    try {
                        resultSet.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        PanelBackground.add(ComboBoxRating);

        JButton ButtonLike = new JButton();
        ButtonLike.setBounds(705, 532, 120, 40);
        ButtonLike.setBackground(Color.decode("#95D5E3"));
        ButtonLike.setForeground(Color.decode("#FFF8F7"));
        if (true) { //Set status of like button
            Connection connection = null;
            Statement stmt = null;
            ResultSet resultSet = null;

            try {
                connection = LibManagement.connect();
                stmt = connection.createStatement();

                String strQuerry = "SELECT * FROM INTERESTING WHERE CID = '" + currUser.getID() + "' AND BID = '" + currBook.getID() + "';";
                resultSet = stmt.executeQuery(strQuerry);

                if (resultSet.next()) {
                    ButtonLike.setText("UNLIKE");
                    ButtonLike.setBackground(Color.decode("#FF5722"));
                    ButtonLike.setForeground(Color.decode("#000000"));
                } else {
                    ButtonLike.setText("LIKE");
                    ButtonLike.setBackground(Color.decode("#95D5E3"));
                    ButtonLike.setForeground(Color.decode("#FFF8F7"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        ButtonLike.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                PreparedStatement pstmt = null;

                try {
                    connection = LibManagement.connect();
                    connection.setAutoCommit(false);

                    if (ButtonLike.getText().equals("LIKE")) { //If like button is LIKE, insert data into INTERESTING table
                        String strQuerry = "INSERT INTO INTERESTING (CID, BID) VALUES (?, ?);";
                        pstmt = connection.prepareStatement(strQuerry);
                        pstmt.setString(1, currUser.getID());
                        pstmt.setString(2, currBook.getID());
                        pstmt.executeUpdate();

                        //Set the status of like button
                        ButtonLike.setText("UNLIKE");
                        ButtonLike.setBackground(Color.decode("#FF5722"));
                        ButtonLike.setForeground(Color.decode("#000000"));
                    } else { //If like button is UNLIKE, delete date from INTERESTING table
                        String strQuerry = "DELETE FROM INTERESTING WHERE CID = ? AND BID = ?;";
                        pstmt = connection.prepareStatement(strQuerry);
                        pstmt.setString(1, currUser.getID());
                        pstmt.setString(2, currBook.getID());
                        pstmt.executeUpdate();

                        //Set the status of like button
                        ButtonLike.setText("LIKE");
                        ButtonLike.setBackground(Color.decode("#95D5E3"));
                        ButtonLike.setForeground(Color.decode("#FFF8F7"));
                    }

                    connection.commit();
                } catch (SQLException ex) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }
                    ex.printStackTrace();
                } catch (Exception ex) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }
                    ex.printStackTrace();
                } finally {
                    try {
                        pstmt.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        PanelBackground.add(ButtonLike);

        JButton ButtonAddToCart = new JButton("Add to cart");
        ButtonAddToCart.setBounds(915, 532, 170, 40);
        ButtonAddToCart.setBackground(Color.decode("#95D5E3"));
        ButtonAddToCart.setForeground(Color.decode("#FFF8F7"));
        ButtonAddToCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                Statement stmt = null;
                PreparedStatement pstmt = null;
                ResultSet resultSet = null;

                try {
                    connection = LibManagement.connect();
                    connection.setAutoCommit(false);
                    stmt = connection.createStatement();

                    String strQuerry = "SELECT * FROM TRANSACTION WHERE CID = '" + currUser.getID() + "' AND Status = 'Waiting'";
                    resultSet = stmt.executeQuery(strQuerry);

                    
                    if (resultSet.next()) { //If exists the transaction being waiting for payment
                        int iNumOfBooks = 0;
                        String strTID = resultSet.getString("TID");
                        int iTotalPrice = resultSet.getInt("TotalPrice");
                        int iTotalRoutine = resultSet.getInt("TotalRoutine");
                        int iQuantity = resultSet.getInt("Quantity");

                        //If this book is borrowing or waiting, has no transaction
                        strQuerry = "SELECT d.* FROM DETAILTRANS d JOIN TRANSACTION t ON d.TID = t.TID " + 
                                    "WHERE t.CID = '" + currUser.getID() + "' AND (t.Status = 'Waiting' OR t.Status = 'Borrowing');";
                        resultSet = stmt.executeQuery(strQuerry);
                        while (resultSet.next()) {
                            if (resultSet.getString("BID").equals(currBook.getID())){
                                JOptionPane.showMessageDialog(null, "You have got this book!");
                                return;
                            }
                        }

                        //Get the number of books of waiting transaction
                        strQuerry = "SELECT COUNT(*) c FROM DETAILTRANS WHERE TID = '" + strTID + "';";
                        resultSet = stmt.executeQuery(strQuerry);
                        resultSet.next();
                        iNumOfBooks += resultSet.getInt("c");

                        //Get the number of books of borrowing transaction
                        strQuerry = "SELECT COUNT(*) c " + 
                                    "FROM DETAILTRANS d JOIN TRANSACTION t ON d.TID = t.TID " +
                                    "WHERE t.CID = '" + currUser.getID() + "' AND t.Status = 'Borrowing'";
                        resultSet = stmt.executeQuery(strQuerry);
                        resultSet.next();
                        iNumOfBooks += resultSet.getInt("c");

                        //If the number of books is greater than or equal to 3, have no transaction
                        if (iNumOfBooks >= 3) {
                            JOptionPane.showMessageDialog(null, "You got the maximum number of book");
                            return;
                        }

                        //If the number of books is less than 3, handling database
                        //Insert this book into detailtransacion
                        strQuerry = "INSERT INTO DETAILTRANS (TID, BID) VALUES ('" + strTID + "', '" + currBook.getID() + "');";
                        stmt.executeUpdate(strQuerry);

                        //Update the total price in db transaction
                        iTotalPrice += currBook.getPrice();
                        strQuerry = "UPDATE TRANSACTION SET TotalPrice = " + iTotalPrice + " WHERE TID = '" + strTID + "';";
                        stmt.executeUpdate(strQuerry);

                        //Update the total routine in db transaction
                        iTotalRoutine += currBook.getRoutine();
                        strQuerry = "UPDATE TRANSACTION SET TotalRoutine = " + iTotalRoutine + " WHERE TID = '" + strTID + "';";
                        stmt.executeUpdate(strQuerry);

                        //Update the quantity in db transaction
                        iQuantity += 1;
                        strQuerry = "UPDATE TRANSACTION SET Quantity = " + iQuantity + " WHERE TID = '" + strTID + "';";
                        stmt.executeUpdate(strQuerry);

                        JOptionPane.showMessageDialog(null, "Add book to cart successfully");
                    } else { //If not exists the transaction being waiting for payment
                        int iNumOfBooks = 0;

                        //If this book is borrowing, has no transaction
                        strQuerry = "SELECT * FROM DETAILTRANS d JOIN TRANSACTION t ON d.TID = t.TID " + 
                                    "WHERE t.CID = '" + currUser.getID() + "' AND (t.Status = 'Waiting' OR t.Status = 'Borrowing');";
                        resultSet = stmt.executeQuery(strQuerry);
                        while (resultSet.next()) {
                            if (resultSet.getString("BID").equals(currBook.getID())){
                                JOptionPane.showMessageDialog(null, "You have got this book!");
                                return;
                            }
                        }

                        //Get the number of books of borrowing transaction
                        strQuerry = "SELECT COUNT(*) c " + 
                                    "FROM DETAILTRANS d JOIN TRANSACTION t ON d.TID = t.TID " +
                                    "WHERE t.CID = '" + currUser.getID() + "' AND t.Status = 'Borrowing'";
                        resultSet = stmt.executeQuery(strQuerry);
                        resultSet.next();
                        iNumOfBooks += resultSet.getInt("c");

                        //If the number of books is greater than or equal to 3, have no transaction
                        if (iNumOfBooks >= 3) {
                            JOptionPane.showMessageDialog(null, "You got the maximum number of books of borrowing");
                            return;
                        }

                        //If the number of books is less than 2, handling database
                        //Generate a new transaction
                        strQuerry = "INSERT INTO TRANSACTION (CID, TotalPrice, TotalRoutine, Quantity, Status) VALUES (?, ?, ?, ?, ?)";
                        pstmt = connection.prepareStatement(strQuerry);
                        pstmt.setString(1, currUser.getID());
                        pstmt.setInt(2, currBook.getPrice());
                        pstmt.setInt(3, currBook.getRoutine());
                        pstmt.setInt(4, 1);
                        pstmt.setString(5, "Waiting");
                        pstmt.executeUpdate();

                        //Insert this book into detailtransacion which has waiting status
                        strQuerry = "SELECT * FROM TRANSACTION WHERE CID = '" + currUser.getID() + "' AND Status = 'Waiting'";
                        resultSet = stmt.executeQuery(strQuerry);
                        resultSet.next();

                        String strTID = resultSet.getString("TID");

                        strQuerry = "INSERT INTO DETAILTRANS (TID, BID) VALUES ('" + strTID + "', '" + currBook.getID() + "');";
                        stmt.executeUpdate(strQuerry); 

                        JOptionPane.showMessageDialog(null, "Add book to cart successfully");
                    }

                    connection.commit();
                } catch (SQLException ex) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }
                    ex.printStackTrace();
                } catch (Exception ex) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }
                    ex.printStackTrace();
                } finally {
                    try {
                        resultSet.close();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }

                    try {
                        pstmt.close();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }

                    try {
                        stmt.close();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }

                    try {
                        connection.close();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }
                }

            }
        });
        PanelBackground.add(ButtonAddToCart);

        frame.setContentPane(PanelBackground);
        frame.pack();
        frame.setLocationRelativeTo(null); //set center the frame on the screen

        return frame;
    }

    public JFrame getUIHistory(){
        JFrame frame = new JFrame("History");
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage("pictures\\ImageBackground\\a6.png");
        JPanel PanelBackground = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        PanelBackground.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));

        JLabel LabelAboutHistory = new JLabel("History");
        LabelAboutHistory.setBounds(480, 82, 450, 70);
        LabelAboutHistory.setBackground(Color.decode("#FFFFFF"));
        LabelAboutHistory.setFont(new Font("Arial", Font.BOLD, 30));
        LabelAboutHistory.setHorizontalAlignment(JLabel.CENTER);
        LabelAboutHistory.setOpaque(true);
        PanelBackground.add(LabelAboutHistory);

        JLabel labelLibMangement = new JLabel("Lib Management");
        labelLibMangement.setBounds(170, 45, 260, 26);
        labelLibMangement.setFont(new Font("Arial", Font.BOLD, 25));
        PanelBackground.add(labelLibMangement);

        JLabel ImageLogo = new JLabel();
        ImageLogo.setIcon(new ImageIcon("pictures\\ImageBackground\\LOGO 1.png"));
        Dimension size = ImageLogo.getPreferredSize();
        ImageLogo.setBounds(0, 5, size.width, size.height);
        PanelBackground.add(ImageLogo);

        JLabel LabelHistoryBorrowBook = new JLabel("All History Borrowed Book");
        LabelHistoryBorrowBook.setFont(new Font("Arial", Font.BOLD, 25));
        LabelHistoryBorrowBook.setBounds(530, 105, 320, 26);
        PanelBackground.add(LabelHistoryBorrowBook);

        ImageIcon IconDashboard = new ImageIcon("pictures\\ImageBackground\\LOGO_Dashboard.png");
        JButton ButtonDashboard = new JButton("Dashboard", IconDashboard);
        ButtonDashboard.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonDashboard.setIconTextGap(10);
        ButtonDashboard.setHorizontalAlignment(JButton.LEFT);
        ButtonDashboard.setBounds(0, 190, 190, 40);
        ButtonDashboard.setBackground(Color.decode("#F4FAFB"));
        ButtonDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIDashboard());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonDashboard);

        ImageIcon IconBook = new ImageIcon("pictures\\ImageBackground\\LOGO_Book.png");
        JLabel LabelHistory = new JLabel("History", IconBook, JLabel.LEFT);
        LabelHistory.setHorizontalTextPosition(SwingConstants.RIGHT);
        LabelHistory.setIconTextGap(10);
        LabelHistory.setBounds(0, 230, 190, 40);
        LabelHistory.setBackground(Color.decode("#A1E7FF"));
        LabelHistory.setForeground(Color.decode("#FFF8F7"));
        LabelHistory.setOpaque(true);
        PanelBackground.add(LabelHistory);

        ImageIcon IconUser = new ImageIcon("pictures\\ImageBackground\\LOGO_User.png");
        JButton ButtonInteresting = new JButton("Interesting", IconUser);
        ButtonInteresting.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonInteresting.setIconTextGap(10);
        ButtonInteresting.setHorizontalAlignment(JButton.LEFT);
        ButtonInteresting.setBounds(0, 271, 190, 40);
        ButtonInteresting.setBackground(Color.decode("#F4FAFB"));
        ButtonInteresting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIInteresting());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonInteresting);

        ImageIcon IconPayment = new ImageIcon("pictures\\ImageBackground\\LOGO_Payment.png");
        JButton ButtonCart = new JButton("Cart", IconPayment);
        ButtonCart.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonCart.setIconTextGap(10);
        ButtonCart.setHorizontalAlignment(JButton.LEFT);
        ButtonCart.setBounds(0, 311, 190, 40);
        ButtonCart.setBackground(Color.decode("#F4FAFB"));
        ButtonCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUICart());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonCart);

        ImageIcon IconSetting = new ImageIcon("pictures\\ImageBackground\\LOGO_Setting.png");
        JButton ButtonLogout = new JButton("Log out", IconSetting);
        ButtonLogout.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonLogout.setIconTextGap(10);
        ButtonLogout.setHorizontalAlignment(JButton.LEFT);
        ButtonLogout.setBounds(0, 572, 190, 40);
        ButtonLogout.setBackground(Color.decode("#F4FAFB"));
        ButtonLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement lib = new LibManagement();
            }
        });
        PanelBackground.add(ButtonLogout);

        JButton ButtonClient = new JButton(this.currUser.getID());
        ButtonClient.setFont(new Font("Arial", Font.BOLD, 15));
        ButtonClient.setBackground(Color.decode("#FFFFFF"));
        ButtonClient.setBounds(1090, 20, 90, 30);
        ButtonClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIShowInfos());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonClient);

        //Get list of history
        JTable table = new JTable();
        table.setFont(new Font("Arial", Font.BOLD, 15));
        table.setBackground(Color.decode("#FFEDF6"));
        table.setForeground(Color.decode("#2368CF"));
        table.setRowHeight(35);
        DefaultTableModel modelTable = new DefaultTableModel();


        Object[] columnsName = {"Transaction ID", "Title", "Price", "Borrowed Date", "Return Date", "Status"};
        modelTable.setColumnIdentifiers(columnsName);

        Object[] rowDate = new Object[6];
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String strQuerry = "SELECT * " +
                                "FROM TRANSACTION t, DETAILTRANS d, BOOK b, BORROWING br " +
                                "WHERE t.CID = '" + currUser.getID() + "' AND " +
                                "t.TID = d.TID AND (t.Status = 'Borrowing' OR t.Status = 'Returned') AND " +
                                "d.BID = b.BID AND b.BID = br.BID;" ;

            connection = LibManagement.connect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(strQuerry);

            while (rs.next()) {
                rowDate[0] = rs.getString("TID");
                rowDate[1] = rs.getString("Tittle");
                rowDate[2] = rs.getInt("Price") + " VND";
                rowDate[3] = rs.getString("BorrowedDate");
                rowDate[4] = rs.getString("ReturnDate");
                rowDate[5] = rs.getString("Status");

                modelTable.addRow(rowDate);
            }

            table.setModel(modelTable);
            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
            table.getTableHeader().setBackground(Color.decode("#ADE6D0"));
            table.getTableHeader().setForeground(Color.decode("#FFFFFF"));
            table.setDefaultEditor(Object.class, null);
            
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
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
        }
        
        JScrollPane scrollPaneBooks = new JScrollPane(table);
        scrollPaneBooks.setBounds(200, 180, 1060, 450);
        //Speed up the scroll speed
        scrollPaneBooks.getVerticalScrollBar().setUnitIncrement(20);
        PanelBackground.add(scrollPaneBooks);

        frame.setContentPane(PanelBackground);
        frame.pack();
        frame.setLocationRelativeTo(null); //set center the frame on the screen

        return frame;
    }

    public JFrame getUIInteresting() {
        JFrame frame = new JFrame("Interesting Books");
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage("pictures\\ImageBackground\\a6.png");
        JPanel PanelBackground = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        PanelBackground.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));

        JLabel LabelAboutInteresting = new JLabel("Interesting Books");
        LabelAboutInteresting.setBounds(480, 82, 450, 70);
        LabelAboutInteresting.setBackground(Color.decode("#FFFFFF"));
        LabelAboutInteresting.setFont(new Font("Arial", Font.BOLD, 30));
        LabelAboutInteresting.setHorizontalAlignment(JLabel.CENTER);
        LabelAboutInteresting.setOpaque(true);
        PanelBackground.add(LabelAboutInteresting);

        JLabel labelLibMangement = new JLabel("Lib Management");
        labelLibMangement.setBounds(170, 45, 260, 26);
        labelLibMangement.setFont(new Font("Arial", Font.BOLD, 25));
        PanelBackground.add(labelLibMangement);

        JLabel ImageLogo = new JLabel();
        ImageLogo.setIcon(new ImageIcon("pictures\\ImageBackground\\LOGO 1.png"));
        Dimension size = ImageLogo.getPreferredSize();
        ImageLogo.setBounds(0, 5, size.width, size.height);
        PanelBackground.add(ImageLogo);

        ImageIcon IconDashboard = new ImageIcon("pictures\\ImageBackground\\LOGO_Dashboard.png");
        JButton ButtonDashboard = new JButton("Dashboard", IconDashboard);
        ButtonDashboard.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonDashboard.setIconTextGap(10);
        ButtonDashboard.setHorizontalAlignment(JButton.LEFT);
        ButtonDashboard.setBounds(0, 190, 190, 40);
        ButtonDashboard.setBackground(Color.decode("#F4FAFB"));
        ButtonDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIDashboard());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonDashboard);

        ImageIcon IconBook = new ImageIcon("pictures\\ImageBackground\\LOGO_Book.png");
        JButton ButtonHistory = new JButton("History", IconBook);
        ButtonHistory.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonHistory.setIconTextGap(10);
        ButtonHistory.setHorizontalAlignment(JButton.LEFT);
        ButtonHistory.setBounds(0, 230, 190, 40);
        ButtonHistory.setBackground(Color.decode("#F4FAFB"));
        ButtonHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIHistory());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonHistory);

        ImageIcon IconUser = new ImageIcon("pictures\\ImageBackground\\LOGO_User.png");
        JLabel LabelInteresting = new JLabel("Interesting", IconUser, JLabel.LEFT);
        LabelInteresting.setHorizontalTextPosition(SwingConstants.RIGHT);
        LabelInteresting.setIconTextGap(10);
        LabelInteresting.setBounds(0, 271, 190, 40);
        LabelInteresting.setBackground(Color.decode("#A1E7FF"));
        LabelInteresting.setForeground(Color.decode("#FFF8F7"));
        LabelInteresting.setOpaque(true);
        PanelBackground.add(LabelInteresting);

        ImageIcon IconPayment = new ImageIcon("pictures\\ImageBackground\\LOGO_Payment.png");
        JButton ButtonCart = new JButton("Cart", IconPayment);
        ButtonCart.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonCart.setIconTextGap(10);
        ButtonCart.setHorizontalAlignment(JButton.LEFT);
        ButtonCart.setBounds(0, 311, 190, 40);
        ButtonCart.setBackground(Color.decode("#F4FAFB"));
        ButtonCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUICart());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonCart);

        ImageIcon IconSetting = new ImageIcon("pictures\\ImageBackground\\LOGO_Setting.png");
        JButton ButtonLogout = new JButton("Log out", IconSetting);
        ButtonLogout.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonLogout.setIconTextGap(10);
        ButtonLogout.setHorizontalAlignment(JButton.LEFT);
        ButtonLogout.setBounds(0, 572, 190, 40);
        ButtonLogout.setBackground(Color.decode("#F4FAFB"));
        ButtonLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement lib = new LibManagement();
            }
        });
        PanelBackground.add(ButtonLogout);

        JButton ButtonClient = new JButton(this.currUser.getID());
        ButtonClient.setFont(new Font("Arial", Font.BOLD, 15));
        ButtonClient.setBackground(Color.decode("#FFFFFF"));
        ButtonClient.setBounds(1090, 20, 90, 30);
        ButtonClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIShowInfos());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonClient);

        JPanel PanelListInterestingBooks = getListInterestingBooks();
        JScrollPane scrollPaneBooks = new JScrollPane(PanelListInterestingBooks);
        scrollPaneBooks.setBounds(200, 180, 1060, 450);
        //Speed up the scroll speed
        scrollPaneBooks.getVerticalScrollBar().setUnitIncrement(20);
        PanelBackground.add(scrollPaneBooks);


        frame.setContentPane(PanelBackground);
        frame.pack();
        frame.setLocationRelativeTo(null); //set center the frame on the screen

        return frame;
    }

    public JFrame getUICart() {
        JFrame frame = new JFrame("Cart");
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage("pictures\\ImageBackground\\a6.png");
        JPanel PanelBackground = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        PanelBackground.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));

        JLabel LabelAboutCart = new JLabel("Cart");
        LabelAboutCart.setBounds(480, 82, 450, 70);
        LabelAboutCart.setBackground(Color.decode("#FFFFFF"));
        LabelAboutCart.setFont(new Font("Arial", Font.BOLD, 30));
        LabelAboutCart.setHorizontalAlignment(JLabel.CENTER);
        LabelAboutCart.setOpaque(true);
        PanelBackground.add(LabelAboutCart);

        JLabel labelLibMangement = new JLabel("Lib Management");
        labelLibMangement.setBounds(170, 45, 260, 26);
        labelLibMangement.setFont(new Font("Arial", Font.BOLD, 25));
        PanelBackground.add(labelLibMangement);

        JLabel ImageLogo = new JLabel();
        ImageLogo.setIcon(new ImageIcon("pictures\\ImageBackground\\LOGO 1.png"));
        Dimension size = ImageLogo.getPreferredSize();
        ImageLogo.setBounds(0, 5, size.width, size.height);
        PanelBackground.add(ImageLogo);

        ImageIcon IconDashboard = new ImageIcon("pictures\\ImageBackground\\LOGO_Dashboard.png");
        JButton ButtonDashboard = new JButton("Dashboard", IconDashboard);
        ButtonDashboard.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonDashboard.setIconTextGap(10);
        ButtonDashboard.setHorizontalAlignment(JButton.LEFT);
        ButtonDashboard.setBounds(0, 190, 190, 40);
        ButtonDashboard.setBackground(Color.decode("#F4FAFB"));
        ButtonDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIDashboard());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonDashboard);

        ImageIcon IconBook = new ImageIcon("pictures\\ImageBackground\\LOGO_Book.png");
        JButton ButtonHistory = new JButton("History", IconBook);
        ButtonHistory.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonHistory.setIconTextGap(10);
        ButtonHistory.setHorizontalAlignment(JButton.LEFT);
        ButtonHistory.setBounds(0, 230, 190, 40);
        ButtonHistory.setBackground(Color.decode("#F4FAFB"));
        ButtonHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIHistory());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonHistory);

        ImageIcon IconUser = new ImageIcon("pictures\\ImageBackground\\LOGO_User.png");
        JButton ButtonInteresting = new JButton("Interesting", IconUser);
        ButtonInteresting.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonInteresting.setIconTextGap(10);
        ButtonInteresting.setHorizontalAlignment(JButton.LEFT);
        ButtonInteresting.setBounds(0, 271, 190, 40);
        ButtonInteresting.setBackground(Color.decode("#F4FAFB"));
        ButtonInteresting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIInteresting());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonInteresting);

        ImageIcon IconPayment = new ImageIcon("pictures\\ImageBackground\\LOGO_Payment.png");
        JLabel LabelCart = new JLabel("Cart", IconPayment, JLabel.LEFT);
        LabelCart.setHorizontalTextPosition(SwingConstants.RIGHT);
        LabelCart.setIconTextGap(10);
        LabelCart.setBounds(0, 311, 190, 40);
        LabelCart.setBackground(Color.decode("#A1E7FF"));
        LabelCart.setForeground(Color.decode("#FFF8F7"));
        LabelCart.setOpaque(true);
        PanelBackground.add(LabelCart);

        ImageIcon IconSetting = new ImageIcon("pictures\\ImageBackground\\LOGO_Setting.png");
        JButton ButtonLogout = new JButton("Log out", IconSetting);
        ButtonLogout.setHorizontalTextPosition(SwingConstants.RIGHT);
        ButtonLogout.setIconTextGap(10);
        ButtonLogout.setHorizontalAlignment(JButton.LEFT);
        ButtonLogout.setBounds(0, 572, 190, 40);
        ButtonLogout.setBackground(Color.decode("#F4FAFB"));
        ButtonLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement lib = new LibManagement();
            }
        });
        PanelBackground.add(ButtonLogout);

        JButton ButtonClient = new JButton(this.currUser.getID());
        ButtonClient.setFont(new Font("Arial", Font.BOLD, 15));
        ButtonClient.setBackground(Color.decode("#FFFFFF"));
        ButtonClient.setBounds(1090, 20, 90, 30);
        ButtonClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibManagement.getCurrFrame().dispose();
                LibManagement.setFrame(getUIShowInfos());
                LibManagement.getCurrFrame().setVisible(true);
            }
        });
        PanelBackground.add(ButtonClient);

        JLabel LabelAllCart = new JLabel("All Cart");
        LabelAllCart.setFont(new Font("Arial", Font.BOLD, 25));
        LabelAllCart.setOpaque(true);
        LabelAllCart.setForeground(Color.decode("#22AAEB"));
        LabelAllCart.setBackground(Color.decode("#FFFFFF"));
        LabelAllCart.setBounds(630, 105, 205, 26);
        PanelBackground.add(LabelAllCart);

        //Show all total values
        JLabel LabelOrderSummary = new JLabel("Order Summary");
        LabelOrderSummary.setBounds(820, 400, 250, 30);
        PanelBackground.add(LabelOrderSummary);

        JLabel LabelTotalPrice = new JLabel("Total Price");
        LabelTotalPrice.setBounds(827, 432, 100, 30);
        PanelBackground.add(LabelTotalPrice);

        JLabel LabelGetTotalPrice = new JLabel("");
        LabelGetTotalPrice.setBounds(930, 432, 100, 30);
        PanelBackground.add(LabelGetTotalPrice);

        JLabel LabelBorrowedDate = new JLabel("Borrowed Date");
        LabelBorrowedDate.setBounds(827, 464, 100, 30);
        PanelBackground.add(LabelBorrowedDate);

        JLabel LabelGetBorrowedDate = new JLabel("");
        LabelGetBorrowedDate.setBounds(930, 464, 100, 30);
        PanelBackground.add(LabelGetBorrowedDate);

        JLabel LabelReturnDate = new JLabel("Return Date");
        LabelReturnDate.setBounds(827, 496, 100, 30);
        PanelBackground.add(LabelReturnDate);

        JLabel LabelGetReturnDate = new JLabel("");
        LabelGetReturnDate.setBounds(930, 496, 100, 30);
        PanelBackground.add(LabelGetReturnDate);

        //Show list of waiting books
        JTable table = new JTable();
        table.setFont(new Font("Arial", Font.BOLD, 15));
        table.setBackground(Color.decode("#FFEDF6"));
        table.setForeground(Color.decode("#2368CF"));
        table.setRowHeight(35);
        DefaultTableModel modelTable = new DefaultTableModel();

        Object[] columnsName = {"Transaction ID", "Title", "Price", "Routine", "Status"};
        modelTable.setColumnIdentifiers(columnsName);

        int iTotalPrice = 0;
        int iTotalRoutine = 0;

        if (true) {
            Object[] rowDate = new Object[5];
            Connection connection = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                connection = LibManagement.connect();
                stmt = connection.createStatement();

                String strQuerry = "SELECT * " +
                            "FROM TRANSACTION t, DETAILTRANS d, BOOK b, BORROWING br " +
                            "WHERE t.CID = '" + currUser.getID() + "' AND " +
                            "t.TID = d.TID AND (t.Status = 'Waiting') AND " +
                            "d.BID = b.BID AND b.BID = br.BID;" ;
                rs = stmt.executeQuery(strQuerry);

                //If has at least one transaction
                if (rs.next() == true){
                    do {
                        iTotalPrice = rs.getInt("TotalPrice");
                        iTotalRoutine = rs.getInt("TotalRoutine");
    
                        rowDate[0] = rs.getString("TID");
                        rowDate[1] = rs.getString("Tittle");
                        rowDate[2] = rs.getInt("Price") + " VND";
                        rowDate[3] = rs.getString("Routine") + " days";
                        rowDate[4] = rs.getString("Status");
    
                        modelTable.addRow(rowDate);
                    } while (rs.next());
    
                    //Set total price:
                    LabelGetTotalPrice.setText(iTotalPrice + " VND");
                    //Set current date
                    java.time.LocalDate CurrentDate = LocalDate.now();
                    java.sql.Date DateCurrentDate = java.sql.Date.valueOf(CurrentDate);
                    LabelGetBorrowedDate.setText("" + DateCurrentDate);
                    //Set return date
                    java.time.LocalDate ReturnDate = CurrentDate.plusDays(iTotalRoutine);
                    java.sql.Date DateReturnDate = java.sql.Date.valueOf(ReturnDate);
                    LabelGetReturnDate.setText("" + DateReturnDate);
    
                    //Generate the table
                    table.setModel(modelTable);
                    table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
                    table.getTableHeader().setBackground(Color.decode("#ADE6D0"));
                    table.getTableHeader().setForeground(Color.decode("#FFFFFF"));
                    //set uneditable for table
                    table.setDefaultEditor(Object.class, null);
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            } catch (Exception ex){
                ex.printStackTrace();
            } finally {
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
            }
        }
        
        JScrollPane scrollPaneBooks = new JScrollPane(table);
        scrollPaneBooks.setBounds(200, 180, 1060, 200);
        //Speed up the scroll speed
        scrollPaneBooks.getVerticalScrollBar().setUnitIncrement(20);
        PanelBackground.add(scrollPaneBooks);

        JButton ButtonCancel = new JButton("Cancel All Books");
        ButtonCancel.setBounds(200, 400, 200, 40);
        ButtonCancel.setBackground(Color.decode("#95D5E3"));
        ButtonCancel.setForeground(Color.decode("#FFF8F7"));
        ButtonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                Statement stmt = null;

                try {
                    connection = LibManagement.connect();
                    connection.setAutoCommit(false);
                    stmt = connection.createStatement();

                    String strQuerry = "UPDATE TRANSACTION SET Status = 'Cancel' " +
                                        "WHERE CID = '" + currUser.getID() + "' AND Status = 'Waiting'"; 
                    stmt.executeUpdate(strQuerry);
                    
                    connection.commit();
                } catch (SQLException ex){
                    try {
                        connection.rollback();
                    } catch (SQLException ex1){
                        ex1.printStackTrace();
                    }

                    ex.printStackTrace();
                } catch (Exception ex){
                    try {
                        connection.rollback();
                    } catch (SQLException ex1){
                        ex1.printStackTrace();
                    }

                    ex.printStackTrace();
                } finally {
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
                }

                //Clear all rows of waiting books
                DefaultTableModel dtm = (DefaultTableModel)table.getModel();
                dtm.setRowCount(0);

                LabelGetTotalPrice.setText("");
                LabelGetBorrowedDate.setText("");
                LabelGetReturnDate.setText("");
            }
        });
        PanelBackground.add(ButtonCancel);

        JButton ButtonContinue = new JButton("Continue to check out");
        ButtonContinue.setBounds(820, 572, 200, 40);
        ButtonContinue.setBackground(Color.decode("#95D5E3"));
        ButtonContinue.setForeground(Color.decode("#FFF8F7"));
        PanelBackground.add(ButtonContinue);

        frame.setContentPane(PanelBackground);
        frame.pack();
        frame.setLocationRelativeTo(null);

        return frame;
    }
    
    public JPanel getListInterestingBooks() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        JPanel PanelBackground = null;

        try {
            conn = LibManagement.connect();
            stmt = conn.createStatement();

            String strQuerry = "SELECT COUNT(*) AS C FROM INTERESTING WHERE CID = '" + currUser.getID() + "';";
            rs = stmt.executeQuery(strQuerry);
            rs.next();

            int iNumOfColumns = 5;
            int iCount = rs.getInt("C");
            int iNumOfRows = (iCount % iNumOfColumns == 0) ? (iCount / iNumOfColumns) : (iCount / iNumOfColumns) + 1;

            PanelBackground = new JPanel();
            PanelBackground.setBounds(20, 90, 500, 1000);
            // ? rows, 5 columns, 5px horizontal gap and 10 px vertical gap
            PanelBackground.setLayout(new GridLayout(iNumOfRows, iNumOfColumns, 5, 10));

            strQuerry = "SELECT * " + 
                        "FROM INTERESTING it, BOOK bk, BORROWING br " +
                        "WHERE it.CID = '" + currUser.getID() + "' AND it.BID = bk.BID AND bk.BID = br.BID " +
                        "ORDER BY bk.Tittle";
            rs = stmt.executeQuery(strQuerry);

            ArrayList<Book> listBooks = new ArrayList<>();
            while (rs.next()) {
                String strID = rs.getString("BID");
                String strTitle = rs.getString("Tittle");
                String strAuthor = rs.getString("Author");
                String strPH = rs.getString("PublishingHouse");
                int iPostedYear = rs.getInt("PostedYear");
                String strGenre = rs.getString("Genre");
                int iHasLeft = rs.getInt("HasLeft");
                String strPlace = rs.getString("Place");
                int iPrice = rs.getInt("Price");
                int iRoutine = rs.getInt("Routine");
                String strPathBook = "pictures\\" + rs.getString("PathImage");

                listBooks.add(new Book(strID, strTitle, strAuthor, strPH, iPostedYear, strGenre, iHasLeft, strPlace, iPrice, iRoutine, strPathBook));
            }

            //Show the list of books
            for (Book book : listBooks) {
                JPanel PanelBook = new JPanel(new BorderLayout());
                // PanelBook.setSize(50, 1000);
                PanelBook.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                PanelBook.setBackground(Color.CYAN);

                JPanel PanelInfos = new JPanel();
                PanelInfos.setLayout(new BoxLayout(PanelInfos, BoxLayout.Y_AXIS));
                
                JLabel LabelID = new JLabel(book.getID(), SwingConstants.CENTER);
                LabelID.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                PanelInfos.add(LabelID);

                JLabel LabelPublishingHouse = new JLabel(book.getTitle(), SwingConstants.CENTER);
                LabelPublishingHouse.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                PanelInfos.add(LabelPublishingHouse);

                JLabel LabelAuthor = new JLabel(book.getAuthor(), SwingConstants.CENTER);
                LabelAuthor.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                PanelInfos.add(LabelAuthor);

                ImageIcon ImageIconBook = new ImageIcon(book.getPathImage());
                Image ImageScaleBook = ImageIconBook.getImage();    
                ImageIconBook = new ImageIcon(ImageScaleBook.getScaledInstance(170, 220, Image.SCALE_SMOOTH));
                JLabel LabelImageBook = new JLabel(ImageIconBook, SwingConstants.CENTER);
                
                PanelBook.add(PanelInfos, BorderLayout.CENTER);
                PanelBook.add(LabelImageBook, BorderLayout.NORTH);

                //Add event to PanelBook
                PanelBook.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e){
                        LibManagement.getCurrFrame().dispose();
                        LibManagement.setFrame(getUIBookDetail(book));
                        LibManagement.getCurrFrame().setVisible(true);
                    }
                });

                PanelBackground.add(PanelBook);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
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
                conn.close();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return PanelBackground;
    }

    public JPanel getListBooks(String strSearch){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        JPanel PanelBackground = null;

        try {
            conn = LibManagement.connect();
            stmt = conn.createStatement();

            String strQuerry = "SELECT COUNT(*) AS C FROM BOOK " + 
                                "WHERE Tittle LIKE '%" + strSearch + "%' OR " +
                                "Author LIKE '%" + strSearch + "%' OR " + 
                                "PublishingHouse LIKE '%" + strSearch + "%' OR " +
                                "Genre LIKE '%" + strSearch + "%';";
            rs = stmt.executeQuery(strQuerry);
            rs.next();

            int iNumOfColumns = 5;
            int iCount = rs.getInt("C");
            int iNumOfRows = (iCount % iNumOfColumns == 0) ? (iCount / iNumOfColumns) : (iCount / iNumOfColumns) + 1;

            PanelBackground = new JPanel();
            PanelBackground.setBounds(20, 90, 500, 1000);
            // ? rows, 5 columns, 5px horizontal gap and 10 px vertical gap
            PanelBackground.setLayout(new GridLayout(iNumOfRows, iNumOfColumns, 5, 10));

            strQuerry = "SELECT * " + 
                        "FROM BOOK bk JOIN BORROWING br ON bk.BID = br.BID " +
                        "WHERE bk.Tittle LIKE '%" + strSearch + "%' OR " +
                        "bk.Author LIKE '%" + strSearch + "%' OR " + 
                        "bk.PublishingHouse LIKE '%" + strSearch + "%' OR " +
                        "bk.Genre LIKE '%" + strSearch + "%' " +
                        "ORDER BY bk.Tittle;";
            rs = stmt.executeQuery(strQuerry);

            ArrayList<Book> listBooks = new ArrayList<>();
            while (rs.next()) {
                String strID = rs.getString("BID");
                String strTitle = rs.getString("Tittle");
                String strAuthor = rs.getString("Author");
                String strPH = rs.getString("PublishingHouse");
                int iPostedYear = rs.getInt("PostedYear");
                String strGenre = rs.getString("Genre");
                int iHasLeft = rs.getInt("HasLeft");
                String strPlace = rs.getString("Place");
                int iPrice = rs.getInt("Price");
                int iRoutine = rs.getInt("Routine");
                String strPathBook = "pictures\\" + rs.getString("PathImage");

                listBooks.add(new Book(strID, strTitle, strAuthor, strPH, iPostedYear, strGenre, iHasLeft, strPlace, iPrice, iRoutine, strPathBook));
            }

            //Show the list of books
            for (Book book : listBooks) {
                JPanel PanelBook = new JPanel(new BorderLayout());
                // PanelBook.setSize(50, 1000);
                PanelBook.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                PanelBook.setBackground(Color.CYAN);

                JPanel PanelInfos = new JPanel();
                PanelInfos.setLayout(new BoxLayout(PanelInfos, BoxLayout.Y_AXIS));
                
                JLabel LabelID = new JLabel(book.getID(), SwingConstants.CENTER);
                LabelID.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                PanelInfos.add(LabelID);

                JLabel LabelPublishingHouse = new JLabel(book.getTitle(), SwingConstants.CENTER);
                LabelPublishingHouse.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                PanelInfos.add(LabelPublishingHouse);

                JLabel LabelAuthor = new JLabel(book.getAuthor(), SwingConstants.CENTER);
                LabelAuthor.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                PanelInfos.add(LabelAuthor);

                ImageIcon ImageIconBook = new ImageIcon(book.getPathImage());
                Image ImageScaleBook = ImageIconBook.getImage();    
                ImageIconBook = new ImageIcon(ImageScaleBook.getScaledInstance(170, 220, Image.SCALE_SMOOTH));
                JLabel LabelImageBook = new JLabel(ImageIconBook, SwingConstants.CENTER);
                
                PanelBook.add(PanelInfos, BorderLayout.CENTER);
                PanelBook.add(LabelImageBook, BorderLayout.NORTH);

                //Add event to PanelBook
                PanelBook.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e){
                        LibManagement.getCurrFrame().dispose();
                        LibManagement.setFrame(getUIBookDetail(book));
                        LibManagement.getCurrFrame().setVisible(true);
                    }
                });

                PanelBackground.add(PanelBook);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
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
                conn.close();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return PanelBackground;
    }
}

//Class client----------------------------------------------------------------------------------------------------------
public class Client extends User{
    private String address;
    private String bankAccount;
    private String bankName;
    private ArrayList<Book> listOfAttentedBooks;

    public Client(String id, String fullname, String phoneNumber, String emailAddress, String gender,
        java.util.Date createdDate, String address, String bankAccount, String bankName){   
        super(id, fullname, phoneNumber, emailAddress, gender, createdDate);
        this.address = address;
        this.bankAccount = bankAccount;
        this.bankName = bankName;
    }

    //Getter 
    public String getAddress() { return this.address; }

    public String getBankAccount() { return this.bankAccount; }

    public String getBankName() { return this.bankName; }
    
    @Override
    public JFrame getDashBoard(){
        ClientUI cu = new ClientUI(this);
        return cu.getUIDashboard();
    }
}