package kiosk_prj.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import kiosk_prj.view.JoinMembershipDesign;
import kiosk_prj.view.PhoneNumDesign;
import kiosk_prj.view.UsingCouponDesign;

public class PhoneNumEvent extends WindowAdapter implements ActionListener{

	private PhoneNumDesign pnd;
	
	public PhoneNumEvent(PhoneNumDesign pnd) {
		this.pnd = pnd;
	}//PhoneNumEvent
	
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();
            
            if (!buttonText.equals("입력")) { // "입력" 버튼이 아닌 경우
                String currentText = pnd.getJtfPhoneNum().getText();
                pnd.getJtfPhoneNum().setText(currentText + buttonText); // 텍스트 필드에 버튼의 텍스트 추가
            } else if(buttonText.equals("입력")) { // "입력" 버튼인 경우
                int result = JOptionPane.showConfirmDialog(null, "일단 Y : 쿠폰조회 N : 회원가입", "확인", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    new UsingCouponDesign(pnd, "옵션 선택");
                } else if (result == JOptionPane.NO_OPTION) {
                    new JoinMembershipDesign();
                    pnd.dispose();
                }
//            } else if(buttonText.equals("<")) {
//            	pnd.getJ
//            }
        }
        }
    }
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		String phoneNum = pnd.getJtfPhoneNum().getText();
//		String currentNum = pnd.getKeypad().getText();
//		if(e.getSource() == pnd.getKeypad()) {
//			System.out.println("1");
//		}
//			if(pnd.getKeypad().getText() == "입력") {
//				int result = JOptionPane.showConfirmDialog(null, "일단 Y : 쿠폰조회 N : 회원가입", 
//						"Confirm", JOptionPane.YES_NO_OPTION);
//				if(result == JOptionPane.YES_OPTION) {
//					new UsingCouponDesign(pnd, "옵션 선택");
//				}
//				if(result == JOptionPane.NO_OPTION) {
//					new JoinMembershipDesign();
//					pnd.dispose();
//				
//			}
//		}
//	}//actionPerformed

}//class
