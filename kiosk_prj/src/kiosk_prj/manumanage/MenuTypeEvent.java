package kiosk_prj.manumanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MenuTypeEvent extends WindowAdapter implements ActionListener {

	private MenuTypeDesign mtd;
	
	public MenuTypeEvent(MenuTypeDesign mtd) {
		this.mtd = mtd;
		
		
	}//MenuTypeEvent
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 다양한 동작을 여기서 처리
        if (e.getSource() == mtd.getJbAdd()) {
            try {
				insertType();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } else if (e.getSource() == mtd.getJbDelete()) {
            deleteType();
        } else if (e.getSource() == mtd.getJbUpdate()) {
            modifyType();
        } else if (e.getSource() == mtd.getJbClose()) {
        	close();
        }
        
	}
	
	public void insertType() throws SQLException {
		String typecode=mtd.getJtfTypecode().getText().trim();
		String typename=mtd.getJtfTypename().getText().trim();
		
		if(typecode.isEmpty() || typename.isEmpty()) {
			JOptionPane.showMessageDialog(mtd, "값을 모두 입력해주세요");
			return;
		}//end if
		
		//MenuTypeVO 객체 생성 및 속성 설정
		MenuTypeVO mtVO = new MenuTypeVO();
		mtVO.setTypeCode(typecode);
		mtVO.setTypeName(typename);
		
		MenuTypeDAO mtDAO=MenuTypeDAO.getInstance();
		
		try {
			mtDAO.insertType(mtVO);
			JOptionPane.showMessageDialog(mtd, "종류추가에 성공하셨습니다.");
			
			DefaultTableModel dtmTypeList=mtd.getDtmTypeList();
			dtmTypeList.setRowCount(0);
			selectAllType();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(mtd, "종류추가에 실패하셨습니다.");
			e.printStackTrace();
		}
		
	}
     
	public void deleteType() {
		String typecode=mtd.getJtfTypecode().getText().trim();
		String typename=mtd.getJtfTypename().getText().trim();
		
		if(typecode.isEmpty()) {
			JOptionPane.showMessageDialog(mtd, "종류코드를 입력해주세요");
			return;
		}//end if
		
		if(!(typename.isEmpty())) {
			JOptionPane.showMessageDialog(mtd, "삭제를 할 때는 종류코드만 입력해주세요.");
			return;
		}//end if
		
		MenuTypeDAO mtDAO=MenuTypeDAO.getInstance();
		
		try {
			mtDAO.deleteType(typecode);
			JOptionPane.showMessageDialog(mtd, "종류삭제에 성공하셨습니다.");
			
			DefaultTableModel dtmTypeList=mtd.getDtmTypeList();
			dtmTypeList.setRowCount(0);
			selectAllType();
		} catch (SQLException e) {
			if(e.getErrorCode() == 2292) {
				JOptionPane.showMessageDialog(mtd, "해당 종류코드와 관련된 메뉴를 먼저 삭제해주세요.");
			} else {
			JOptionPane.showMessageDialog(mtd, "종류삭제에 실패하셨습니다.");
			}
			e.printStackTrace();
		}
		
	}//deleteType
           
	public void modifyType() {
		String typecode=mtd.getJtfTypecode().getText().trim();
		String typename=mtd.getJtfTypename().getText().trim();
		
		if(typecode.isEmpty()) {
			JOptionPane.showMessageDialog(mtd, "종류코드를 입력해주세요");
			return;
		}//end if
		
		//MenuTypeVO 객체 생성 및 속성 설정
		MenuTypeVO mtVO = new MenuTypeVO();
		mtVO.setTypeCode(typecode);
		mtVO.setTypeName(typename);
				
	   MenuTypeDAO mtDAO =MenuTypeDAO.getInstance();
		try {
			mtDAO.updateType(mtVO);
			JOptionPane.showMessageDialog(mtd, "종류변경에 성공하셨습니다.");
			
			DefaultTableModel dtmTypeList=mtd.getDtmTypeList();
			dtmTypeList.setRowCount(0);
			selectAllType();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(mtd, "종류변경에 실패하셨습니다.");
			e.printStackTrace();
		}
			
	}//modifyType
	
	public void selectAllType() {
		
		DefaultTableModel dtmTypeList=mtd.getDtmTypeList();
		
		MenuTypeDAO mtDAO = MenuTypeDAO.getInstance();
		try {
			List<MenuTypeVO> listTypeVO = mtDAO.selectTypeInfo();
			
			for (MenuTypeVO mtVO : listTypeVO) {
				Object[] row = { mtVO.getTypeCode(), mtVO.getTypeName() };
				dtmTypeList.addRow(row);
			}//end for
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//selectAllType
	
	public void close() {
		
		 mtd.dispose();
		 
	}//close
		 
	@Override
	public void windowClosing(WindowEvent e) {
		
			close();
				
	}
	
}//class
