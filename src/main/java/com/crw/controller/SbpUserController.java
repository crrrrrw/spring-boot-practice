package com.crw.controller;

import com.crw.common.ApiResult;
import com.crw.model.SbpUser;
import com.crw.service.SbpUserService;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/sbpUser")
public class SbpUserController {

    @Resource
    private SbpUserService sbpUserService;

    @ApiOperation(value = "获取用户列表,分页", notes = "")
    @GetMapping("/list")
    public ApiResult getUserList(@RequestParam(defaultValue = "1") int pageNo,
                                 @RequestParam(defaultValue = "10") int pageSize) {
        Page<SbpUser> page = sbpUserService.getListPageInfo(pageNo, pageSize);
        return ApiResult.success(page);
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "SbpUser")
    @PostMapping("/insert")
    public ApiResult postUser(@RequestBody SbpUser user) {
        sbpUserService.insert(user);
        return ApiResult.success();
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResult getUser(@PathVariable Long id) {
        SbpUser user = sbpUserService.getObjectById(id);
        return ApiResult.success(user);
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "SbpUser")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ApiResult putUser(@PathVariable Long id, @RequestBody SbpUser user) {
        SbpUser oldUser = sbpUserService.getObjectById(id);
        BeanUtils.copyProperties(user, oldUser);
        user.setId(id);
        sbpUserService.update(user);
        return ApiResult.success();
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ApiResult deleteUser(@PathVariable Long id) {
        sbpUserService.deleteById(id);
        return ApiResult.success();
    }
}