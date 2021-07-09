package com.accolite.typehead;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
   static int maxProductsCount=3; // max products to be displayed
	public static void main(String[] args) 
	{
		List<String> shoppingList= Stream.of("mobile","mortein","momos","mouse","moneyking","monitor","mousepad","mountains").collect(Collectors.toList());
				
		System.out.println(shoppingList);
		System.out.println("Enter the search word :");
		
		Scanner sc=new Scanner(System.in);
		String searchWord=sc.next();
		/////// It will give top 3 search in lexicoographically order if maxProductsCount=3
		
		List<List<String>> listOfList= suggestedItems(shoppingList,searchWord);
		System.out.println(listOfList);

	}
	

	private static List<List<String>> suggestedItems(List<String> shoppingList, String searchWord) {
		// TODO Auto-generated method stub
		
		List<List<String>> result=new ArrayList<List<String>>();
		TrieNode root=new TrieNode();
		for(String str:shoppingList)
		{
			insert(str,root);
		}
		
		for(int i=0; i<searchWord.length();i++)
		{
            String f=searchWord.substring(0,i+1);
            List<String> ls= findPrefix(f,root);
            result.add(ls);
        
        }
		return result;
	}
	 private static List<String> findPrefix(String prefix, TrieNode root) {
		// TODO Auto-generated method stub
		 TreeMap<Character, TrieNode> child= root.children;
	        TrieNode last=null;
	        for(int i=0; i<prefix.length();i++)
	        {
	            char ch= prefix.charAt(i);
	            TrieNode t;
	            if(!child.containsKey(ch))
	                return new ArrayList<>();
	            else
	                t=child.get(ch);
	            
	            child=t.children;
	                //set leaf node
	            if(i==prefix.length()-1)
	                last = t; 

	        }
	        List<String> matched= new ArrayList<>();
	        find(last,matched);
	        
	        return matched;
	}


	private static void find(TrieNode node, List<String> matched) {
		// TODO Auto-generated method stub
		 if(node == null)
	            return;
	        if(node.isLeaf) {
	            if(matched.size() < maxProductsCount){
	                matched.add(node.value);
	             if(matched.size() ==maxProductsCount ){
	                 return;
	             }   
	            }
	            else 
	                return;
	        }
	        for(Character ch : node.children.keySet()){
	            TrieNode n= node.children.get(ch);
	            find(n, matched);
	        }
		
	}


	public static void insert(String s, TrieNode root){
	       TreeMap<Character, TrieNode> child= root.children;
	        
	        for(int i=0; i<s.length();i++){
	            
	            char ch= s.charAt(i);
	            TrieNode t;
	            if(!child.containsKey(ch)){
	                t= new TrieNode(ch);
	                child.put(ch,t);
	            }
	            else{
	                t=child.get(ch);
	            }
	            t.value=s.substring(0,i+1);
	                child=t.children;
	                //set leaf node
	            if(i==s.length()-1)
	                t.isLeaf = true; 

	        }
	    }

}
