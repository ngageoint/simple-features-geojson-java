package mil.nga.sf.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Read through a byte array
 * 
 * @author osbornb
 */
public class ByteReader {

	/**
	 * Character set
	 */
	private static final String CHAR_SET = "UTF-8";

	/**
	 * Next byte index to read
	 */
	private int nextByte = 0;

	/**
	 * Bytes to read
	 */
	private final byte[] bytes;

	/**
	 * Byte order
	 */
	private ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;

	/**
	 * Constructor
	 * 
	 * @param bytes
	 *            bytes
	 */
	public ByteReader(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * Get the next byte to be read
	 * 
	 * @return next byte to be read
	 */
	public int getNextByte() {
		return nextByte;
	}

	/**
	 * Get the byte order
	 * 
	 * @return byte order
	 */
	public ByteOrder getByteOrder() {
		return byteOrder;
	}

	/**
	 * Set the byte order
	 * 
	 * @param byteOrder
	 *            byte order
	 */
	public void setByteOrder(ByteOrder byteOrder) {
		this.byteOrder = byteOrder;
	}

	/**
	 * Read a String from the provided number of bytes
	 * 
	 * @param num
	 *            number of bytes
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	public String readString(int num) throws UnsupportedEncodingException {
		verifyRemainingBytes(num);
		String value = new String(bytes, nextByte, num, CHAR_SET);
		nextByte += num;
		return value;
	}

	/**
	 * Read a byte
	 * 
	 * @return byte
	 */
	public byte readByte() {
		verifyRemainingBytes(1);
		byte value = bytes[nextByte];
		nextByte++;
		return value;
	}

	/**
	 * Read an integer
	 * 
	 * @return integer
	 */
	public int readInt() {
		verifyRemainingBytes(4);
		int value = ByteBuffer.wrap(bytes, nextByte, 4).order(byteOrder)
				.getInt();
		nextByte += 4;
		return value;
	}

	/**
	 * Read a double
	 * 
	 * @return double
	 */
	public double readDouble() {
		verifyRemainingBytes(8);
		double value = ByteBuffer.wrap(bytes, nextByte, 8).order(byteOrder)
				.getDouble();
		nextByte += 8;
		return value;
	}

	/**
	 * Verify with the remaining bytes that there are enough remaining to read
	 * the provided amount
	 * 
	 * @param bytesToRead
	 *            number of bytes to read
	 */
	private void verifyRemainingBytes(int bytesToRead) {
		if (nextByte + bytesToRead > bytes.length) {
			throw new SFException(
					"No more remaining bytes to read. Total Bytes: "
							+ bytes.length + ", Bytes already read: "
							+ nextByte + ", Attempted to read: " + bytesToRead);
		}
	}

}
