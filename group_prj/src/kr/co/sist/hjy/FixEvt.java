package kr.co.sist.hjy;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FixEvt extends WindowAdapter implements MouseListener {
	private FixPanel fp;
	
	public FixEvt(FixPanel fp) {
		this.fp=fp;
	}//FixEvt
	
	
	
	@Override
	public void windowClosing(WindowEvent e) {
//		fp.dispose();
	}//windowClosing
	
	@Override
	public void mouseClicked(MouseEvent e) {
		new FixDetailView(fp);
		

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
