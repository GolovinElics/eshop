package com.qingshixun.project.eshop.module.history.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingshixun.project.eshop.dto.MemberDTO;
import com.qingshixun.project.eshop.dto.ProductCategoryDTO;
import com.qingshixun.project.eshop.dto.ProductDTO;
import com.qingshixun.project.eshop.module.history.dao.HistoryDaoMyBatis;
import com.qingshixun.project.eshop.web.BaseService;

@Service
public class HistoryService extends BaseService {

	@Autowired
	private HistoryDaoMyBatis historyDaoMyBatis;

	/**
	 * 获取所有历史记录
	 */
	public List<ProductDTO> getProduct() {

		List<ProductDTO> product = historyDaoMyBatis.getProduct();

		for (int i = 0; i < product.size(); i++) {
			for (int j = 0; j < product.size(); j++) {
				if (product.get(i).getName().equals(product.get(j).getName()) && i != j) {
					product.remove(j);
				}
			}
		}

		return product;
	}

	/**
	 * 设置浏览过的ID
	 * 
	 * @param productId
	 */
	public void setHistoryProductId(Long productId) {
		historyDaoMyBatis.setProductId(productId);
	}
}
