package com.dodo.utility;


import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;


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
