package kiosk_prj.controller.memberShipEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kiosk_prj.view.memverShipView.AddMemberDesign;

public class AddMemberEvent extends WindowAdapter implements ActionListener{
	private AddMemberDesign admd;
	public AddMemberEvent(AddMemberDesign admd) {
		this.admd = admd;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == admd.getExit()) {
			admd.dispose();
		}
	}
}
