package com.crud.transactionalbank.Transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

	private final TransferCommandHandler transferCommandHandler;
	public BankController(TransferCommandHandler transferCommandHandler) {
		this.transferCommandHandler = transferCommandHandler;
	}

	@PostMapping("/transfer")
	public ResponseEntity transfer(@RequestBody TransferDTO transferDTO) {
		return transferCommandHandler.transfer(transferDTO);
	}
}
