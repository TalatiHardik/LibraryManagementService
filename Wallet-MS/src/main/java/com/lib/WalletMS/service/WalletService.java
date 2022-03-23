package com.lib.WalletMS.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.lib.WalletMS.clients.AuthClient;
import com.lib.WalletMS.exception.InsufficientBalanceException;
import com.lib.WalletMS.models.FinedOrder;
import com.lib.WalletMS.models.Response;
import com.lib.WalletMS.models.Wallet;
import com.lib.WalletMS.repository.FinedOrderRepo;
import com.lib.WalletMS.repository.WalletRepo;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service
public class WalletService implements IWalletService {

	static Logger logger=LoggerFactory.getLogger(WalletService.class);

	@Autowired
	private WalletRepo walletRepo;

	@Autowired
	private AuthClient authClient ;

	@Autowired
	private FinedOrderRepo finedOrderRepo;

	private static int iid = 999;

	private static int oid=0;

	@Override
	public ResponseEntity<Object> exportInvoice(String username, int orderId, String bookName) throws FileNotFoundException, JRException {
		List <FinedOrder> orders = finedOrderRepo.findByOrderIdAndBookName(orderId, bookName);
		orders.get(0).setTransaction(username);
		File file = ResourceUtils.getFile("classpath:invoice.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "team-b");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\"+System.getProperty("user.name")+"\\Downloads"+"\\lmsinvoice"+(++iid)+".pdf");
		return new ResponseEntity<>(new Response("Invoice downloaded"), HttpStatus.OK);
	}

	public Comparator<FinedOrder> walletComp = new Comparator<FinedOrder>() {
		public int compare(FinedOrder f1, FinedOrder f2) {
			int o1 = f1.getOrderId();
			int o2 = f2.getOrderId();
			return o2-o1;
		}
	};

	@Override
	public void createWallet(String Username) {
		logger.info("Started create Wallet method");
		Wallet wallet = new Wallet();
		wallet.setUsername(Username);
		wallet.setBalance(10);
		wallet.setOrders(null);
		walletRepo.save(wallet);
		logger.info("Ended create Wallet method");
	}

	//Method to deduct from wallet based on orderid and fine
	@Override
	public void deductWallet(String token,String Username,int orderId ,String bookName, double fine) throws InsufficientBalanceException {
		logger.info("Started deduct Wallet method");
		Wallet wallet = getWallet(token ,Username);
		if(wallet != null) {
			double balance = wallet.getBalance();
			if(balance >= fine) {
				balance = balance - fine;
				wallet.setBalance(balance);
				wallet.addOrders(new FinedOrder(orderId,wallet.getWalletId(),bookName,fine,"Deducted",LocalDate.now()));
				walletRepo.save(wallet);
			}else {
				throw new InsufficientBalanceException(Username+" has insufficient balance");
			}
		}
		logger.info("Ended deduct Wallet method");
	}

	@Override
	public ResponseEntity<Object> addWallet(String token,String Username,double amount) {
		logger.info("Started add Wallet method");
		if(checkToken(token)) {
			Wallet wallet = getWallet(token ,Username);
			if(wallet != null) {
				double balance = wallet.getBalance();
				balance = balance + amount;
				wallet.setBalance(balance);
				wallet.addOrders(new FinedOrder(++oid,wallet.getWalletId(),amount,"Credited",LocalDate.now()));
				walletRepo.save(wallet);

			}
			logger.info("Ended add Wallet method");
			return new ResponseEntity<>(wallet, HttpStatus.OK);	
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);


	}

	@Override
	public Wallet getWallet(String token ,String Username) {
		if(checkToken(token)) {
			Wallet wallet = walletRepo.findByUsername(Username);
			List<FinedOrder> orders = wallet.getOrders();
			Collections.sort(orders,walletComp);
			wallet.setOrders(orders);
			return wallet;
		}
		else {
			return null;
		}

	}
	@Override
	public Boolean checkToken(String token) {
		return authClient.validateToken(token.substring(7)).getStatusCodeValue()==200 ? true : false;
	}
	
	

	@Override
	public List<FinedOrder> getOrderDetailsById(String username,int orderid) {
	return finedOrderRepo.getOrderDetail(orderid);
	}

	@Override
	public List<Integer> getOrderIds(String username) {
		// TODO Auto-generated method stub
		return walletRepo.getOrderIds(username);
	}

}
