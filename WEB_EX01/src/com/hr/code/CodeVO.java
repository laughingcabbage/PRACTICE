package com.hr.code;

import com.hr.cmn.DTO;

public class CodeVO extends DTO {
	private String codeTypeId   ; //코드유형ID
	private String codeId       ; //코드ID
	private String codeNm       ; //코드명
	private String useYn        ; //사용여부
	private int    num          ; //순서
	private String regId        ; //등록자ID
	private String regDt        ; //등록일
	private String modId        ; //수정자ID
	private String modDt        ; //수정일
	
	public CodeVO(){}

	public CodeVO(String codeTypeId, String codeId, String codeNm, String useYn, int num, String regId, String regDt,
			String modId, String modDt) {
		super();
		this.codeTypeId = codeTypeId;
		this.codeId = codeId;
		this.codeNm = codeNm;
		this.useYn = useYn;
		this.num = num;
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public String getCodeTypeId() {
		return codeTypeId;
	}

	public void setCodeTypeId(String codeTypeId) {
		this.codeTypeId = codeTypeId;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeNm() {
		return codeNm;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "CodeVO [codeTypeId=" + codeTypeId + ", codeId=" + codeId + ", codeNm=" + codeNm + ", useYn=" + useYn
				+ ", num=" + num + ", regId=" + regId + ", regDt=" + regDt + ", modId=" + modId + ", modDt=" + modDt
				+ "]";
	}
	
}
