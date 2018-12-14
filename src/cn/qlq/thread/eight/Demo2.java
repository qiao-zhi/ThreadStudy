package cn.qlq.thread.eight;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通过管道进行线程间通信:字符流
 * 
 * @author Administrator
 *
 */
public class Demo2 {

	private static final Logger LOGGER = LoggerFactory.getLogger(Demo2.class);

	public static void main(String[] args) throws IOException {
		final PipedWriter writer = new PipedWriter();
		final PipedReader reader = new PipedReader();

		// 使管道建立连接
		// writer.connect(reader);
		reader.connect(writer);

		// 开启线程读和写入
		new Thread(new Runnable() {
			@Override
			public void run() {
				LOGGER.info("threamName ->{} 进入run ", Thread.currentThread().getName());
				try {
					for (int i = 0; i < 5; i++) {
						String data = String.valueOf(i);
						writer.write(data);
						LOGGER.info("threadName->{} write data ->{}", Thread.currentThread().getName(), data);
					}
					writer.close();
					LOGGER.info("threamName ->{} 结束run ", Thread.currentThread().getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, "writerThread").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					LOGGER.info("threamName ->{} 进入run ", Thread.currentThread().getName());
					char[] buffer = new char[20];
					int read = reader.read(buffer);
					while (read != -1) {
						String data = new String(buffer, 0, read);
						LOGGER.info("threadName->{} read data ->{}", Thread.currentThread().getName(), data);
						read = reader.read(buffer);
					}
					reader.close();
					LOGGER.info("threamName ->{} 结束run ", Thread.currentThread().getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, "readThread").start();
	}
}
