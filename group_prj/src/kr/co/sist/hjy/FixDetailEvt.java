package kr.co.sist.hjy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;


public class FixDetailEvt extends WindowAdapter implements ActionListener{
	FixDetailView fv;
	public FixDetailEvt(FixDetailView fv) {
		this.fv=fv;
	}//FixDetailEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == fv.getJbtnClose()) {
			windowClosing();
		}//end if
		
	}//actionPerformed
	

	public void windowClosing() {
		fv.dispose();
	}//windowClosing
}//class
