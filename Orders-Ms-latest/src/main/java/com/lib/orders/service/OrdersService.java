package com.lib.orders.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lib.orders.clients.AuthClient;
import com.lib.orders.clients.BookClient;
import com.lib.orders.clients.CartClient;
import com.lib.orders.clients.WalletClient;
import com.lib.orders.models.Book;
import com.lib.orders.models.CheckedOutBook;
import com.lib.orders.models.OrdersEntity;
import com.lib.orders.models.Response;
import com.lib.orders.repository.CheckedOutBookRepository;
import com.lib.orders.repository.OrdersRepository;

import feign.FeignException;

@Service
public class OrdersService implements IOrdersService {
	
	@Autowired
	private OrdersRepository ordersRepo; 
	@Autowired
	private CheckedOutBookRepository chBookRepo;
	@Autowired
	private CartClient cartClient;
	@Autowired
	private BookClient bookClient;
	@Autowired
	private AuthClient authClient;
	@Autowired
	private WalletClient walletClient;
	private static int i=1519;
	private static int j=0; 
	private static final Logger log = LoggerFactory.getLogger(OrdersService.class);

	@Override
	public ResponseEntity<Object> processOrder(String username, String auth) {	
		log.info("inside processOrder - service");
		boolean flag = true;
		try {
			if(authClient.validateToken(auth.substring(7)).getStatusCodeValue()==200) {
				List<Book> books = cartClient.getCart(username, auth);
				OrdersEntity order = new OrdersEntity();
				order.setUsername(username);
				order.setOrderid(++i);
				for(Book book: books) {
					if(!bookClient.decreaseQuantity(book.getName(), auth)) {
						flag = false;
						continue;
					}
					CheckedOutBook ch = new CheckedOutBook();
					ch.setFine_amount(0);
					ch.setName(book.getName());
					ch.setImage(book.getImage());
					ch.setName(book.getName());
					ch.setIssue_date(LocalDate.now());
					ch.setReturn_date(LocalDate.now().plusDays(30));
					ch.setOrderid(i);
					chBookRepo.save(ch);
					order.setId(++j);
					order.setCheckedBookName(ch.getName());
					ordersRepo.save(order);
				}
				cartClient.checkOut(username, auth);
				if(flag) {
					return new ResponseEntity<>(new Response("Success"), HttpStatus.CREATED);
				}else {
					return new ResponseEntity<>(new Response("One or more book is already issused"), HttpStatus.CREATED);
				}
			}
		} catch(FeignException e) {
			log.error("inside processOrder - service");
		}
		return new ResponseEntity<>(new Book(), HttpStatus.FORBIDDEN);
	}

	//show-return-books
	@Override
	public ResponseEntity<Object> getAllBooksOrdered(String username, String auth) {
		log.info("inside getAllBooksOrdered - service");
		try {
			if(authClient.validateToken(auth.substring(7)).getStatusCodeValue()==200) {
				List<Integer> orders = ordersRepo.getDistinctOrderIds(username);
				List<CheckedOutBook> books = new ArrayList<>();
				for(Integer id: orders) {
					List<CheckedOutBook> chbooks = chBookRepo.findByOrderid(id);
					for(CheckedOutBook book: chbooks) {
						int fine = calcFine(book.getIssue_date(), LocalDate.now());
						book.setFine_amount(fine);
						books.add(book);
						chBookRepo.save(book);
					}
				}
				return new ResponseEntity<>(books, HttpStatus.OK);
			}
		} catch(FeignException e) {
			log.error("inside getAllBooksOrdered - service");
		}
		return new ResponseEntity<>(new ArrayList<Object>(), HttpStatus.FORBIDDEN);
	}
	
	@Override
	public ResponseEntity<Object> returnBook(String username, String name, int orderid, String auth) {
		log.info("inside returnBook - service");
		try {
			if(authClient.validateToken(auth.substring(7)).getStatusCodeValue()==200) {
				List<CheckedOutBook> chbooks = chBookRepo.findByOrderidAndName(orderid, name);
				CheckedOutBook chbook = chbooks.get(0);
				walletClient.deductWallet(auth, username, orderid, name, chbook.getFine_amount());
				chBookRepo.delete(chbook);
				List<OrdersEntity> orders = ordersRepo.findByOrderidAndUsernameAndCheckedBookName(orderid, username, name);
				ordersRepo.deleteById(orders.get(0).getId());
				bookClient.increaseQuantity(name, auth);
				return new ResponseEntity<>(new Response("transaction completed"), HttpStatus.OK);
			}
		} catch(FeignException e) {
			log.error("inside returnBook - service");
			return new ResponseEntity<>(new Response("Insufficient balance"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new Response("transaction failed"), HttpStatus.FORBIDDEN);
	}
	
	private int calcFine(LocalDate issue_date, LocalDate return_date) {	
		log.info("inside calcFine - service");
		long days = ChronoUnit.DAYS.between(issue_date, return_date);
		return (int) (days>30 ? days-30 : 0);	
	}
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------
	// gets all books on his name
	
	/*
	public List<CheckedOutBook> getAllBooksOrdered(String username)
	{
		log.info("inside getAllBooksOrdered - service");
		List<OrdersEntity> orders= getAllOrders(username);
		List<CheckedOutBook> books=new ArrayList<>();
		for(OrdersEntity order: orders)
		{
			List<CheckedOutBook> chbooks=chBookRepo.findByOrderidAndName(order.getOrderid(), order.getCheckedBookName());
			for(CheckedOutBook book: chbooks)
			{
				book.setFine_amount(calc_fine(book.getIssue_date(), LocalDate.now()));
				books.add(book);
			}
			
		}
		return books;
	}
	*/
	
	/*
	@Override
	public List<CheckedOutBook> getOrderDetails(String username) {
		log.info("inside getOrderDetails - service");
		List<CheckedOutBook> books= new ArrayList<CheckedOutBook>();
		List<OrdersEntity> orders= ordersRepo.findByUsername(username);
		
		if(orders.size() == 1)
		{
			return chBookRepo.findByNameAndOrderid(orders.get(0).getCheckedBookName(), orders.get(0).getOrderid());
		}
		else if(orders.size() > 1)
		{
			
			// making list with unique objects
			for(int i=0;i< orders.size();i++)
			{
				for(int j=i+1;j< orders.size();j++)
				{
					if(orders.get(i).getCheckedBookName() .equals(orders.get(j).getCheckedBookName()))
					{
						orders.remove(j);
						j--;
						//i--;
					}
				}
			}
			
			System.err.println("unique list   "+ orders);
			
			for(OrdersEntity order: orders)
			{
				List<CheckedOutBook> temp= chBookRepo.findByNameAndOrderid(order.getCheckedBookName(), order.getOrderid());
				for(CheckedOutBook book: temp)
				{
					books.add(book);
				}				
			}
		}
		
		System.err.println("\n\ngetting order details....  "+ orders);
		return books;
	}
	*/
	
	/*
	@Override
	public List<OrdersEntity> getAllOrders(String username) {
		log.info("inside getAllOrders - service");
		
		List<OrdersEntity> orders = ordersRepo.findByUsername(username);
		
		
		for(int i=0;i< orders.size();i++)
		{
			for(int j=i+1;j< orders.size();j++)
			{
				if(orders.get(i).getCheckedBookName() .equals(orders.get(j).getCheckedBookName()))
				{
					orders.remove(j);
					j--;
					//i--;
				}
			}
		}
		
		return orders;
	} 
	*/
	
}
