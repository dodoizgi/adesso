package com.dodo.utility;


import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

import com.dodo.model.Countries;
import com.dodo.model.Country;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;


public class Utility {

	private static final String FORMAT = "%.2f";

	/**
	 * Function: setFullscreen()
	 */
	public static void setFullscreen(Activity act) {

		act.requestWindowFeature(Window.FEATURE_NO_TITLE);
		act.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	/**
	 * Function: KeepActivityOnScreen()
	 */
	public static void KeepActivityOnScreen(Activity activity) {

		activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	/**
	 * Function: DisableKeyGuard()
	 */
	public static void DisableKeyGuard(Activity activity) {

		activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
	}

	/**
	 * Function: DisableKeyboard()
	 */
	public static void DisableKeyboard(Activity activity) {

		activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	/**
	 * Function: convertJson()
	 */
	public static <T> String convertJson(T model) {

		GsonBuilder builder = new GsonBuilder();
		return builder.create().toJson(model);
	}

	/**
	 * Function: convertJson()
	 */
	public static <T> String convertJsonWithExcludedClass(T model, final Class<?> t) {

		GsonBuilder builder = new GsonBuilder();
		builder.addSerializationExclusionStrategy(new ExclusionStrategy() {

			@Override
			public boolean shouldSkipField(FieldAttributes arg0) {
				return false;
			}

			@Override
			public boolean shouldSkipClass(Class<?> arg0) {

				return t == arg0;
			}
		});

		return builder.create().toJson(model);
	}

	/**
	 * Function: convertJson()
	 */
	public static <T> String convertJson(T[] t) {

		if (t == null)
			return null;

		Gson builder = new Gson();
		return builder.toJson(t);
	}


	/**
	 * Function: getArray()
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Countries> getArray(final String responseBody)throws JSONException {

			ArrayList<Countries> countries = new ArrayList<>();
			JSONObject jsonObject = new JSONObject(responseBody);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			for (int i=0; i< jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				Countries country = new Countries();

				String code = object.getString("code");
				String currencyCodes = object.getString("currencyCodes");
				String name = object.getString("name");
				String wikiDataId = object.getString("wikiDataId");

				country.setCode(code);
				country.setCurrencyCodes(Collections.singletonList(currencyCodes));
				country.setWikiDataId(wikiDataId);
				country.setName(name);

				countries.add(country);

				System.out.println("code : " + code + " currencyCodes : " + currencyCodes +
						" name : " + name + " wiki : " + wikiDataId);
			}

			System.out.println("countries : " + countries);



		return countries;
	}

	/**
	 * Function: getArray()
	 */
	@SuppressWarnings("unchecked")
	public static Country getCountry(final String responseBody)throws JSONException {

		JSONObject object = new JSONObject(responseBody.substring(responseBody.indexOf("{"),responseBody.lastIndexOf("}")+1));
		JSONArray jsonArray = object.toJSONArray(object.names());
		Country country = new Country();
		object = jsonArray.getJSONObject(0);

		String capital = object.getString("capital");
		String code = object.getString("code");
		String callingCode = object.getString("callingCode");
		String flagImageUri = object.getString("flagImageUri");
		String numRegions = object.getString("numRegions");
		String currencyCodes = object.getString("currencyCodes");
		String name = object.getString("name");
		String wikiDataId = object.getString("wikiDataId");

		country.setNumRegions(numRegions);
		country.setFlagImageUri(flagImageUri);
		country.setCapital(capital);
		country.setCallingCode(callingCode);
		country.setCode(code);
		country.setCurrencyCodes(Collections.singletonList(currencyCodes));
		country.setWikiDataId(wikiDataId);
		country.setName(name);

		System.out.println("code : " + code + " currencyCodes : " + currencyCodes +
				" name : " + name + " wiki : " + wikiDataId);

		return country;
	}
}
