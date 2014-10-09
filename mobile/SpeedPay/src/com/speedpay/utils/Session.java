package com.speedpay.utils;
import java.util.HashMap;
import java.util.Map;

public class Session {

	@SuppressWarnings("unchecked")
	private Map _objectContainer;

	private static Session session;

	// Attention here, DO NOT USE keyword 'new' to create this object.
	// Instead, use getSession method.
	@SuppressWarnings("unchecked")
	private Session() {
		_objectContainer = new HashMap();
	}

	public static Session getSession() {

		if (session == null) {
			session = new Session();
			return session;
		} else {
			return session;
		}
	}

	@SuppressWarnings("unchecked")
	public void put(Object key, Object value) {

		_objectContainer.put(key, value);
	}

	public Object get(Object key) {

		return _objectContainer.get(key);
	}

	public void cleanUpSession() {
		_objectContainer.clear();
	}

	public void remove(Object key) {
		_objectContainer.remove(key);
	}
}