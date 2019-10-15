package com.hr.api.naver.blog;

import com.google.gson.Gson;

public class GsonJsonToVo {

	public static void main(String[] args) {
		//id, name, age
		String jsonStr = "{\"id\":\"james\",\"name\":\"홍길동\",\"age\":18}";
		
		Gson gson = new Gson();
		MemberVO memberVO = gson.fromJson(jsonStr, MemberVO.class);
		
		System.out.println("=============================================");
		System.out.println("memberVO="+memberVO);
		System.out.println("=============================================");
	}
}
