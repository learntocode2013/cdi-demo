package org.javaee7.cdi;

import org.javaee7.cdi.producers.Account;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.logging.Logger;

/* TODO: Find out how to create a Conversation Context */

@ConversationScoped
public class CustomerCreatorWizard implements Serializable {
	private Account customerAccount  ;
	private Login   loginCredentials ;

	@Inject
	private Conversation    conversation    ;
	@Inject
	private transient CustomerService customerService ;
	@Inject
	private transient Logger          logger          ;

	public CustomerCreatorWizard saveLogin(String userName, String passwd) {
		conversation.begin() ;
		loginCredentials = new Login(userName,passwd) ;
		logger.info("---- STEP-1: Login details created");
		return this;
	}

	public CustomerCreatorWizard saveAccount(String firstName, String lastName, String email) {
		customerAccount = new Account(email) ;
		customerAccount.set_firstName(firstName) ;
		customerAccount.set_lastName(lastName) ;
		logger.info("---- STEP-2: Accout info created");
		return this;
	}

	public void createCustomer() {
		Customer newCustomer = new Customer(loginCredentials,customerAccount);
		customerService.create(newCustomer);
		conversation.end();
		logger.info("---- STEP-3: Customer created in system");
	}
}
