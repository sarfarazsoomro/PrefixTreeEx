/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prefixtreeex;

/**
 *
 * @author ssoomro
 */
public class PrefixTreeEx {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PrefixTree pt=new PrefixTree();
        pt.put("add");
        pt.put("added");
        pt.put("not");
        pt.put("tree");
        pt.put("travel");
        pt.put("adrenaline");
        pt.put("hello");
        pt.put("hell");
        //pt.travelTree("");
        System.out.println(pt.getSuggestions("he"));
    }
    
}
