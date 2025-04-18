package kr.co.sist.hjy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class FixDetailEvt extends WindowAdapter implements ActionListener, KeyListener {
	private FixDetailView fv;
	

	private Map<String, String> statusMap;
	private String dStatus;// '1','2','3'
//	private int saveBtnFlag;
	private String strCloseStatus;
	private int overMemoFlag;//overMemoFlag가 0이면, 넘치치 않음. 1이면 넘침
	

	public FixDetailEvt(FixDetailView fv, String status) {
		this.fv = fv;
		this.dStatus = status + "";
		fixStatus();
		//창을 닫기 위해 필요한 상태 flag
		strCloseStatus=fv.getFe().getTableList().get(fv.getFe().getListRowNum()).getFixStatus();
	}// FixDetailEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		FixService fs = new FixService();
			

		if (ae.getSource() == fv.getJbtnSave()) {
			// yes:0, no:1
			int selectBtnInd = JOptionPane.showConfirmDialog(fv, "저장하시겠습니까?", "저장", JOptionPane.YES_NO_OPTION);

//			System.out.println(selectBtnInd);
			if (selectBtnInd == 0) {
				/* 저장 버튼을 누른 경우, 수정된 처리상태 저장 */
				saveStatusAMemo(fs);
				//현재 count된 메모사이즈를, memoList의 size에 넣어준다.
				fv.getFe().getMemoList().get(fv.getFe().getListRowNum()).setFixMemoSize(fv.getKeySum()); 
				JOptionPane.showMessageDialog(fv, "저장되었습니다.");
			} // end if
		} // end if

		if (ae.getSource() == fv.getJbtnModifySave()) {
			//yes:0, no:1
			int selectedBtnInd=JOptionPane.showConfirmDialog(fv, "수정하시겠습니까?","수정",JOptionPane.YES_NO_OPTION);
			if(selectedBtnInd==0) {
				saveMemo(fs);
				//현재 count된 메모사이즈를, memoList의 size에 넣어준다.
				fv.getFe().getMemoList().get(fv.getFe().getListRowNum()).setFixMemoSize(fv.getKeySum()); 
				JOptionPane.showMessageDialog(fv, "수정되었습니다.");
			}//end if
		} // end if

		
		/*닫기 버튼을 눌렀을 경우*/
		if (ae.getSource() == fv.getJbtnClose() ) {
//			System.out.println("strCloseStatus"+strCloseStatus);
			closeSetting();
		
			fv.dispose();
		
		} // end if

	}// actionPerformed

	private void fixStatus() {
		statusMap = new HashMap<String, String>();
		statusMap.put("1", "접수완료");
		statusMap.put("2", "정비중");
		statusMap.put("3", "정비완료");

	}// fixStatus


	private void closeSetting() {
		/*접수완료, 정비중 화면에서 / 정비상태와 정비메모가 수정되었는데 저장버튼을 누르지 않았을 경우.*/
		if(strCloseStatus.compareTo("1")==0 || strCloseStatus.compareTo("2")==0) { 
			if(compareSataus()==false || compareMemo()==false) {
				int ret=JOptionPane.showConfirmDialog(fv, "저장하지 않고 닫으시겠습니까?","저장",JOptionPane.YES_NO_OPTION);
				if(ret == 1) {
					return;
				}//end if
			}//end if
		}//end if
		
		/*정비완료 화면에서 / 정비메모가 수정되었는데 저장버튼을 누르지 않았을 경우.*/
		else if (strCloseStatus.compareTo("3")==0){
			if(compareMemo()==false) {
				int ret=JOptionPane.showConfirmDialog(fv, "정비 메모가 수정되었습니다.\n 수정된 것을 저장하지 않고 닫으시겠습니까?","수정",JOptionPane.YES_NO_OPTION);
				if(ret == 1) {
					return;
				}//end if
			}//end if
			
		}//end else if
	}//closeSetting

	
	
	/**
	 * 맨 처음 화면에 뿌릴 fixMemo,
	 * 즉, 해당 정비번호에 해당하는 fixMemo를 return으로 반환
	 */
	public String returnMemoContents(int rowNum) {
		List<MemoVO> memoList=fv.getFe().getMemoList();
		String memo="";
		if(memoList == null) {
			fv.getFe().readFixMemo();
		}//end if
		
		memo=memoList.get(rowNum).getStrFixMemo().toString();
	
		return memo;
	}//returnMemoContents


	/**
	 * 처리 상태와 정비메모 저장한 후, update query를 날린다.
	 * @param fs
	 */
	public void saveStatusAMemo(FixService fs) {
		List<MemoVO> memoList=fv.getFe().getMemoList();
		
		// 처리상태, 정비메모 저장한 후에
		// 처리 상태,정비메모 저장 - update 쿼리를 사용한 service 필요
		String modiFixStatus = fv.getJtbStatus().getSelectedIndex() + 1 + "";
		String fixMemo = fv.getJtaFixMemo().getText();// 정비메모
		String chosenFixNum = fv.getFe().getTableList().get(fv.getFe().getListRowNum()).getFixNum();// 선택된 접수번호

//		System.out.println("jtaFixMemo------"+fv.getJtaFixMemo().getText());	
//		System.out.println("saveStatusAMemo에서  ...."+fixMemo);
		
		fs.modifyStatusAMemo(modiFixStatus, fixMemo, chosenFixNum);

		/* 리스트도 수정해줘야해 */
		// 처리상태 수정
		fv.getFe().getTableList().get(fv.getFe().getListRowNum()).setFixStatus(modiFixStatus);
		// 정비메모 수정
		StringBuilder sb=new StringBuilder(fixMemo);
		memoList.get(fv.getFe().getListRowNum()).setStrFixMemo(sb);

//		System.out.println("saveStatusAMemo에서 sb출력   "+sb.toString());
		
		// 그리고 부모 창을 refresh해줘야 해... 즉 FixPanel창을 ...
		fv.getFe().tableRow();

	}// saveStatus

	/**
	 * 정비 메모만 저장
	 * @param fs
	 */
	public void saveMemo(FixService fs) {
		List<MemoVO> memoList=fv.getFe().getMemoList();
		
		String fixMemo = fv.getJtaFixMemo().getText();// 정비메모
		String chosenFixNum = fv.getFe().getTableList().get(fv.getFe().getListRowNum()).getFixNum();// 선택된 접수번호

//		System.out.println("saveMemo 부분" + fixMemo);

		fs.modifyMemo(fixMemo, chosenFixNum);

		/* 이제 리스트 수정해주자. */
		// 정비메모 수정
		// 여기서 에러남
		StringBuilder sb=new StringBuilder(fixMemo);
//		System.out.println("saveMemo에서 sb출력   "+sb.toString());
		memoList.get(fv.getFe().getListRowNum()).setStrFixMemo(sb);
		// 여기서는 부모 창을 refresh해 줄 필요가 없음.
	}// saveMemo

	@Override
	public void windowClosing(WindowEvent we) {
		
		if(strCloseStatus != null) {
			closeSetting();
		}//end if
			fv.dispose();
	}// windowClosing
	
	
	
	
	/**
	 * 기존 처리상태(status)와 현재 내가 선택한 상태를 비교해
	 * 같으면, true, 다르면 false를 반환한다.
	 * @return statusFlage boolean
	 */
	private boolean compareSataus() {
		boolean statusFlag=false;
		//기존 처리상태, 리스트에서 불러오면 되겠지.
		String originStatus=statusMap.get(fv.getFe().getTableList().get(fv.getFe().getListRowNum()).getFixStatus());
		String newStaus=statusMap.get(fv.getJtbStatus().getSelectedIndex()+1+"");
//		System.out.println("originStatus"+originStatus);
//		System.out.println("newStatus"+newStaus);

		if(originStatus.compareTo(newStaus) == 0) {//0이면 동일
			statusFlag=true;
		}//end if
		
		return statusFlag;
	}//compareSataus
	
	/**
	 * 기존 처리상태(status)와 현재 내가 선택한 상태를 비교해
	 * 같으면, true, 다르면 false를 반환한다.
	 * @return statusFlage boolean
	 */
	private boolean compareMemo() {
		boolean statusFlag=false;
		
		//기존 처리상태, 리스트에서 불러오면 되겠지.
		String originMemo=fv.getFe().getMemoList().get(fv.getFe().getListRowNum()).getStrFixMemo().toString();
		String newMemo=fv.getJtaFixMemo().getText();
//		System.out.println("originMemo"+originMemo);
//		System.out.println("newMemo"+newMemo);

		if(originMemo.compareTo(newMemo) == 0) {//0이면 동일
			statusFlag=true;
		}//end if
		
		
		return statusFlag;
	}//compareMemo

	//////// getter method/////////////////////////////////////////////////////

	public FixDetailView getFv() {
		return fv;
	}

	public Map<String, String> getStatusMap() {
		return statusMap;
	}

	public String getdStatus() {
		return dStatus;
	}
	
	
	


	////// setter
	////// method//////////////////////////////////////////////////////////////////
	public void setdStatus(String dStatus) {
		this.dStatus = dStatus;
	}

	//////////////////////////////////////////////////////////////////

	@Override
	public void keyTyped(KeyEvent ke) {
		//한글은 keyPressed에서 못잡으니까, keyType으로 옮김.
		int keyCnt=(int)ke.getKeyChar();
//		System.out.println(keyCnt);
		
		if(overMemoFlag == 0) {
			if(keyCnt>44031 && keyCnt<55203) {
				fv.setKeySum(3);
				fv.getJlblKeyCount().setText(fv.getKeySum()+"");
				fv.keyCountView();
	
			}else if(keyCnt>12592 && keyCnt<12644) {
				fv.setKeySum(3);
				fv.getJlblKeyCount().setText(fv.getKeySum()+"");
				fv.keyCountView();
			}//end if ~ else if
			
			keySumChk();
			
			if(fv.getKeySum()<1000) {
				overMemoFlag=0;
			}//end if

		}//end if
//		System.out.println(fv.getKeySum());
		
	}// keyTyped

	@Override
	public void keyPressed(KeyEvent ke) {
		fv.getFe().getMemoList().get(fv.getFe().getListRowNum()).getFixMemoSize();
		
		// 아 ... 여기서 Stream 쓰면 될 것 같은데....
		// inputstream으로 count하는거지
		//0~127까지는 1바이트
		//44032 ~  50807까지는 한글이므로 3byte
		int keyCnt=(int)ke.getKeyChar();
//		System.out.println(keyCnt);
		if(ke.isControlDown() || keyCnt==ke.VK_DELETE) {
			JOptionPane.showMessageDialog(fv, "사용할 수 없는 키입니다.");
			String text=fv.getFe().getMemoList().get(fv.getFe().getListRowNum()).getStrFixMemo().toString();
			fv.getJtaFixMemo().setText(text);
		}//end if
	
		if(keyCnt==8) {//백스페이스 혹은 delete 키
			
			//만약 빈 정비메모이면,
			if(checkEmpty()) {
				return;
			}//end if
			
			
			fv.getJtaFixMemo().setEditable(true);
			fv.getJtaFixMemo().setCaretPosition(fv.getJtaFixMemo().getText().length());
			fv.getJtaFixMemo().getCaret().setVisible(true);

			StringBuilder sb=new StringBuilder();
			sb.append(fv.getJtaFixMemo().getText());
			int keysum=fv.getFe().byteCalculate(sb);
			fv.setKeySum(-keysum);		
			fv.getJlblKeyCount().setText(fv.getKeySum()+"");
			
			fv.keyCountView();
			
			if(fv.getKeySum()<1000) {
				overMemoFlag=0;
			}//end if
			
			
			
		}//end if
			
			
		if(overMemoFlag == 0) {//overMemoFlag가 0이면, 넘치치 않음.
			if((keyCnt>32 && keyCnt<127)||(keyCnt ==32)||(keyCnt==10)) {
				fv.setKeySum(1);
				fv.getJlblKeyCount().setText(fv.getKeySum()+"");
				fv.keyCountView();
	
				
				keySumChk();
			}//end if
		}//end if
	
	
		//여기서 예외처리를 하긴 해야할 것 같은데.
		//그러면 애초에 입력받을 때, Stringbuilder와 flag 2개를 가지고, 초과했는지 계속 비교하면서 해야해,
		//만약에 초과되었다면, 초과되기 전까지의 문자열까지만 남겨주는거지.
		
		
	}//keyPressed

	private void keySumChk() {
		//1000byte를 넘어가면, 중단.
//		System.out.println(fv.getKeySum());
		if(fv.getKeySum()>1000) {
			overMemoFlag=1;
			fv.getJtaFixMemo().setEditable(false); //더이상 textarea에 쓸 수 없음.
			JOptionPane.showMessageDialog(fv, "1000byte를 초과하였습니다.");
			
		}//end if
	}//keySumChk
	
	
	@Override
	public void keyReleased(KeyEvent ke) {
		
		checkEmpty();
		
//		
//		System.out.println("get text -------|"+fv.getJtaFixMemo().getText()+"|-----------------");
//		fv.getJtaFixMemo().setCaretPosition(fv.getJtaFixMemo().getText().length());
//		System.out.println("getCaretPosition ------ "+fv.getJtaFixMemo().getCaretPosition());

	}//keyReleased

	private boolean checkEmpty() {
		boolean checkFlag=false;
		
		if(fv.getJtaFixMemo().getText().matches("\\s*\\n*\\t*")) {
			fv.setKeySum(0);
//			System.out.println("set 0 하였음.");
			fv.getJlblKeyCount().setText(fv.getKeySum()+"");
			fv.keyCountView();
			fv.getJtaFixMemo().setEditable(true);
			overMemoFlag=0;
			checkFlag=true;
		}//end if
		
		
		return checkFlag;
	}//checkEmpty
	
	
	

}// class
