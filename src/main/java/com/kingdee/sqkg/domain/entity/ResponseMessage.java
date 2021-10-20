package com.kingdee.sqkg.domain.entity;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseMessage {
	private String L_RET_STATUS;
	private String L_RET_ERROR_CODE;
	private String L_RET_MESSAGE;
    private String FStatus;
    private String ATTRIBUTE1;
	private String  LoginName;
	public String getLoginName() {
		return LoginName;
	}
	public void setLoginName(String LoginName) {
		LoginName = LoginName;
	}
	public String getFStatus() {
		return FStatus;
	}
	public String getATTRIBUTE1() {
		return ATTRIBUTE1;
	}
	public void setFStatus(String fStatus) {
		FStatus = fStatus;
	}
	public void setATTRIBUTE1(String aTTRIBUTE1) {
		ATTRIBUTE1 = aTTRIBUTE1;
	}
	public String getL_RET_STATUS() {
		return L_RET_STATUS;
	}
	public String getL_RET_ERROR_CODE() {
		return L_RET_ERROR_CODE;
	}
	public String getL_RET_MESSAGE() {
		return L_RET_MESSAGE;
	}

	public void setL_RET_STATUS(String l_RET_STATUS) {
		L_RET_STATUS = l_RET_STATUS;
	}
	public void setL_RET_ERROR_CODE(String l_RET_ERROR_CODE) {
		L_RET_ERROR_CODE = l_RET_ERROR_CODE;
	}
	public void setL_RET_MESSAGE(String l_RET_MESSAGE) {
		L_RET_MESSAGE = l_RET_MESSAGE;
	}

	public static ResponseMessage fail(String code, String msg, String L_RET_STATUS,String LoginName) {
		ResponseMessage r = new ResponseMessage();
		r.setL_RET_STATUS(L_RET_STATUS);
		r.setL_RET_MESSAGE(msg);
		r.setL_RET_ERROR_CODE("201");
		r.setLoginName(LoginName);
		return r;
	}
	public static ResponseMessage fail( String L_RET_MESSAGE) {
		ResponseMessage r = new ResponseMessage();
		r.setL_RET_STATUS("E");
		r.setL_RET_MESSAGE(L_RET_MESSAGE);
		r.setL_RET_ERROR_CODE("201");
		return r;
	}
	@Override
	public String toString() {
		return "ResponseMessage{" +
				"L_RET_STATUS='" + L_RET_STATUS + '\'' +
				", L_RET_ERROR_CODE='" + L_RET_ERROR_CODE + '\'' +
				", L_RET_MESSAGE='" + L_RET_MESSAGE + '\'' +
				", FStatus='" + FStatus + '\'' +
				", ATTRIBUTE1='" + ATTRIBUTE1 + '\'' +
				", LoginName='" + LoginName + '\'' +
				'}';
	}

	public static ResponseMessage succ(String  LoginName) {
		ResponseMessage r = new ResponseMessage();
		r.setL_RET_STATUS("S");
		r.setL_RET_MESSAGE("执行成功");
		r.setL_RET_ERROR_CODE("200");
		r.setLoginName(LoginName);
		return r;
	}

	public static ResponseMessage succ() {
		ResponseMessage r = new ResponseMessage();
		r.setL_RET_STATUS("S");
		r.setL_RET_MESSAGE("执行成功");
		r.setL_RET_ERROR_CODE("200");
		return r;
	}
	public static ResponseMessage succ(String l_RET_MESSAGE,String  LoginName) {
		ResponseMessage r = new ResponseMessage();
		r.setL_RET_STATUS("S");
		r.setL_RET_MESSAGE(l_RET_MESSAGE);
		r.setL_RET_ERROR_CODE("200");
		return r;
	}

}
