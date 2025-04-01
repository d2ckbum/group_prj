package kr.co.sist.hjs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminTabView extends JFrame implements ActionListener {
    private CardLayout cl;
    private JPanel mainPanel;
    private MemberPane memp;
    private ItemPane itemp;
    private FixPane fixp;
    private SalesPane salesp;
    private InquiryPane inquiryp;
    private JButton jbtnLogout;
    private JButton jbtnMember, jbtnItemManage, jbtnFixManage, jbtnSales, jbtnInquiryManage;
    private JButton[] buttons;
    
    public AdminTabView() {
        jbtnMember = new JButton("회원현황");
        jbtnItemManage = new JButton("상품관리");
        jbtnFixManage = new JButton("정비관리");
        jbtnSales = new JButton("매출현황");
        jbtnInquiryManage = new JButton("문의관리");
        jbtnLogout = new JButton("로그아웃");
        
        buttons = new JButton[]{jbtnMember, jbtnItemManage, jbtnFixManage, jbtnSales, jbtnInquiryManage};
        
        JPanel jpNorth = new JPanel();
        jpNorth.add(jbtnMember);
        jpNorth.add(jbtnItemManage);
        jpNorth.add(jbtnFixManage);
        jpNorth.add(jbtnSales);
        jpNorth.add(jbtnInquiryManage);
        
        cl = new CardLayout();
        mainPanel = new JPanel(cl);
        
        memp = new MemberPane();
        itemp = new ItemPane();
        fixp = new FixPane();
        salesp = new SalesPane();
        inquiryp = new InquiryPane();
        
        mainPanel.add(memp, "memp");
        mainPanel.add(itemp, "itemp");
        mainPanel.add(fixp, "fixp");
        mainPanel.add(salesp, "salesp");
        mainPanel.add(inquiryp, "inquiryp");
        
        add("North", jpNorth);
        add("Center", mainPanel);
        
        jbtnMember.addActionListener(this);
        jbtnItemManage.addActionListener(this);
        jbtnFixManage.addActionListener(this);
        jbtnSales.addActionListener(this);
        jbtnInquiryManage.addActionListener(this);
        jbtnLogout.addActionListener(this);
        
        setBounds(100, 100, 600, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resetButtonColors();
        
        if (e.getSource() == jbtnMember) {
            cl.show(mainPanel, "memp");
            jbtnMember.setBackground(Color.LIGHT_GRAY);
        } else if (e.getSource() == jbtnItemManage) {
            cl.show(mainPanel, "itemp");
            jbtnItemManage.setBackground(Color.LIGHT_GRAY);
        } else if (e.getSource() == jbtnFixManage) {
            cl.show(mainPanel, "fixp");
            jbtnFixManage.setBackground(Color.LIGHT_GRAY);
        } else if (e.getSource() == jbtnSales) {
            cl.show(mainPanel, "salesp");
            jbtnSales.setBackground(Color.LIGHT_GRAY);
        } else if (e.getSource() == jbtnInquiryManage) {
            cl.show(mainPanel, "inquiryp");
            jbtnInquiryManage.setBackground(Color.LIGHT_GRAY);
        } else if (e.getSource() == jbtnLogout) {
            JOptionPane.showMessageDialog(this, "로그아웃 되었습니다.");
            dispose();
        }
    }
    
    private void resetButtonColors() {
        for (JButton btn : buttons) {
            btn.setBackground(null);
        }
    }

    public static void main(String[] args) {
        new AdminTabView();
    }
}
