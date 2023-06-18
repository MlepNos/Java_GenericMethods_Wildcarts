package util;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class QueryList <T extends Student & QueryItem>{

    private List<T> items;
    public QueryList(List<T>items){
        this.items= items;
    }
    public static<T extends QueryItem> List<T> getMatches(List<T> items,String field, String value){
        List<T> matches = new ArrayList<>();
        for(var item: items){
            if(item.MatchFieldValue(field,value)){
                matches.add(item);
            }
        }
        return matches;
    }

    public List<T> getMatches(String field, String value){
        List<T> matches = new ArrayList<>();
        for(var item: items){
            if(item.MatchFieldValue(field,value)){
                matches.add(item);
            }
        }
        return matches;
    }
}
