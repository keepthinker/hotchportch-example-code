package com.keepthinker.example.spring.expression.main;

import com.keepthinker.example.spring.expression.model.Inventor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
/**
 * Expression Evaluation using Spring's Expression Interface
 * @author keshengkai
 *
 */
public class MainSEI 
{
	public static void main( String[] args )
	{
		ExpressionParser parser = new SpelExpressionParser();
		general(parser);
		bytes(parser);
		bytesLength(parser);
		stringConstructor(parser);
		expressionAgainstObject(parser);
		typeConversion(parser);
	}

	private static void general(ExpressionParser parser){
		Expression expression = parser.parseExpression("'hello world'.concat('!')");
		System.out.println(expression.getValue());
	}

	private static void bytes(ExpressionParser parser){
		Expression expression = parser.parseExpression("'hello world!'.bytes");
		byte[] bytes = (byte[]) expression.getValue();
		for(int i = 0; i<bytes.length; i++){
			System.out.print((char)bytes[i]);
		}
		System.out.println();
	}

	private static void bytesLength(ExpressionParser parser){
		Expression expression = parser.parseExpression("'hello world!'.bytes.length");
		Integer length = (Integer) expression.getValue();
		System.out.println(length);
	}

	private static void stringConstructor(ExpressionParser parser){
		Expression expression = parser.parseExpression("new String('hello world!').toUpperCase()");
		String length = expression.getValue(String.class);
		System.out.println(length);
	}

	private static void expressionAgainstObject(ExpressionParser parser){
		GregorianCalendar c = new GregorianCalendar();
		Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
		c.set(1856, 7, 9);

		Expression exp = parser.parseExpression("name");
		EvaluationContext context = new StandardEvaluationContext(tesla);
		String name = (String) exp.getValue(context);
		System.out.println(name);

		System.out.println((String) exp.getValue(tesla));

		exp = parser.parseExpression("name == 'Nikola Tesla'");
		boolean result = exp.getValue(context, Boolean.class);
		System.out.println(result);
	}

	private static void typeConversion(ExpressionParser parser){
		class Simple {
			public List<Boolean> booleanList = new ArrayList<Boolean>();
			public String name;
		}

		Simple simple = new Simple();
		simple.booleanList.add(true);
		simple.name = "John";
		StandardEvaluationContext simpleContext = new StandardEvaluationContext(simple);
		// false is passed in here as a string. SpEL and the conversion service will
		// correctly recognize that it needs to be a Boolean and convert it
		parser.parseExpression("booleanList[0]").setValue(simpleContext, "false");
		// b will be false
		Boolean b = simple.booleanList.get(0);
		System.out.println(b);
		System.out.println(parser.parseExpression("name").getValue(simpleContext));
	}
}
