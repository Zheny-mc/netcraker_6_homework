package ru.netcraker.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long orderNumber;

	//Uni-directional
	@Getter @Setter
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "sellers_id")
	private List<Shop> shopList;

	//Uni-directional
	@Getter @Setter
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "buyer_id")
	private List<Buyer> buyerList;

	//Uni-directional
	@Getter @Setter
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id")
	private List<Book> bookList;

	@Setter
	private Calendar dataTime;

	@Getter @Setter
	private Integer volume;

	@Getter @Setter
	private Integer summa;

	public Calendar getCalendar() { return dataTime; }
	public Date getDataTime() {
		return dataTime.getTime();
	}

	@Override
	public String toString() {
		return "\nPurchase{" +
				"orderNumber=" + orderNumber +
				", shopList=" + shopList +
				", buyerList=" + buyerList +
				", bookList=" + bookList +
				", dataTime=" + dataTime +
				", volume=" + volume +
				", summa=" + summa +
				'}';
	}
}
