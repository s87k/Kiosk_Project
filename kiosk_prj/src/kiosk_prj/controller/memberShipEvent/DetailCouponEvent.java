package kiosk_prj.controller.memberShipEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kiosk_prj.view.memverShipView.DetailCouponDesign;

public class DetailCouponEvent extends WindowAdapter implements ActionListener{
	private DetailCouponDesign dcd;
	
	public DetailCouponEvent(DetailCouponDesign dcd) {
		this.dcd = dcd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == dcd.getBack()) {
			dcd.dispose();
		}
	}

}
