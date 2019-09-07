/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
/**
 * 自定义商品的js文件用于封装商品的js
 *
 * @author QingShiXun
 *
 * @version 1.0
 */
var product = (function () {

  var productId
  var pageNo = 1
  var status
  var brandId = ''
  var categoryId = ''

  function initList (categoryId) {
    categoryId = categoryId
    // 点击筛选触发事件
    $('.searchItem').click(
      function () {
        searchPargam = ''
        var $parent = $(this).parent().parent()
        $parent.find('.productSelect').each(function (index) {
          $(this).removeClass('productSelect')
        })
        $(this).addClass('productSelect')

        $('.productSelect').each(function (index) {
          var type = $(this).data('type')
          if (type == 'brand') {
            brandId = $(this).data('id')
          } else {
            searchPargam += $(this).html() + ','
          }
        })

        $('#productList').load(
          g_rootPath + '/product/search/list?searchPargam=' + searchPargam + '&brandId=' + brandId + '&categoryId=' + categoryId + '&pageNo=' + pageNo + '&productName=' + productName)
      })
  }

  function initMain (productId) {
    $('#evaluateListPanel').load(g_rootPath + '/front/product/evaluate/list/?productId=' + productId + '&status=' + status )
    $('.evaluateStatus').click(function () {
      $('.evaluateStatus').removeClass('active')
      $(this).addClass('active')
      pageNo = pageNo + 1;
      // 问题就出在这里，这里的status的值
      status = $(this).data('value')
      //改写一：
      // alert(document.getElementById('status').dataset.value);
      var url
      if (status == "") {
        url = g_rootPath + '/front/product/evaluate/list/?productId=' + productId
      }else {
        url = g_rootPath + '/front/product/evaluate/list/?productId=' + productId  + '&status=' + status
      }
      $('#evaluateListPanel').load(url)
    })

    $('.productAttribute').click(function () {
      searchPargam = ''
      var type = $(this).data('type')
      var $parent = $(this).parent().parent()
      $parent.find('.product_attribute_selete').each(function (index) {
        $(this).removeClass('product_attribute_selete')
      })
      $(this).addClass('product_attribute_selete')
      if (type == 'brand') {
        brandId = $(this).data('id')
      } else {
        $('.product_attribute_selete').each(function (index) {
          searchPargam += $(this).html() + ','
        })
      }

      $('#productList').load(g_rootPath + '/front/product/search/list?searchPargam=' + searchPargam + '&brandId=' + brandId)
    })

    var submitFormOptions = {
      url: g_rootPath + '/front/cart/save',
      type: 'POST',
      success: function (response) {
        if (response.status == '0') {
          toastr.success('购物车添加成功！')
          var count = parseInt($('#cartCount').html()) + parseInt($('#productQuantity').val())
          $('#cartCount').html(count)
        } else {
          toastr.warning('购物车添加失败！' + response.error)
        }
      },
      error: function (context, xhr) {
        $.alert({
          title: '出现错误',
          content: xhr,
          confirmButton: '确定'
        })
      }
    }

    // 提交菜单表单信息
    $('#cartItem').submit(function () {
      $(this).ajaxSubmit(submitFormOptions)

    })
  }

  return {
    initList: initList,
    initMain: initMain
  }
})()