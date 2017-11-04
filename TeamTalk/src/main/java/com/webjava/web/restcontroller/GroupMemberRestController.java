package com.webjava.web.restcontroller;
/**
 * Created by wx on 2017/10/27.
 */
import com.webjava.kernel.service.IGroupMemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;



@RestController
@RequestMapping("/users")
public class GroupMemberRestController {

    @Resource
    private IGroupMemberService groupMemberService;

    @RequestMapping(value = "/userGroupMember",method = RequestMethod.GET)
    public String getAllUsers(){
        return "userList";

    }

}