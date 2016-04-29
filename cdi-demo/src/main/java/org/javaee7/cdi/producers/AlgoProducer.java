package org.javaee7.cdi.producers;

import org.javaee7.cdi.ValidationAlgorithm;
import org.javaee7.cdi.annotations.Algorithm;

import javax.enterprise.inject.Produces;

public class AlgoProducer {
	private final ValidationAlgorithm _algorithm ;

	public AlgoProducer() {
		_algorithm = new ValidationAlgorithm();
	}

	@Produces @Algorithm
	public ValidationAlgorithm get() {
		return _algorithm;
	}
}
