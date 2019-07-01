package com.neo.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.app.dao.ws.FuncRepository;
import com.neo.app.utils.AlgorithmHash;

@Service
public class FuncServiceWS {

	@Autowired
	private FuncRepository funcRepository;

	private Environment env;
	private ObjectMapper mapper;

	public FuncServiceWS() {
		super();
	}

	@Autowired
	public FuncServiceWS(Environment env,ObjectMapper mapper) {
		super();
		this.env = env;
		this.mapper = mapper;
	}
	
	//# getter - setter
	public FuncRepository getFuncRepository() {
		return funcRepository;
	}

	public void setFuncRepository(FuncRepository funcRepository) {
		this.funcRepository = funcRepository;
	}

	public Environment getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}
	//# end getter - setter
	
	// #login fold
	public <Type> Type login(final TypeReference<Type> type,String username, String password) {
		//cai tien thuat toan ma hoa o day SHA512
		password = AlgorithmHash.encrypt(env.getProperty("HASH_PASS").trim(), password);
		return funcRepository.login(type,username, password);
	}
	// #end login fold
	
	
	// #qry fold
	public <T> T qryListModel(final TypeReference<T> type, Map<Object, Object> params, String token) {
		return funcRepository.qryListModel(type, params, token);
	}

	// # end qry fold

	// #ref fold
	public <T> T refListModel(final TypeReference<T> type, Map<Object, Object> params, String token) {
		return funcRepository.refListModel(type, params, token);
	}
	// # end ref fold

	// # val fold
	public Object valPostParam(Map<Object, Object> params, String token) {
		return funcRepository.valPostParam(params, token);
	}

	public Object valPostBody(Map<Object, Object> params, String token) {
		return funcRepository.valPostBody(params, token);
	}
	// #end val fold

	// # val fold
	public Object updateParam(Map<Object, Object> params, String token) {
		return funcRepository.updateParam(params, token);
	}

	public Object updateBody(Map<Object, Object> params, String token) {
		return funcRepository.updateBody(params, token);
	}
	// #end val fold

	// #convert object to map
	// we usually use model object for saving or updating to database, therefore
	// just
	// check when is inserted or updated
	@SuppressWarnings("unchecked")
	public <Type> Type convertModelToMap(Type type, String token) {
		Type t = null;
		Map<Object, Object> params = mapper.convertValue(type, Map.class);
		t = (Type) funcRepository.updateParam(params, token);
		return t;
	}
	// #end convert object to map
}
