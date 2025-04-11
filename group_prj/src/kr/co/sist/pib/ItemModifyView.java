package kr.co.sist.pib;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

@SuppressWarnings("serial")
public class ItemModifyView extends JDialog {

    private DefaultTableModel tableModel;
    private int selectedRow;
    private JLabel productNum;
    private JTextField nameField;
    private JTextField stockField;
    private JTextField quantityField;
    private JTextField carTypeField;
    private JTextField costField;
    private JTextField priceField;
    private JButton decreaseButton;
    private JButton increaseButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton closeButton;
    private JComboBox<String> carTypeComboBox;
    private JTextField quantityUnitField;
    private JPanel mainPanel;
    

    public ItemModifyView(DefaultTableModel tableModel, int selectedRow, JFrame parent, JPanel mainPanel) {
        super(parent, "상품 수정", true);
        setTitle("상품 수정");
        
        this.mainPanel = mainPanel;
        this.tableModel = tableModel;
        this.selectedRow = selectedRow;
        Font font = new Font("맑은 고딕", Font.BOLD, 20);
        Font buttonFont = new Font("맑은 고딕", Font.BOLD, 10);
        Dimension buttonDimension = new Dimension(40, 30);
        
        
        

        setSize(800, 700);
        setLayout(new BorderLayout());
        setResizable(false);

        // Title Panel with Separator
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("상품 정보 수정", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        titleLabel.setBorder(new EmptyBorder(15, 0, 15, 0));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(new JSeparator(), BorderLayout.SOUTH);
        add(titlePanel, BorderLayout.NORTH);
        
        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(30, 60, 30, 60));
       
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        String productCode = (String) tableModel.getValueAt(selectedRow, 0);
        String productName = (String) tableModel.getValueAt(selectedRow, 1);
        String carType = (String) tableModel.getValueAt(selectedRow, 2);
        String stock = (String) tableModel.getValueAt(selectedRow, 3);
        String cost = (String) tableModel.getValueAt(selectedRow, 4);
        String price = (String) tableModel.getValueAt(selectedRow, 5);

        productNum = new JLabel();
        productNum.setText(productCode);
        productNum.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        addLabelAndComponent("상품번호", productNum, formPanel, gbc, 0);
        nameField = new JTextField(productName, 20);
        nameField.setFont(font);
        nameField.setForeground(Color.GRAY);
        nameField.setBorder(BorderFactory.createCompoundBorder(
        		new LineBorder(Color.gray, 1), // 기존 테두리 유지
        	    BorderFactory.createEmptyBorder(10, 5, 10, 5) // 내부 패딩 추가
        	));
        addLabelAndComponent("상품명", nameField, formPanel, gbc, 1);

        stockField = new JTextField(stock, 10);
        stockField.setFont(font);
        stockField.setEditable(false);
        stockField.setFocusable(false);
        stockField.setHorizontalAlignment(JTextField.CENTER);
        stockField.setBorder(BorderFactory.createCompoundBorder(
        		new LineBorder(Color.gray, 1), // 기존 테두리 유지
        		BorderFactory.createEmptyBorder(10, 5, 10, 5) // 내부 패딩 추가
        		));
     // 재고 수량 조절 버튼과 텍스트 필드를 아래에 배치
        addLabelAndComponent("재고", stockField, formPanel, gbc, 2);
        
        JPanel stockControlPanel = new JPanel();
        stockControlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // 왼쪽 정렬
        
        var border = new LineBorder(Color.BLACK, 1); 
        
        decreaseButton = new JButton("-");
        decreaseButton.setFont(buttonFont);
        decreaseButton.setBackground(new Color(200, 200, 200));
        decreaseButton.setPreferredSize(buttonDimension);
        decreaseButton.setFocusPainted(false);
        decreaseButton.setBorder(border);
        
        increaseButton = new JButton("+");
        increaseButton.setFont(buttonFont);
        increaseButton.setBackground(new Color(200, 200, 200));
        increaseButton.setPreferredSize(buttonDimension);
        increaseButton.setFocusPainted(false);
        increaseButton.setBorder(border);
        
        quantityUnitField = new JTextField("1", 3);
        quantityUnitField.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 15));
        quantityUnitField.setPreferredSize(new Dimension(70, 30));
        quantityUnitField.setBorder(border);
        quantityUnitField.setHorizontalAlignment(JTextField.CENTER);
        
        stockControlPanel.add(decreaseButton);
        stockControlPanel.add(quantityUnitField);
        stockControlPanel.add(increaseButton);

        // GridBagLayout을 사용해 stockControlPanel을 아래에 배치
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;  // 두 칸을 차지하게 설정
        formPanel.add(stockControlPanel, gbc);
        
