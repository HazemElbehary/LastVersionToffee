/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// package com.mycompany.toffee;

/**
 *
 */
import java.util.ArrayList;
public class Catalog {
    ArrayList<Object> items = new ArrayList<>();
    public static void main(String[] args){
        
    }
    public Catalog(ArrayList<Object> it){
        for(int i=0;i<it.size();i++){
            items.add(it.get(i));
        }
    }
    void display_catalog(){
        for(int i=0;i<items.size();i++) {
            Object obj = items.get(i);
            Item item = (Item) obj;
            System.out.println(item.name + " : " + item.price);
        }
    }
}

