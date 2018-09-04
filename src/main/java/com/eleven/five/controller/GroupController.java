package com.eleven.five.controller;

import com.eleven.five.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhihong
 */
@RestController
public class GroupController {

     @Autowired
     private GroupService groupService;



}
