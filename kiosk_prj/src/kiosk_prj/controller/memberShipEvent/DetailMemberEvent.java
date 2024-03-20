package kiosk_prj.controller.memberShipEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kiosk_prj.view.memverShipView.ConsumePatternDesign;
import kiosk_prj.view.memverShipView.DetailCouponDesign;
import kiosk_prj.view.memverShipView.DetailMemberDesign;
import kiosk_prj.view.memverShipView.DetailOrderDesign;

public class DetailMemberEvent extends WindowAdapter implements ActionListener{
	private DetailMemberDesign dmd;
	public DetailMemberEvent(DetailMemberDesign dmd) {
		this.dmd = dmd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == dmd.getjOrderList()) {
			new DetailOrderDesign(dmd, "회원상세 >>> 주문목록");
		}
		if(e.getSource() == dmd.getjCouponList()) {
			new DetailCouponDesign(dmd, "회원상세 >>> 쿠폰목록");
		}
		if(e.getSource() == dmd.getjConsumeTrend()) {
			new ConsumePatternDesign(dmd, "회원상세 >>> 소비패턴");
		}
	}

}
