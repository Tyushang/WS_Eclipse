package com.uplooking.car_renting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class UserAccess {
	private static String userFilePath = 
			"D:/9_WORKSPACE/Eclipse/temp_file/Car_Renting/Users.txt";
	public static HashMap<String, User> hashMap;
	public static File userFile;
	
	public UserAccess() throws IOException{
		hashMap = new HashMap<String, User>();
		userFile = new File(userFilePath);
		FileInputStream  in  = new FileInputStream(userFile);
		InputStreamReader reader = new InputStreamReader(in);
		BufferedReader bReader = new BufferedReader(reader);
		
		UserInterface user2AccessIf = new User();
		String item;
		User user;
		while((item = bReader.readLine()) != null) {
			String keyStr = user2AccessIf.getKeyStrByItem(item);
			user = user2AccessIf.str2obj(item);
			hashMap.put(keyStr, user);
		}
		bReader.close();
	}
	
	public static boolean add(User user) throws IOException {
		UserInterface user2AccessIf = new User();
		String item = user2AccessIf.obj2str(user);
		String keyStr = user2AccessIf.getKeyStrByItem(item);
		if(hashMap.containsKey(keyStr)) {
			return false;
		}
		hashMap.put(keyStr, user);

		FileOutputStream out;
		out = new FileOutputStream(userFile, true);
		OutputStreamWriter writer = new OutputStreamWriter(out);
		writer.write(item);
		writer.close();
		return true;
	}
	
	public static User getUser(String keyStr) {
		return hashMap.get(keyStr);
	}
}
