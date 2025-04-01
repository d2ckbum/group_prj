package kr.co.sist.hjy;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FixEvt extends WindowAdapter implements MouseListener {
	private FixView fv;
	
	public FixEvt(FixView fv) {
		this.fv=fv;
	}//FixEvt
	
	
	
	@Override
	public void windowClosing(WindowEvent e) {
		fv.dispose();
	}//windowClosing
	
	@Override
	public void mouseClicked(MouseEvent e) {
		new FixDetailView(fv);
		

	}//mouseClicked

	
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}//class
