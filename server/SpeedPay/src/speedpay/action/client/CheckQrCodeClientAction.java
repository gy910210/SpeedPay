package speedpay.action.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import speedpay.dao.Qrcode;
import speedpay.dao.QrcodeDAO;

public class CheckQrCodeClientAction {
	private String qrCode_content;
	QrcodeDAO qrcodeDao;
	
	public void doAction() {
		
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject json=new JSONObject();
		
		@SuppressWarnings("unchecked")
		List<Qrcode> list=qrcodeDao.findByQrCodeContent(qrCode_content);
		System.out.println("list.get(0).getQrCodeStatus()"+list.get(0).getQrCodeStatus());
		
		if(list.size()>0)
		{
			if(list.get(0).getQrCodeStatus()==0)
			{
				json.accumulate("flag", true);
			}
			else
			{
				json.accumulate("flag", false);
			}
		}
		else
		{
			json.accumulate("flag", false);
		}
		
		out.println(json.toString());
		out.flush();
		out.close();
		
	}

	public String getQrCode_content() {
		return qrCode_content;
	}

	public void setQrCode_content(String qrCode_content) {
		this.qrCode_content = qrCode_content;
	}

	public QrcodeDAO getQrcodeDao() {
		return qrcodeDao;
	}

	public void setQrcodeDao(QrcodeDAO qrcodeDao) {
		this.qrcodeDao = qrcodeDao;
	}
	
	
}
