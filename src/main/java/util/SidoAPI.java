package util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import data.sido.SidoResponse;
import data.sido.SidoResponseResult;

public class SidoAPI {
	public static SidoResponse getSidos() {
		try {
			String target = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/sido";
			Map<String, String> params = new HashMap<>();
			params.put("serviceKey", "i4T0c6Bc1OdrNu%2F%2BO9hce12G%2FrIkoduK6OgJLPlRW%2BCfWpR79vQ6IvDuSGXSl%2FP1pp28qAlxa5is5RZdwF91jw%3D%3D");
			params.put("_type", "json");
			params.put("numOfRows", "17");
			String queryString = QueryStringBuilder.bulid(params);
			URI uri = new URI(target+"?"+queryString);
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			Gson gson = new Gson();
			SidoResponseResult responseResult = gson.fromJson(response.body(), SidoResponseResult.class);
			
			return responseResult.getResponse();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
}
