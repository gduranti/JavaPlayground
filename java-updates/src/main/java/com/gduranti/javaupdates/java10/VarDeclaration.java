package com.gduranti.javaupdates.java10;

import java.util.Arrays;

public class VarDeclaration {

    // Var is not allowed here
    // private var nome = "Marcio";

	public static void main(String... args) {
		VarDeclaration main = new VarDeclaration();
		main.varExemples();
	}

    private void varExemples() {
        System.out.println("\nVar Examples");
        System.out.println("-----------------------");

		// Until Java 9
		Integer i = 9;
		String s = getValue();
		System.out.println(s + i);

        // Type mismatch: cannot convert from String to Integer
        // i = "test";
        i = 2;

		// Java 10
		var x = 10;
		var y = getValue();
		System.out.println(y + x);

        // compile
        String name;
        name = "Java";
        System.out.println(name);

        // Does not compile
        // var idade;
        // idade = 10;

        // Complex object
        var complexObject = Arrays.asList("Ana", "Bia", "Lú");
        System.out.println(complexObject);
	}

    private String getValue() {
        return "Java ";
    }

}
