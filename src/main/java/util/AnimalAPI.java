package util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;

import data.animal.AnimalItem;
import data.animal.AnimalResponse;
import data.animal.AnimalResponseResult;

public class AnimalAPI {
	public static Map<String, AnimalItem> cache;
	static {
		cache = new HashMap<>();
	}
	// OPEN API 연동해서 데이털 받아와서 파싱해서 컨트롤러로
	public static AnimalResponse getAnimals(String upkind, String upr_cd, String pageNo, String bgnde, String endde) {
		try {
			String target = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic";
			Map<String, String> params = new LinkedHashMap<>();
			params.put("serviceKey", "i4T0c6Bc1OdrNu%2F%2BO9hce12G%2FrIkoduK6OgJLPlRW%2BCfWpR79vQ6IvDuSGXSl%2FP1pp28qAlxa5is5RZdwF91jw%3D%3D");
			params.put("_type", "json");
			params.put("numOfRows", "12");
			if (upr_cd != null)params.put("upr_cd", upr_cd);
			if (upkind != null)params.put("upkind", upkind);
			if (pageNo != null)params.put("pageNo", pageNo);
			if (bgnde != null)params.put("bgnde", bgnde);
			if (endde != null)params.put("endde", endde);
			String queryString = QueryStringBuilder.bulid(params);
			
			URI uri = new URI(target+"?"+queryString);
			
			HttpClient client = HttpClient.newHttpClient();		
			HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			Gson gson = new Gson();
			AnimalResponseResult responseResult = gson.fromJson(response.body(), AnimalResponseResult.class);
			
			for (AnimalItem one : responseResult.getResponse().getBody().getItems().getItem()) {
				cache.put(one.getDesertionNo(), one);
			}
//			System.out.println("cache Size = "+cache.size());
			return responseResult.getResponse();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static AnimalItem findByDesertionNo(String no) {
		return cache.get(no);
	}
}
