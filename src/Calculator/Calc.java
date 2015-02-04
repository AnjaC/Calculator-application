package Calculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Calc implements Calculator{
	private ArrayList<String> formulas = new ArrayList<String>() ;
	private int currentF=-1 ;
	public static final String DIVISION_BY_ZERO = "Division by zero";
	public static final String BAD_FORMAT = "Bad formatted input";
	public static final String END_LIST_UP = "End of the list up";
	public static final String END_LIST_DOWN = "End of the list down";

	public void Input(String s){
		formulas.add(s);
		if (formulas.size()>5){
			formulas.remove(0); 
		}
		currentF= formulas.size()-1 ;
	}
	public String current_formula(){
		if (currentF!=-1){
			return formulas.get(currentF);
		}
		else {
			return BAD_FORMAT ;
		}
	}

	/*
	 * Return the result of the current operations or error message one of
	 * (Division by zero - bad formatted input)
	 */
	public String getResults(){
		if (currentF==-1){
			return BAD_FORMAT ;
		}
		String express=formulas.get(currentF) ;
		Evaluate formula1 = new Evaluate () ;
		formula1.setExpr(express);
		String c = formula1.checkFormat() ;
		if (c ==BAD_FORMAT){
			return BAD_FORMAT;
		}
		else {
			formula1.postfixExpr() ;
			String result = formula1.result() ;
			return result ;
		}
	}
	
	
	
	

	
	public void save(){
		File file = new File ("formulas.txt") ;
		ArrayList<String> tmp = new ArrayList<String> () ;
		int counter =0 ;
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(file)) ;
			for (int i=0;i<formulas.size();i++ ){
				output.write(formulas.get(i)+"\n");
			}
			output.close(); 
		}catch (IOException e) {
			System.out.println("file not found");
		}
	}

	public void load(){
		formulas.clear();
		try {
			BufferedReader input = new BufferedReader (new FileReader ("formulas.txt"));
			while (input.ready()){
				String s = input.readLine();
				formulas.add(s) ;
			}
			currentF=formulas.size()-1 ; 
		}catch(IOException e ){
			System.out.println("file not found");
		}
		return ; 
	}

	public String prev(){
		if (currentF<1){
			return END_LIST_UP ;  
		}
		else {
			currentF-- ;
			return formulas.get(currentF);
		}
	}
	
	
	public String next(){
		if (currentF==formulas.size()-1||currentF==-1){
			return END_LIST_DOWN ;
		}
		else {
			currentF++; 
			return formulas.get(currentF);
		}
	}
}
