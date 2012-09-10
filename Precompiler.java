import com.pascalraszyk.Lexer;
import com.pascalraszyk.PRScriptParser; // Just to compile on-fly with Precompiler;

import java.io.IOException;

public class Precompiler {
	public static void main(String[] args) throws IOException {
		String file = args[0];
		
		Lexer l = new Lexer(file);
		
		// Parse Informations and shit. (nothign done yet)
		
		l.compile(); // Compile PR-Script to Java
		
		// JavaCompiler.syscompile(l.getFilePath()); // (well.. how do I syscompile? xD)
	}
}