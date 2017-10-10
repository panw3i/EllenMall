package com.ellenmall.controller.backend;

import com.ellenmall.common.Constants;
import com.ellenmall.common.ResponseCode;
import com.ellenmall.common.ServerReponse;
import com.ellenmall.pojo.User;
import com.ellenmall.service.ICategoryService;
import com.ellenmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by zhengying on 2017/9/29.
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;

    /**
     * 添加分类
     * @param session
     * @param cateName
     * @param parentId
     * @return
     */
    @RequestMapping("add_cate.do")
    @ResponseBody //使返回的resp序列化
    public ServerReponse<User> addCategory(HttpSession session, String cateName,int parentId) {
        //判断是否登录
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerReponse.createByErrCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
        }
        //效验是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            return iCategoryService.addCategory(cateName,parentId);
        } else {
            return ServerReponse.createByErrorMessage("无权限,需要管理员权限");
        }
    }

    /**
     * 修改类别名称
     * @param session
     * @param cateId
     * @param cateName
     * @return
     */
    @RequestMapping("set_cate_name.do")
    @ResponseBody //使返回的resp序列化
    public ServerReponse setCateName(HttpSession session,Integer cateId,String cateName){
        //判断用户登录
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerReponse.createByErrCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
        }
        //效验是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            return iCategoryService.updateCateName(cateId,cateName);
        } else {
            return ServerReponse.createByErrorMessage("无权限,需要管理员权限");
        }
    }

    /**
     *获取一级子分类
     * @param session
     * @param cateId
     * @return
     */
    @RequestMapping("get_child_cate.do")
    @ResponseBody
    public ServerReponse getChildrenParallelCate(HttpSession session,int cateId){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerReponse.createByErrCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
        }
        //效验是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            return iCategoryService.getChildrenParallelCate(cateId);
        } else {
            return ServerReponse.createByErrorMessage("无权限,需要管理员权限");
        }
    }

    /**
     * 获取所有子分类ID
     * @param session
     * @param cateId
     * @return
     */
    @RequestMapping("get_deep_cate.do")
    @ResponseBody
    public ServerReponse getCateAndDeepChildrenCate(HttpSession session,int cateId){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerReponse.createByErrCodeMsg(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
        }
        //效验是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //查询当前节点的id和递归子节点的id
            return iCategoryService.selectCateAndChildrenById(cateId);
        } else {
            return ServerReponse.createByErrorMessage("无权限,需要管理员权限");
        }
    }
}
