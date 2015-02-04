package Calculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class Lab1_test {

	Calculator C=new Calc(); 

	@Test
	public void Lab1_test0() {
	C.Input("");
	assertEquals(Calculator.BAD_FORMAT, C.getResults());
	}
	
	@Test
	public void Lab1_test1() {
	C.Input("3.5-2*4/4");
	assertEquals("1.5", C.getResults());
	}
	
	@Test
	public void Lab1_test2() {
	C.Input("9*3/5-5");
	assertEquals("0.4", C.getResults());
	}
	
	@Test
	public void Lab1_test3() {
	C.Input("0/5-5");
	assertEquals("-5.0", C.getResults());
	}
	
	@Test
	public void Lab1_test4() {
	C.Input("5*-4/10");
	assertEquals("-2.0", C.getResults());
	}
	
	@Test
	public void Lab1_test5() {
		C.Input("5*-4/10");
		assertEquals("-2.0", C.getResults());
	}
	
	@Test
	public void Lab1_test6() {
	C.Input("5*-4/10");
	C.Input("17 / 2");
	C.prev();
	assertEquals("5*-4/10",C.current_formula());
	}
	@Test
	public void Lab1_test7() {
	C.Input("3+2");
	assertEquals(Calculator.END_LIST_UP,C.prev());
	}
	
	@Test
	public void Lab1_test8() {
	C.Input("5*-4/10");
	C.Input("17 / 2");
	C.Input("3+9");
	C.Input("15*8");
	C.Input("6/2");
	C.prev();
	assertEquals("120.0",C.getResults());
	}
	
	
	@Test
	public void Lab1_test9() {
	C.Input("17 / 2");
	C.Input("3+9");
	C.Input("15*8");
	C.Input("6/2");
	C.save();
	C.Input("3+2+5");
	C.Input("6-9");
	C.load();
	C.prev() ;
	C.prev() ;
	C.prev() ;
	
	assertEquals("17 / 2",C.current_formula());
	}
	
}
