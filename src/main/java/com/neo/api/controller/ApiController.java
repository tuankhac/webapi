package com.neo.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neo.api.config.SqlProperties;
import com.neo.api.model.ObjectId;
import com.neo.api.service.ObjectService;
import com.neo.api.service.TokenService;
import com.neo.api.utils.ConstantParams;

@Transactional
@RestController
@RequestMapping("/neo/gw")
// @PropertySources({ @PropertySource("classpath:static/sql.properties") })
public class ApiController {
	@Autowired
	private SqlProperties env;

	@Autowired
	private ObjectService objectService;

	@Autowired
	private TokenService tokenService;

	final String CONNECTION_STRING = "constr";
	final String PARAM = ".param";

	@RequestMapping("/")
	@ResponseBody
	public String welcome() {
		return "Welcome to RestTemplate Example.";
	}

	@RequestMapping(value = "/api/{path}")
	public void apiGateway(@PathVariable("path") String path, HttpServletRequest request,
			HttpServletResponse response) {
		RequestDispatcher rd = request.getRequestDispatcher("/neo/gw/" + path);
		System.out.println("call qua api gateway" + path);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * @login
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ObjectId login(@RequestParam String username, @RequestParam String password) {
		String token = "";
		ObjectId objectId;
		objectId = objectService.login(username, password, env.getProperty("login_service"));
		if (objectId != null) {
			token = tokenService.createToken(username);
			objectId.setToken(token);
			System.out.println("tra ra day");
			return objectId;
		}
		return null;
	}

	@RequestMapping(value = "/nlogin", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ObjectId nlogin(@RequestParam String username, @RequestParam String password) {
		String token = "";
		ObjectId objectId;
		objectId = objectService.login(username, password, env.getProperty("nlogin_service"));
		if (objectId != null) {
			token = tokenService.createToken(username);
			objectId.setToken(token);
			System.out.println("tra ra day");
			return objectId;
		}
		return null;
	}

	/*
	 * @param cac cau query dang nay chu yeu xuat ra du lieu dang result set va chi
	 * la lay du lieu ra do vay method se la get va truyen param va lay ra du lieu
	 * dang resultset
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/qry", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public <Type> Type qry(@RequestParam Map<Object, Object> params) {
		System.out.println(params.toString());
		String sql = env.getProperty(params.get(CONNECTION_STRING).toString());

		Logger.getLogger("ws").info(params.toString() + "| SQL: ======>" + sql);

		String[] order = null;
		if (env.getProperty(params.get(CONNECTION_STRING) + PARAM) != null) {
			order = env.getProperty(params.get(CONNECTION_STRING) + PARAM).split(ConstantParams.SPLIT_CHARACTER);
		} else {
			order = new String[0];
		}

		List<Map<Object, Object>> list = objectService.qry(params, sql, order);
		if (list == null) {
			return null;
		} else {
			return ((Type) list);
		}
	}

	/*
	 * @param cac cau query dang nay chu yeu xuat ra du lieu dang result set va chi
	 * la lay du lieu ra do vay method se la get va truyen param va lay ra du lieu
	 * dang resultset, khac query o cho, no truyen vao cac function hay procedure
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ref", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public <Type> Type ref(@RequestParam Map<Object, Object> params) {
		System.out.println(params.toString());

		String sql = env.getProperty(params.get(CONNECTION_STRING).toString());
		Logger.getLogger("ws").info(params.toString() + "| SQL: ======>" + sql);
		String[] order = null;
		if (env.getProperty(params.get(CONNECTION_STRING) + PARAM) != null) {
			order = env.getProperty(params.get(CONNECTION_STRING) + PARAM).split(ConstantParams.SPLIT_CHARACTER);
		} else {
			order = new String[0];
		}

		List<Map<Object, Object>> list = objectService.ref(params, sql, order);
		if (list == null) {
			return null;
		} else {
			return ((Type) list);
		}
	}

	/*
	 * @param , ho tro truc tiep tra ra json
	 */
	@RequestMapping(value = "/rej", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Object rej(@RequestParam Map<Object, Object> params) {
		System.out.println(params.toString());
		String sql = env.getProperty(params.get(CONNECTION_STRING).toString());

		Logger.getLogger("ws").info(params.toString() + "| SQL: ======>" + sql);

		Object result = objectService.rej(params, sql,
				env.getProperty(params.get(CONNECTION_STRING) + PARAM).split(ConstantParams.SPLIT_CHARACTER));
		return result;
	}

	/*
	 * @param cac cau query dang nay xuat phat tu cac function hay procedure va tra
	 * ra du lieu 1 dang kieu du lieu nao do nhu vay can call va tra ra du lieu
	 */
	@RequestMapping(value = "/val1", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object valParam(@RequestParam Map<Object, Object> params) {
		Object result = null;
		System.out.println(params.toString());

		String sql = env.getProperty(params.get(CONNECTION_STRING).toString());
		Logger.getLogger("ws").info(params.toString() + "| SQL: ======>" + sql);
		String[] order = null;
		if (env.getProperty(params.get(CONNECTION_STRING) + PARAM) != null) {
			order = env.getProperty(params.get(CONNECTION_STRING) + PARAM).split(ConstantParams.SPLIT_CHARACTER);
		} else {
			order = new String[0];
		}

		// escape html character
		for (Entry<Object, Object> entry : params.entrySet()) {
			String finalString = entry.getValue().toString();
			finalString = StringUtils.replaceEach(finalString, new String[] { "&", "\"", "<", ">" },
					new String[] { "&amp;", "&quot;", "&lt;", "&gt;" });
			params.put(entry.getKey(), (Object) finalString);
		}
		result = objectService.val(params, sql, order);
		return result;
	}

	@RequestMapping(value = "/val2", method = RequestMethod.POST)
	@ResponseBody
	public Object valBody(@RequestBody Map<Object, Object> params) {
		Object result = null;
		System.out.println(params.toString());

		String sql = env.getProperty(params.get(CONNECTION_STRING).toString());
		Logger.getLogger("ws").info(params.toString() + "| SQL: ======>" + sql);
		String[] order = null;
		if (env.getProperty(params.get(CONNECTION_STRING) + PARAM) != null) {
			order = env.getProperty(params.get(CONNECTION_STRING) + PARAM).split(ConstantParams.SPLIT_CHARACTER);
		} else {
			order = new String[0];
		}

		// escape html character
		for (Entry<Object, Object> entry : params.entrySet()) {
			String finalString = entry.getValue().toString();
			finalString = StringUtils.replaceEach(finalString, new String[] { "&", "\"", "<", ">" },
					new String[] { "&amp;", "&quot;", "&lt;", "&gt;" });
			params.put(entry.getKey(), (Object) finalString);
		}
		result = objectService.val(params, sql, order);
		return result;
	}

	/*
	 * @param cac cau query dang nay la dang update, insert, delete cac bang table,
	 * nen can ban method post va truyen du lieu di
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateParam(@RequestParam Map<Object, Object> params) {
		Object result = 0;
		System.out.println(params.toString());

		String sql = env.getProperty(params.get(CONNECTION_STRING).toString());
		Logger.getLogger("ws").info(params.toString() + "| SQL: ======>" + sql);
		String[] order = null;
		if (env.getProperty(params.get(CONNECTION_STRING) + PARAM) != null) {
			order = env.getProperty(params.get(CONNECTION_STRING) + PARAM).split(ConstantParams.SPLIT_CHARACTER);
		} else {
			order = new String[0];
		}

		// escape html character
		for (Entry<Object, Object> entry : params.entrySet()) {
			String finalString = entry.getValue().toString();
			finalString = StringUtils.replaceEach(finalString, new String[] { "&", "\"", "<", ">" },
					new String[] { "&amp;", "&quot;", "&lt;", "&gt;" });
			params.put(entry.getKey(), (Object) finalString);
		}
		result = objectService.update(params, sql, order);
		return result;
	}

	/*
	 * khi 1 request ma day len dang body se vao day
	 */
	@RequestMapping(value = "/updates", method = RequestMethod.POST)
	@ResponseBody
	public Object updateBody(@RequestBody Map<Object, Object> params) {
		Object result = 0;
		System.out.println(params.toString());

		String sql = env.getProperty(params.get(CONNECTION_STRING).toString());
		Logger.getLogger("ws").info(params.toString() + "| SQL: ======>" + sql);
		String[] order = null;
		if (env.getProperty(params.get(CONNECTION_STRING) + PARAM) != null) {
			order = env.getProperty(params.get(CONNECTION_STRING) + PARAM).split(ConstantParams.SPLIT_CHARACTER);
		} else {
			order = new String[0];
		}

		// escape html character
		for (Entry<Object, Object> entry : params.entrySet()) {
			String finalString = entry.getValue().toString();
			finalString = StringUtils.replaceEach(finalString, new String[] { "&", "\"", "<", ">" },
					new String[] { "&amp;", "&quot;", "&lt;", "&gt;" });
			params.put(entry.getKey(), (Object) finalString);
		}
		result = objectService.update(params, sql, order);
		return result;
	}

	@RequestMapping(value = "/uploads", method = RequestMethod.POST)
	@ResponseBody
	public Object uploads(HttpServletRequest request, @RequestParam(name = "fileUploads") MultipartFile[] photos,
			@RequestParam Map<Object, Object> params) {
		System.out.println(params.toString());
		Logger.getLogger("ws").info("uploads:========>" + params.toString());
		Object result = 0;
		System.out.println(photos[0].getOriginalFilename());

		// upload file len server
		objectService.uploads(request, photos, params);

		if (params.get(CONNECTION_STRING) != null || !"".equals(params.get(CONNECTION_STRING))) {
			result = objectService.update(params, env.getProperty(params.get(CONNECTION_STRING).toString()),
					env.getProperty(params.get(CONNECTION_STRING) + PARAM).split(ConstantParams.SPLIT_CHARACTER));
		}

		return result;
	}

	// @RequestMapping(value = "/updates", method = RequestMethod.POST)
	// @ResponseBody
	// public Object updateBody(HttpServletRequest request,@RequestBody Map<Object,
	// Object> params) {
	// System.out.println("request.getParameter(\"pid\")" +
	// request.getParameter("pid"));
	// StringBuilder sb = new StringBuilder();
	// BufferedReader reader = null;
	//
	// InputStream is;
	// try {
	// is = request.getInputStream();
	// BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	// String buffer = null;
	// while ((buffer = br.readLine()) != null) {
	// sb.append(buffer);
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// System.out.println(sb.toString());
	// Object result = 0;
	// result = objectService.update(params,
	// env.getProperty(params.get(CONNECTION_STRING).toString()),
	// env.getProperty(params.get(CONNECTION_STRING) +
	// PARAM).split(ConstantParams.SPLIT_CHARACTER));
	// return result;
	// }

}
