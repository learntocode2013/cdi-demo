package org.javaee7.cdi;

import org.javaee7.cdi.constraints.ChronologicalDates;

import java.time.LocalDate;

//--- Data Bean
@ChronologicalDates
public class Order {
	private String    orderId      ;
	private LocalDate creationDate ;
	private LocalDate paymentDate  ;
	private LocalDate deliveryDate ;
	private double    totalWorth   ;

	public Order(LocalDate creationDate, LocalDate deliveryDate, String orderId,
	             LocalDate paymentDate, double totalWorth) {
		this.creationDate = creationDate;
		this.deliveryDate = deliveryDate;
		this.orderId = orderId;
		this.paymentDate = paymentDate;
		this.totalWorth = totalWorth;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	@Override
	public String toString() {
		return "Order{" +
				"creationDate=" + creationDate +
				", orderId='" + orderId + '\'' +
				", paymentDate=" + paymentDate +
				", deliveryDate=" + deliveryDate +
				", totalWorth=" + totalWorth +
				'}';
	}
}
