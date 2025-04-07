package kr.co.sist.kji;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFixInfoEvt implements ActionListener{
	
	private MyFixInfoView mfiv;
	
	public MyFixInfoEvt(MyFixInfoView mfiv) {
		this.mfiv = mfiv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==mfiv.getConfirmBtn()){
			new MyInfoView(mfiv.getId());
			mfiv.dispose();
			
		}//end if
		
		if(e.getSource()==mfiv.getLogoutBtn()) {
			new LoginpageView();
			mfiv.dispose();
		}//end if
	}//actionPerformed

}//class
