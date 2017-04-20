package mil.nga.sf.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Write a byte array
 * 
 * @author osbornb
 */
public class ByteWriter {

	/**
	 * Output stream to write bytes to
	 */
	private final ByteArrayOutputStream os = new ByteArrayOutputStream();

	/**
	 * Byte order
	 */
	private ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;

	/**
	 * Constructor
	 */
	public ByteWriter() {
	}

	/**
	 * Close the byte writer
	 */
	public void close() {
		try {
			os.close();
		} catch (IOException e) {
		}
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
	 * Get the written bytes
	 * 
	 * @return written bytes
	 */
	public byte[] getBytes() {
		return os.toByteArray();
	}

	/**
	 * Get the current size in bytes written
	 * 
	 * @return bytes written
	 */
	public int size() {
		return os.size();
	}

	/**
	 * Write a String
	 * 
	 * @param value
	 *            string value
	 * @throws IOException
	 */
	public void writeString(String value) throws IOException {
		byte[] valueBytes = value.getBytes();
		os.write(valueBytes);
	}

	/**
	 * Write a byte
	 * 
	 * @param value
	 *            byte
	 */
	public void writeByte(byte value) {
		os.write(value);
	}

	/**
	 * Write an integer
	 * 
	 * @param value
	 *            int
	 * @throws IOException
	 */
	public void writeInt(int value) throws IOException {
		byte[] valueBytes = new byte[4];
		ByteBuffer byteBuffer = ByteBuffer.allocate(4).order(byteOrder)
				.putInt(value);
		byteBuffer.flip();
		byteBuffer.get(valueBytes);
		os.write(valueBytes);
	}

	/**
	 * Write a double
	 * 
	 * @param value
	 *            double
	 * @throws IOException
	 */
	public void writeDouble(double value) throws IOException {
		byte[] valueBytes = new byte[8];
		ByteBuffer byteBuffer = ByteBuffer.allocate(8).order(byteOrder)
				.putDouble(value);
		byteBuffer.flip();
		byteBuffer.get(valueBytes);
		os.write(valueBytes);
	}

}
