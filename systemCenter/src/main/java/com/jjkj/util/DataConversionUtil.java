package com.jjkj.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.ListOrderedMap;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DataConversionUtil {

	/*
	 * 【xml数据流-->Map】 dom4j 1.6.1 将HTTP請求的XML数据流数据格式转换为Map格式
	 * 
	 * @author yc
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream ins = null;
		try {
			ins = request.getInputStream();
		} catch (IOException e) {

			e.printStackTrace();
		}
		Document docment = null;
		try {
			docment = reader.read(ins);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = docment.getRootElement();
		List<Element> list = root.elements();
		for (Element ele : list) {
			map.put(ele.getName(), ele.getText());
		}
		return map;
	}

	/*
	 * 【xml的String字符串-->Map】 xml字符串转换为Map 支持简单一级
	 * 
	 * @author yc
	 */
	public static Map<String, String> StrXmlTOMap(String strXml) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		Document docment = DocumentHelper.parseText(strXml);
		Element root = docment.getRootElement();
		List<Element> list = root.elements();
		for (Element ele : list) {
			map.put(ele.getName(), ele.getText());
		}
		return map;
	}

	/*
	 * 【Map-->Xml字符串数据流】 Map转换成Xml
	 * 
	 * @author yc
	 */
	public static String MapToXml(Map<String, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/*
	 * =============================================================================
	 * ===================== JSONxiang'guanxiang相关
	 */

	/*
	 * map转换json】 详细说明
	 * @param map 集合
	 * @return
	 * @return String json字符串
	 * @throws
	 * @author yc
	 */
	public static String mapToJson(Map<String, String> map) {
		Set<String> keys = map.keySet();
		String key = "";
		String value = "";
		StringBuffer jsonBuffer = new StringBuffer();
		jsonBuffer.append("{");
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			key = (String) it.next();
			value = map.get(key);
			jsonBuffer.append(key + ":" + "\"" + value + "\"");
			if (it.hasNext()) {
				jsonBuffer.append(",");
			}
		}
		jsonBuffer.append("}");
		return jsonBuffer.toString();
	}

	/*
	 * 【json字符串转map|{a:"a",b:"b"}】
	 * json转换map
	 * @param jsonStr json字符串
	 * @return
	 * @return Map<String,Object> 集合
	 * @throws
	 * @author yc
	 */
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		ListOrderedMap map = new ListOrderedMap();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}
	
	/*
	 * 【http的url转Map】
	 * 通过HTTP获取JSON数据. <br>
	 * 通过HTTP获取JSON数据返回map
	 * @param url 链接
	 * @return
	 * @return Map<String,Object> 集合
	 * @throws
	 * @author yc
	 */
	public static Map<String, Object> getMapByUrl(String url) {
		try {
			// 通过HTTP获取JSON数据
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return parseJSON2Map(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 【json字符串转list| json字符串有相关格式要求["":{"":""}]】
	 * json转换list
	 * 详细说明
	 * @param jsonStr json字符串
	 * @return
	 * @return List<Map<String,Object>> list
	 * @throws 
	 * @author yc
	 */
	public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(parseJSON2Map(json2.toString()));
		}
		return list;
	}

	/*
	 * 【url转list】
	 * 通过HTTP获取JSON数据. <br>
	 * 通过HTTP获取JSON数据返回list
	 * @param url 链接
	 * @return
	 * @return List<Map<String,Object>> list
	 * @throws 
	 * @author yc
	 */
	public static List<Map<String, Object>> getListByUrl(String url) {
		try {
			// 通过HTTP获取JSON数据
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return parseJSON2List(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

	public static void main(String[] args) throws Exception {
		String s = "<xml><a>aaa</a></xml>";
		System.out.println(StrXmlTOMap(s));
		Map map = new HashMap<>();
		map.put("b", "bb");
		map.put("a", "a");
		System.out.println(MapToXml(map));
		System.out.println(mapToJson(map));
		String jsonstr = mapToJson(map);
//		System.out.println(parseJSON2List(jsonstr));
		System.out.println(parseJSON2Map(jsonstr));

	}

}
