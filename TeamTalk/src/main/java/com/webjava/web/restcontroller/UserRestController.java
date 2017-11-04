package com.webjava.web.restcontroller;

/**
 * Created by wx on 2017/10/27.
 */


import com.google.gson.Gson;
import com.webjava.kernel.entity.IMUser;
import com.webjava.kernel.service.IUserService;
import com.webjava.model.IDList;
import com.webjava.utils.EncryptHelper;
import com.webjava.utils.HttpUtils;
import com.webjava.utils.ResponseInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Random;



@RestController
@RequestMapping("/users")
public class UserRestController {

    public static Logger logger= LogManager.getLogger(UserRestController.class.getName());

    @Resource
    private IUserService userService;

    @RequestMapping(value = "/user/list",method = RequestMethod.GET)
    public void listUser(HttpServletRequest request, HttpServletResponse response){
        List<IMUser> users=this.userService.getAllUser();

        logger.info("用户数量；"+users.size());

        if(users.size()>0){
            Gson gson=new Gson();
            String data=gson.toJson(users);
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"显示所有用户",data));
        }
        else
        {
            System.out.println("fail");
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"无内容"));
        }
    }

    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    public void addUser(HttpServletRequest request, HttpServletResponse response){

        String saltStr = Integer.toString(new Random().nextInt(10000));

        String  strJson= HttpUtils.getJsonBody(request);



        Gson gson=new Gson();

        IMUser user=gson.fromJson(strJson,IMUser.class);

        System.out.println(user);
        IMUser existUser =this.userService.getUserByName(user.getName());
        if(existUser !=null){
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"用户名已存在！"));
        }
        else{
            user.setSalt(saltStr);
            user.setPassword(EncryptHelper.encodeByMD5(user.getPassword()+saltStr));
            this.userService.addUser(user);
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"添加用户成功！"));
        }

    }

    @RequestMapping(value = "/user/remove",method = RequestMethod.POST)
    public void removeUser(HttpServletRequest request,HttpServletResponse response ){

        String strjson =HttpUtils.getJsonBody(request);
        Gson gson =new Gson();
        IDList IDs =gson.fromJson(strjson,IDList.class);

        for(int i=0 ; i<IDs.getParams().size() ;i++){

            Integer id = IDs.getParams().get(i).getId();
            IMUser exitId =this.userService.getUserById(id);
            if(exitId!=null){
                this.userService.deleteUser(id);
                HttpUtils.setJsonBody(response,new ResponseInfo(1,"修改成功！"));
            }else
            {
                HttpUtils.setJsonBody(response,new ResponseInfo(0,"无此ID信息！"));
            }

        }

       /* String strData = HttpUtils.getJsonBody(request);
        Gson gson=new Gson();
        IMUser user=gson.fromJson(strData,IMUser.class);
        IMUser existUser=this.userService.getUserById(user.getId());
        if(existUser == null){
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"用户不存在，无法进行删除操作"));
        }
        else {
            this.userService.deleteUser(user.getId());
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"用户删除成功"));
        }*/


    }

    @RequestMapping(value = "/user/update",method = RequestMethod.POST)
    public void updateUser (HttpServletRequest request,HttpServletResponse response){
       // String saltStr = Integer.toString(new Random().nextInt(10000));

        String strData =HttpUtils.getJsonBody(request);
           Gson gson=new Gson();
           IMUser user = gson.fromJson(strData,IMUser.class);
           IMUser existUser=this.userService.getUserById(user.getId());
           if(existUser !=null){
              // user.setSalt(saltStr);
              // user.setPassword(EncryptHelper.encodeByMD5(user.getPassword()+saltStr));
               user.setPassword(null);
               this.userService.updateUser(user);
               HttpUtils.setJsonBody(response,new ResponseInfo(0,"更新用户信息成功！"));
           }
           else {
               HttpUtils.setJsonBody(response,new ResponseInfo(1,"无此用户，用户信息更新失败"));

           }

    }

    @RequestMapping(value = "/user/updatePassword",method = RequestMethod.POST)
    public void updatePassword (HttpServletRequest request,HttpServletResponse response){

        String saltStr = Integer.toString(new Random().nextInt(10000));
        String strData =HttpUtils.getJsonBody(request);
        Gson gson=new Gson();


        IMUser user = gson.fromJson(strData,IMUser.class);
        logger.info(user);
        IMUser existUser=this.userService.getUserById(user.getId());
        if(existUser !=null){
            user.setSalt(saltStr);
            System.out.println(user);
            user.setPassword(EncryptHelper.encodeByMD5(user.getPassword()+saltStr));
            this.userService.updatePassword(user);
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"更新用户信息成功！"));
        }
        else {
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"无此用户，用户信息更新失败"));

        }

    }

}