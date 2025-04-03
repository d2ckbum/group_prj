package kr.co.sist.hjy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;


public class FixDetailEvt extends WindowAdapter implements ActionListener, KeyListener{
	private FixDetailView fv;
	
	private Map<String,String> statusMap;
	private String dStatus;//'1','2','3'
	
	
	public FixDetailEvt(FixDetailView fv,String status) {
		this.fv=fv;
		this.dStatus=status+"";
		fixStatus();
	}//FixDetailEvt

	@Override
	public void actionPerformed(ActionEvent ae) {

		if(ae.getSource() == fv.getJbtnSave()) {
			//처리 상태 저장 - update 쿼리를 사용한 service 필요
			
			//정비메모 저장 - update 쿼리를 사용한 service 필요
		}//end if
		
		if(ae.getSource() == fv.getJbtnClose()) {
			windowClosing();
		}//end if
		
	}//actionPerformed
	
	private void fixStatus() {
		statusMap=new HashMap<String, String>();
		statusMap.put("1", "접수완료");
		statusMap.put("2", "정비중");
		statusMap.put("3", "정비완료");
		
	}//fixStatus
	
	
	public String readFixMemo(List<FixPanelVO> tableList , int rowNum) throws IOException {
		String memo="";
		Clob clob=tableList.get(rowNum).getFixMemo();
		
		Reader reader=null;
		BufferedReader br=null;
		StringBuilder sb=new StringBuilder();
	
		try {
			reader=clob.getCharacterStream();
			br=new BufferedReader(reader);
			
			
			while((memo=br.readLine())!=null) {
				sb.append(memo).append("\n");
			}//end while
			
			System.out.println(br);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(reader != null) {reader.close();}
			if(br != null) {br.close();}
		}//end try ~ finally
		
		
		memo=sb.toString();
		
		return memo;
	}//readFixMemo
	
	
	

	public void windowClosing() {
		fv.dispose();
	}//windowClosing
	
	////////getter method/////////////////////////////////////////////////////
	
	public FixDetailView getFv() {
		return fv;
	}
	
	public Map<String, String> getStatusMap() {
		return statusMap;
	}


	public String getdStatus() {
		return dStatus;
	}

	
	//////setter method//////////////////////////////////////////////////////////////////
	public void setdStatus(String dStatus) {
		this.dStatus = dStatus;
	}
	
	
	
	//////////////////////////////////////////////////////////////////

	@Override
	public void keyTyped(KeyEvent e) {
	//아 ... 여기서 Stream 쓰면 될 것 같은데....
		//inputstream으로 count하는거지
	}//keyTyped

	@Override
	public void keyPressed(KeyEvent e) {
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}//class
