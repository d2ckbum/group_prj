package kr.co.sist.khb.event;

import kr.co.sist.khb.vo.OrderVO;
import kr.co.sist.khb.view.OrderView;
import kr.co.sist.khb.service.OrderService; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class OrderEvt implements ActionListener {

    private OrderView view;

    public OrderEvt(OrderView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getRequestButton()) {
            handleFixRequest();
        } else if (e.getSource() == view.getCancelButton()) {
            handleCancelAction();
        }
    }

    private void handleFixRequest() {
        OrderVO orderVO = view.getFinalOrderDetails();

        if (orderVO != null) {
            System.out.println("[OrderEvt] Service 호출 전 데이터 확인: " + orderVO.toString());

            try {
                String generatedFixNum = OrderService.getInstance().placeMaintenanceRequest(orderVO);


                Object[] message = {
                    "정비 요청이 성공적으로 접수되었습니다.",
                    "접수번호 : " + generatedFixNum
                };

                JOptionPane.showMessageDialog(view,message,"요청 완료",
                        JOptionPane.INFORMATION_MESSAGE);

                Container parent = view.getParent();
                if (parent != null && parent.getLayout() instanceof CardLayout) {
                    CardLayout cl = (CardLayout) parent.getLayout();
                    cl.previous(parent);
                } else {
                    Window window = SwingUtilities.getWindowAncestor(view);
                    if (window != null) { window.dispose(); }
                }

            } catch (SQLException se) {
                se.printStackTrace();
                JOptionPane.showMessageDialog(view, "데이터베이스 처리 중 오류: " + se.getMessage(), "DB 오류", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "처리 중 오류 발생: " + ex.getMessage(), "처리 오류", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "저장할 데이터가 없습니다.", "오류", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleCancelAction() {
        System.out.println("취소 버튼 클릭 (OrderEvt)");
        JOptionPane.showMessageDialog(view, "취소되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);

        Container parent = view.getParent();
        if (parent != null && parent.getLayout() instanceof CardLayout) {
            CardLayout cl = (CardLayout) parent.getLayout();
            cl.previous(parent); 
        } else {
             Window window = SwingUtilities.getWindowAncestor(view);
             if (window != null) {
                 window.dispose();
             }
        }
    }

} // class OrderEvt