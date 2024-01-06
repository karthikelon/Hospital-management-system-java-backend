package com.hexaware.exception;


public class AuthorizationException  extends Exception{

	public void AuthorizationException(){
		System.out.println("User Not Found ");
	}

	public String toString(){
		return "incorrect credentials";
		
	}
}
