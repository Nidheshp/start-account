package com.qa.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

public class AccountServiceTest {

	private AccountService service;
	private Account johnDoe;
	private Account janeDoe;
	private JSONUtil util;

	@Before
	public void init() {
		service = new AccountService();
		johnDoe = new Account((long) 1, "John", "Doe", "1234");
		janeDoe = new Account((long) 2, "Jane", "Doe", "1235");
		util = new JSONUtil();
	}

	@Test
	public void addAndRemoveAccountTest() {
		service.addAccountFromMap(johnDoe);
		Assert.assertEquals(service.getAccountMap().size(), 1);
		service.addAccountFromMap(janeDoe);
		Assert.assertEquals(service.getAccountMap().size(), 2);
		service.removeAccountFromMap(0);
		Assert.assertEquals(service.getAccountMap().size(), 1);
		service.removeAccountFromMap(1);
		Assert.assertEquals(service.getAccountMap().size(), 0);
		service.removeAccountFromMap(5);
		Assert.assertEquals(service.getAccountMap().size(), 0);
	}

	@Test
	public void accountConversionToJSONTest() {
		String emptyMap = util.getJSONForObject(service.getAccountMap());
		Assert.assertEquals("{}", emptyMap);
		String accountAsJSON = "{\"0\":{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"},\"1\":{\"firstName\":\"Jane\",\"secondName\":\"Doe\",\"accountNumber\":\"1235\"}}";
		Assert.assertEquals("{}", emptyMap);
		service.addAccountFromMap(johnDoe);
		service.addAccountFromMap(janeDoe);
		String populatedAccountMap = util.getJSONForObject(service.getAccountMap());
		Assert.assertEquals(accountAsJSON, populatedAccountMap);
	}

	@Test
	public void getCountForFirstNamesInAccount() {
		Assert.assertEquals(service.getNumberOfAccountWithFirstName("Joe"), 0);
		service.addAccountFromMap(johnDoe);
		Assert.assertEquals(service.getNumberOfAccountWithFirstName("John"), 1);
		Account joeGordon = new Account((long) 1, "Joe", "Gordon", "1234");
		service.addAccountFromMap(joeGordon);
		Assert.assertEquals(service.getNumberOfAccountWithFirstName("Joe"), 1);
	}

}