package Calculator;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import org.omg.CORBA.BAD_CONTEXT;

public class Evaluate {
	private String expr; 
	private ArrayList <String> expression = new ArrayList<String>(); 
	private ArrayList <String> postfixexpr = new ArrayList<String> ();
	/*private boolean isNumber(char a ){
		if (a!='0'&&a!='1'&&a!='2'&&a!='3'&&a!='4'&&a!='5'&&a!='6'&&a!='7'&&a!='8'&&a!='9'){
			return false ; 
		}
		return true; 
	}*/
	private boolean isOperator(char a ){
		if (a!='+'&&a!='-'&&a!='*'&&a!='/'){
			return false ;  
		}
		return true ; 
	}
	
	public void setExpr (String s ){
		this.expr=s ;
	}
	public String checkFormat (){
		expr=expr.replaceAll(" ",""); 
		if (expr.length()==0){
			return "Bad formatted input" ;
		}
		String[] tokens = expr.split("(?<=[+*/=()])|((?<=-)(?!\\d))|(?=[()\\-+*/=])");
		
		for (int i=0;i<tokens.length;i++){
			if (tokens[i].equals("")){
				continue ;
			}
			if (tokens[i].matches("-?\\d+(\\.\\d+)?")&&i>0&&tokens[i-1].matches("-?\\d+(\\.\\d+)?")&&tokens[i].charAt(0)=='-'){
				expression.add("-");
				String tmp =tokens[i]; 
				expression.add(tmp.substring(1));
			}
			else {
				expression.add(tokens[i]);
			}
		}
		for (int j=0;j<expression.size();j+=2){
			if (!(expression.get(j).matches("-?\\d+(\\.\\d+)?"))){
				return "Bad formatted input" ;
			}
		}
		for (int j=1;j<expression.size();j+=2){
			if (expression.get(j).length()>1||!isOperator(expression.get(j).charAt(0))){
				return "Bad formatted input";
			}
		}
		if (expression.size()%2==0){
			return "Bad formatted input" ;
		}
		return "true" ; 
	}
	
	public void postfixExpr( ){
		Stack<String>  s = new Stack<String>()  ;
		for (int i=0;i<expression.size();i++){
			if(i%2==1){ // if the current in the expression is operator
				if (s.isEmpty()==false&&isOperator(s.peek().charAt(0))){ //if the peek in the stack is operator 
					///to find the priority of each operand 
					String z= new String ();
					z="+*";
					String y =new String ();
					y="-/";
					int priority1=0;
					int priority2=0; 
					for (int j=0;j<2;j++){
						if (expression.get(i).charAt(0)==z.charAt(j)){
							priority1=j;
						}
						if (expression.get(i).charAt(0)==y.charAt(j)){
							priority1=j;
						}
					}
					char tmp=s.peek().charAt(0);
					for (int j=0;j<2;j++){
						if (tmp==z.charAt(j)){
							priority2=j;
						}
						if (tmp==y.charAt(j)){
							priority2=j;
						}
					}
					///the end of priority code
					///priority1 is the priority of the operator in the infix expression 
					///priority2 is the priority of the operator in the top of the stack 
					if (priority1>priority2){
						s.push(expression.get(i));
					}
					if (priority1<=priority2){
						postfixexpr.add(s.pop());
						i--;
					}
				}
				else { //stack is empty 
					s.push(expression.get(i));
				}
			}
			else if (i%2==0){
				postfixexpr.add(expression.get(i));
			}
		}
		if (s.isEmpty()==false){
			while(s.isEmpty()==false){
				postfixexpr.add(s.pop());
			}
		}
	}
	
	
	public String result (){
		Stack<Double> s=new Stack <Double> ();
		double answer =0;
		for (int i=0;i<postfixexpr.size();i++){
			if (postfixexpr.get(i).matches("-?\\d+(\\.\\d+)?")){///postfix expression will contain only integer numbers and operators but the result of each operations may be float or integer save it in array as a string and parse it as a float 
				double  t =Double.parseDouble(postfixexpr.get(i));
				s.push(t);
			}
			else {
				double  op1 =0;
				double op2 =0;	
				op1=(double)s.pop();
				op2=(double)s.pop();
				if (postfixexpr.get(i).charAt(0)=='+'){
					Addition op = new Addition () ;
					double result = op.doOp(op1, op2)  ;
					s.push(result);
				}
				if (postfixexpr.get(i).charAt(0)=='-'){
					Subtraction op = new Subtraction () ;
					double  result =op.doOp(op2, op1 );
					s.push(result);
				}
				if (postfixexpr.get(i).charAt(0)=='*'){
					Multiplication op = new Multiplication();
					double result = op.doOp(op1, op2);
					s.push(result);
				}
				if (postfixexpr.get(i).charAt(0)=='/'){
					if (op1==0){
						return "Division by zero" ;
					}
					Division op = new Division();
					double result = op.doOp(op2, op1); 
					s.push(result);
				}
			}
		}
		answer =(double)s.pop();
		DecimalFormat formatter = new DecimalFormat("#0.0");  
		formatter.setRoundingMode(RoundingMode.DOWN);
		String tmp = formatter.format(answer);    
		
		return tmp  ;
	}
}
