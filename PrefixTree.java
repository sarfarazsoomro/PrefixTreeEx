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
    //isLeaf would point out the end of a word in the tree
    boolean isLeaf;
    HashMap<Character, PrefixTree> map;
    
    public PrefixTree() {
        map=new HashMap<Character, PrefixTree>();
        isLeaf=false;
    }
    
    //insert a new word into the tree
    public void put(String s) {
        if(s.length()==0) {
            //if the word ends here, we need to point it out using the isLeaf
            //boolean field, and the insertion job is done, so return
            isLeaf=true;
            return;
        }
        //if the first character of the string doesn't exits
        //we create a new node for the and insert it into the map
        if(!map.containsKey(s.charAt(0))) {
            map.put(s.charAt(0), new PrefixTree());
        }
        //now this newly create node or if it already existed
        //would contain the rest of the substring
        map.get(s.charAt(0)).put(s.substring(1));
    }
    
    //tree travelsal to examine the contents of the tree
    public void travelTree(String perm) {
        if(isLeaf) {
            System.out.println(perm);
        }
        if(map.size()==0) return;
        for(Character c:map.keySet()) {
            map.get(c).travelTree(perm+c);
        }
    }
    
    //this overloaded function is used as a helper function with the getSuggestions
    //functions
    //doesn't need to be called on from the root
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
        //I am passing along this List object into the nested recursive calls 
        //from here onwards, is this a better approach than having every 
        //recursive function call create an arraylist object and append the 
        //return items and finally return the final list
        List<String> suggestions=new ArrayList<String>();
        getSuggestions(q, "", suggestions);
        return suggestions;
    }
    
    public void getSuggestions(String q, String perm, List<String> sl) {
        if(q.length()==0) {            
            travelTree(perm, sl);
            //we don't return as we need to go into further depth for more
            //suggestions
        }
        if(map.size()==0) return;
        if(q.length()>0 && map.containsKey(q.charAt(0))) {
            //make sure we call getSuggestions on specific node instead of
            //current instance's getSuggestions
            map.get(q.charAt(0)).getSuggestions(q.substring(1), perm+q.charAt(0), sl);
        }
    }
}
