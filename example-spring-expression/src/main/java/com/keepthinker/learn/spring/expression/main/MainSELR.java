package com.keepthinker.example.spring.expression.main;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.keepthinker.example.spring.expression.model.Inventor;
import com.keepthinker.example.spring.expression.model.PlaceOfBirth;

/**
 * Language Reference
 * @author keshengkai
 *
 */
public class MainSELR {
	public static void main(String[] args){
		ExpressionParser parser = new SpelExpressionParser();
		literalExpressions(parser);
		propertiesArraysListsMapsIndexers(parser);
		types(parser);
		variables(parser);
		ternaryOperator(parser);
		elvis(parser);
		safeNavigationOperator(parser);
		expressionTemplating(parser);
	}

	public static void literalExpressions(ExpressionParser parser){
		// evals to "Hello World"
		String helloWorld = (String) parser.parseExpression("'Hello World'").getValue();
		System.out.println(helloWorld);
		double avogadrosNumber
		= (Double) parser.parseExpression("6.0221415E+23").getValue();
		System.out.println(avogadrosNumber);
		// evals to 2147483647
		int maxValue = parser.parseExpression("0x7FFFFFFF").getValue(Integer.class);
		System.out.println(maxValue);
		boolean trueValue = (Boolean) parser.parseExpression("true").getValue();
		System.out.println(trueValue);
		Object nullValue = parser.parseExpression("null").getValue();
		System.out.println(nullValue);
	}

	public static void propertiesArraysListsMapsIndexers(ExpressionParser parser){
		Inventor inventor = new Inventor("Kate", new Date(), "US");
		// evals to 1856
		int year = (Integer) parser.parseExpression("Birthdate.Year + 1900").getValue(inventor);
		System.out.println(year);
		inventor.setPlaceOfBirth(new PlaceOfBirth("Shen Zhen", "China"));
		String city = (String) parser.parseExpression("placeOfBirth.City").getValue(inventor);
		System.out.println(city);
		class Holder {
			// you can have the same result with inventor being declared with the modifier public and without get set method; 
			private Inventor[] inventors = 
				{
						new Inventor("Mike", new Date(), "British"),
				};

			public Inventor[] getInventors() {
				return inventors;
			}
			public void setInventors(Inventor[] inventors) {
				this.inventors = inventors;
			}

			public Map<String, Inventor> maps = new TreeMap<>();

			public Holder(){
				maps.put("1", inventors[0]);
				maps.put("2", new Inventor("Obama", new Date(), "US"));
			}

			public String getStr(){
				return "string";
			}
		}

		Holder holder = new Holder();
		Inventor inventor1 = parser.parseExpression("inventors[0]").getValue(holder,
				Inventor.class);
		System.out.println(inventor1.getName());

		Inventor inventor2 = parser.parseExpression("maps['2']").getValue(holder,
				Inventor.class);
		System.out.println(inventor2.getName());

		System.out.println(parser.parseExpression("{1,2}").getValue());

		System.out.println(parser.parseExpression("getStr()").getValue(holder));

	}

	@SuppressWarnings("rawtypes")
	public static void types(ExpressionParser parser){
		Class dateClass = parser.parseExpression("T(java.util.Date)").getValue(Class.class);
		System.out.println(dateClass);
		Class stringClass = parser.parseExpression("T(String)").getValue(Class.class);
		System.out.println(stringClass);
		boolean trueValue =
				parser.parseExpression("T(java.math.RoundingMode).CEILING < T(java.math.RoundingMode).FLOOR")
				.getValue(Boolean.class);
		System.out.println(trueValue);
	}

	public static void variables(ExpressionParser parser){
		Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
		StandardEvaluationContext context = new StandardEvaluationContext(tesla);
		context.setVariable("newName", "Mike Tesla");
		parser.parseExpression("Name = #newName").getValue(context);
		System.out.println(tesla.getName()); // "Mike Tesla"
	}

	/*	public static void beanReference(){
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setBeanResolver(new MyBeanResolver());
		// This will end up calling resolve(context,"foo") on MyBeanResolver during evaluation
		Object bean = parser.parseExpression("@foo").getValue(context);
	}*/

	public static void ternaryOperator(ExpressionParser parser){
		class RootObject{
			private String name;
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public boolean isMember(String queryName){
				return "Nikola Tesla".equals(queryName);
			}
		}
		StandardEvaluationContext societyContext = new StandardEvaluationContext(new RootObject());
		parser.parseExpression("Name").setValue(societyContext, "IEEE");
		societyContext.setVariable("queryName", "Nikola Tesla");
		String expression = "isMember(#queryName)? #queryName + ' is a member of the ' " +
				"+ Name + ' Society' : #queryName + ' is not a member of the ' + Name + ' Society'";
		String queryResultString = parser.parseExpression(expression)
				.getValue(societyContext, String.class);
		System.out.println(queryResultString);

	}

	public static void elvis(ExpressionParser parser){
		Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
		StandardEvaluationContext context = new StandardEvaluationContext(tesla);
		String name = parser.parseExpression("Name?:'Elvis Presley'").getValue(context, String.class);
		System.out.println(name); // Nikola Tesla
		tesla.setName(null);
		name = parser.parseExpression("Name?:'Elvis Presley'").getValue(context, String.class);
		System.out.println(name); // Elvis Presley
	}

	public static void safeNavigationOperator(ExpressionParser parser){
		Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
		tesla.setPlaceOfBirth(new PlaceOfBirth("Smiljan"));
		StandardEvaluationContext context = new StandardEvaluationContext(tesla);
		String city = parser.parseExpression("PlaceOfBirth?.City").getValue(context, String.class);
		System.out.println(city); // Smiljan
		tesla.setPlaceOfBirth(null);
		city = parser.parseExpression("PlaceOfBirth?.City").getValue(context, String.class);
		System.out.println(city); // null - does not throw NullPointerException!!!
	}

	public static void expressionTemplating(ExpressionParser parser){
		class TemplateParserContext implements ParserContext {
			public String getExpressionPrefix() {
				return "#{";
			}
			public String getExpressionSuffix() {
				return "}";
			}
			public boolean isTemplate() {
				return true;
			}
		}
		String randomPhrase = parser.parseExpression(
				"random number is #{T(java.lang.Math).random()}",
				new TemplateParserContext()).getValue(String.class);
		System.out.println(randomPhrase);
		
	}
}
