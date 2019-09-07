//package com.qingshixun.project.eshop.module.evaluate.controller;
//
//import com.qingshixun.project.eshop.module.evaluate.service.EvaluateServiceImpl;
//import com.qingshixun.project.eshop.web.BaseController;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 该方法已在productConrtoller中直接调用事物层处理，不经由此处
 * @author Golovin
 */

//@Controller
//@RequestMapping("/front/product/evaluate")
//public class EvaluateController extends BaseController {
//
//    @Autowired
//    private EvaluateServiceImpl evaluateService;
//
//    @RequestMapping(value = "/list/{productId}/{status}", method = RequestMethod.POST)
//    @ResponseBody
//    public String evaluateBackPage(@PathVariable Long productId , @PathVariable String status) {
//        evaluateService.getEvaluateCountByStatusAndProduct(status, productId);
//        return "/product/evaluate/list";
//    }
//}
