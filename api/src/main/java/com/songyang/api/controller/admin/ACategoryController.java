package com.songyang.api.controller.admin;

import com.github.pagehelper.PageInfo;
import com.songyang.common.StandardResponse;
import com.songyang.pojo.Category;
import com.songyang.query.Catequery;
import com.songyang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
public class ACategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public StandardResponse addCategory(Category category){
        if (category!=null){
            Boolean b=categoryService.addCategory(category);
            return b==true?StandardResponse.SuccessResponseMessage("插入成功"):StandardResponse.ErrorResponseMessage("插入失败");
        }else return StandardResponse.ErrorResponseMessage("参数为空");
    }
    @PostMapping("/deletecategory")
    public  StandardResponse deleteCategroy(Catequery catequery){
        int conut=categoryService.deleteCategory(catequery.getIds());
        if (conut>0){
            return StandardResponse.SuccessResponseMessage("删除成功");
        }else  return StandardResponse.ErrorResponseMessage("删除失败");
    }
    @PostMapping("/update/{id}")
    public  StandardResponse updateCategory(@PathVariable(value = "id") int id ,Category category){
        category.setId(id);
      return   categoryService.update(category);
    }
    @GetMapping("/listCategory")
    public StandardResponse listCategory(@RequestParam(defaultValue = "10") int pageSize ,@RequestParam(defaultValue = "1") int pageNum){
        PageInfo pageInfo =categoryService.getList(pageSize,pageNum);
        return StandardResponse.SuccessResponse("查询成功",pageInfo);
    }

}
