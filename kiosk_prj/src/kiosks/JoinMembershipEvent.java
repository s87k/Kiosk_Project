package kiosks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kiosks.JoinMembershipDesign;
import kiosks.PaymentPageDesign;

public class JoinMembershipEvent extends WindowAdapter implements ActionListener{

	private JoinMembershipDesign jmd;
	private PhoneNumDesign pnd;
	
	public JoinMembershipEvent(JoinMembershipDesign jmd, PhoneNumDesign pnd) {
		this.jmd = jmd;
		this.pnd = pnd;
	}//JoinMembershipEvent
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jmd.getCancel()) {
			pnd.dispose();
		}
		if(e.getSource() == jmd.getJoin()) {
			pnd.dispose();
		}
	}//actionPerformed

	@Override
	public void windowClosed(WindowEvent e) {
		jmd.dispose();
	}

}//class
