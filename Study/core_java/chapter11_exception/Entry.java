package chapter11_exception;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Entry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File dataFile = new File("E:/some_dir/data.txt");

		int ret = 0;
		try {
			ret = readFile(dataFile);
		} catch (WhateverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("returns: " + ret);
	}

	public static int readFile(File file) throws WhateverException {
		FileInputStream in = null;
		String cmd = "a"
				+ "b"
				+ "c"
				+ "d"
				+ "234";
		
		String ct = "create table STUDENT(             "    
		+ "		SNO varchar2(10) primary key,"    
		+ "		NAME varchar2(20),           "
		+ "		AGE number(2),               "
		+ "		SEX varchar2(5)              "
		+ ");";
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			WhateverException we = new WhateverException("File not found!");
			we.initCause(e);
			throw we;
		}
		
		return 199;
	}

}
