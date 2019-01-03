package cn.qlq.thread.seventeen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo1 extends Thread {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo1.class);
	private SimpleDateFormat simpleDateFormat;
	private String dateStr;

	public Demo1(SimpleDateFormat simpleDateFormat, String dateStr) {
		super();
		this.simpleDateFormat = simpleDateFormat;
		this.dateStr = dateStr;
	}

	@Override
	public void run() {
		try {
			Date parse = simpleDateFormat.parse(dateStr);
			String format = simpleDateFormat.format(parse).toString();
			LOGGER.info("threadName ->{} ,dateStr ->{},格式化后的->{}", Thread.currentThread().getName(), dateStr, format);
		} catch (ParseException e) {
			LOGGER.error("parseException", e);
		}
	}

	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String[] dateStrs = new String[] { "2018-01-01", "2018-01-03", "2018-01-03" };
		Thread[] threads = new Thread[3];
		for (int i = 0; i < 3; i++) {
			threads[i] = new Demo1(simpleDateFormat, dateStrs[i]);
		}
		for (int i = 0; i < 3; i++) {
			threads[i].start();
		}

	}
}
