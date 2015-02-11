package com.StepByStepModel;

public class Item {

	public int icon;
	public int text;
	public int count=0;
	
	public Item(int newicon, int newtext){
		icon=newicon;
		text=newtext;
	}
	
	public Item(int newicon, int newText, int newcount){
		icon=newicon;
		text=newText;
		count=newcount;
	}
	
	
	
	public int getItemtext(){
		return text;
	}
}
