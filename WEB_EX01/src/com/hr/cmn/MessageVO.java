package com.hr.cmn;

public class MessageVO extends DTO {

	private String msgId;
	private String msgContents;
	
	//msgId >0 : 성공
	//msgId<=0 : 실패
	
	public MessageVO(){}

	public MessageVO(String msgId, String msgContents) {
		super();
		this.msgId = msgId;
		this.msgContents = msgContents;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgContents() {
		return msgContents;
	}

	public void setMsgContents(String msgContents) {
		this.msgContents = msgContents;
	}

	@Override
	public String toString() {
		return "MessageVO [msgId=" + msgId + ", msgContents=" + msgContents + ", toString()=" + super.toString() + "]";
	}

	
}
