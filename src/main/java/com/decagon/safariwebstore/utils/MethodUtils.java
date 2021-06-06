package com.decagon.safariwebstore.utils;

import com.decagon.safariwebstore.model.ProductPage;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

public class MethodUtils {

	private MethodUtils() {
	}
	
	public static String prepareErrorJSON(HttpStatus status, Exception ex) {
		JSONObject errorJSON = new JSONObject();
		try {
			errorJSON.put("status", status.value());
			errorJSON.put("error", status.getReasonPhrase());
			errorJSON.put("message", ex.getMessage());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return errorJSON.toString();
	}

	public static Pageable getPageable(ProductPage productPage) {
		Sort sort = Sort.by(productPage.getSortDirection(), productPage.getSortBy());
		return PageRequest.of(productPage.getPageNumber(), productPage.getPageSize(), sort);
	}
}