//        JPanel stockPanel = new JPanel();
//        stockPanel.add(stockField);
//        stockPanel.add(decreaseButton);
//        stockPanel.add(quantityUnitField);
//        stockPanel.add(increaseButton);
//        addLabelAndComponent("재고", stockPanel, formPanel, gbc, 2);
       
        
        carTypeComboBox = new JComboBox<>(new String[]{"소형", "중형", "대형"});
//        carTypeComboBox.setBorder(BorderFactory.createCompoundBorder(
//        		new LineBorder(Color.gray, 1), // 기존 테두리 유지
//        	    BorderFactory.createEmptyBorder(10, 5, 10, 5) // 내부 패딩 추가
//        	));
        carTypeComboBox.setPreferredSize(new Dimension(100, 40));
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);  // 텍스트를 중앙 정렬로 설정
        carTypeComboBox.setRenderer(renderer);
        carTypeComboBox.setSelectedItem(carType);
        addLabelAndComponent("차종", carTypeComboBox, formPanel, gbc, 4);
        
        
        costField = new JTextField(cost, 20);
        
        costField.setForeground(Color.GRAY);
        costField.setFont(font);
        costField.setBorder(BorderFactory.createCompoundBorder(
        	    new LineBorder(Color.gray, 1), // 기존 테두리 유지
        	    BorderFactory.createEmptyBorder(10, 5, 10, 5) // 내부 패딩 추가
        	));
        addLabelAndComponent("원가", costField, formPanel, gbc, 5);
        
        


        priceField = new JTextField(price, 20);
        priceField.setForeground(Color.GRAY);
        priceField.setFont(font);
        priceField.setBorder(BorderFactory.createCompoundBorder(
        	    new LineBorder(Color.gray, 1), // 기존 테두리 유지
        	    BorderFactory.createEmptyBorder(10, 5, 10, 5) // 내부 패딩 추가
        	));
        addLabelAndComponent("판매가", priceField, formPanel, gbc, 6);

        add(formPanel, BorderLayout.CENTER);
        
        //이벤트 등록
        ItemModifyEvent evt = new ItemModifyEvent(this);
        evt.registerNumericDocumentFilter(costField);
        evt.registerNumericDocumentFilter(priceField);
        evt.registerNumericDocumentFilter(quantityUnitField);
        nameField.addFocusListener(evt);
        costField.addFocusListener(evt);
        priceField.addFocusListener(evt);
        
        increaseButton.addActionListener(evt);
        decreaseButton.addActionListener(evt);

        // Bottom Panel with Separator
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(new JSeparator(), BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(15, 0, 15, 0));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));  // 오른쪽 정렬로 설정
        saveButton = new JButton("수정");
        saveButton.setBackground(new Color(217,217,217));
        saveButton.setPreferredSize(new Dimension(70, 35));
        deleteButton = new JButton("삭제");
        deleteButton.setBackground(new Color(217,217,217));
        deleteButton.setPreferredSize(new Dimension(70, 35));
        closeButton = new JButton("닫기");
        closeButton.setBackground(new Color(217,217,217));
        closeButton.setPreferredSize(new Dimension(70, 35));
        saveButton.addActionListener(evt);
        deleteButton.addActionListener(evt);
        closeButton.addActionListener(evt);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(closeButton);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 예를 들어 상품명 텍스트 필드에 포커스를 주지 않도록 하고,
                // 다른 요소나 다이얼로그의 기본 동작을 기다립니다.
            	titleLabel.requestFocusInWindow();  // nameField는 포커스를 받지 않게 설정

                // 만약 나중에 특정 필드로 포커스를 주고 싶다면, 아래처럼 사용할 수 있습니다.
                // costField.requestFocusInWindow(); // 포커스를 주고 싶은 필드에 포커스
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
        
    }

    private void addLabelAndComponent(String labelText, Component component, JPanel panel, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        
        panel.add(label, gbc);
        
        gbc.gridx = 1;
        panel.add(component, gbc);
    }
    
    
    
    
    
    public JPanel getMainPanel() {
		return mainPanel;
	}

	public JButton getCloseButton() {
    	return closeButton;
    }
    
    public JTextField getCostField() {
		return costField;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public int getSelectedRow() {
		return selectedRow;
	}

	public JLabel getProductNum() {
		return productNum;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JTextField getStockField() {
		return stockField;
	}

	public JTextField getQuantityField() {
		return quantityField;
	}

	public JTextField getCarTypeField() {
		return carTypeField;
	}

	public JTextField getPriceField() {
		return priceField;
	}

	public JButton getDecreaseButton() {
		return decreaseButton;
	}

	public JButton getIncreaseButton() {
		return increaseButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public JComboBox<String> getCarTypeComboBox() {
		return carTypeComboBox;
	}

	public JTextField getQuantityUnitField() {
		return quantityUnitField;
	}
    
    
    
}