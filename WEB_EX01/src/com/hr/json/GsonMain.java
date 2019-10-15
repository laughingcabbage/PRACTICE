package com.hr.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonMain {

	public static void main(String[] args) {
		
		String jsonStr = "{\"name\":\"james\", \"lang\":1.8}";
		JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
		System.out.println(jsonObject.get("name").getAsString());
		System.out.println(jsonObject.get("lang"));
	}
}
