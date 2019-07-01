package test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.neo.app.dao.ws.FuncRepository;
import com.neo.app.model.UserInfo;

public class Test {
	public static void main(String[] args) {
		FuncRepository func = new FuncRepository();

		// Map<Object, Object> params = new HashMap<>();
		// params.put("constr", "search_1");
		// params.put("pname", "Quản");

		// func.qryString(params,"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkQXQiOjE1NTgzNzQ1NjMsInVzZXJJZCI6InRoYW5ncGgifQ.zJ5vPwhhKxJb3P4XiUEuyLgUoGI7RU1FYHsBtmCos-E");
		// String list2 = func.qryListModel(new TypeReference<String>() {
		// },params,
		// "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkQXQiOjE1NTgzNzQ1NjMsInVzZXJJZCI6InRoYW5ncGgifQ.zJ5vPwhhKxJb3P4XiUEuyLgUoGI7RU1FYHsBtmCos-E");

		// List<UserInfo> list2 = func.qryListModel(new TypeReference<List<UserInfo>>()
		// {
		// },params,
		// "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkQXQiOjE1NTgzNzQ1NjMsInVzZXJJZCI6InRoYW5ncGgifQ.zJ5vPwhhKxJb3P4XiUEuyLgUoGI7RU1FYHsBtmCos-E");

		// List<LinkedHashMap<Object, Object>> list2 = func.refListModel(new
		// TypeReference<List<LinkedHashMap<Object, Object>>>() {
		// },params,
		// "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkQXQiOjE1NTgzNzQ1NjMsInVzZXJJZCI6InRoYW5ncGgifQ.zJ5vPwhhKxJb3P4XiUEuyLgUoGI7RU1FYHsBtmCos-E");
		//
		// System.out.println(list2);

		Map<Object, Object> params = new HashMap<>();
		params.put("constr", "search_1");
		params.put("pa", "Qun");
		params.put("pb", "Qun");
		params.put("pc", "Qun");
		params.put("pd", "Qun");
		Object list2 = func.valPostParam(params,
				"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkQXQiOjE1NTgzNzQ1NjMsInVzZXJJZCI6InRoYW5ncGgifQ.zJ5vPwhhKxJb3P4XiUEuyLgUoGI7RU1FYHsBtmCos-E");

		System.out.println(list2);

		// TypeReference<String> ref = new TypeReference<String>(){};
		// System.out.println(ref.getType().getTypeName().equals("java.lang.String"));

		// System.out.println(list2.size());
		// for(LinkedHashMap<Object, Object> item : list2) {
		// for(Map.Entry<Object, Object> entry : item.entrySet()) {
		// System.out.println(entry.getKey()+"|"+entry.getValue());
		// }
		// }
		// for(UserInfo u : list2) {
		// System.out.println(u.getId());
		// }
		// System.out.println(u);
	}
}
