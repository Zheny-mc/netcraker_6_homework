package ru.netcraker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.netcraker.entity.Book;
import ru.netcraker.entity.Buyer;
import ru.netcraker.entity.Purchase;
import ru.netcraker.entity.Shop;
import ru.netcraker.repository.BookRepository;
import ru.netcraker.repository.BuyerRepository;
import ru.netcraker.repository.PurchaseRepository;
import ru.netcraker.repository.ShopRepository;

import javax.transaction.Transactional;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootApplication
public class SpringdataJPAApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringdataJPAApplication.class, args);
    }

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

	@Transactional
    @Override
    public void run(String... args) {
	    for (int i = 1; i <= 5; i++) {
		    pushPurchase(i);
	    }

	    var y = purchaseRepository.findAll();
	    System.out.println("y = " + y);
	    
	    
    }

    public void pushPurchase(int i) {
	    Book book = new Book();
	    book.setTitle("title_book" + i);
	    book.setCost(i*100);
	    book.setWarehouse("warehouse" + i);
	    book.setVolume(i*1000);
	    //-------------------------------
	    Buyer buyer = new Buyer();
	    buyer.setLastName("lastName" + i);
	    buyer.setAreaOfResidence("residence" + i);
	    buyer.setDiscount(i * 10);
	    //---------------------------------------
	    Shop shop = new Shop();
	    shop.setTitle("title" + i);
	    shop.setCommission(i * 10);
	    shop.setLocationArea("locationArea" + i);
	    //---------------------------------------
	    Purchase purchase = new Purchase();
	    purchase.setOrderNumber(i * 1000L);
	    purchase.setDataTime(new GregorianCalendar());
	    purchase.setVolume(i * 10);
	    purchase.setSumma(i * 1000);

	    purchase.setBookList(List.of(book));
	    purchase.setBuyerList(List.of(buyer));
	    purchase.setShopList(List.of(shop));
	    //----------------
	    purchaseRepository.save(purchase);
    }


}
