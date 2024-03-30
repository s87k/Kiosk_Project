package kiosk_prj.coupon.controller;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import kiosk_prj.coupon.UpdateIconImpl;
import kiosk_prj.coupon.dao.CouponKindDAO;
import kiosk_prj.coupon.view.AddCouponDesign;
import kiosk_prj.coupon.vo.CouponKindVO;
import kiosk_prj.coupon.vo.ExpirePeriod;

import static java.lang.Integer.parseInt;

public class AddCouponEvent extends WindowAdapter implements ActionListener, UpdateIconImpl{
	
	private AddCouponDesign acd;
	private int periodMonth;
	private String img;
	
	public AddCouponEvent(AddCouponDesign acd) {
		this.acd = acd;
	} // AddCouponEvent
	
	public void enablePeriodSetting(boolean flag) {
		acd.getArrJbtnPeriod()[ExpirePeriod.MONTH1.ordinal()].setEnabled(flag);
		acd.getArrJbtnPeriod()[ExpirePeriod.MONTH3.ordinal()].setEnabled(flag);
		acd.getArrJbtnPeriod()[ExpirePeriod.YEAR1.ordinal()].setEnabled(flag);
		acd.getJcbPeriodDetail().setEnabled(!flag);
	} // enablePeriodDefault
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == acd.getJrbPeriodDefault()) {	// 이용기간 - 기본 설정 JRadioButton
			enablePeriodSetting(true);
		} // end if
		if(ae.getSource() == acd.getJrbPeriodDetail()) {	// 이용기간 - 상세 설정 JRadioButton
			enablePeriodSetting(false);
		} // end if
		if(ae.getSource() == acd.getJbtnGoMain()) {			// 닫기 버튼
			closeDialog();
		} // end if
		if(ae.getSource() == acd.getJbtnAddCoupon()) {		// 등록 버튼
			addCouponKind();
		} // end if
		if(ae.getSource() == acd.getJbtnCancel()) {			// 취소 버튼
			closeDialog();
		} // end if
		if(ae.getSource() == acd.getJbtnImgLoad()) { 		// 이미지 열기 버튼
			img = loadImg(acd.getJlblImg());
		}
		if(ae.getSource() == acd.getArrJbtnPeriod()[ExpirePeriod.MONTH1.ordinal()]) {			// 이용기간 - 기본 설정 - 1개월 JButton
			changeBtnIcon(ExpirePeriod.MONTH1.ordinal());
			acd.getJcbPeriodDetail().setSelectedIndex(acd.getDcbmPeriodDetail().getIndexOf("1개월"));
			periodMonth = 1;
		} // end if
		if(ae.getSource() == acd.getArrJbtnPeriod()[ExpirePeriod.MONTH3.ordinal()]) {			// 이용기간 - 기본 설정 - 3개월 JButton
			changeBtnIcon(ExpirePeriod.MONTH3.ordinal());
			acd.getJcbPeriodDetail().setSelectedIndex(acd.getDcbmPeriodDetail().getIndexOf("3개월"));
			periodMonth = 3;
		} // end if
		if(ae.getSource() == acd.getArrJbtnPeriod()[ExpirePeriod.YEAR1.ordinal()]) {			// 이용기간 - 기본 설정 - 1년 JButton
			changeBtnIcon(ExpirePeriod.YEAR1.ordinal());
			acd.getJcbPeriodDetail().setSelectedIndex(acd.getDcbmPeriodDetail().getIndexOf("12개월"));
			periodMonth = 12;
		} // end if
		
	} // actionPerformed
	
	public void addCouponKind() {
		CouponKindVO ckVO = null;
		
		String coupKindName = acd.getJtfCouponKindName().getText().trim();
		
		if(coupKindName.equals("") || coupKindName == null) {
			JOptionPane.showMessageDialog(acd, "쿠폰 이름을 입력해주세요");
			return;
		} // end if
		if(coupKindName.length() > 31) {
			JOptionPane.showMessageDialog(acd, "쿠폰 이름은 31자 이내로 작성해주세요");
			return;
		} // end if
		
		int discount = 0;
		try {
			discount = parseInt(acd.getJtfDiscount().getText().trim());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(acd, "할인 금액을 입력해주세요");
			return;
		} // end catch
		
		int period = 0;
		if(acd.getJrbPeriodDefault().isSelected()) {
			period = periodMonth;
		} else {
			period = parseInt(acd.getDcbmPeriodDetail().getElementAt(acd.getJcbPeriodDetail().getSelectedIndex()).replace("개월", ""));
		} // end else
		if(period == 0) {
			JOptionPane.showMessageDialog(acd, "이용 기간을 입력해주세요");
			return;
		} // end if
		boolean flagPublishable = acd.getJrbPublishableOk().isSelected() == true ? true : false;
		
		ckVO = new CouponKindVO(acd.getMcd().getAmpd().getAdminId(), coupKindName, discount, period, flagPublishable, img);
		CouponKindDAO ckDAO = CouponKindDAO.getInstance();
		try {
			ckDAO.insertCoupKind(ckVO);
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(acd, "쿠폰 등록에 실패했습니다");
			se.printStackTrace();
			return;
		} // end catch
		JOptionPane.showMessageDialog(acd, "쿠폰이 정상적으로 등록되었습니다");
		
	} // publishCoupon
	
	public String loadImg(JLabel jlblImg) {
		String strImg = null;
		
		FileDialog fd = new FileDialog(acd, "파일 열기", FileDialog.LOAD);
		fd.setFile("*.jpg;*.jpeg;*.png;");
		fd.setVisible(true);
		String path = fd.getDirectory();
		String name = fd.getFile();
		if(path != null) {
			strImg = path.concat(name);
			ImageIcon iiImgCoupon = new ImageIcon(strImg);
			jlblImg.setIcon(iiImgCoupon);
		} // end if
		return strImg;
	} // loadImg
	
