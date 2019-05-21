package edu.handong.analysis.utils;

public class NotEnoughArgumentException extends Exception {
	public NotEnoughArgumentException() {
		super("Not enough argument");
	}
	
	public NotEnoughArgumentException(String message) {
		super(message);
	}
}
