package speedpay.services;

import java.util.ArrayList;
import java.util.List;

public class PageDivision {
	private int item_sum;
	private int divisor;
	private int pn;
	private int page_sum = 0;
	private int above_page;
	private int below_page;
	private int begin;
	private int end;
	private List<Integer> aboveList;
	private List<Integer> belowList;

	public PageDivision(int item_sum, int divisor, int pn) {
		super();
		this.item_sum = item_sum;
		this.divisor=divisor;
		this.pn=pn;
		aboveList=new ArrayList<Integer>();
		belowList=new ArrayList<Integer>();
		execute();
		beginAndEnd();
		doAbove();
		doBelow();

	}

	private void execute() {
		page_sum=item_sum/divisor;
		if (item_sum%divisor!=0) page_sum++;
		if(pn>page_sum) pn=page_sum;
		if (this.pn<1) this.pn=1;
		if (pn<4+1) above_page=pn-1;
		else above_page=4;
		if(page_sum-pn<4) below_page=page_sum-pn;
		else below_page=4;
	}
	private void beginAndEnd() {
		begin=(pn-1)*divisor;
		end=pn*divisor-1;
		if(end>item_sum) end=item_sum-1;
		if(end<0) end=0;
	}
	private void doAbove(){
		for(int i=0;i<above_page;i++){
			Integer a=pn-above_page+i;
		aboveList.add(a);		
		}
		
	}
	private void doBelow(){
		for(int i=0;i<below_page;i++){
			Integer b=pn+i+1;
//			System.out.println(above_page);
//			System.out.println(below_page);
//			System.out.println(pn);
//			System.out.println(page_sum);
//			System.out.println(b);
		belowList.add(b);	
		}
		
	}
	//get and set
	public int getAbove_page() {
		return above_page;
	}
	public int getBelow_page() {
		return below_page;
	}
	public int getPage_sum() {
		return page_sum;
	}
	public int getDivisor() {
		return divisor;
	}
	public int getPn() {
		return pn;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public List<Integer> getAboveList() {
		return aboveList;
	}

	public void setAboveList(List<Integer> aboveList) {
		this.aboveList = aboveList;
	}

	public List<Integer> getBelowList() {
		return belowList;
	}

	public void setBelowList(List<Integer> belowList) {
		this.belowList = belowList;
	}

	public int getItem_sum() {
		return item_sum;
	}

	public void setItem_sum(int item_sum) {
		this.item_sum = item_sum;
	}

	public void setDivisor(int divisor) {
		this.divisor = divisor;
	}

	public void setPn(int pn) {
		this.pn = pn;
	}

	public void setPage_sum(int page_sum) {
		this.page_sum = page_sum;
	}

	public void setAbove_page(int above_page) {
		this.above_page = above_page;
	}

	public void setBelow_page(int below_page) {
		this.below_page = below_page;
	}

}
