package com.qingshixun.project.eshop.module.history.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qingshixun.project.eshop.dto.ProductDTO;
import com.qingshixun.project.eshop.module.history.service.HistoryService;
import com.qingshixun.project.eshop.web.BaseController;

@Controller
@RequestMapping("/front/history")
public class HistoryController extends BaseController{
	
	@Autowired
	private HistoryService historyService;
	
	@RequestMapping("/search")
    public String search(Model model){
        List<ProductDTO> historyproducts = historyService.getProduct();
        model.addAttribute("historyproducts",historyproducts);
        return "/history";
    }

}
