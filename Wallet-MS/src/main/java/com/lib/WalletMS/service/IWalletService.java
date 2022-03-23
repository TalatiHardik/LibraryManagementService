package com.lib.WalletMS.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lib.WalletMS.exception.InsufficientBalanceException;
import com.lib.WalletMS.models.FinedOrder;
import com.lib.WalletMS.models.Wallet;

import net.sf.jasperreports.engine.JRException;

public interface IWalletService {

	void createWallet(String Username);

	//Method to deduct from wallet based on orderid and fine
	void deductWallet(String token, String Username, int orderId, String bookName, double fine)
			throws InsufficientBalanceException;

	ResponseEntity<Object> addWallet(String token, String Username, double amount);

	//	public Wallet getWallet(String token ,String Username) {
	//		if(checkToken(token)) {
	//			return	walletRepo.findByUsername(Username);	
	//		}
	//		else
	//			return null;
	//	}
	Wallet getWallet(String token, String Username);

	Boolean checkToken(String token);

	ResponseEntity<Object> exportInvoice(String username, int orderId, String bookName)
			throws FileNotFoundException, JRException;

	List<Integer> getOrderIds(String username);

	List<FinedOrder> getOrderDetailsById(String username ,int orderid);

}