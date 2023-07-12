package util;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import data.address.AddressDocument;
import data.address.AddressResponseResult;

/*
 * 주소에 해당하는 상세 값들을 얻어올 때 사용할 API
 */
public class AddressAPI {
	public static AddressDocument getAddress(String query) {
		try {
			String target = "https://dapi.kakao.com/v2/local/search/address";
			
			String queryString = "query="+URLEncoder.encode(query, "utf-8");
			
			URI uri = new URI(target+"?"+queryString);
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(uri)
					.header("Authorization", "KakaoAK f1e1a6a0e227c4a576a3285d9db06b7a").GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			Gson gson = new Gson();
//			System.out.println(response.body());
			AddressResponseResult responseResult = gson.fromJson(response.body(), AddressResponseResult.class);
			
			return responseResult.getDocuments()[0];
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
