/* Lexer
--------
Not a "real" lexer, well. The real Lexer features are coming soon.
Regex Tokenizing etc.
--------

REMEMBER: THIS IS JUST FOR FUN! :D
*/


package com.pascalraszyk;

import com.pascalraszyk.PRScriptParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap; // HashList/HashSet/whatever you want to use.. (fix the var below)
import java.io.File; // I SUCK

public class Lexer {
	final double version = 0.6;
	private String file;
	private String[] lines; 
	private int fileLength;
	private PRScriptParser pr = new PRScriptParser();
	
	private List<String> compiledFile = new ArrayList<String>(); // THATS JAVA! :3
	
	/* CHARSEQUENCES */
	private CharSequence CPRIVATE = "private";
	private CharSequence CMAIN = "void main"; // change this to regex later
	private CharSequence CPRINT = "print("; // change this to regex later
	
	private CharSequence CSTRING = "String";
	private CharSequence CINT = "int";
	private CharSequence COBJECT = "Object";
	private CharSequence CDOUBLE = "double";
	private CharSequence CFLOAT = "float";
	private CharSequence CSTRINGA = "String[]";
	private CharSequence CINTA = "int[]";
	private CharSequence COBJECTA = "Object[]";
	private CharSequence CDOUBLEA = "double[]";
	private CharSequence CFLOATA = "float[]"; // basic only
	
	/* other types: try to fetch them automatically. (HashMap, File etc.) */
	/* ScriptParser Required Vars */
	// NOW PLAYING: I CRY - Flo Rida - SPOTIFY RULES <3 (nonsense comment)
	
	HashMap variables = new HashMap();
	
	
	/* Constructors */
	public Lexer() {}
	public Lexer(String file) throws IOException {
		this.file = file;
		// Buffer Lines to Array:
		this.lines = readLines();
		this.fileLength = this.lines.length;
		// Add Copyright and shit :P
		this.compiledFile.add("/*");
		this.compiledFile.add(" - Written in PRScript - Compiled to: Java");
		this.compiledFile.add("*/");
	}
	
	public void compile(){
		// compile current file.
		String l = "";
		for (int i = 0; i < this.fileLength; i++){
			l = this.lines[i];
			l = l.trim(); // Strip leading whitespaces.
			
			if (l.length() > 0 && l.charAt(0) == '@') { // PRScript Var
				this.compiledFile.add(this.pr.parse(l, this.variables));
				
			}
			else if (l.contains(this.CMAIN)){
				// short main method - recreate!
				this.compiledFile.add(l.replace("void main", "public static void main"));
			}
			else if (l.contains(this.CPRINT))
			{
				// Short print() Function
				if (!l.contains("System.out."))
					this.compiledFile.add(l.replace("print", "System.out.print"));
			}
			/* 
				TODO HERE:
				
				- Find method declerations (public & ()  as Identifiers)
				- Find variables and add them (through static types)
				
			
			*/
			else if (l.contains(this.CPRIVATE)) // var definition
			{
					// Detect Var Type and Value(mostly type)
					String value = l.split("=")[1].split(";")[0];
					String nameAndType = l.split("=")[0];
					String name = nameAndType.split(" ")[2];
					
					String type = find_type_in(nameAndType.split(" ")[1]);
					// System.out.println("Define " + name + " as " + type); //DEBUG
					//Object[] values = {type, i}; // lineNumber and shit.
					// name, type (save this for ScriptParser)
					this.variables.put(name, type);
					
					// done! variable saved.
					value = null;
					nameAndType = null;
					name = null;
					type = null;
					//values = null; 
					
					// save the var to the compiledFile through (this is just for var detection)
					this.compiledFile.add(l);
					
					// GARBAGE COLLECTOR! I CHOOSE YOU!
			}
			else {
				// just save code to rawFile
				this.compiledFile.add(l);
			}
			
		}
		
		// WRITE THE GOD DAMN FILE!
		try {
			File f = new File("CompiledCode.java");
			FileWriter fw = new FileWriter(f);
			for (String line : this.compiledFile){ // AGAIN! JAVA -.-
				fw.write(line + "\n"); // CHANGE THIS TO WINDOWS \r\n MAYBE?
			}
			fw.flush(); // flush the toilet!
			fw.close(); // close the toilet! :D
			System.out.print("I SUCK SO HARD. BUT I FINISHED IT.\n");
		} catch (IOException p){
			p.printStackTrace();
		}
		// How about pretty print? o_o
		// How about more features? o_o
		// How about SHUT THE FUCK UP? o_o
		// time is money, I don't have time so I neither have money.
	}
	
	
	public String find_type_in(String a){
		return a; // I AM SUCH A BADASS DUDE
	}
	
	public String[] getFileContentArray() {
		return this.lines; // hehe. WUT?
	}
	

	
	public String[] readLines() throws IOException { // DAT CODE <3
		FileReader fileReader = new FileReader(this.file);
		BufferedReader br = new BufferedReader(fileReader);
		List<String> lines = new ArrayList<String>();
		String line = null;
		
		while ((line = br.readLine()) != null) {
			lines.add(line);
		}
		
		br.close();
		return lines.toArray(new String[lines.size()]);
	}
}