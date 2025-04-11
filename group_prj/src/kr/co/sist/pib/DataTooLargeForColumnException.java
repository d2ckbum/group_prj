package kr.co.sist.pib;

@SuppressWarnings("serial")
public class DataTooLargeForColumnException extends Exception {
	private String errorColumnName;
	
	
	@Override
	public void printStackTrace() {
		System.err.println(errorColumnName + " 컬럼의 데이터크기 초과");
	}


	public DataTooLargeForColumnException(String errorColumnName) {
		this.errorColumnName = errorColumnName;
	}


	public String getErrorColumnName() {
		return errorColumnName;
	}
	
	
	
	
}
