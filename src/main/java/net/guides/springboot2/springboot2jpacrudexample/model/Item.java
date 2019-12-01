package net.guides.springboot2.springboot2jpacrudexample.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
@ApiModel(description = "All details about the item. ")
public class Item {

	@ApiModelProperty(notes = "The database generated item ID")
	private long itemNo;

	@ApiModelProperty(notes = "The item name")
	private String name;

	@ApiModelProperty(notes = "The amount of item")
	private long amount;

	@ApiModelProperty(notes = "The inventory code")
	private long invCode;
	
	public Item() {
		
	}
	
	public Item(String name, long amount, long invCode) {
		this.name = name;
		this.amount = amount;
		this.invCode = invCode;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getItemNo() {
		return itemNo;
	}
	public void setItemNo(long itemNo) {
		this.itemNo = itemNo;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "amount", nullable = false)
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	@Column(name = "invCode", nullable = false)
	public long getInvCode() {
		return invCode;
	}
	public void setInvCode(long invCode) {
		this.invCode = invCode;
	}

	@Override
	public String toString() {
		return "Item [itemNo=" + itemNo + ", name=" + name + ", amount=" + amount + ", invCode=" + invCode
				+ "]";
	}
	
}
