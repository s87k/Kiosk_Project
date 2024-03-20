package kiosk_prj.controller.memberShipEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import kiosk_prj.view.memverShipView.ModifyMemberDesign;

public class ModifyMemberEvent extends WindowAdapter implements ActionListener {
	private ModifyMemberDesign mmd;
	
	public ModifyMemberEvent(ModifyMemberDesign mmd) {
		this.mmd = mmd;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mmd.getExit()) {
			mmd.dispose();
		}
	}

}
