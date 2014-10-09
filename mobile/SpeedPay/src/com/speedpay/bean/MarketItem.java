package com.speedpay.bean;

public class MarketItem {
	private int goods_id;
	private String goods_name;
	private double goods_price;
	private int goods_quantity;

	public MarketItem(int goods_id, String goods_name, double goods_price,
			int goods_quantity) {
		super();
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.goods_price = goods_price;
		this.goods_quantity = goods_quantity;
	}

	public MarketItem() {
		super();
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public double getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}

	public int getGoods_quantity() {
		return goods_quantity;
	}

	public void setGoods_quantity(int goods_quantity) {
		this.goods_quantity = goods_quantity;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
    public String addBlack(String str){
    	String result="";
    	if(str.length()<=13){
    	int number=13-str.length();
    	System.out.println(number);
    	for(int i=0;i<=number;i++){
    		result=result+" ";
    	}
    	}
    	return result;
    }
	@Override
	public String toString() {
		return goods_name + addBlack(goods_name) + goods_price + addBlack(String.valueOf(goods_price)) + goods_quantity+"\n";
	}

}
