package kr.co.sist.hjy;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

public class FixEvt extends WindowAdapter implements MouseListener {
	private FixPanel fp;
	private Map<String,String> statusMap;
	private List<FixPanelVO> tableList;
	private List<MemoVO> memoList;
	private int listRowNum;
	
	private String[] rowData;
	private int refreshFlag;//0 :DB연결필요, 1:DB연결 불필요
	
	public FixEvt(FixPanel fp) {
		this.fp=fp;
		fixStatus();
	}//FixEvt
	
	
	
	@Override
	public void windowClosing(WindowEvent e) {
//		fp.dispose();
	}//windowClosing
	
	@Override
	public void mouseClicked(MouseEvent me) {
//		System.out.println(fp.getJtbSummary().getSelectedRow());
//		System.out.println("mouseClicked" +  me.getComponent());
//		System.out.println("mouserClicked x좌표" + fp.getJtbSummary().getAlignmentX());
		//애초에 여기서 넘길 때, 처리상태를 같이 넘겨야, 받는 부분에서 어떤걸 띄워줄지 결정할 수 있음.
//		System.out.println("fp의 주소값 -- "+fp.hashCode());
//		System.out.println("행의 갯수"+fp.getJtbSummary().getRowCount());
//		System.out.println("getRootPane"+fp.getJtbSummary().getRootPane().hashCode());
//		System.out.println("mouseClicked jtbSummary"+fp.getJtbSummary().hashCode());
		listRowNum=fp.getJtbSummary().getSelectedRow();
		
//		System.out.println("mouseClicked listRowNum    "+listRowNum);
		new FixDetailView(this,tableList.get(listRowNum).getFixStatus());
		

	}//mouseClicked

	
	/**
	 * "접수번호","접수일","상품명","차량번호","접수자","결제금액","처리상태"
	 * @return
	 */
	public void tableRow(){
		DecimalFormat df = new DecimalFormat("#,###,###");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
		
//		System.out.println("FixEvt tableRecord 메소드   " + listRowNum);
		/*처음 DB 연결하는거임.*/
		//아예 여기서 if문으로 구분해서 넣어주면 될 것 같은데
		if(refreshFlag==0) {
			FixService fs=new FixService();
			tableList=fs.viewAllFixList();
			refreshFlag=1;
			return;
		}//end if
		

		Iterator<FixPanelVO> ita=tableList.iterator();
		FixPanelVO fVO=null;
		DefaultTableModel dtm=fp.getDtm();//모델 받기
		
		
		//기존의 행수가 하나 이상이라면, model 초기화
		if(dtm.getRowCount() > 0) {
			dtm.setRowCount(0);
		}//end if
		
		rowData=new String[7];
		//새로운 데이터 채우기
		while(ita.hasNext()) {
			fVO=ita.next();
			
			rowData[0]=fVO.getFixNum();//접수번호
			rowData[1]=sdf.format(fVO.getFixRegDate());//접수일
			rowData[2]=fVO.getItemName();//상품명
			rowData[3]=fVO.getMfgName()+" "+fVO.getCarType();
			rowData[4]=fVO.getMemName();
			rowData[5]=df.format(fVO.getTotal());
			rowData[6]=statusMap.get(fVO.getFixStatus());
			

			//DefaultTableModel에 추가(addRow)
			dtm.addRow(rowData);
		}//end while
		
		if(memoList == null) {
			readFixMemo();
		}//end if
		

	}//tableRecord
	
	
	

	private void fixStatus() {
		statusMap=new HashMap<String, String>();
		statusMap.put("1", "접수완료");
		statusMap.put("2", "정비중");
		statusMap.put("3", "정비완료");
		
	}//fixStatus
	
	
	
	/**
	 * CLOB 데이터형을 읽어서 StringBuilder 형태로 저장해준다.
	 * MemoVO 이용
	 * @param tableList
	 * @param rowNum
	 * @throws IOException
	 */
	public void readFixMemo() {
//		List<FixPanelVO> tableList=fv.getFe().getTableList();
//		List<MemoVO> memoList=fv.getFe().getMemoList();
//		int row=fv.getFe().getListRowNum();

		String memo = "";
		Clob clob = null;
		
		memoList=new ArrayList<MemoVO>();
		
		
		Reader reader = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
//		System.out.println("readFixMemo에서 tablelistSize    "+tableList.size());
		
		
		for(int row=0;row<tableList.size();row++) {
		
			MemoVO memoVO=null;
			clob=tableList.get(row).getFixMemo();
			if(clob ==null) {
				//만약에 clob의 값이 null이면,
				StringBuilder sbsb=new StringBuilder();
				sbsb.append("");
				int size=0;
				memoVO=new MemoVO(tableList.get(row).getFixNum(), sbsb, size);
				
				memoList.add(memoVO);
				
				continue;
			}//end if
			
			try {
				reader = clob.getCharacterStream();
				br = new BufferedReader(reader);
				
				sb=new StringBuilder();
				
				while ((memo = br.readLine()) != null) {
					sb.append(memo).append("\n");
				} // end while
	
//				System.out.println("sb의 length-----------"+sb.length());
				//memoList에 넣어주자.
				int keySize=byteCalculate(sb);
//				System.out.println("keySize -------------"+keySize);
				memoVO=new MemoVO(tableList.get(row).getFixNum(), sb, keySize);
//				System.out.println(sb.toString());
				memoList.add(memoVO);
				
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
				if (br != null) {try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			} // end try ~ finally
		
		}//end for
		
//		fv.getFe().setMemoList(memoList);
		
	}// readFixMemo
	
	private int byteCalculate(StringBuilder sb) {
		int keySize=0;
		
		String str=sb.toString();
		String[] strArr=str.split("");
		int[] intArr=new int[strArr.length];
		
		
		
		for(int i=0; i<strArr.length;i++) {
			int num=strArr[i].charAt(0);
			intArr[i]=num;
//			System.out.println("intArr==============="+intArr[i]);
		}//end for

		
		for(int i=0; i<intArr.length;i++) {
			int compare=intArr[i];
			if((compare>47 && compare<128)||(compare ==32)) {
				//1추가
				keySize+=1;
	
			}else if(compare>44031 && compare<55203) {
				//3추가
				keySize+=3;
	
			}else if(compare>12592 && compare<12644) {
				//3추가
				keySize+=3;
			}//end if ~ else if
		
		}//end for
		return keySize;

	}//byteCalculate
	
	////////사용 안하는 method//////////////////////////////////////////////////////////////////////////
	
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



	public List<FixPanelVO> getTableList() {
		return tableList;
	}



	public int getListRowNum() {
		return listRowNum;
	}



	public FixPanel getFp() {
		return fp;
	}



	public Map<String, String> getStatusMap() {
		return statusMap;
	}



	public int getRefreshFlag() {
		return refreshFlag;
	}



	public void setRefreshFlag(int refreshFlag) {
		this.refreshFlag = refreshFlag;
	}



	public List<MemoVO> getMemoList() {
		return memoList;
	}

	public void setMemoList(List<MemoVO> memoList) {
		this.memoList = memoList;
	}
	

}//class
