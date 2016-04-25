package org.javaee7.cdi;

import org.javaee7.cdi.interceptors.LoggingInterceptor;

import javax.interceptor.Interceptors;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Interceptors(LoggingInterceptor.class)
public class Book {
	@NotNull(message = "Book must have a title")
	@Size(min=1, max=20, message = "Book title length must be between 1-20 characters")
	private String title;
	@Size(min = 8, max = 200)
	private String description;
	@NotNull(message = "Book must contain an identification number")
	private String number;
	@NotNull @Min(value = 5, message = "Book price must be atleast 5 dollars or more")
	private Float price;

	public Book(String description, String number, Float price, String title) {
		this.description = description;
		this.number = number;
		this.price = price;
		this.title = title;
	}

	public Book() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Book book = (Book) o;

		if (!title.equals(book.title)) return false;
		if (!description.equals(book.description)) return false;
		if (!number.equals(book.number)) return false;
		return price.equals(book.price);

	}

	/*
	@Override
	public int hashCode() {
		int result = title.hashCode();
		result = 31 * result + description.hashCode();
		result = 31 * result + number.hashCode();
		result = 31 * result + price.hashCode();
		return result;
	}*/

	@Override
	public String toString() {
		return "Book{" +
				"description='" + description + '\'' +
				", title='" + title + '\'' +
				", number='" + number + '\'' +
				", price=" + price +
				'}';
	}
}
