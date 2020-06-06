package com.songyang.service;

import com.github.pagehelper.PageInfo;
import com.songyang.common.StandardResponse;
import com.songyang.pojo.Category;


public interface CategoryService {
    Boolean addCategory(Category category);
    int deleteCategory(Integer[] ids);
    StandardResponse update(Category category);
    PageInfo<Category> getList(int pageSize,int pageNum);
    PageInfo<Category> getMainList(int pageSize,int pageNum);
    StandardResponse getCategoryAndParentSet(int categoryId);
    StandardResponse getCategoryAndChildSet(int categoryId);
}
