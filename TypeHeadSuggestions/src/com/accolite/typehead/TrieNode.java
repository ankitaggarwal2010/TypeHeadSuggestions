package com.accolite.typehead;

import java.util.TreeMap;

class TrieNode{
    char c;
    String value="";
    TreeMap<Character, TrieNode> children = new TreeMap<>();
    boolean isLeaf;
    
    public TrieNode() 
    {}
    public TrieNode(char c)
    {
        this.c = c;
    }
    
}
