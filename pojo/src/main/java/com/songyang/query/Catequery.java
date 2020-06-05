package com.songyang.query;

import java.util.List;

public class Catequery {
    private Integer[] ids;
    private List<Catequery> catequeryList;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public List<Catequery> getCatequeryList() {
        return catequeryList;
    }

    public void setCatequeryList(List<Catequery> catequeryList) {
        this.catequeryList = catequeryList;
    }
}
