package kiosk_prj.login;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import kiosk_prj.adminMain.AdminMainPageDesign;

public class LoginEvent extends WindowAdapter implements ActionListener {
	private LoginDesign ld;
	private Map<String, LoginVO> user = new HashMap<>();

	
	
	
	public LoginEvent(LoginDesign ld) {
		this.ld = ld;
		
		//관리자 로그인 id pw 설정
		user.put("김병년", new LoginVO("김병년","1234"));
		user.put("유재희", new LoginVO("유재희","101"));
		user.put("빠나나", new LoginVO("빠나나","12345"));
	}//LoginEvent

	@Override
	public void windowClosing(WindowEvent e) {
		ld.dispose();
	}//windowClosing

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == ld.getLogin()) { //로그인 버튼 눌렸을시
			login();
		}//end if
		
		//db에서 아이디 비밀번호 비교해서 로그인했어야했는데!
		if (ae.getSource() == ld.getiD() || ae.getSource() == ld.getPassword()) {
			String id = ld.getiD().getText();
			String pw = String.valueOf(ld.getPassword().getPassword());
			if(!id.equals("") && !pw.equals("")) {
				login();
			}//end if
		}//end if
		
	}//actionPerformed


	public void login() {
		String id = ld.getiD().getText().trim();
		String pw = String.valueOf(ld.getPassword().getPassword());
		
		if (user.containsKey(id)) {
			LoginVO currentUser = user.get(id);
			if (!currentUser.getPw().equals(pw)) {
				JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력해주세요");
				System.out.println(id);
				System.out.println(pw);
			} else {
				ld.dispose();
				new AdminMainPageDesign(id);
			}//end else
		} else {
			JOptionPane.showMessageDialog(null, "존재하지 않는 아이디 입니다.");
		}//end else
		
	}//login

}//class
