package kiosk_prj.membership;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ConsumePatternEvent extends WindowAdapter implements ActionListener {
	private ConsumePatternDesign cspd;
	
	public ConsumePatternEvent(ConsumePatternDesign cspd) {
		this.cspd = cspd;
		setConsumePattern(cspd.getPhoneNum());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cspd.getBack()) {
			cspd.dispose();
		}
	}
	public void setConsumePattern(String phoneNum) {
		ConsumePatternDAO cpDAO = ConsumePatternDAO.getInstance();

		try {
			List<ConsumePatternVO> consumePattern = cpDAO.searchConusmePattern(phoneNum);
			// 모델 객체 값 설정
			DefaultTableModel dtm = cspd.getDtmConsumePattern();
			// 값 설정 전 모델 초기화
			dtm.setRowCount(0);

			String[] rowData = null;
			ConsumePatternVO cpVO = null;
			
			// 판매량의 총합을 저장할 변수 초기화
			int totalCounts = 0;
			int totalSales = 0;
			
			// 판매량의 총합 계산
			for (int i = 0; i < consumePattern.size(); i++) {
				cpVO = consumePattern.get(i);
				totalCounts += cpVO.getSalesCount();
			}
			
			// 판매액의 총합 계산
			for (int i = 0; i < consumePattern.size(); i++) {
				cpVO = consumePattern.get(i);
				totalSales += cpVO.getAmount();
			}
			
			// 판매량 비율 계산 및 rowData 배열에 추가
			for (int i = 0; i < consumePattern.size(); i++) {
				cpVO = consumePattern.get(i);
				rowData = new String[6];
				// 1. 번호
				rowData[0] = "" + (i + 1) + "";
				// 2. 메뉴명
				rowData[1] = cpVO.getMenuName();
				// 3. 판매량
				rowData[2] = "" + cpVO.getSalesCount();
				// 4. 판매비율 계산
				double countsRatio = ((double) cpVO.getSalesCount() / totalCounts) * 100;
				rowData[3] = String.format("%.2f", countsRatio);
				// 5. 판매액
				rowData[4] = "" + cpVO.getAmount();
				// 6. 판매액 비율
				double salesRatio = ((double) cpVO.getAmount() / totalSales) * 100;
				rowData[5] = String.format("%.2f", salesRatio);
				dtm.addRow(rowData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
