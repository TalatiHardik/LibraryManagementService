package com.lib.WalletMS.controller;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.WalletMS.exception.InsufficientBalanceException;
import com.lib.WalletMS.models.Wallet;
import com.lib.WalletMS.service.IWalletService;

import net.sf.jasperreports.engine.JRException;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/wallet")
public class WalletController implements IWalletController {
	private static Logger logger=LoggerFactory.getLogger(WalletController.class);

	@Autowired
	private IWalletService walletserivce;
	
	//Method to get Wallet
	@Override
	@GetMapping("/{username}")
	public ResponseEntity<Wallet> getWallet(@ApiIgnore @RequestHeader("Authorization") String token ,@PathVariable String username){
		logger.info("Inside get wallet controller");
		return new ResponseEntity<>(walletserivce.getWallet(token,username), HttpStatus.OK);
	}
	
	@GetMapping("/generate-invoice/{username}/{orderid}/{bookname}")
	public ResponseEntity<Object> generateInvoice(@PathVariable String username, @PathVariable int orderid, @PathVariable String bookname) throws FileNotFoundException, JRException{
	return walletserivce.exportInvoice(username, orderid, bookname);
	}
	//Call create wallet when user registers
	@Override
	@PostMapping("/{username}")
	public void createWallet(@PathVariable String username){
		logger.info("Inside create wallet controller");
		walletserivce.createWallet(username);
		logger.info("End of create wallet controller");
	}
	
	//Deduct from wallet using orderid and fine
	@Override
	@PutMapping("/deductWallet/{username}/{orderid}/{bookName}/{fine}")
	public void deductWallet(@ApiIgnore @RequestHeader("Authorization") String token , @PathVariable String username ,@PathVariable int orderid ,@PathVariable String bookName,@PathVariable double fine) throws InsufficientBalanceException {
		logger.info("Inside deduct wallet controller");
			// wallet = walletserivce.deductWallet(token,username,orderid,fine);
			 walletserivce.deductWallet(token,username,orderid,bookName,fine);
		
		
	}
	
	//Add amount to wallet
	@Override
	@PutMapping("/addToWallet/{username}/{amount}")
	public ResponseEntity<Object> addToWallet(@ApiIgnore @RequestHeader("Authorization") String token , @PathVariable String username ,@PathVariable double amount) {
		logger.info("Inside add wallet controller");
		return walletserivce.addWallet(token,username,amount);
	}
	
	
	@GetMapping("/get-order-ids/{username}")
    public  ResponseEntity<Object> getOrderIds(@PathVariable String username)
    {
        return new ResponseEntity<>(walletserivce.getOrderIds(username), HttpStatus.OK);
    }

    @GetMapping("/get-order-details/{username}/{orderid}")
    public  ResponseEntity<Object> getOrderDetailsById(@PathVariable String username, @PathVariable int orderid)
    {
        return new ResponseEntity<>(walletserivce.getOrderDetailsById(username , orderid), HttpStatus.OK);
    }
	
	
	
}
