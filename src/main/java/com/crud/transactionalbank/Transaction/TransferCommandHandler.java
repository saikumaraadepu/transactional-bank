package com.crud.transactionalbank.Transaction;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
public class TransferCommandHandler {
	private final BankAccountRepository bankAccountRepository;
	public TransferCommandHandler(BankAccountRepository bankAccountRepository) {
		this.bankAccountRepository = bankAccountRepository;
	}

	public ResponseEntity<String> transfer(TransferDTO transfer) {
		Optional<BankAccount> fromAccount = bankAccountRepository.findById(transfer.getFromUser());
		Optional<BankAccount> toAccount = bankAccountRepository.findById(transfer.getToUser());

		if (fromAccount.isEmpty() || toAccount.isEmpty()) {
			throw new RuntimeException("User not found");
		}

		BankAccount from = fromAccount.get();
		BankAccount to = toAccount.get();

		add(to, transfer.getAmount());
		deduct(from, transfer.getAmount());
		return ResponseEntity.ok().body("Transfer successful");
	}

	private void deduct(BankAccount bankAccount, double amount) {
		if (bankAccount.getBalance() < amount) {
			throw new RuntimeException("Insufficient funds");
		}
		bankAccount.setBalance(bankAccount.getBalance() - amount);
	}

	private void add(BankAccount bankAccount, double amount) {
		bankAccount.setBalance(bankAccount.getBalance() + amount);
	}

}
