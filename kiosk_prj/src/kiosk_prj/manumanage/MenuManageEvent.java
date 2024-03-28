package kiosk_prj.manumanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class MenuManageEvent extends WindowAdapter implements ActionListener {
	
	private MenuManageDesign mmd;
	
	public MenuManageEvent(MenuManageDesign mmd) {
		this.mmd = mmd;
	
		
	}//MenuManageEvent
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 다양한 동작을 여기서 처리
        if (e.getSource() == mmd.getJbAdd()) {
            try {
				insertMenu();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } else if (e.getSource() == mmd.getJbDelete()) {
            deleteMenu();
        } else if (e.getSource() == mmd.getJbUpdate()) {
            modifyMenu();
        } else if (e.getSource() == mmd.getJbAddType()) {
            setMenuType();
        } else if (e.getSource() == mmd.getJbGoback()) {
            goBack();
        }
        
	}//actionPerformed
	
	public void insertMenu() throws SQLException {
		String menucode=mmd.getJtfMenucode().getText().trim();
		String typecode=mmd.getJtfTypecode().getText().trim();
		String menuname=mmd.getJtfMenuname().getText().trim();
		String strmenuprice = mmd.getJtfPrice().getText().trim();
		String menuimg=mmd.getJtfMenuImg().getText().trim();
	
		if(menucode.isEmpty() || typecode.isEmpty() || menuname.isEmpty() 
				|| strmenuprice.isEmpty()) {
			JOptionPane.showMessageDialog(mmd, "값을 모두 입력해주세요");
			return;
		}//end if
		
		int menuprice;
		try {
		        menuprice = Integer.parseInt(strmenuprice);
		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(mmd, "가격은 숫자로 입력해주세요");
		        return;
		    }

		// MenuVO 객체 생성 및 속성 설정
	    MenuVO mVO = new MenuVO();
	    mVO.setMenuCode(menucode);
	    mVO.setTypeCode(typecode);
	    mVO.setMenuName(menuname);
	    mVO.setMenuPrice(menuprice);
	    mVO.setMenuImg(menuimg);
	    
	    MenuDAO mDAO=MenuDAO.getInstance();	
	    
		try {
			mDAO.insertMenu(mVO);
			JOptionPane.showMessageDialog(mmd, "메뉴추가에 성공하셨습니다.");
			
			DefaultTableModel dtmMenuList=mmd.getDtmMenuList();
			dtmMenuList.setRowCount(0);
			selectAllMenu();
		} catch (SQLException e) {
			if(e.getErrorCode() == 2291) {
			JOptionPane.showMessageDialog(mmd, "종류코드를 종류추가메뉴에서 먼저 추가해주세요.");
		} else {
			JOptionPane.showMessageDialog(mmd, "메뉴추가에 실패하셨습니다.");
		}
			e.printStackTrace();
		}
		
	}//insertMenu
	

	public void deleteMenu() {
		String menucode=mmd.getJtfMenucode().getText().trim();
		String typecode=mmd.getJtfTypecode().getText().trim();
		String menuname=mmd.getJtfMenuname().getText().trim();
		String strmenuprice = mmd.getJtfPrice().getText().trim();
		String menuimg=mmd.getJtfMenuImg().getText().trim();
		
		
		if(menucode.isEmpty() || typecode.isEmpty()) {
			JOptionPane.showMessageDialog(mmd, "메뉴코드와 종류코드를 입력해주세요.");
			return;
		}//end if
		
		if(!(menuname.isEmpty())) {
			JOptionPane.showMessageDialog(mmd, "삭제를 할 때는 메뉴코드와 종류코드만 입력해주세요.");
			return;
		}//end if
		
		if(!(strmenuprice.isEmpty())) {
			JOptionPane.showMessageDialog(mmd, "삭제를 할 때는 메뉴코드와 종류코드만 입력해주세요.");
			return;
		}//end if
		
		if(!(menuimg.isEmpty())) {
			JOptionPane.showMessageDialog(mmd, "삭제를 할 때는 메뉴코드와 종류코드만 입력해주세요.");
			return;
		}//end if
		
		
		MenuDAO mDAO=MenuDAO.getInstance();
		
		try {
			mDAO.deleteMenu(menucode, typecode);
			JOptionPane.showMessageDialog(mmd, "메뉴삭제에 성공하셨습니다.");
			
			DefaultTableModel dtmMenuList=mmd.getDtmMenuList();
			dtmMenuList.setRowCount(0);
			selectAllMenu();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(mmd, "메뉴삭제에 실패하셨습니다.");
			e.printStackTrace();
		}
	
		
	}//deleteMenu
	
	public void modifyMenu() {
		String menucode=mmd.getJtfMenucode().getText().trim();
		String typecode=mmd.getJtfTypecode().getText().trim();
		String menuname=mmd.getJtfMenuname().getText().trim();
		String strmenuprice = mmd.getJtfPrice().getText().trim();
		String menuimg=mmd.getJtfMenuImg().getText().trim();
		
		
		if(!(typecode.isEmpty())) {
			JOptionPane.showMessageDialog(mmd, "종류코드는 메뉴코드와 같이 변경할 수 없습니다. 종류추가에서 변경해주세요.");
			return;
		}
			 	
		int menuprice;
		try {
		        menuprice = Integer.parseInt(strmenuprice);
		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(mmd, "가격은 숫자로 입력해주세요");
		        return;
		    }
		
	    // MenuVO 객체 생성 및 속성 설정
	    MenuVO mVO = new MenuVO();
	    mVO.setMenuCode(menucode);
	    mVO.setTypeCode(typecode);
	    mVO.setMenuName(menuname);
	    mVO.setMenuPrice(menuprice);
	    mVO.setMenuImg(menuimg);
	    
	    MenuDAO mDAO=MenuDAO.getInstance();	
		try {
			mDAO.updateMenu(mVO);
			JOptionPane.showMessageDialog(mmd, "메뉴변경에 성공하셨습니다.");
			
			DefaultTableModel dtmMenuList=mmd.getDtmMenuList();
			dtmMenuList.setRowCount(0);
			selectAllMenu();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(mmd, "메뉴변경에 실패하셨습니다.");
			e.printStackTrace();
		}
		
	}//modifyMenu
	
	public void selectAllMenu() {
	
		DefaultTableModel dtmMenuList=mmd.getDtmMenuList();
		
		MenuDAO mDAO = MenuDAO.getInstance();
		try {
			List<MenuVO> listMenuVO = mDAO.selectMenuInfo();

			for (MenuVO mVO : listMenuVO) {
				Object[] row = { mVO.getMenuCode(), mVO.getTypeCode(), mVO.getMenuName(),
						mVO.getMenuPrice(), mVO.getMenuImg() };
				dtmMenuList.addRow(row);
			} // end for

		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}//selectAllMenu
	
	public void setMenuType() {
	
		MenuTypeDesign mtd = new MenuTypeDesign(mmd, null);
		
	}//setMenuType
	
	
	public void goBack() {
		
		 mmd.dispose();
		
	}//goBack

	
	@Override
	public void windowClosing(WindowEvent e) {
			goBack();
		
	}

}//class
