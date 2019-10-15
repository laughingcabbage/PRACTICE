package com.hr.cmn;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int maxNum      = 1001;
		int currPageNo  = 1;
		int rowPerPage  = 10;
		int bottomCount = 10;
		String url = "member.do";
		String scriptName = "doSearchPage";
		System.out.println(StringUtil.renderPaging(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName));
	}

}
