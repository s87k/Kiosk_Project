package kiosk_prj.manumanage;

public class MenuTypeVO {
	private String typeCode,typeName;

	public MenuTypeVO() {
		
	}
	
	public MenuTypeVO(String typeCode, String typeName) {
		this.typeCode = typeCode;
		this.typeName = typeName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "MenuTypeVO [typeCode=" + typeCode + ", typeName=" + typeName + "]";
	}
	
	
}//class
