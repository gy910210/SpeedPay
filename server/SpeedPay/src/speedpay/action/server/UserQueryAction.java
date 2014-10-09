package speedpay.action.server;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import speedpay.dao.Borrowproof;
import speedpay.dao.Consumptionproof;
import speedpay.dao.Purchasecontent;
import speedpay.dao.Transferproof;
import speedpay.dao.User;
import speedpay.dao.UserDAO;
import speedpay.dao.Withdrawproof;
import speedpay.services.PageDivision;

import com.opensymphony.xwork2.ActionSupport;
public class UserQueryAction extends ActionSupport{
	/**用户查询
	 * @author Shang
	 * @since 2013/8/7
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private UserDAO userDao;
	private int querytype;
	public Date startdate;
	public Date enddate;
	private Purchasecontent purchasecontent;
	private ArrayList<Purchasecontent> purchasecontentList;
	private Set<Purchasecontent> hs;
	private Iterator<Purchasecontent> hsIt;
	private Borrowproof borrowproof;
	private Iterator<Borrowproof> bwIt;
	private Set<Borrowproof> bw;
	private ArrayList<Borrowproof> borrowproofList;
	private Transferproof transferproof;
	private Iterator<Transferproof> tfIt;
	private Set<Transferproof> tf;
	private ArrayList<Transferproof> transferproofList;
	private Withdrawproof withdrawproof;
	private Iterator<Withdrawproof> wtIt;
	private Set<Withdrawproof> wt;
	private ArrayList<Withdrawproof> withdrawproofList;
	private Consumptionproof consumptionproof;
	private Iterator<Consumptionproof> csIt;
	private Set<Consumptionproof> cs;
	private ArrayList<Consumptionproof> consumptionproofList;
	private PageDivision pd;
	private int pageNum=1;
	private Date end;
	private Date start;
	private int type;
	
	public PageDivision getPd() {
		return pd;
	}
	public void setPd(PageDivision pd) {
		this.pd = pd;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public UserQueryAction() {
		super();
		purchasecontentList=new ArrayList<Purchasecontent>();
		borrowproofList = new ArrayList<Borrowproof>();
		transferproofList = new ArrayList<Transferproof>();
		withdrawproofList = new ArrayList<Withdrawproof>();
		consumptionproofList = new ArrayList<Consumptionproof>();
		 
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	public String type(){
	
		user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(startdate == null){
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("enddate", enddate);
			ServletActionContext.getRequest().getSession().setAttribute("startdate", startdate);
			ServletActionContext.getRequest().getSession().setAttribute("querytype",querytype);
		}
		start =(Date) ServletActionContext.getRequest().getSession().getAttribute("startdate");
		end =(Date) ServletActionContext.getRequest().getSession().getAttribute("enddate");
		type=(Integer) ServletActionContext.getRequest().getSession().getAttribute("querytype");
		if(type == 0){	
			purchase();
		}else if(type == 1){
			consumption();
		}else if(type == 2){
			transfer();
		}else if(type == 3){
			withdraw();
		}else{
			borrow();
		}
		return "type";
	}
	
	

	@SuppressWarnings("unchecked")
	public void purchase(){
		 hs =user.getPurchasecontentsForPurchaseContentUserId();	
		 hsIt = hs.iterator();
		    for(int j=1;hsIt.hasNext();j++){
		    purchasecontent = (Purchasecontent) hsIt.next();
		    Date d=purchasecontent.getPurchaseContentConsumptionTime();  
		    if(d.before(start))
		    {
		    	//this.addActionError("该时间段内没有消费");
		    	}else if(d.after(end)){
		    	//this.addActionError("该时间段内没有消费");
		    	}else{
		    		purchasecontentList.add(purchasecontent);
		    	}
		    }
		    pd=new PageDivision(purchasecontentList.size(), 5, pageNum);
	}
	
	public void consumption(){
		 cs =user.getConsumptionproofsForConsumptionProofUserId();	
		 csIt = cs.iterator();
		    for(int j=1;csIt.hasNext();j++){
		    	consumptionproof = csIt.next();
		    Date d=consumptionproof.getConsumptionProofTime();  
		    if(d.before(startdate))
		    {
		    	//this.addActionError("该时间段内没有消费");
		    	}else if(d.after(enddate)){
		    	//this.addActionError("该时间段内没有消费");
		    	}else{
		    		consumptionproofList.add(consumptionproof);
		    	}
		    }
		    pd=new PageDivision(consumptionproofList.size(), 5, pageNum);
	}
	public void borrow(){
		bw = user.getBorrowproofsForBorrowProofBorrowUserId();
		bwIt =bw.iterator();
		for(int j=1;bwIt.hasNext();j++){
			borrowproof = bwIt.next();
			Date d =borrowproof.getBorrowProofBorrowTime();
			 if(d.before(startdate))
			    {
			    	//this.addActionError("该时间段内没有消费");
			    	}else if(d.after(enddate)){
			    	//this.addActionError("该时间段内没有消费");
			    	}else{
			    		borrowproofList.add(borrowproof);
			    	}
			    }
		 pd=new PageDivision(borrowproofList.size(), 5, pageNum);
		}
		
	public void transfer() {
		tf = user.getTransferproofsForTransferProofTransferUserId();
		tfIt =tf.iterator();
		for(int j=1;tfIt.hasNext();j++){
			transferproof = tfIt.next();
			Date d =transferproof.getTransferProofTime();
			 if(d.before(startdate))
			    {
			    	//this.addActionError("该时间段内没有消费");
			    	}else if(d.after(enddate)){
			    	//this.addActionError("该时间段内没有消费");
			    	}else{
			    		transferproofList.add(transferproof);
			    	}
			    }
		pd=new PageDivision(transferproofList.size(), 5, pageNum);
	}
	
	public void withdraw(){
		wt = user.getWithdrawproofs();
		wtIt =wt.iterator();
		for(int j=1;wtIt.hasNext();j++){
			withdrawproof = wtIt.next();
			Date d =withdrawproof.getWithdrawProofTime();
			 if(d.before(startdate))
			    {
			    	//this.addActionError("该时间段内没有消费");
			    	}else if(d.after(enddate)){
			    	//this.addActionError("该时间段内没有消费");
			    	}else{
			    		withdrawproofList.add(withdrawproof);
			    	}
			    }
		pd=new PageDivision(withdrawproofList.size(), 5, pageNum);
	}
	
	
	
	public Borrowproof getBorrowproof() {
		return borrowproof;
	}
	public void setBorrowproof(Borrowproof borrowproof) {
		this.borrowproof = borrowproof;
	}
	public ArrayList<Borrowproof> getBorrowproofList() {
		return borrowproofList;
	}
	public void setBorrowproofList(ArrayList<Borrowproof> borrowproofList) {
		this.borrowproofList = borrowproofList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public int getQuerytype() {
		return querytype;
	}

	public void setQuerytype(int querytype) {
		this.querytype = querytype;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Purchasecontent getPurchasecontent() {
		return purchasecontent;
	}
	public void setPurchasecontent(Purchasecontent purchasecontent) {
		this.purchasecontent = purchasecontent;
	}
	
	public ArrayList<Purchasecontent> getPurchasecontentList() {
		return purchasecontentList;
	}
	public void setPurchasecontentList(
			ArrayList<Purchasecontent> purchasecontentList) {
		this.purchasecontentList = purchasecontentList;
	}
	public Transferproof getTransferproof() {
		return transferproof;
	}
	public void setTransferproof(Transferproof transferproof) {
		this.transferproof = transferproof;
	}
	public ArrayList<Transferproof> getTransferproofList() {
		return transferproofList;
	}
	public void setTransferproofList(ArrayList<Transferproof> transferproofList) {
		this.transferproofList = transferproofList;
	}
	public Withdrawproof getWithdrawproof() {
		return withdrawproof;
	}
	public void setWithdrawproof(Withdrawproof withdrawproof) {
		this.withdrawproof = withdrawproof;
	}
	public ArrayList<Withdrawproof> getWithdrawproofList() {
		return withdrawproofList;
	}
	public void setWithdrawproofList(ArrayList<Withdrawproof> withdrawproofList) {
		this.withdrawproofList = withdrawproofList;
	}
	public Consumptionproof getConsumptionproof() {
		return consumptionproof;
	}
	public void setConsumptionproof(Consumptionproof consumptionproof) {
		this.consumptionproof = consumptionproof;
	}
	public ArrayList<Consumptionproof> getConsumptionproofList() {
		return consumptionproofList;
	}
	public void setConsumptionproofList(
			ArrayList<Consumptionproof> consumptionproofList) {
		this.consumptionproofList = consumptionproofList;
	}
	
	
	
	
}
