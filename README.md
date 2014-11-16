Precompiler
===========

Because every german school uses stupid java in their programming courses I wrote this neat little Precompiler.
Guess what, it's written in Java itself because I don't know any better. JK, I thought it would fit nice.

This thing isn't really a precompiler, well it is, but in fact I use such a stupid method to precompile it that it would blame every other precompiler out there...

Instead of coding a simple tokenizer, I prefer the SPLIT/REGEX tokening... 

This thing does:

simplify getter & setter methods by adding a new set of functions.
You can for example do this using my Precompiler:

```
public class ExampleCode {

	private int myVar = 0;
	private String myName = "Tom!";
	
	@setter: myVar, myName;
	@getter: myVar;
	
	void main(String[] args){
		print("Easy Printings!");
	}
	
}
```

The @setter, @getter at-script creates setter/getter methods for every variable (you can also comma seperate variables if you want to).

That's it. hillarious right? But i'm tired of all these setter/getter methods. This is rly useless after someone told me eclipse(or any other IDE) would automatically generate them...
So I thought, hey, let's add an @import statement as well, which includes another file. it's like a partial for java source.

README was added 4 years later. lol.
