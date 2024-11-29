package com.crud.transactionalbank.Transaction;

import lombok.Data;

@Data
public class TransferDTO {

	private String fromUser;
	private String toUser;
	private double amount;
}
