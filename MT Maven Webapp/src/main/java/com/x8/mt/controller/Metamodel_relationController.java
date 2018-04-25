package com.x8.mt.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.x8.mt.common.GlobalMethodAndParams;
import com.x8.mt.common.Log;
import com.x8.mt.entity.Metamodel_relation;
import com.x8.mt.service.Metamodel_hierarchyService;
import com.x8.mt.service.Metamodel_relationService;

@CrossOrigin(origins = "*", maxAge = 3600)
// 解决跨域请求的注解
@Controller
@RequestMapping(value = "/metamodel_relation")
public class Metamodel_relationController {

	@Resource
	Metamodel_relationService metamodel_relationService;
	@Resource
	Metamodel_hierarchyService metamodel_hierarchyService;

	/**
	 * 
	 * 作者:itcoder 
	 * 时间:2018年4月10日 
	 * 作用:获得所有的依赖关系 
	 * 参数：元模型id 即metamodelid
	 */
	@RequestMapping(value = "/getMetamodel_relationDEPENDENCY", method = RequestMethod.POST)
	@ResponseBody
	@Log(operationType = "getMetamodel_relationDEPENDENCY", operationDesc = "获得所有的依赖关系")
	public JSONObject getMetamodel_relationDEPENDENCY(
			HttpServletRequest request, HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		JSONObject responsejson = new JSONObject();

		// if(!GlobalMethodAndParams.checkLogin()){
		// responsejson.put("result", false);
		// responsejson.put("count",0);
		// return responsejson;
		// }
		GlobalMethodAndParams.setHttpServletResponse(request, response);

		if (!map.containsKey("metamodelid")
				|| map.get("metamodelid").toString().trim().equals("")) {
			responsejson.put("result", false);
			responsejson.put("count", 0);
			return responsejson;
		}

		JSONArray data = new JSONArray();
		int metamodelid = 0;
		String metamodelidStr = map.get("metamodelid").toString().trim();
		try {
			metamodelid = Integer.parseInt(metamodelidStr);
			List<Metamodel_relation> dependencyRelationList = metamodel_relationService
					.getDependencyRelationByMetamodelid(metamodelid);

			for (Metamodel_relation metamodel_relation : dependencyRelationList) {
				JSONObject json = new JSONObject();
				json.put("metamodelid",
						metamodel_relation.getRelatedmetamodelid());
				json.put(
						"metamodelname",
						metamodel_hierarchyService.getMetamodel_hierarchyById(
								metamodel_relation.getRelatedmetamodelid())
								.getName());
				data.add(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		responsejson.put("result", true);
		responsejson.put("data", data);
		responsejson.put("count", 1);
		return responsejson;
	}

	/**
	 * 
	 * 作者:itcoder 
	 * 时间:2018年4月10日 
	 * 作用:增加元模型关系
	 * 参数：sourcemetamodelid，targetmetamodelid都不能为空
	 */
	@RequestMapping(value = "/insertMetamodel_relation", method = RequestMethod.POST)
	@ResponseBody
	@Log(operationType = "insertMetamodel_relation", operationDesc = "增加元模型关系")
	public JSONObject insertMetamodel_relation(HttpServletRequest request,
			HttpServletResponse response, @RequestBody Map<String, Object> map) {
		JSONObject responsejson = new JSONObject();

		// if(!GlobalMethodAndParams.checkLogin()){
		// responsejson.put("result", false);
		// responsejson.put("count",0);
		// return responsejson;
		// }
		GlobalMethodAndParams.setHttpServletResponse(request, response);

		if (!(map.containsKey("sourcemetamodelid")
				&& map.containsKey("targetmetamodelid"))) {
			responsejson.put("result", false);
			responsejson.put("count", 0);
			return responsejson;
		}

		if (map.get("sourcemetamodelid").toString().trim().equals("")
				|| map.get("targetmetamodelid").toString().trim().equals("")) {
			responsejson.put("result", false);
			responsejson.put("count", 0);
			return responsejson;
		}

		Metamodel_relation metamodel_relation = new Metamodel_relation();
		metamodel_relation.setMetamodelid(Integer.parseInt(map.get(
				"sourcemetamodelid").toString()));
		metamodel_relation.setRelatedmetamodelid(Integer.parseInt(map.get(
				"targetmetamodelid").toString()));
		metamodel_relation
				.setType(GlobalMethodAndParams.metamodel_relation_dependency);

		boolean flag = metamodel_relationService
				.insertMetamodel_relation(metamodel_relation);

		if (flag) {
			responsejson.put("result", true);
			responsejson.put("count", 1);
			return responsejson;
		} else {
			responsejson.put("result", false);
			responsejson.put("count", 0);
			return responsejson;
		}

	}

	/**
	 * 
	 * 作者:itcoder 
	 * 时间:2018年4月10日 
	 * 作用:删除元模型关系 
	 * 参数：relationid
	 */
	@RequestMapping(value = "/deleteMetamodel_relation", method = RequestMethod.POST)
	@ResponseBody
	@Log(operationType = "deleteMetamodel_relation", operationDesc = "删除元模型关系")
	public JSONObject deleteMetamodel_relation(HttpServletRequest request,
			HttpServletResponse response, @RequestBody Map<String, Object> map) {
		JSONObject responsejson = new JSONObject();

		// if(!GlobalMethodAndParams.checkLogin()){
		// responsejson.put("result", false);
		// responsejson.put("count",0);
		// return responsejson;
		// }
		GlobalMethodAndParams.setHttpServletResponse(request, response);

		if (!(map.containsKey("relationid"))) {
			responsejson.put("result", false);
			responsejson.put("count", 0);
			return responsejson;
		}

		if (map.get("relationid").toString().trim().equals("")) {
			responsejson.put("result", false);
			responsejson.put("count", 0);
			return responsejson;
		}
		String relationidStr = map.get("relationid").toString().trim();
		int id = 0; 
		boolean flag = false;
		try {
			id = Integer.parseInt(relationidStr);
			flag = metamodel_relationService.deleteMetamodel_relation(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		if (flag) {
			responsejson.put("result", true);
			responsejson.put("count", 1);
			return responsejson;
		} else {
			responsejson.put("result", false);
			responsejson.put("count", 0);
			return responsejson;
		}

	}

}
