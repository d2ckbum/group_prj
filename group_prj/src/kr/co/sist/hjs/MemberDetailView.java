package kr.co.sist.hjs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import kr.co.sist.kji.MemberDataDTO; // MemberDataDTO 사용
import kr.co.sist.kji.MemberVO;

public class MemberDetailView extends JDialog {
    private MemberPane mp;
    private MemberVO mVO;
    private MemberDataDTO mDTO; // MemberDataDTO 추가
    private JLabel jlblNum, jlblId, jlblPass, jlblName, jlblEmail, jlblTell, jlblZipcode, jlblAddr1, jlblAddr2,
            jlblRegDate, jlblFlag, jlblCarType, jlblMfgName; // 라벨 이름 변경
    private JPasswordField jtfPass;
    private JTextField jtfNum, jtfId, jtfName, jtfEmail, jtfTell, jtfZipcode, jtfAddr1, jtfAddr2, jtfRegDate, jtfFlag,
            jtfCarType, jtfMfgName; // 텍스트 필드 이름 변경
    private JButton jbtnOk;

    // private 폰트 선언
    private Font labelFont = new Font("맑은 고딕", Font.BOLD, 15);
    private Font textFont = new Font("맑은 고딕", Font.BOLD, 15);

    public MemberDetailView(MemberPane mp, MemberDataDTO mDTO) { // 생성자 파라미터 변경
        // super("회원 상세 정보", true); // true: 모달 다이얼로그
        this.mp = mp;
        this.mVO = mDTO.getmVO(); // MemberVO는 DTO에서 가져옴
        this.mDTO = mDTO; // MemberDataDTO 저장

        // UI 컴포넌트 초기화
        jlblNum = new JLabel("회원번호 :");
        jlblId = new JLabel("아이디 :");
        jlblPass = new JLabel("비밀번호 :");
        jlblName = new JLabel("이름 :");
        jlblEmail = new JLabel("이메일 :");
        jlblTell = new JLabel("전화번호 :");
        jlblZipcode = new JLabel("우편번호 :");
        jlblAddr1 = new JLabel("주소 :");
        jlblAddr2 = new JLabel("상세주소 :");
        jlblRegDate = new JLabel("생성일 :");
        jlblFlag = new JLabel("탈퇴 Y/N :");
        jlblCarType = new JLabel("차량 종류 :"); // 라벨 이름 변경
        jlblMfgName = new JLabel("제조사 :"); // 라벨 이름 변경

        jtfNum = new JTextField(15);
        jtfId = new JTextField(15);
        jtfPass = new JPasswordField(15);
        jtfName = new JTextField(15);
        jtfEmail = new JTextField(15);
        jtfTell = new JTextField(15);
        jtfZipcode = new JTextField(15);
        jtfAddr1 = new JTextField(15);
        jtfAddr2 = new JTextField(15);
        jtfRegDate = new JTextField(15);
        jtfFlag = new JTextField(15);
        jtfCarType = new JTextField(15); // 텍스트 필드 이름 변경
        jtfMfgName = new JTextField(15); // 텍스트 필드 이름 변경

        jbtnOk = new JButton("확인");
        jbtnOk.setFont(labelFont);
        jbtnOk.setBackground(new Color(217, 217, 217));
        jbtnOk.setPreferredSize(new Dimension(120, 60));

        // 텍스트 필드에 회원 정보 설정
        jtfNum.setText(String.valueOf(mVO.getMemNum()));
        jtfId.setText(mVO.getMemId());
        jtfPass.setText(mVO.getMemPass());
        jtfName.setText(mVO.getMemName());
        jtfEmail.setText(mVO.getMemEmail());
        jtfTell.setText(mVO.getMemTell());
        jtfZipcode.setText(mVO.getMemZipcode());
        jtfAddr1.setText(mVO.getMemAddr1());
        jtfAddr2.setText(mVO.getMemAddr2());
        // Date 타입은 SimpleDateFormat을 사용하여 문자열로 변환
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jtfRegDate.setText(mVO.getMemRegDate() != null ? sdf.format(mVO.getMemRegDate()) : "");
        jtfFlag.setText(mVO.getMemFlag());
        jtfCarType.setText(mDTO.getCarType()); // DTO에서 차량 종류 가져오기
        jtfMfgName.setText(mDTO.getMfgName()); // DTO에서 제조사 가져오기

        // 텍스트 필드 편집 불가능하도록 설정 (상세 정보 확인용)
        jtfNum.setEditable(false);
        jtfId.setEditable(false);
        jtfPass.setEditable(false);
        jtfName.setEditable(false);
        jtfEmail.setEditable(false);
        jtfTell.setEditable(false);
        jtfZipcode.setEditable(false);
        jtfAddr1.setEditable(false);
        jtfAddr2.setEditable(false);
        jtfRegDate.setEditable(false);
        jtfFlag.setEditable(false);
        jtfCarType.setEditable(false);
        jtfMfgName.setEditable(false);

        // 레이아웃 설정
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 간격 설정
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 라벨 및 텍스트 필드 가운데 정렬 및 추가
        gbc.gridx = 0;
        gbc.gridy = 0;
        jlblNum.setHorizontalAlignment(SwingConstants.CENTER);
        jlblNum.setFont(labelFont);
        add(jlblNum, gbc);
        gbc.gridx = 1;
        jtfNum.setHorizontalAlignment(JTextField.CENTER);
        jtfNum.setFont(textFont);
        add(jtfNum, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jlblId.setHorizontalAlignment(SwingConstants.CENTER);
        jlblId.setFont(labelFont);
        add(jlblId, gbc);
        gbc.gridx = 1;
        jtfId.setHorizontalAlignment(JTextField.CENTER);
        jtfId.setFont(textFont);
        add(jtfId, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jlblPass.setHorizontalAlignment(SwingConstants.CENTER);
        jlblPass.setFont(labelFont);
        add(jlblPass, gbc);
        gbc.gridx = 1;
        jtfPass.setHorizontalAlignment(JTextField.CENTER);
        jtfPass.setFont(textFont);
        add(jtfPass, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jlblName.setHorizontalAlignment(SwingConstants.CENTER);
        jlblName.setFont(labelFont);
        add(jlblName, gbc);
        gbc.gridx = 1;
        jtfName.setHorizontalAlignment(JTextField.CENTER);
        jtfName.setFont(textFont);
        add(jtfName, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jlblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        jlblEmail.setFont(labelFont);
        add(jlblEmail, gbc);
        gbc.gridx = 1;
        jtfEmail.setHorizontalAlignment(JTextField.CENTER);
        jtfEmail.setFont(textFont);
        add(jtfEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jlblTell.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTell.setFont(labelFont);
        add(jlblTell, gbc);
        gbc.gridx = 1;
        jtfTell.setHorizontalAlignment(JTextField.CENTER);
        jtfTell.setFont(textFont);
        add(jtfTell, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jlblZipcode.setHorizontalAlignment(SwingConstants.CENTER);
        jlblZipcode.setFont(labelFont);
        add(jlblZipcode, gbc);
        gbc.gridx = 1;
        jtfZipcode.setHorizontalAlignment(JTextField.CENTER);
        jtfZipcode.setFont(textFont);
        add(jtfZipcode, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jlblAddr1.setHorizontalAlignment(SwingConstants.CENTER);
        jlblAddr1.setFont(labelFont);
        add(jlblAddr1, gbc);
        gbc.gridx = 1;
        jtfAddr1.setHorizontalAlignment(JTextField.CENTER);
        jtfAddr1.setFont(textFont);
        add(jtfAddr1, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jlblAddr2.setHorizontalAlignment(SwingConstants.CENTER);
        jlblAddr2.setFont(labelFont);
        add(jlblAddr2, gbc);
        gbc.gridx = 1;
        jtfAddr2.setHorizontalAlignment(JTextField.CENTER);
        jtfAddr2.setFont(textFont);
        add(jtfAddr2, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jlblRegDate.setHorizontalAlignment(SwingConstants.CENTER);
        jlblRegDate.setFont(labelFont);
        add(jlblRegDate, gbc);
        gbc.gridx = 1;
        jtfRegDate.setHorizontalAlignment(JTextField.CENTER);
        jtfRegDate.setFont(textFont);
        add(jtfRegDate, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jlblFlag.setHorizontalAlignment(SwingConstants.CENTER);
        jlblFlag.setFont(labelFont);
        add(jlblFlag, gbc);
        gbc.gridx = 1;
        jtfFlag.setHorizontalAlignment(JTextField.CENTER);
        jtfFlag.setFont(textFont);
        add(jtfFlag, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jlblCarType.setHorizontalAlignment(SwingConstants.CENTER); // 라벨 이름 변경
        jlblCarType.setFont(labelFont);
        add(jlblCarType, gbc);
        gbc.gridx = 1;
        jtfCarType.setHorizontalAlignment(JTextField.CENTER); // 텍스트 필드 이름 변경
        jtfCarType.setFont(textFont);
        add(jtfCarType, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        jlblMfgName.setHorizontalAlignment(SwingConstants.CENTER); // 라벨 이름 변경
        jlblMfgName.setFont(labelFont);
        add(jlblMfgName, gbc);
        gbc.gridx = 1;
        jtfMfgName.setHorizontalAlignment(JTextField.CENTER); // 텍스트 필드 이름 변경
        jtfMfgName.setFont(textFont);
        add(jtfMfgName, gbc);

        // 확인 버튼 하단 가운데 정렬
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(jbtnOk);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // 두 열을 차지하도록 설정
        gbc.fill = GridBagConstraints.NONE; // 채우지 않음
        gbc.anchor = GridBagConstraints.CENTER; // 가운데 정렬
        add(buttonPanel, gbc);
        gbc.gridwidth = 1; // 다시 기본값으로 설정

        // 확인 버튼 클릭 이벤트
        jbtnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 다이얼로그만 닫음
            }
        });

        // 창 설정
        setSize(600, 800); // 창 크기 조정
        setLocationRelativeTo(mp); // 부모 창 가운데에 표시
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // 다이얼로그만 닫음
        // 창 크기 고정
        setResizable(false);
        setVisible(true);
    }
}// class