//	public void saveImg2Loal() {
//		FileInputStream fis = null;
//		FileOutputStream fos = null;
//		File file = null;
//		
//		byte[] data = new byte[512];
//		int dataSize = 0;
//
//		try {
//			file = new File(getClass().getClassLoader().getResource("kiosk_prj/image/coupon/").toURI());
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		file = new File(file.toString().replace("\\", "/").concat("/").concat(imgName));
//		if(file.exists()) {
//			JOptionPane.showMessageDialog(acd, "동일한 파일명을 가진 이미지가 있어 수정되어야 합니다");
//			return;
//		} // end if
//		
//		try {
//			 fis = new FileInputStream(imgPath.concat(imgName));
//			 fos = new FileOutputStream(file);
//			 while((dataSize = fis.read(data)) != -1) {
//				 fos.write(data);
//				 fos.flush();
//			 } // end while
//			 fos.flush();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(acd, "지정된 파일을 찾을 수 없습니다");
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if(fis != null) {
//				try {
//					fis.close();
//					fos.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				} // end catch
//			} // end if
//		} // end finally
//		
//	} // saveImg2Local
	
	
	public void closeDialog() {
		acd.getMcd().getMce().changeBtnIcon(-1);
		acd.dispose();
	} // closeDialog

	@Override
	public void windowClosing(WindowEvent e) {
		closeDialog();
	}

	@Override
	public void changeBtnIcon(int indexBtn) {
		ImageIcon[] arrIiBtn = acd.getArrIiBtnPeriod();
		ImageIcon[] arrIiBtnClick = acd.getArrIiBtnPeriodClick();
		JButton[] arrJbtn = acd.getArrJbtnPeriod();
		
		for(int i = 0; i < arrIiBtn.length; i++) {
			if(i == indexBtn) {
				arrJbtn[i].setIcon(arrIiBtnClick[i]);
			} else {
				arrJbtn[i].setIcon(arrIiBtn[i]);
			}
		} // end for
	} // changeBtnIcon
} // class
