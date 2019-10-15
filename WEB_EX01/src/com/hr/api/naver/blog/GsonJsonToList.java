package com.hr.api.naver.blog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonJsonToList {

	public static void main(String[] args) {
		String jsonStr = "[{\"id\":\"james\",\"name\":\"홍길동\",\"age\":18},{\"id\":\"james01\",\"name\":\"홍길동01\",\"age\":20}]";
		
		//1.
//		Gson gson = new Gson();
//		MemberVO[] memberArray = gson.fromJson(jsonStr, MemberVO[].class);
//		List<MemberVO> list = Arrays.asList(memberArray);
//		for(MemberVO vo : list){
//			System.out.println(vo);
//		}
//		
		//2.
		List<MemberVO> mList = new ArrayList<>();
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonStr);
		for(int i=0; i<jsonArray.size(); i++){
			MemberVO vo = new MemberVO();
			JsonObject obj = (JsonObject) jsonArray.get(i);
			
			//System.out.println(obj.get("id"));
			
			String id = obj.get("id").getAsString();
			String name = obj.get("name").getAsString();
			int age = obj.get("age").getAsInt();
			
			vo.setId(id);
			vo.setName(name);
			vo.setAge(age);
			
			mList.add(vo);
		}
		
		for(MemberVO vo : mList){
			System.out.println(vo);
		}
	}

}
