package com.songyang.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.songyang.common.StandardResponse;
import com.songyang.dao.CategoryMapper;
import com.songyang.pojo.Category;
import com.songyang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PageInfo<Category> getMainList(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Category> categoryList= categoryMapper.selectMainCategorys();
        PageInfo pageInfo =new PageInfo();
        pageInfo.setList(categoryList);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StandardResponse getCategoryAndParentSet(int categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
       categorySet= this.getParentSetCategory(categorySet, categoryId);
        return StandardResponse.SuccessResponse("success",categorySet);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StandardResponse getCategoryAndChildSet(int categoryId) {
        Set categorySet = Sets.newHashSet();
        Category category =  categoryMapper.selectCategoryByPrimary(categoryId);
        if (category!=null){
            categorySet.add(category);
            this.getChildSet(categorySet,category.getId());
        }
        return StandardResponse.SuccessResponse("success",categorySet);
    }

    private  Set<Category> getChildSet(Set set,int categoryId){
        List<Category> list=categoryMapper.selectChildCategory(categoryId);
        if (list.size()>0) {
            for (Category category : list) {
                set.add(category);
                this.getChildSet(set,category.getId());
            }
        }
        return set;
    }

    private Set<Category> getParentSetCategory(Set set ,int categoryId){
      Category category =  categoryMapper.selectCategoryByPrimary(categoryId);
      if (category!=null){
          set.add(category);
          if (category.getParentid()!=0)
         this.getParentSetCategory(set,category.getParentid());
      }
      return set;

    }


}
