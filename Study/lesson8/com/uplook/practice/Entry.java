package com.uplook.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Entry {
	public static void main(String[] args) throws IOException {
/* 
		//第1题		
		String autoFileName = new String();
		String autoFileCont = new String();
		SimpleDateFormat formatterOfName = new SimpleDateFormat("yyyyMMddHHmmssSS");
		SimpleDateFormat formatterOfCont = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss.");
		
		for (int i = 0; i < 10; i++) {
			//创建文件
			autoFileName = formatterOfName.format(new Date());
			File autoFile = new File("E:/WS_JAVA/OP_DIR/" + autoFileName);
			//创建文件输出流
			FileOutputStream outStream = new FileOutputStream(autoFile);
			//创建输出流写出器
			OutputStreamWriter writer = new OutputStreamWriter(outStream);
			//写入内容
			autoFileCont = "日志信息写入时间：" + formatterOfCont.format(new Date());
			writer.write(autoFileCont);
			//关闭流
			writer.close();
			outStream.close();
		}
*/
/*		
		//第2题
		int fileLength = 1024 + (int)(Math.random()*(10240 - 1024));
//		int fileLength = 100;
		File srcFile = new File("E:/WS_JAVA/OP_DIR/srcFile.bin");
		File dstFile = new File("E:/WS_JAVA/OP_DIR/dstFile.bin");
		//创建文件
		FileOutputStream outCreate = new FileOutputStream(srcFile);
		for(int i=0; i<fileLength; i++) {
			outCreate.write((int)Math.random()*1000);
		}
		outCreate.close();
		
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(dstFile);
		int b;
		int num = 0;
		while ((b = in.read()) != -1) {
			if (num++ % 200 == 0) {
				System.out.printf("进度： %02d%% 剩余：%8d\n", (int) (100 * (float) num / (float) fileLength),
						(fileLength - num));
			}
			out.write(b);
		}
		out.close();
		in.close();
*/
		
		//第3、4、5题
		File dataFile = new File("D:/9_WORKSPACE/Eclipse/temp_file/data.txt");
		Scanner scanner = new Scanner(System.in);
		
		String[] prompt = {
				"用户名",
				"密码"  ,
				"姓名"  ,
				"年龄"  ,
				"性别"  ,
		};
		while (true) {
			System.out.println("请输入：");
			System.out.println("r: register");
			System.out.println("s: show");
			System.out.println("q: querry");
			System.out.println("l: login");
			System.out.println("e: exit");
			String opt = scanner.next();
			if(opt.equals("e")) {
				break;
			}
			if (opt.equals("r")) {
				FileOutputStream out = new FileOutputStream(dataFile, true);
				OutputStreamWriter writer = new OutputStreamWriter(out);
				for (String string : prompt) {
					System.out.println("请输入 "+string);
					String username = scanner.next();
					writer.write(string + ":" + username + ";");
				}
				writer.write("\n");
				writer.close();
			} else if (opt.equals("s")) {
				FileInputStream  in  = new FileInputStream(dataFile);
				InputStreamReader reader = new InputStreamReader(in);
				BufferedReader bReader = new BufferedReader(reader);
				String aRecord;
				String[] res = new String[prompt.length];
				while((aRecord = bReader.readLine()) != null) {
					lineParse(aRecord, prompt, res);
					for (String string : res) {
						System.out.println(string);
					}
				}
				bReader.close();
				reader.close();
				in.close();
			} else if (opt.equals("q")) {
				String abc = "123abcjfasfjskbabc8888";
				String xyz = abc.replace("abc", "");
				System.out.println(xyz);
			} else if (opt.equals("l")) {

			}
		}
		scanner.close();
	}
	
	public static void lineParse(String line, String[] prompt, String[] res) {
		String tmpStr = new String();
		for (int i=0; i<prompt.length; i++) {
			Pattern p = Pattern.compile(prompt[i] + ":[a-zA-Z0-9]*;");
			Matcher m = p.matcher(line);
			if(m.find()) {
				tmpStr = m.group();
			} else {
				tmpStr = "Not Found";
			}
			res[i] = tmpStr.replaceAll(prompt[i] + ":|;", "");
		}
	}
}
























