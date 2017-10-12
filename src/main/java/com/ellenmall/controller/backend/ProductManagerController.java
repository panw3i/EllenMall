package com.ellenmall.controller.backend;

import com.ellenmall.common.Constants;
import com.ellenmall.common.ServerReponse;
import com.ellenmall.pojo.Product;
import com.ellenmall.pojo.User;
import com.ellenmall.service.IFileService;
import com.ellenmall.service.IProductService;
import com.ellenmall.service.IUserService;
import com.ellenmall.util.PropertiesUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by zhengying on 2017/10/9.
 */
@Controller
@RequestMapping("/manage/product")
public class ProductManagerController {

    @Autowired
    IUserService iUserService;
    @Autowired
    IProductService iProductService;
    @Autowired
    IFileService iFileService;

    /**
     * 保存或更新商品
     * @param session
     * @param product
     * @return
     */
    @RequestMapping("save.do")
    @ResponseBody //使返回的resp序列化
    public ServerReponse productSave(HttpSession session, Product product){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user==null){
            return ServerReponse.createByErrorMessage("用户未登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iProductService.saveOrUpdateProduct(product);
        }else{
            return ServerReponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 设置在售状态
     * @param session
     * @param productId
     * @param status
     * @return
     */
    @RequestMapping("set_sale_status.do")
    @ResponseBody //使返回的resp序列化
    public ServerReponse setSaleStatus(HttpSession session,Integer productId,Integer status){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user==null){
            return ServerReponse.createByErrorMessage("用户未登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iProductService.setSaleStatus(productId,status);
        }else{
            return ServerReponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 商品详情
     * @param session
     * @param productId
     * @return
     */
    @RequestMapping("detail.do")
    @ResponseBody //使返回的resp序列化
    public ServerReponse getDetail(HttpSession session,Integer productId){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user==null){
            return ServerReponse.createByErrorMessage("用户未登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iProductService.manageProductDetail(productId);
        }else{
            return ServerReponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 商品列表
     * @param session
     * @param rowNum
     * @param pageSize
     * @return
     */
    @RequestMapping("list.do")
    @ResponseBody //使返回的resp序列化
    public ServerReponse list(HttpSession session,Integer cateId,
                              @RequestParam(value = "rowNum",defaultValue = "1") Integer rowNum,
                              @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user==null){
            return ServerReponse.createByErrorMessage("用户未登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
           return iProductService.manageProductList(cateId,rowNum,pageSize);
        }else{
            return ServerReponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 搜索商品
     * @param session
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("search.do")
    @ResponseBody //使返回的resp序列化
    public ServerReponse search(HttpSession session,Integer productId,String product_name,
                              @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user==null){
            return ServerReponse.createByErrorMessage("用户未登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iProductService.manageSearchList(productId,product_name,pageNum,pageSize);
        }else{
            return ServerReponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 上传图片
     * @param session
     * @param file
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("upload.do")
    @ResponseBody
    public ServerReponse upload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file,HttpServletRequest httpServletRequest){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user==null){
            return ServerReponse.createByErrorMessage("用户未登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            String path = httpServletRequest.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file,path);
            if(StringUtils.isBlank(targetFileName)){
                return ServerReponse.createByErrorMessage("上传失败");
            }
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
            Map fileMap = Maps.newHashMap();
            fileMap.put("uri",targetFileName);
            fileMap.put("url",url);
            return ServerReponse.createBySuccess(fileMap);
        }else{
            return ServerReponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 上传富文本的图片
     * @param session
     * @param file
     * @param httpServletRequest
     * @return
     * 当没有RequestParam时 参数就是方法中定义的参数的名字 当有RequestParam时 参数就是RequestParam中的参数
     */
    @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map richtextImgUpload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse){
        Map reultMap = Maps.newHashMap();
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user==null){
            reultMap.put("success",false);
            reultMap.put("msg","用户未登录");
            return reultMap;
        }

        //富文本中对于返回值有自己的要求,我们使用的是simditor 所以按照simditor的要求进行返回 所以直接返回一个map
        //这个方法只针对simditor返回的
        if(iUserService.checkAdminRole(user).isSuccess()){
            String path = httpServletRequest.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file,path);
            if(StringUtils.isBlank(targetFileName)){
                reultMap.put("success",false);
                reultMap.put("msg","上传失败");
                return reultMap;
            }
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;

            reultMap.put("success",true);
            reultMap.put("msg","上传成功");
            reultMap.put("file_path",url);
            //很多插件 对后台的返回值都是有要求的
            httpServletResponse.addHeader("Access-Control-Allow-Headers","X-File-Name");//这个是我们和前端的一个约定
            return reultMap;
        }else{
            Map fileMap = Maps.newHashMap();
            fileMap.put("success",false);
            fileMap.put("msg","无权限操作");
            return fileMap;
        }
    }

}
