package com.lib.WalletMS.controller;

import org.springframework.http.ResponseEntity;

import com.lib.WalletMS.exception.InsufficientBalanceException;
import com.lib.WalletMS.models.Wallet;

public interface IWalletController {

	//Method to get Wallet
	ResponseEntity<Wallet> getWallet(String token, String username);

	//Call create wallet when user registers
	void createWallet(String username);

	//Deduct from wallet using orderid and fine
	void deductWallet(String token, String username, int orderid, String bookName, double fine)
			throws InsufficientBalanceException;

	//Add amount to wallet
	ResponseEntity<Object> addToWallet(String token, String username, double amount);

}