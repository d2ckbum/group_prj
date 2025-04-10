package kr.co.sist.khb.view; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date; 

public class SalesCalView extends JDialog {

    private Calendar calendar;
    private JLabel monthLabel;
    private JButton[] dayButtons;
    private JButton prevButton;
    private JButton nextButton;
    private Date selectedDate = null; 

    public SalesCalView(Frame owner) {
        super(owner, "달력", true); // Modal 설정
        this.calendar = Calendar.getInstance();

        monthLabel = new JLabel();
        prevButton = new JButton("◀");
        nextButton = new JButton("▶");
        dayButtons = new JButton[42];

        setLayout(new BorderLayout());
        JPanel monthPanel = new JPanel(new FlowLayout());
        monthPanel.add(prevButton); monthPanel.add(monthLabel); monthPanel.add(nextButton);
        JPanel dayPanel = new JPanel(new GridLayout(7, 7));
        String[] dayNames = {"일", "월", "화", "수", "목", "금", "토"};
        for (String dayName : dayNames) { dayPanel.add(new JLabel(dayName, SwingConstants.CENTER)); }
        for (int i = 0; i < dayButtons.length; i++) { dayButtons[i] = new JButton(); dayPanel.add(dayButtons[i]); }
        add(monthPanel, BorderLayout.NORTH); add(dayPanel, BorderLayout.CENTER);

        // --- 내부 이벤트 리스너 등록 ---
        prevButton.addActionListener(e -> {
            calendar.add(Calendar.MONTH, -1);
            updateCalendar();
        });
        nextButton.addActionListener(e -> {
            calendar.add(Calendar.MONTH, 1);
            updateCalendar();
        });

        ActionListener dayClickListener = e -> {
            JButton clickedButton = (JButton) e.getSource();
            String dayStr = clickedButton.getText();
            if (!dayStr.isEmpty()) {
                try {
                    int day = Integer.parseInt(dayStr);
                    calendar.set(Calendar.DATE, day);
                    this.selectedDate = calendar.getTime(); 
                    dispose(); 
                } catch (NumberFormatException nfe) { /* ignore */ }
            }
        };
        for (JButton button : dayButtons) {
            button.addActionListener(dayClickListener);
        }

        updateCalendar();
        pack();
        setLocationRelativeTo(owner);
    }

    public void updateCalendar() {
        calendar.set(Calendar.DATE, 1);
        int month = calendar.get(Calendar.MONTH); int year = calendar.get(Calendar.YEAR);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthLabel.setText(String.format("%d년 %d월", year, month + 1));
        for (int i = 0; i < dayButtons.length; i++) {
            int day = i - firstDayOfWeek + 1;
            if (i < firstDayOfWeek || day > maxDays) {
                dayButtons[i].setText(""); dayButtons[i].setEnabled(false);
            } else {
                dayButtons[i].setText(String.valueOf(day)); dayButtons[i].setEnabled(true);
                Calendar today = Calendar.getInstance(); 
                if (year == today.get(Calendar.YEAR) && month == today.get(Calendar.MONTH) && day == today.get(Calendar.DATE)) {
                    dayButtons[i].setBackground(Color.CYAN);
                } else { dayButtons[i].setBackground(null); }
            }
        }
    }

    
    public Date getSelectedDate() {
        return selectedDate;
    }
}