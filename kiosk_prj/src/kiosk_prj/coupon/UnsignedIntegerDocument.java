package kiosk_prj.coupon;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import static java.lang.Integer.parseInt;

@SuppressWarnings("serial")
public class UnsignedIntegerDocument extends PlainDocument{
	
	/**
	 * 최대 자릿수 (0 이하의 값 입력시 텍스트 필드에 숫자가 입력되지 않음)
	 */
	private int digit;
	/**
	 * 최소값 (범위: 0 ~ 9)
	 */
	private int minVal;
	/**
	 * 최대값 (범위: minVal ~ 2147483647) 
	 */
	private int maxVal;
	
	/**
	 * 파라미터에 이상이 있어, 값이 수정된 경우 true 
	 */
	private boolean flagParamChanged;
	
	/**
	 * @param digit 최대 자릿수
	 * @param minVal 최소값 (0 이상 9 이하)
	 * @param maxVal 최대값
	 */
	public UnsignedIntegerDocument(int digit, int minVal, int maxVal) {
//		System.out.println(digit + " " + minVal + " " + maxVal);
		if(digit > 10) {
			digit = 10;
			flagParamChanged = true;
		} // end if
		if(minVal < 0) {
			minVal = 0;
			flagParamChanged = true;
		} // end if
		if(minVal > 9) {
			minVal = 9;
			flagParamChanged = true;
		} // end if
		
		if(maxVal < minVal) {
			maxVal = minVal;
			flagParamChanged = true;
		} // end if
				
//		System.out.println(digit + " " + minVal + " " + maxVal);
		
		this.digit = digit;
		this.minVal = minVal;
		this.maxVal = maxVal;
	} // MyIntegerDocument

	/**
	 * @param offs 입력된 값의 위치 (인덱스)
	 * @param str 입력된 값
	 * @param a
	 */
	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if(str == null) {
			return;
		} // end if
		
		int digitCurrent = getLength() + str.length();
		if(digitCurrent > digit) {
			return;
		} // end if
		
		try {
			int numCurrentOne = parseInt(str);
			
			switch(digitCurrent) {
			case 1:
				if(numCurrentOne >= minVal && numCurrentOne <= maxVal) {
					super.insertString(offs, str, a);
				} // end if
				break;
			default: 
				int numEntire = parseInt(getText(0, getLength()).concat(str));
				
				if(numEntire >= minVal && numEntire <= maxVal) {
					super.insertString(offs, str, a);
				} // end if
			} // end switch
		} catch (NumberFormatException nfe) {
			return;
		} // end catch
	} // insertString
	
	public boolean isFlagParamChanged() {
		return flagParamChanged;
	}

	@Override
	public String toString() {
		String strIsChanged = flagParamChanged == false ? "" : "(parameter changed) "; 
		return "MyUnsignedIntegerDocument " + strIsChanged + "[digit=" + digit + ", minVal=" + minVal + ", maxVal=" + maxVal + "]";
	}
	
} // class