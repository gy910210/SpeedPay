package com.speedpay.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.speedpay.bean.MarketItem;

public class ErCodeParse 
{
	public final static String SuperMarket="/1/超市ID/超市名/消费金额/消费时间/商品Id1_商品名1_商品单价1_购买数量1,商品Id2.商品名2.商品单价2.购买数量2,...,/生成时间";//生成时间
	public final static String shop="/2/顾客ID/消费金额/消费时间/消费原因/生成时间";//生成时间
	public final static String ATM="/3/取款人ID/取款金额/取款时间/生成时间";//生成时间
	public final static String transfer="/4/转出方ID/转账金额/转账时间/转账原因/收款方手机/生成时间";//收款方手机//生成时间
	public final static String borrow="/5/借入方ID/借入金额/借入时间/还款时间/借出方手机/生成时间";//借出方手机//生成时间
	
	
	
	public Map<String,Object> parse(String ercode)
	{
		Map<String,Object> map=new HashMap<String, Object>();
		map.clear();
		String[] strList=ercode.split("/");
		switch(Integer.parseInt(strList[1]))
		{
		case 1:
			map.put("MarketId", Integer.parseInt(strList[2]));
			map.put("MarketName", strList[3]);
			map.put("MarketSum",Double.parseDouble(strList[4]));
			map.put("MarketTime", strList[5]);
			String[] itemList=strList[6].split(",");
			List<MarketItem> list=new ArrayList<MarketItem>();
			for (String marketItem : itemList) {
				String[] itemContent=marketItem.split("_");
				MarketItem item=new MarketItem(Integer.parseInt(itemContent[0]), itemContent[1], Double.parseDouble(itemContent[2]), Integer.parseInt(itemContent[3]));
				list.add(item);
			}
			map.put("MarketItemList", list);
			break;
		case 2:
			map.put("CustomId", Integer.parseInt(strList[2]));
			map.put("ShopSum", Double.parseDouble(strList[3]));
			map.put("ShopTime", strList[4]);
			map.put("ShopCause", strList[5]);
			break;
		case 3:
			map.put("WithDrawUserId", Integer.parseInt(strList[2]));
			map.put("WithDrawSum", Double.parseDouble(strList[3]));
			map.put("WithDrawTime", strList[4]);
			break;
		case 4:
			map.put("TransferUerId", Integer.parseInt(strList[2]));
			map.put("TransferSum", Double.parseDouble(strList[3]));
			map.put("TransferTime", strList[4]);
			map.put("TransferCause", strList[5]);
			map.put("ReceiverPhone", strList[6]);
			break;
		case 5:
			map.put("BorrowUserId", Integer.parseInt(strList[2]));
			map.put("BorrowSum", Double.parseDouble(strList[3]));
			map.put("BorrowTime", strList[4]);
			map.put("RepayTime", strList[5]);
			map.put("LendPhone", strList[6]);
			break;
		}
		return map;
		
	}
}