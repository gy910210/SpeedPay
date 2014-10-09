package com.speedpay.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class SDFileUtils 
{
	private String SDPATH;
	
	public String getSDPATH()
	{
		return SDPATH;
	}
	
	public SDFileUtils()
	{
		SDPATH=Environment.getExternalStorageDirectory()+"/";
		System.out.println(SDPATH);
	}
	
	//在SD卡上创建文件
	public File createSDFile(String fileName) throws IOException
	{
		File file=new File(SDPATH+fileName);
		file.createNewFile();
		return file;
	}
	
	//在ＳＤ卡上创建目录
	public File createSDDir(String dirName)
	{
		File dir=new File(SDPATH+dirName);
		dir.mkdir();
		return dir;
	}
	
	//判断SD卡的文件夹是否存在
	public boolean isFileExist(String fileName)
	{
		File file=new File(SDPATH+fileName);
		return file.exists();
	}
	
	public File write2SDFromInput(String path,String fileName,InputStream input)
	{
		File file=null;
		OutputStream output=null;
		
		try
		{
			createSDDir(path);
			file=createSDFile(path+fileName);
		
			output=new FileOutputStream(file);
			byte[] buffer=new byte[4*1024];
			while((input.read(buffer))!=-1)
			{
				output.write(buffer);
			}
			output.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
	}
}

