package com.hr.upload;

import com.hr.cmn.DTO;

public class FileVO extends DTO {

	private String fileId      ; //파일ID
	private String fNum        ; //순번
	private String orgFileNm   ; //원본파일명
	private String saveFineNm  ; //저장파일명
	private long   fSize       ; //파일사이즈
	private String ext         ; //확장자
	private String regDt       ; //등록일
	private String regId       ; //등록자ID
		
	public FileVO(){}
	
	public FileVO(String fileId, String fNum, String orgFileNm, String saveFineNm, long fSize, String ext, String regDt,
			String regId) {
		super();
		this.fileId = fileId;
		this.fNum = fNum;
		this.orgFileNm = orgFileNm;
		this.saveFineNm = saveFineNm;
		this.fSize = fSize;
		this.ext = ext;
		this.regDt = regDt;
		this.regId = regId;
	}
	public String getFileId() {
		return fileId;
	}
	public String getfNum() {
		return fNum;
	}
	public void setfNum(String fNum) {
		this.fNum = fNum;
	}
	public String getOrgFileNm() {
		return orgFileNm;
	}
	public void setOrgFileNm(String orgFileNm) {
		this.orgFileNm = orgFileNm;
	}
	public String getSaveFineNm() {
		return saveFineNm;
	}
	public void setSaveFineNm(String saveFineNm) {
		this.saveFineNm = saveFineNm;
	}
	public long getfSize() {
		return fSize;
	}
	public void setfSize(long fSize) {
		this.fSize = fSize;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	@Override
	public String toString() {
		return "FileVO [fileId=" + fileId + ", fNum=" + fNum + ", orgFileNm=" + orgFileNm + ", saveFineNm=" + saveFineNm
				+ ", fSize=" + fSize + ", ext=" + ext + ", regDt=" + regDt + ", regId=" + regId + ", toString()="
				+ super.toString() + "]";
	}
	
}
