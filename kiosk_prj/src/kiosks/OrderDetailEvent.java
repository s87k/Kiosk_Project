package kiosks;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kiosks.OrderDesign;
import kiosks.OrderDetailDesgin;

public class OrderDetailEvent extends WindowAdapter implements ActionListener, ItemListener {

	private OrderDetailDesgin odd;
	private OrderDesign od;
	
	private Color originalColor;
	private int count, menuPrice, totalPrice;
	private String cup, temp, shot;

	public OrderDetailEvent(OrderDetailDesgin odd, OrderDesign od) {
		this.odd = odd;
		this.od = od;
		originalColor = odd.getStoreCup().getBackground();
		// 라디오버튼의 선택 상태를 감지하는 리스너
		odd.getShotOption().addItemListener(this);
	}// OrderDetailEvent

	@Override
	public void actionPerformed(ActionEvent e) {
		// 메뉴금액 가져오기
		menuPrice = Integer.parseInt(odd.getMenuPrice().getText());
		// 수량 가져오기
		count = Integer.parseInt(odd.getJlCount().getText());

		// 컵 옵션 변경
		if (e.getSource() == odd.getStoreCup()) {
			handleCupSelection(odd.getStoreCup());
		} else if (e.getSource() == odd.getPersonalCup()) {
			handleCupSelection(odd.getPersonalCup());
		
		// 온도 옵션 변경
		} else if (e.getSource() == odd.getHot()) {
			handleTempSelection(odd.getHot());
		} else if (e.getSource() == odd.getIce()) {
			handleTempSelection(odd.getIce());
			
		// 수량 변경
		} else if (e.getSource() == odd.getMenuMinus()) {
			handleCountSelection(odd.getMenuMinus());
		} else if (e.getSource() == odd.getMenuPlus()) {
			handleCountSelection(odd.getMenuPlus());
			
		// 주문 담기
		} else if (e.getSource() == odd.getAddMenu()) {
			handleAddMenuButton();
		}
	}//actionPerformed

	// 수량 선택 처리
	private void handleCountSelection(Component countComponent) {
		//수량 빼기
		if (countComponent == odd.getMenuMinus()) {
			if (count > 1) {
				count--;
				odd.getJlCount().setText(Integer.toString(count));
				if (odd.getShotOption().isSelected()) {
					totalPrice = (menuPrice * count) + 500;
					odd.getAddMenu().setText(totalPrice + "원 담기");
				} else {
					totalPrice = menuPrice * count;
					odd.getAddMenu().setText(totalPrice + "원 담기");
				}
			}
		// 수량 더하기
		} else if (countComponent == odd.getMenuPlus()) {
			count++;
			odd.getJlCount().setText(Integer.toString(count));
			if (odd.getShotOption().isSelected()) {
				totalPrice = (menuPrice * count) + 500;
				odd.getAddMenu().setText(totalPrice + "원 담기");
			} else {
				totalPrice = menuPrice * count;
				odd.getAddMenu().setText(totalPrice + "원 담기");
			}
		}
	}

	// 컵 선택 처리
	private void handleCupSelection(Component cupComponent) {
		// 선택된 옵션의 색을 회색으로 변경
		if (cupComponent == odd.getStoreCup()) {
			odd.getStoreCup().setBackground(Color.GRAY);
			odd.getPersonalCup().setBackground(originalColor);
			cup = odd.getStoreCup().getText();
			
		} else if (cupComponent == odd.getPersonalCup()) {
			odd.getPersonalCup().setBackground(Color.GRAY);
			odd.getStoreCup().setBackground(originalColor);
			cup = odd.getPersonalCup().getText();
		}
	}

	// 온도 선택 처리
	private void handleTempSelection(Component tempComponent) {
		// 선택된 옵션의 색을 회색으로 변경
		if (tempComponent == odd.getHot()) {
			odd.getHot().setBackground(Color.GRAY);
			odd.getIce().setBackground(originalColor);
			temp = odd.getHot().getText();
			
		} else if (tempComponent == odd.getIce()) {
			odd.getIce().setBackground(Color.GRAY);
			odd.getHot().setBackground(originalColor);
			temp = odd.getIce().getText();
		}
	}

	// 주문 담기 버튼 처리
	private void handleAddMenuButton() {
		// 컵 옵션과 온도 옵션 중 하나라도 선택되지 않은 경우 메시지 다이얼로그 출력
		if ((odd.getPersonalCup().getBackground() != Color.GRAY && odd.getStoreCup().getBackground() != Color.GRAY)
				|| (odd.getHot().getBackground() != Color.GRAY && odd.getIce().getBackground() != Color.GRAY)) {
			JOptionPane.showMessageDialog(odd, "주문할 음료의 컵, 온도를 체크해주세요.", "안내", JOptionPane.INFORMATION_MESSAGE);
		} else {
			//선택된 옵션과 수량을 장바구니에 추가
			String menuName = odd.getMenuName().getText();
			StringBuilder option = new StringBuilder();
			// 샷 기본 설정
			shot = "";
			// 주문 금액 기본 설정
			totalPrice = menuPrice*count;
			option
			.append(cup)
			.append(",")
			.append(temp);
			if (odd.getShotOption().isSelected()) {
				totalPrice += 500;
				shot = "1샷 추가(+500원)";
				option.append(", ").append(shot);
			}
			Object[] rowData = { menuName, option, totalPrice };
			od.getDtmCartList().addRow(rowData);
			odd.dispose();
		}
	}

	// 라디오버튼의 선택 상태 변화를 감지하는 메서드
	@Override
	public void itemStateChanged(ItemEvent e) {
		// 선택 상태에 따라 totalPrice 업데이트
		updateTotalPrice();
	}

	// 샷 추가 변동에 따라 totalPrice 업데이트
	private void updateTotalPrice() {
		menuPrice = Integer.parseInt(odd.getMenuPrice().getText());
		count = Integer.parseInt(odd.getJlCount().getText());
		
		if (odd.getShotOption().isSelected()) {
			totalPrice = (menuPrice * count) + 500;
		} else {
			totalPrice = menuPrice * count;
		}
		odd.getAddMenu().setText(totalPrice + "원 담기");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		odd.dispose();
	}

}
