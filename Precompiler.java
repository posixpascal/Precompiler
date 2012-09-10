import com.pascalraszyk.Lexer;
import com.pascalraszyk.PRScriptParser; // Just to compile on-fly with Precompiler;

// prettyprint:

import java.io.IOException;

public class Precompiler {
	public static void main(String[] args) throws IOException {
		String file = args[0];
		
		Lexer l = new Lexer(file);
		
		// Parse Informations and shit.
		
		l.compile(); // Compile PR-Script to Java
		
		// JavaCompiler.syscompile(l.getFilePath());
	}
}