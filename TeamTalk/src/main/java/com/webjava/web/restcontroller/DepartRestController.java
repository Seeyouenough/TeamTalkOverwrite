package com.webjava.web.restcontroller;

/**
 * Created by wx on 2017/10/27.
 */
import com.google.gson.Gson;
import com.webjava.kernel.entity.IMDepart;
import com.webjava.kernel.service.IDepartService;
import com.webjava.model.IDList;
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


@RestController
@RequestMapping("/users")
public class DepartRestController {
    public static Logger logger= LogManager.getLogger(DepartRestController.class.getName());
    @Resource
    private IDepartService departService;


    @RequestMapping(value = "/depart/list",method = RequestMethod.GET)
    public void listDepart(HttpServletRequest request, HttpServletResponse response){
        List<IMDepart> departs=this.departService.getAllDepart();

        logger.info("部门数量："+departs.size());

        if(departs.size()>0){
            Gson gson=new Gson();
            String data=gson.toJson(departs);
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"显示所有用户",data));
        }
        else
        {
            System.out.println("fail");
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"无内容"));
        }
    }

    @RequestMapping(value = "/depart/add",method = RequestMethod.POST)
    public void addDepart(HttpServletRequest request, HttpServletResponse response){
        System.out.println("aaaa");
        String  strJson= HttpUtils.getJsonBody(request);

        Gson gson=new Gson();

        IMDepart depart=gson.fromJson(strJson,IMDepart.class);

        System.out.println(depart);

        IMDepart existdepart =this.departService.getDepartByName(depart.getDepartname());
        if(existdepart !=null){
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"用户名已存在！"));
        }
        else{
            this.departService.addDepart(depart);
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"添加用户成功！"));
        }

        System.out.println("bbb");
    }

    @RequestMapping(value = "/depart/remove",method = RequestMethod.POST)
    public void removeDepart(HttpServletRequest request,HttpServletResponse response ){

        String strjson =HttpUtils.getJsonBody(request);
        Gson gson =new Gson();
        IDList IDs =gson.fromJson(strjson,IDList.class);

        for(int i=0 ; i<IDs.getParams().size() ;i++){

            Integer id = IDs.getParams().get(i).getId();
            IMDepart exitId =this.departService.getDepartById(id);
            if(exitId!=null){
                this.departService.deleteDepart(id);
                HttpUtils.setJsonBody(response,new ResponseInfo(1,"修改成功！"));
            }else
            {
                HttpUtils.setJsonBody(response,new ResponseInfo(0,"无此ID信息！"));
            }

        }

    }

    @RequestMapping(value = "/depart/update",method = RequestMethod.POST)
    public void updateDepart (HttpServletRequest request,HttpServletResponse response){


        String strData =HttpUtils.getJsonBody(request);
        Gson gson=new Gson();
        IMDepart depart = gson.fromJson(strData,IMDepart.class);
        IMDepart existdepart=this.departService.getDepartById(depart.getId());
        if(existdepart !=null){
            this.departService.updateDepart(depart);
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"更新用户信息成功！"));
        }
        else {
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"无此用户，用户信息更新失败"));

        }

    }

}