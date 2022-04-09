package com.dodo.utility;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;


public class Utility {

	private static final String FORMAT = "%.2f";


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

	@SuppressWarnings("unchecked")
	public static <T> T[] getArray(final String entity, final Class<T> c) {

		if (entity == null)
			return null;

		GsonBuilder builder = new GsonBuilder();

		T[] array;
		T[] objArray;

		try {
			array = (T[]) Array.newInstance(c, 0);
			objArray = (T[]) builder.create().fromJson(entity, array.getClass());
		} catch (Exception e) {
			return null;
		}

		return objArray;
	}

}
