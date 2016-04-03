package org.javaee7.cdi.producers;

import org.javaee7.cdi.annotations.ThirteenDigits;

import javax.enterprise.inject.Produces;

public class NumberProducer {
	@Produces @ThirteenDigits
	private String prefix = "13-" ;
	@Produces @ThirteenDigits
	private int editorNumber = 8576 ;

	@Produces @org.javaee7.cdi.annotations.Random
	public double getPostFix() {
		return Math.abs(new java.util.Random().nextInt());
	}
}
