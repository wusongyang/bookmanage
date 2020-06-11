package com.songyang.api.controller.visitor;

import com.github.pagehelper.PageInfo;
import com.songyang.common.StandardResponse;
import com.songyang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visitor/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("")
    public StandardResponse getMainCategoryList(@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "10") int pageSize){
      PageInfo info= categoryService.getMainList(pageSize,pageNum);
      return  StandardResponse.SuccessResponse("success",info);
    }
    @GetMapping("/parents/{categoryId}")
    public StandardResponse getCategoryAndParentList(@PathVariable(value = "categoryId") int categoryId){
      return   categoryService.getCategoryAndParentSet(categoryId);
    }
    @GetMapping("/child/{categoryId}")
    public StandardResponse getCategoryAndChildList(@PathVariable(value ="categoryId") int categoryId){
        return categoryService.getCategoryAndChildSet(categoryId);
    }

}
