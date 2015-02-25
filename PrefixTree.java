/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prefixtreeex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ssoomro
 */
public class PrefixTree {
    boolean isLeaf;
    HashMap<Character, PrefixTree> map;
    
    public PrefixTree() {
        map=new HashMap<Character, PrefixTree>();
        isLeaf=false;
    }
    
    public void put(String s) {
        if(s.length()==0) {
            isLeaf=true;
            return;
        }
        if(!map.containsKey(s.charAt(0))) {
            map.put(s.charAt(0), new PrefixTree());
        }
        map.get(s.charAt(0)).put(s.substring(1));
    }
    
    public void travelTree(String perm) {
        if(isLeaf) {
            System.out.println(perm);
        }
        if(map.size()==0) return;
        for(Character c:map.keySet()) {
            map.get(c).travelTree(perm+c);
        }
    }
    
    public void travelTree(String perm, List<String> sl) {
        if(isLeaf) {
            sl.add(perm);
        }
        if(map.size()==0) return;
        for(Character c:map.keySet()) {
            map.get(c).travelTree(perm+c, sl);
        }
    }    
    
    public List<String> getSuggestions(String q) {
        List<String> suggestions=new ArrayList<String>();
        getSuggestions(q, "", suggestions);
        return suggestions;
    }
    
    public void getSuggestions(String q, String perm, List<String> sl) {
        if(q.length()==0) {            
            travelTree(perm, sl);
        }
        if(map.size()==0) return;
        if(q.length()>0 && map.containsKey(q.charAt(0))) {
            map.get(q.charAt(0)).getSuggestions(q.substring(1), perm+q.charAt(0), sl);
        }
    }
}
