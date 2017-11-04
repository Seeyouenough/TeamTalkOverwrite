package com.webjava.web.restcontroller;

/**
 * Created by wx on 2017/10/27.
 */

import com.google.gson.Gson;
import com.webjava.kernel.entity.IMDiscovery;
import com.webjava.kernel.service.IDepartService;
import com.webjava.kernel.service.IDiscoveryService;
import com.webjava.model.IDList;
import com.webjava.utils.HttpUtils;
import com.webjava.utils.ResponseInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/users")
public class DiscoveryRestController {

    @Resource
    private IDiscoveryService discoveryService;


    @RequestMapping(value = "/discovery/list",method = RequestMethod.GET)
    public void listDiscovery(HttpServletRequest request, HttpServletResponse response){
        List<IMDiscovery> users=this.discoveryService.getAllDiscovery();
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

    @RequestMapping(value = "/discovery/add",method = RequestMethod.POST)
    public void addDiscovery(HttpServletRequest request, HttpServletResponse response){

        String  strJson= HttpUtils.getJsonBody(request);

        Gson gson=new Gson();

        IMDiscovery discovery=gson.fromJson(strJson,IMDiscovery.class);

        System.out.println(discovery);
        IMDiscovery existDiscovery =this.discoveryService.getDiscoveryByName(discovery.getItemname());
        if(existDiscovery !=null){
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"用户名已存在！"));
        }
        else{
            this.discoveryService.addDiscovery(discovery);
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"添加用户成功！"));
        }

    }

    @RequestMapping(value = "/discovery/remove",method = RequestMethod.POST)
    public void removeDiscovery(HttpServletRequest request,HttpServletResponse response ) {

        String strjson = HttpUtils.getJsonBody(request);
        Gson gson = new Gson();
        IDList IDs = gson.fromJson(strjson, IDList.class);

        for (int i = 0; i < IDs.getParams().size(); i++) {

            Integer id = IDs.getParams().get(i).getId();
            IMDiscovery exitId = this.discoveryService.getDiscoveryById(id);
            if (exitId != null) {
                this.discoveryService.deleteDiscovery(id);
                HttpUtils.setJsonBody(response, new ResponseInfo(1, "修改成功！"));
            } else {
                HttpUtils.setJsonBody(response, new ResponseInfo(0, "无此ID信息！"));
            }

        }


    }

    @RequestMapping(value = "/discovery/update",method = RequestMethod.POST)
    public void updateDiscovery (HttpServletRequest request,HttpServletResponse response){
        String strData =HttpUtils.getJsonBody(request);
        Gson gson=new Gson();
        IMDiscovery discovery = gson.fromJson(strData,IMDiscovery.class);
        IMDiscovery existDiscovery=this.discoveryService.getDiscoveryById(discovery.getId());
        if(existDiscovery !=null){
            this.discoveryService.updateDiscovery(discovery);
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"更新用户信息成功！"));
        }
        else {
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"无此用户，用户信息更新失败"));

        }

    }

}