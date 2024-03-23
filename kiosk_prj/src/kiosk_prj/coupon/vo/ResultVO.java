package kiosk_prj.coupon.vo;

/**
 * CallableStatementDAO에서 사용하는 VO
 */
public class ResultVO {
	private int cnt;
	private String errMsg;
	
	public ResultVO(int cnt, String errMsg) {
		this.cnt = cnt;
		this.errMsg = errMsg;
	} // ResultVO
	
	public ResultVO() {
	} // ResultVO
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	@Override
	public String toString() {
		return "ResultVO [cnt=" + cnt + ", errMsg=" + errMsg + "]";
	}
	
}
