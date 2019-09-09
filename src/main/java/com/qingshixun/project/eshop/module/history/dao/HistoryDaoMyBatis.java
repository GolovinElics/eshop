package com.qingshixun.project.eshop.module.history.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qingshixun.project.eshop.dto.ProductCategoryDTO;
import com.qingshixun.project.eshop.dto.ProductDTO;
import com.qingshixun.project.eshop.web.MyBatisRepository;

@MyBatisRepository
public interface HistoryDaoMyBatis {

	List<ProductDTO> getProduct();
	
	void setProductId(Long productId);
}
