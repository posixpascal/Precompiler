package com.pascalraszyk;

import java.util.HashMap;

public class PRScriptParser {
	private HashMap vars;
	
	public String parse(String command, HashMap variables){
		// get types of (a, b, c)
		// makes @setter: a, b, c to setMethods for EACH: a,b,c
		this.vars = variables;
		// split method out.
		String method = command.split(":")[0].split("@")[1];
		String methodContent;
		
		// return method;
		
		if (method.equals("setter"))
		{
			// check if multiple vars are setters
			methodContent = command.split(";")[0].split("setter:")[1];
			if (methodContent.contains(","))
			{
				String[] vars = methodContent.split(",");
				return multiSetter(vars);
			}
			
			// only 
			return setter(methodContent.trim());
		}
		else if (method.equals("getter"))
		{
			methodContent = command.split(";")[0].split("getter:")[1];
			if (methodContent.contains(","))
			{
				String[] vars = methodContent.split(",");
				return multiGetter(vars);
			}

			return getter(methodContent.trim());
		}
		
		
		return "METHOD_UNDEFINED (implement: " + method + ")";
		
	}
	
	/* @setter: */
	public String multiSetter(String[] vars){
		String w = "";
		for (int i = 0; i < vars.length; i++)
			w += this.setter(vars[i].trim()) + "\n";
			
		return w;
	}
	public String setter(String s){
		// check if s is in variableList
		String type = "Object";
		if (this.vars.containsKey(s)) {
			type = this.vars.get(s).toString();
		}
		
		String firstLetter = Character.toString(s.charAt(0));
		String newString = firstLetter.toUpperCase() + s.substring(1);
	
		return ("public void set" + newString + "(" + type + " " + s +  "){this." + s + " = " + s + ";}");
	}
	
	
	/* @getter: */
	public String multiGetter(String[] vars){
		String w = "";
		for (int i = 0; i < vars.length; i++)
			w += this.getter(vars[i].trim()) + "\n";
			
		return w;
	}
	
	public String getter(String s){
		String type = "Object";
		if (this.vars.containsKey(s)) {
			type = this.vars.get(s).toString();
		}
		
		String firstLetter = Character.toString(s.charAt(0));
		String newString = firstLetter.toUpperCase() + s.substring(1);
	
		return ("public " + type + " get" + newString + "(){return this." + s + ";}");
		
	}
}	