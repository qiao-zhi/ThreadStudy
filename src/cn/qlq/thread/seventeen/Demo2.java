package cn.qlq.thread.seventeen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo2 extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(Demo2.class);
	private static final ThreadLocal<SimpleDateFormat> THREAD_LOCAL = new ThreadLocal<SimpleDateFormat>();

	public static SimpleDateFormat getSimpleDateFormat() {
		SimpleDateFormat simpleDateFormat2 = THREAD_LOCAL.get();
		if (simpleDateFormat2 == null) {
			simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			THREAD_LOCAL.set(simpleDateFormat2);
		}
		return simpleDateFormat2;
	}

	private SimpleDateFormat simpleDateFormat;
	private String dateStr;

	public Demo2(String dateStr) {
		super();
		this.dateStr = dateStr;
	}

	@Override
	public void run() {
		try {
			// 从ThreadLocal中获取simpleDateFormat
			this.simpleDateFormat = Demo2.getSimpleDateFormat();

			Date parse = simpleDateFormat.parse(dateStr);
			String format = simpleDateFormat.format(parse).toString();
			LOGGER.info("threadName ->{} ,dateStr ->{},格式化后的->{}", Thread.currentThread().getName(), dateStr, format);
		} catch (ParseException e) {
			LOGGER.error("parseException", e);
		}
	}

	public static void main(String[] args) {
		String[] dateStrs = new String[] { "2018-01-01", "2018-01-03", "2018-01-03" };
		Thread[] threads = new Thread[3];
		for (int i = 0; i < 3; i++) {
			threads[i] = new Demo2(dateStrs[i]);
		}
		for (int i = 0; i < 3; i++) {
			threads[i].start();
		}

	}
}
