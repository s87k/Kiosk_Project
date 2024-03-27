package kiosk_prj.coupon;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class StringLimitLengthDocument extends PlainDocument {
	
	private int maxLength;
	
	public StringLimitLengthDocument(int maxLength) {
		if(maxLength < 0) {
			maxLength = 0;
		} // end if
		this.maxLength = maxLength;
	} // StringLimitDocument

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if(str == null) {
			return;
		} // end if
		if(getLength() + str.length() <= maxLength) {
			super.insertString(offs, str, a);
		} // end if
	} // insertString

} // class
