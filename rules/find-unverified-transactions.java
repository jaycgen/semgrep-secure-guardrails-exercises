/*
 * In this financial trading application, every transaction MUST be verified before it is made
 *
 * Specifically:
 *   verify_transaction() must be called on a transaction object before that object is passed to
 *   make_transaction() 
 */
public class TransactExample {
	public void base_ok(Transaction t) {
		// OK: verify called before make
        verify_transaction(t);
		make_transaction(t);
	}
    
	// ruleid:find-unverified-transactions
	public void no_verify(Transaction t) {
		// BAD: transaction isn’t verified
		make_transaction(t);
	}

	// ruleid:find-unverified-transactions
	public void late_verify(Transaction t){
		// BAD: transaction verified after being made
		make_transaction(t);
		verify_transaction(t);
	}

	public void verify_only(Transaction t) {
		// OK: no transaction occurs
		verify_transaction(t); 
	}         

	// ruleid:find-unverified-transactions
	public void other_statements_bad(Transaction t) {
		// bad: verify called before make
		System.out.println("making transaction");
		make_transaction(t);
		System.out.println("about to verify");
		verify_transaction(t);
		System.out.println("verified!"); 
	}    

	public void other_statements_ok(Transaction t) {
		// OK: verify called before make
		System.out.println("about to verify");
		verify_transaction(t);
		System.out.println("verified");
		make_transaction(t);
		System.out.println("success!"); 
	} 

	// ruleid:find-unverified-transactions
	public void verify_wrong_transaction(Transaction t) {
		// BAD: the wrong transaction is verified
		Transaction other = new Transaction();
		verify_transaction(other);
		make_transaction(t);
	}    
}