package com.songyang.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.songyang.common.StandardResponse;
import com.songyang.dao.CategoryMapper;
import com.songyang.pojo.Category;
import com.songyang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addCategory(Category category) {
        int conut =categoryMapper.insertSelective(category);
        return  conut>0 ? true:false;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteCategory(Integer[] ids) {
        return categoryMapper.deleteByPrimary(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StandardResponse update(Category category) {
        int conut=categoryMapper.updateByPrimary(category);
        return  conut>0 ? StandardResponse.SuccessResponseMessage("更新成功"):StandardResponse.ErrorResponseMessage("失败");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageInfo<Category> getList(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
       List<Category> categoryList=categoryMapper.selectCategorys();
       PageInfo<Category> pageInfo=new PageInfo<>();
       pageInfo.setList(categoryList);
       return pageInfo;
    }


}
