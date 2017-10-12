package com.ellenmall.service.impl;

import com.ellenmall.common.Constants;
import com.ellenmall.common.ResponseCode;
import com.ellenmall.common.ServerReponse;
import com.ellenmall.dao.CategoryMapper;
import com.ellenmall.dao.ProductMapper;
import com.ellenmall.pojo.Category;
import com.ellenmall.pojo.Product;
import com.ellenmall.service.IProductService;
import com.ellenmall.util.DateTimeUtils;
import com.ellenmall.util.PropertiesUtil;
import com.ellenmall.vo.ProductDetailVo;
import com.ellenmall.vo.ProductListVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by zhengying on 2017/10/10.
 */
@Service("iProductService")
public class IProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 新增或更新产品
     * @param product
     * @return
     */
    public ServerReponse saveOrUpdateProduct(Product product){
        if(product != null){
            //设置商品主图
            if(StringUtils.isNoneBlank(product.getSubImages())){
                String[] subImgArray = product.getSubImages().split(",");
                if(subImgArray.length>0){
                    product.setMainImage(subImgArray[0]);
                }
            }
            //更新商品
            if(product.getId() != null){
                int rowCount = productMapper.updateByPrimaryKey(product);
                if(rowCount>0){
                    return ServerReponse.createBySuccessMessage("更新产品成功");
                }else{
                    return ServerReponse.createByErrorMessage("更新产品失败");
                }
            //新增商品
            }else{
                int rowCount = productMapper.insert(product);
                if(rowCount>0){
                    return ServerReponse.createBySuccessMessage("新增产品成功");
                }else{
                    return ServerReponse.createByErrorMessage("新增产品失败");
                }
            }
        }
        return ServerReponse.createByErrorMessage("新增或更新产品参数不正确");
    }

    /**
     * 设置商品在售状态
     * @param productId
     * @param status
     * @return
     */
    public ServerReponse setSaleStatus(Integer productId,Integer status){
        if(productId == null || status == null){
            return ServerReponse.createByErrCodeMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if(rowCount > 0){
            return ServerReponse.createBySuccess("修改产品销售状态成功");
        }
        return ServerReponse.createByErrorMessage("修改产品销售状态失败");
    }

    /**
     * 获取产品详情
     * @param productId
     * @return
     */
    public ServerReponse manageProductDetail(Integer productId){
        if(productId == null){
            return ServerReponse.createByErrCodeMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product!=null){
            //vo对象 - value object 我们这里使用的是这个vo valueObject
            //pojo -> bo{business obj} -> vo(view object)
            ProductDetailVo productDetailVo = assembleProductDetailVo(product);
            return ServerReponse.createBySuccess(productDetailVo);
        }
        return ServerReponse.createByErrorMessage("产品已下架或删除");
    }

    /**
     *获取产品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerReponse<PageInfo> manageProductList(Integer cateId, int pageNum, int pageSize){
        //start page
        PageHelper.startPage(pageNum,pageSize);
        List<Product> productList = productMapper.selectProductListByCateId(cateId);
        List<ProductListVO> productListVOList = Lists.newArrayList();
        for(Product product: productList){
            ProductListVO productListVO = assembleProductListVo(product);
            productListVOList.add(productListVO);
        }
        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVOList);
        return ServerReponse.createBySuccess(pageInfo);
    }

    /**
     * 后台搜索产品
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerReponse<PageInfo> manageSearchList(Integer product_id,String product_name,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNoneBlank(product_name)){
            product_name = new StringBuilder().append("%").append(product_name).append("%").toString();
        }
        List<Product>  productList = productMapper.selectByNameAndProductId(product_id,product_name);
        List<ProductListVO> productListVOList = Lists.newArrayList();
        for(Product product: productList){
            ProductListVO productListVO = assembleProductListVo(product);
            productListVOList.add(productListVO);
        }
        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVOList);
        return ServerReponse.createBySuccess(pageInfo);
    }

    /**
     * 前端获取产品详情
     * @param productId
     * @return
     */
    public ServerReponse productDetail(Integer productId){
        if(productId == null){
            return ServerReponse.createByErrCodeMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product!=null){
            if(product.getStatus() != Constants.ProductStatusEnum.ON_SALE.getCode()){
                return ServerReponse.createByErrorMessage("该商品已下架或删除");
            }
            //vo对象 - value object 我们这里使用的是这个vo valueObject
            //pojo -> bo{business obj} -> vo(view object)
            ProductDetailVo productDetailVo = assembleProductDetailVo(product);
            return ServerReponse.createBySuccess(productDetailVo);
        }
        return ServerReponse.createByErrorMessage("产品已下架或删除");
    }

    /**
     *获取产品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerReponse<PageInfo> productList(Integer cateId, int pageNum, int pageSize){
        //start page
        PageHelper.startPage(pageNum,pageSize);
        List<Product> productList = productMapper.selectProductListByCateId(cateId);
        List<ProductListVO> productListVOList = Lists.newArrayList();
        for(Product product: productList){
            if(product.getStatus() == Constants.ProductStatusEnum.ON_SALE.getCode()){//和服务端不同的是 只返回在售状态的产品
                ProductListVO productListVO = assembleProductListVo(product);
                productListVOList.add(productListVO);
            }
        }
        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVOList);
        return ServerReponse.createBySuccess(pageInfo);
    }



    private ProductDetailVo assembleProductDetailVo(Product product){
        ProductDetailVo productDetailVo = new ProductDetailVo();
        productDetailVo.setId(product.getId());
        productDetailVo.setSubtitle(product.getSubtitle());
        productDetailVo.setPrice(product.getPrice());
        productDetailVo.setMainImage(product.getMainImage());
        productDetailVo.setSubImage(product.getSubImages());
        productDetailVo.setCategoryId(product.getCategoryId());
        productDetailVo.setDetai(product.getDetail());
        productDetailVo.setName(product.getName());
        productDetailVo.setStatus(product.getStatus());
        productDetailVo.setStock(product.getStock());

        productDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));
        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if(category == null){
            productDetailVo.setParentCategoryId(0);
        }else{
            productDetailVo.setParentCategoryId(category.getParentId());
        }
        productDetailVo.setCreateTime(DateTimeUtils.dateToStr(product.getCreateTime()));
        productDetailVo.setUpdateTime(DateTimeUtils.dateToStr(product.getUpdateTime()));
        return productDetailVo;
    }

    private ProductListVO assembleProductListVo(Product product){
        ProductListVO productListVO = new ProductListVO();
        productListVO.setId(product.getId());
        productListVO.setCategoryId(product.getCategoryId());
        productListVO.setPrice(product.getPrice());
        productListVO.setMainImage(product.getMainImage());
        productListVO.setSubtitle(product.getSubtitle());
        productListVO.setStatus(product.getStatus());
        productListVO.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));
        return productListVO;
    }


}
