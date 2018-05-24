package com.neo.web;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neo.entity.ExaminationArrange;
import com.neo.entity.User;
import com.neo.json.AjaxJson;
import com.neo.repository.ExaminationArrangeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "ExaminationArrangeController", description = "考试编排接口")
@Controller
public class ExaminationArrangeController {

	@Resource
	ExaminationArrangeRepository examinationArrangeRepository;

	@ApiOperation(value="新增或者考试场次接口", notes="新增的时候不用传Id,修改的时候传上ID")
	@RequestMapping(value = "/addExaminationArrange", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson addExaminationArrange(@RequestBody ExaminationArrange examinationArrange,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		examinationArrangeRepository.save(examinationArrange);
		j.setMessage("新增成功");
		j.setSuccess(true);
		return j;
	}

	@ApiOperation(value="查询全部考试场次", notes="查询全部考试场次接口")
	@RequestMapping(value = "/findAllExaminationArrange", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson findAllExaminationArrange() {
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		j.setMessage("查询成功");
		j.setObject(examinationArrangeRepository.findAll());
		return j;
	}
	
	
	@ApiOperation(value="考生考试场次通过UserName查询", notes="传的时候注意userName有值")
	@RequestMapping(value = "/findExaminationArrangeByUserName", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson findExaminationArrangeByUserName(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		j.setMessage("查询成功");
		j.setObject(examinationArrangeRepository.findByUserListContaining(request.getParameter("userName")));
		return j;
	}

}
