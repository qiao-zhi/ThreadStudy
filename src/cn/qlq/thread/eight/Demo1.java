package cn.qlq.thread.eight;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通过管道进行线程间通信:字节流
 * 
 * @author Administrator
 *
 */
public class Demo1 {

	private static final Logger LOGGER = LoggerFactory.getLogger(Demo1.class);

	public static void main(String[] args) throws IOException {
		final PipedInputStream inputStream = new PipedInputStream();
		final PipedOutputStream outputStream = new PipedOutputStream();

		// 使管道建立连接
		// inputStream.connect(outputStream);
		outputStream.connect(inputStream);

		// 开启线程读和写入
		new Thread(new Runnable() {
			@Override
			public void run() {
				LOGGER.info("threamName ->{} 进入run ", Thread.currentThread().getName());
				try {
					for (int i = 0; i < 10; i++) {
						String data = String.valueOf(i);
						outputStream.write(data.getBytes());
						LOGGER.info("threadName->{} write data ->{}", Thread.currentThread().getName(), data);
						outputStream.flush();
					}
					outputStream.close();
					LOGGER.info("threamName ->{} 结束run ", Thread.currentThread().getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, "writeThread").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					LOGGER.info("threamName ->{} 进入run ", Thread.currentThread().getName());
					byte[] buffer = new byte[20];
					int readLength = inputStream.read(buffer);
					while (readLength != -1) {
						String data = new String(buffer, 0, readLength);
						LOGGER.info("threadName->{} read data ->{}", Thread.currentThread().getName(), data);
						readLength = inputStream.read(buffer);
					}
					inputStream.close();
					LOGGER.info("threamName ->{} 结束run ", Thread.currentThread().getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, "readThread").start();
	}
}
