package co.birthday.view;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		
//		String today = simpleDateFormat.toString();
		String today = simpleDateFormat.format(nowDate).substring(2);
		
		System.out.println(today);
	}

}
