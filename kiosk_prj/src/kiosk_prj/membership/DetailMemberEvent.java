package kiosk_prj.membership;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class DetailMemberEvent extends WindowAdapter implements ActionListener{
	private DetailMemberDesign dmd;
	
	public DetailMemberEvent(DetailMemberDesign dmd) {
		this.dmd = dmd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String phoneNum = dmd.getPhoneNum();
		if(e.getSource() == dmd.getjOrderList()) {
			new DetailOrderDesign(dmd, "회원상세 >>> 주문목록", phoneNum);
		}
		if(e.getSource() == dmd.getjCouponList()) {
			new DetailCouponDesign(dmd, "회원상세 >>> 쿠폰목록", phoneNum);
		}
		if(e.getSource() == dmd.getjConsumeTrend()) {
			new ConsumePatternDesign(dmd, "회원상세 >>> 소비패턴");
		}
	}

}
