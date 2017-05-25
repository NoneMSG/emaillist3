package com.jx372.emaillist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx372.emaillist.dao.EmaillistDao;
import com.jx372.emaillist.vo.EmaillistVo;

@Controller
public class EmaillistController {
	
	//root Application context에서 Dao객체를 찾기위해 멤버 변수를 만들어 주어야 한다.
	@Autowired //자동적으로 Dao객체를 가리키고 있도록 하고있다. 이걸 안 하면 bean설정해서 get,set방식으로 접근해야됨
	private EmaillistDao emaillistDao;
	
	@RequestMapping("/list")
	public String list(Model model){
		//new를해서 heap에 메모리를 할당 안 해도 된다.
		List<EmaillistVo> list = emaillistDao.getList();
		for(EmaillistVo vo : list){
			System.out.println(vo);
		}
		if(list.size()<=0){
			System.out.println("list is empty");
		}
		model.addAttribute("list",list);
		return "/WEB-INF/views/index.jsp";
	}
	
	@RequestMapping("/form")
	public String form(){
		return "/WEB-INF/views/form.jsp";
	}
	
	@RequestMapping( value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute EmaillistVo vo){
		emaillistDao.insert(vo);
		return "redirect:/list";
	}
}
