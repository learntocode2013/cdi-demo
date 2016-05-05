package org.javaee7.cdi;

import org.javaee7.cdi.constraints.ChronologicalDates;
import org.javaee7.cdi.constraints.groups.Delivery;
import org.javaee7.cdi.constraints.groups.Payment;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.groups.Default;
import java.time.LocalDate;

//--- Data Bean
@ChronologicalDates(groups = {Default.class,Payment.class,Delivery.class})
public class Order {
	public String getOrderId() {
		return orderId;
	}

	private String    orderId      ;
	@NotNull
	@Past
	private LocalDate creationDate ;

	@NotNull(groups = Payment.class)
	@Past(groups = Payment.class)
	private LocalDate paymentDate  ;

	@NotNull(groups = Delivery.class)
	@Future(groups = Delivery.class)
	private LocalDate deliveryDate ;

	private double    totalWorth   ;

	//@Sanitize
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Order order = (Order) o;

		if (Double.compare(order.totalWorth, totalWorth) != 0) return false;
		if (!orderId.equals(order.orderId)) return false;
		if (!creationDate.equals(order.creationDate)) return false;
		if (!paymentDate.equals(order.paymentDate)) return false;
		return deliveryDate.equals(order.deliveryDate);

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = orderId.hashCode();
		result = 31 * result + creationDate.hashCode();
		result = 31 * result + paymentDate.hashCode();
		result = 31 * result + deliveryDate.hashCode();
		temp = Double.doubleToLongBits(totalWorth);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
