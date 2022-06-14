package ru.netcraker.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Buyer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String lastName;
	private String areaOfResidence;
	private Integer discount;
}
