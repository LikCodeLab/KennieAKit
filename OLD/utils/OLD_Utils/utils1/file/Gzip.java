

package com.learkoo.utils.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 * <b>Project:</b> LearKooSDK<br>
 * <b>Create Date:</b> 16/2/26<br>
 * <b>Author:</b> LearKoo<br>
 * <b>See {@link }</b>
 * <b>Description:</b>
 *
 * <br>
 */
public class Gzip {

	public static String uncompress(byte[] bytes) throws IOException {
		return uncompress(bytes, "utf-8");
	}

	public static String uncompress(byte[] bytes, String encoding) throws IOException {
		StringBuilder buffer = new StringBuilder();	//非线程安全的
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(bytes)), encoding);
			char[] cbuf = new char[2096];
			int len = 0;
			while ((len = reader.read(cbuf)) != -1) {
				buffer.append(cbuf, 0, len);
			}
		} finally {
			if(reader != null) try { reader.close(); } catch (IOException e) {}
		}
		return buffer.toString();
	}

	public static String uncompress(InputStream in) throws IOException {
		StringBuilder buffer = new StringBuilder();	//非线程安全的
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(new GZIPInputStream(in));
			char[] cbuf = new char[2096];
			int len = 0;
			while ((len = reader.read(cbuf)) != -1) {
				buffer.append(cbuf, 0, len);
			}
		} finally {
			if(reader != null) try { reader.close(); } catch (IOException e) {}
		}
		return buffer.toString();
	}

	public static void uncompress(InputStream in, OutputStream out) throws IOException {
		GZIPInputStream gzipin = null;
		try {
			gzipin = new GZIPInputStream(in);
			byte[] cbuf = new byte[2096];
			int len = 0;
			while ((len = gzipin.read(cbuf)) != -1) {
				out.write(cbuf, 0, len);
			}
		} finally {
			if(out != null) out.close();
			if(gzipin != null) gzipin.close();
		}
	}

	/////////////////////////////compress////////////////////////////////////

	public static byte[] compress(String s) throws IOException {
		return compress(s, "utf-8");
	}

	public static byte[] compress(String s, String encoding) throws IOException {
		ByteArrayOutputStream byteout = new ByteArrayOutputStream();
		GZIPOutputStream out = null;
		try {
			out = new GZIPOutputStream(byteout);
			out.write(s.getBytes(encoding));
		} finally {
			if(out != null) out.close();	//不要try...catch()，只有正常close()，才能完整写入返回。
		}
		return byteout.toByteArray();
	}

	public static byte[] compress(InputStream in) throws IOException {
		ByteArrayOutputStream byteout = new ByteArrayOutputStream();
		GZIPOutputStream out = null;
		try {
			out = new GZIPOutputStream(byteout);
			byte[] cbuf = new byte[2096];
			int len = 0;
			while ((len = in.read(cbuf)) != -1) {
				out.write(cbuf, 0, len);
			}
		} finally {
			if(out != null) out.close();
			if(in != null) in.close();
		}
		return byteout.toByteArray();
	}

	public static void compress(InputStream in, OutputStream out) throws IOException {
		GZIPOutputStream gzipout = null;
		try {
			gzipout = new GZIPOutputStream(out);
			byte[] cbuf = new byte[2096];
			int len = 0;
			while ((len = in.read(cbuf)) != -1) {
				gzipout.write(cbuf, 0, len);
			}
		} finally {
			if(gzipout != null) gzipout.close();
			if(in != null) in.close();
		}
	}
}
