package cn.canway.model;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

// 类-->表   对象-->记录   属性 --> 字段
public class Product {

	private Integer id;
	
	private String name;
	
	private BigDecimal price;
	
	private String remark;
	
	private Date date;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Product() {
		super();
		System.out.println("Product()......");
	}
	
	public Product(Integer id, String name, BigDecimal price, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", remark=" + remark + ", date=" + date
				+ "]";
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {

		this.id = id;
	}
	public String getName() {
		System.out.println("getName");
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPriceA(BigDecimal price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
