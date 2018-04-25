package com.qa.service.testrepo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

import com.qa.repository.AccountDBRepo;

@RunWith(MockitoJUnitRunner.class)
public class AccountDBRepoTest {
	
	@InjectMocks
	private AccountDBRepo repo;
	
	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"id\":2,\"firstName\":\"Jane\",\"secondName\":\"Doe\",\"accountNumber\":\"1235\"}]";

	private static final String MOCK_OBJECT = "{\"id\":2,\"firstName\":\"Jane\",\"secondName\":\"Doe\",\"accountNumber\":\"1235\"}";

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllAccounts() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account((long) 2, "Jane", "Doe", "1235"));
		Mockito.when(query.getResultList()).thenReturn(accounts);
		Assert.assertEquals(MOCK_DATA_ARRAY, repo.listAllAccounts());
	}

	@Test
	public void testCreateAccount() {
		String reply = repo.createAccount(MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"Response\": \"Account created!\"}");
	}
	
	@Test
	public void testDeleteAccount() {
		String reply = repo.delete(2);
		Assert.assertEquals(reply, "{\"Response\": \"Account deleted!\"}");
	}

	@Test
	public void testUpdateAccount() {
		String reply = repo.updateAccount(2, MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"Response\": \"Account updated!\"}");
	}
	
	@Test
	public void UpdateAccountFail() {
		String reply = repo.updateAccount(2, null);
		Assert.assertEquals(reply, "{\"Response\": \"Account not found so unable to update!\"}");
	}

}