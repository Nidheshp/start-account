package com.qa.application;

import com.qa.domain.Account;
import com.qa.service.AccountService;
import com.qa.util.JSONUtil;

public class App {

	public static void main(String[] args) {
		AccountService service = new AccountService();
		JSONUtil util = new JSONUtil();
		Account johnDoe = new Account((long) 1, "John", "Doe", "1234");
		Account janeDoe = new Account((long) 2, "Jane", "Doe", "1235");
		service.addAccountFromMap(johnDoe);
		service.addAccountFromMap(janeDoe);
		String mapAsJSON = util.getJSONForObject(service.getAccountMap());
		System.out.println("This is the account map as JSON " + mapAsJSON);

	}

}
