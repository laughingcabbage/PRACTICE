package com.hr.board;

import com.hr.cmn.DTO;

public class BoardVO extends DTO {
	private String seq;			//게시글_ID
	private String title;		//제목
	private String readCnt;		//조회수
	private String contents;	//내용
	private String regId;		//등록자ID
	private String regDt;		//등록일
	
	/**
	 * 
	 * <PRE>
	 * 1. MethodName : BoardVO
	 * 2. ClassName  : BoardVO
	 * 3. Comment   : 
	 * 4. 작성자    : sist
	 * 5. 작성일    : 2019. 6. 27. 오후 2:12:44
	 * </PRE>
	 */
	public BoardVO(){}

	/**
	 * 
	 * <PRE>
	 * 1. MethodName : BoardVO
	 * 2. ClassName  : BoardVO
	 * 3. Comment   : 
	 * 4. 작성자    : sist
	 * 5. 작성일    : 2019. 6. 27. 오후 2:12:37
	 * </PRE>
	 *   @param seq
	 *   @param title
	 *   @param readCnt
	 *   @param contents
	 *   @param regId
	 *   @param regDt
	 */
	public BoardVO(String seq, String title, String readCnt, String contents, String regId, String regDt) {
		super();
		this.seq = seq;
		this.title = title;
		this.readCnt = readCnt;
		this.contents = contents;
		this.regId = regId;
		this.regDt = regDt;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(String readCnt) {
		this.readCnt = readCnt;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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

	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", readCnt=" + readCnt + ", contents=" + contents
				+ ", regId=" + regId + ", regDt=" + regDt + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((readCnt == null) ? 0 : readCnt.hashCode());
		result = prime * result + ((regId == null) ? 0 : regId.hashCode());
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardVO other = (BoardVO) obj;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (readCnt == null) {
			if (other.readCnt != null)
				return false;
		} else if (!readCnt.equals(other.readCnt))
			return false;
		if (regId == null) {
			if (other.regId != null)
				return false;
		} else if (!regId.equals(other.regId))
			return false;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	

}
