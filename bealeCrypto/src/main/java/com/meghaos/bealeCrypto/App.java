package com.meghaos.bealeCrypto;

import org.xml.sax.ext.DeclHandler;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
    	BealeCiphers.analyze();
    	String[] wordArr = BealeCiphers.DeclOfIndpendence.split(" ");
    	char[] charArr =new char[wordArr.length];
    	
    	for(int i=1; i<wordArr.length+1; i++) {
    		System.out.print (wordArr[i-1] +"("+i+") ");
    		charArr[i-1] = wordArr[i-1].charAt(0);
    	}
    	System.out.println();
    	int[] cipher = BealeCiphers.cipher2;
    	for(int j=0; j<cipher.length; j++) {
    		if(j<cipher.length){
	    		int k = cipher[j];
	    		if(k<charArr.length) {
	    		char c = charArr[k-1];
	    		System.out.print(c);
	    		}
    		}
    	};
    	
    }
}
