package com.keepthinker.spring.general.database;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

public class SimpleService {
	private final TransactionTemplate transactionTemplate;
	// use constructor-injection to supply the PlatformTransactionManager
	public SimpleService(PlatformTransactionManager transactionManager) {
		Assert.notNull(transactionManager, "The ''transactionManager'' argument must not be null.");
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}
	
	public Object someServiceMethod() {
		return transactionTemplate.execute(new TransactionCallback<Object>() {
			// the code in this method executes in a transactional context
			public Object doInTransaction(TransactionStatus status) {
				//updateOperation1();
				return null;//resultOfUpdateOperation2();
			}
		});
	}

	public void someServiceMethodWithoutArgs() {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				//updateOperation1();
				return;//resultOfUpdateOperation2();
				
			}
		});
	}
}
