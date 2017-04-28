package com.keepthinker.example.general.test.mokito;

import junit.framework.Assert;
import org.junit.Test;

import org.mockito.Mockito;

public class SimpleTest {

	@Test
	public void testA(){
		Attack test = Mockito.mock(Attack.class);
		int i = 5;
		Result result = test.shot(i);
		System.out.println(result);
		Mockito.when(result).thenReturn(new Result(true, "Destroy " + i + " targets"));
		result = test.shot();
		System.out.println(test.shot(i));
	}

	@Test
	public void testReturnValueDependentOnMethodParameter()  {
		@SuppressWarnings("unchecked")
		Comparable<String> c= Mockito.mock(Comparable.class);
		Mockito.when(c.compareTo("Mockito")).thenReturn(1);
		Mockito.when(c.compareTo("Eclipse")).thenReturn(2);
		//assert
		Assert.assertEquals(1,c.compareTo("Mockito"));
	}

	@Test
	public void testB(){
		Attack test = Mockito.mock(Attack.class);
		for(int i = 0; i < 10; i++){
			Mockito.when(test.shot(i)).thenReturn(new Result(true, "Destroy " + i + " targets"));
		}
		System.out.println(test.shot(3));
		System.out.println(test.shot(3));
		System.out.println(test.shot(7));
		System.out.println(test.shot(12));
		
		Mockito.verify(test).shot(Mockito.eq(12));  //if argument with 12 is tested before
		Mockito.verify(test, Mockito.times(2)).shot(3); //if shot(3) was executed 2 times
	}
}
