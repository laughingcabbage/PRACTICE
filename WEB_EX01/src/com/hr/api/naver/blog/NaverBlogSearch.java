package com.hr.api.naver.blog;
import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.hr.api.naver.blog.domain.Channel;
import com.hr.api.naver.blog.domain.Item;

public class NaverBlogSearch {

	public static void main(String[] args) {
		String clientId 	= "L93EmFgi2coGlqsTsJYy";
		String clientSecret = "G9UmoHcTYg";
		
		try{
			String text   = URLEncoder.encode("홍대", "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/blog?&display=20&query="+text;     //JSON
			//String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+text; //XML
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			
			int responseCode = con.getResponseCode();
			//System.out.println(responseCode);
			
			BufferedReader br;
			
			if(responseCode==200){
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else{
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			
			String line;
			StringBuilder sb = new StringBuilder();
			
			while((line=br.readLine())!=null){
				sb.append(line);
			}
			
			br.close();
			System.out.println(sb.toString());
			
			Gson gson = new Gson();
			Channel channel = gson.fromJson(sb.toString(), Channel.class);
			for(Item item : channel.getItems()){
				System.out.println(item);
			}

		}catch(Exception e){
			
		}finally{
			
		}
		
	}

}
