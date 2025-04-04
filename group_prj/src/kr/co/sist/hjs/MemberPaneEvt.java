package kr.co.sist.hjs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;

public class MemberPaneEvt extends WindowAdapter implements MouseListener {
	private MemberPane mp;

	public MemberPaneEvt(MemberPane mp) {
		this.mp = mp;
	}// MemberPaneEvt

	@Override
	public void mouseClicked(MouseEvent e) {
		new MemberDetailView(mp, null);
	}// mouseClicked

	@Override
	public void mousePressed(MouseEvent e) {

	}// mousePressed

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}// mouseReleased

	@Override
	public void mouseEntered(MouseEvent e) {

	}// mouseEntered

	@Override
	public void mouseExited(MouseEvent e) {

	}// mouseExited

}// class
