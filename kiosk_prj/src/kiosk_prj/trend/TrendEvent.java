package kiosk_prj.trend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TrendEvent extends WindowAdapter implements ActionListener, WindowListener{
	private TrendDesign td;
	
	public TrendEvent(TrendDesign td) {
		this.td = td;
		trendCheck();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == td.getExit()) {
			td.dispose();
		}
		
	}
//	public void trendCheck() {
//		
//		TrendDAO tDAO = TrendDAO.getInstance();
//		try {
//			
//			List<TrendVO> list = tDAO.searchTrend();
//			TrendVO tVO = new TrendVO();
//			int price = 0;
//			
//			StringBuilder sb = new StringBuilder();
//			for(int i = 0; i < list.size(); i++) {
//				tVO = list.get(i);
//				String menuName = tVO.getMenuName();
//				sb.append(menuName).append("\n");
//			}
//			td.getTrendArea().setText(sb.toString());
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		
//	}
	public void trendCheck() {
	    TrendDAO tDAO = TrendDAO.getInstance();
	    try {
	        List<TrendVO> list = tDAO.searchTrend();
	        
	        // 각 메뉴 이름의 발생 횟수를 저장하기 위한 Map
	        Map<String, Integer> menuCountMap = new HashMap<>();
	        
	        // 각 메뉴 이름의 발생 횟수 계산
	        for (TrendVO tVO : list) {
	            String menuName = tVO.getMenuName();
	            menuCountMap.put(menuName, menuCountMap.getOrDefault(menuName, 0) + 1);
	        }
	        
	        // Map을 값으로 내림차순 정렬
	        List<Map.Entry<String, Integer>> sortedMenuCounts = new ArrayList<>(menuCountMap.entrySet());
	        Collections.sort(sortedMenuCounts, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));
	        
	        // 결과를 담을 StringBuilder
	        StringBuilder sb = new StringBuilder();
	        
	        // 상위 3개의 요소만 StringBuilder에 추가
	        int count = 0;
	        for (Map.Entry<String, Integer> entry : sortedMenuCounts) {
	            String menuName = entry.getKey();
	            int menuCount = entry.getValue();
	            sb.append(count + 1 + "위 ").append(menuName).append(" (").append(menuCount).append("개)\n");
	            count++;
	        }
	        
	        // 결과를 JTextArea에 설정
	        td.getTrendArea().setText(sb.toString());
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}
