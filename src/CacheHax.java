import java.util.*;
import java.io.*;

public class CacheHax {
	public static void main(String args[]) throws Exception {
		byte[] bytes = new byte[1 << 20];
		RandomAccessFile file = new RandomAccessFile(args[0], "r");
		int i = 0;
		while(true) {
			file.readFully(bytes);
			System.out.println("" + i++);
		}
	}
}
