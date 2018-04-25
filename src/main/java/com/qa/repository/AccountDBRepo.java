package com.qa.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.persistence.Query;
import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class AccountDBRepo {

	@Inject
	private JSONUtil util;

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	public String listAllAccounts() {
		Query query = manager.createQuery("Select n FROM Account n");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();

		return util.getJSONForObject(accounts);
	}

	public Account findAccount(Long id) {

		return manager.find(Account.class, id);

	}

	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Account aAccount = util.getObjectForJSON(account, Account.class);
		manager.persist(aAccount);

		return "{\"Response\": \"Account created!\"}";
	}

	@Transactional(REQUIRED)
	public String updateAccount(long id, String updateSetAccount) {
		Account updatedAccount = util.getObjectForJSON(updateSetAccount, Account.class);
		Account originalAccount = findAccount(id);
		if (updateSetAccount != null) {

			originalAccount = updatedAccount;
			manager.merge(originalAccount);

			return "{\"Response\": \"Account updated!\"}";
		}

		else {

			return "{\"Response\": \"Account not found so unable to update!\"}";

		}
	}

	@Transactional(REQUIRED)
	public String delete(long id) {
		manager.remove(manager.getReference(Account.class, id));

		return "{\"Response\": \"Account deleted!\"}";
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}
}