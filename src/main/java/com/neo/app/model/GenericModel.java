package com.neo.app.model;

public class GenericModel<T> {

	private T object;

	public GenericModel(Class<T> tclass) throws InstantiationException, IllegalAccessException {
		this.object = (T) tclass.newInstance();
	}

	public T getObject() {
		return this.object;
	}
}
