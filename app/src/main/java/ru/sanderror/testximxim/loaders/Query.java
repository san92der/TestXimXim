package ru.sanderror.testximxim.loaders;

import java.util.List;

import ru.sanderror.testximxim.models.ListItem;
public class Query{
    private String mQuery;
    private List<ListItem> mResponse;
    private  Integer mPosition = 0;
    public Query(String query, List<ListItem> response){
        mQuery = query;
        mResponse = response;
    }
    public String getQuery(){return mQuery;}
    public List<ListItem> getResponse(){return mResponse;}
    public void setResponse(List<ListItem> response){mResponse = response;}
    public void setPosition(Integer position){mPosition = position;}
    public int getPosition(){ return mPosition;}
}
