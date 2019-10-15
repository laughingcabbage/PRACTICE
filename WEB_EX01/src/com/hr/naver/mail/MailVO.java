package com.hr.naver.mail;

import com.hr.cmn.DTO;

public class MailVO extends DTO {
	private String mailTo;
	private String title;
	private String contents;
	
	public MailVO(){}

	public MailVO(String mailTo, String title, String contents) {
		super();
		this.mailTo = mailTo;
		this.title = title;
		this.contents = contents;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "MailVO [mailTo=" + mailTo + ", title=" + title + ", contents=" + contents + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
