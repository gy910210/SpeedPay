package com.speedpay.bean;

public class Goods 
{
	private int goods_id;
	private String goods_name;
	private double goods_price;
	private String goods_barCode;
	public Goods(int goods_id, String goods_name, double goods_price,
			String goods_barCode) {
		super();
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.goods_price = goods_price;
		this.goods_barCode = goods_barCode;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public double getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}
	public String getGoods_barCode() {
		return goods_barCode;
	}
	public void setGoods_barCode(String goods_barCode) {
		this.goods_barCode = goods_barCode;
	}
	@Override
	public String toString() {
		return "商品编号：" + goods_id + ", 商品名：" + goods_name
				+ ", 商品单价：" + goods_price;
	}
}
