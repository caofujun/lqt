package com.nis.comm.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.nis.comm.utils.ab;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;

public class ai {
	private static Logger logger = Logger.getLogger(ai.class);
	private static final String cI = "【ZXingUtil】";

	public static void a(String context, String filePath, String imgFormat, Integer width, Integer height,
			String charSet) {
		if (ab.isEmpty(context)) {
			logger.error("【ZXingUtil】二维码写入文件参数异常！二维码内容为空！");
		} else if (ab.isEmpty(filePath)) {
			logger.error("【ZXingUtil】二维码写入文件参数异常！文件全路径为空");
		} else {
			width = Integer.valueOf(width == null ? 300 : width.intValue());
			height = Integer.valueOf(height == null ? 300 : height.intValue());
			charSet = ab.isEmpty(charSet) ? "utf-8" : charSet;
			imgFormat = ab.isEmpty(imgFormat) ? "png" : imgFormat;
			BitMatrix bitMatrix = a(context, width, height, charSet);
			if (bitMatrix != null) {
				BufferedImage image = a(bitMatrix);
				File outputFile = new File(filePath);

				try {
					boolean e = ImageIO.write(image, imgFormat, outputFile);
					if (!e) {
						logger.error("【ZXingUtil】二维码写入文件异常！【图片格式】" + imgFormat + "【图片路径】" + filePath);
					}
				} catch (IOException arg9) {
					logger.error("【ZXingUtil】二维码写入文件异常！【异常情况】" + arg9.getMessage());
					arg9.printStackTrace();
				}

			}
		}
	}

	public static void a(String context, String filePath, Integer width, Integer height) {
		a(context, (String) filePath, (String) null, width, height, (String) null);
	}

	public static void a(String context, OutputStream outStream, String imgFormat, Integer width, Integer height,
			String charSet) {
		if (ab.isEmpty(context)) {
			logger.error("【ZXingUtil】二维码写入到输出流中参数异常！二维码内容为空！");
		} else if (outStream == null) {
			logger.error("【ZXingUtil】二维码写入到输出流中参数异常！输出流为空");
		} else {
			width = Integer.valueOf(width == null ? 300 : width.intValue());
			height = Integer.valueOf(height == null ? 300 : height.intValue());
			charSet = ab.isEmpty(charSet) ? "utf-8" : charSet;
			imgFormat = ab.isEmpty(imgFormat) ? "png" : imgFormat;
			BitMatrix bitMatrix = a(context, width, height, charSet);
			if (bitMatrix != null) {
				BufferedImage image = a(bitMatrix);

				try {
					boolean e = ImageIO.write(image, imgFormat, outStream);
					if (!e) {
						logger.error("【ZXingUtil】二维码写入到输出流中异常！【图片格式】" + imgFormat);
					}
				} catch (IOException arg8) {
					logger.error("【ZXingUtil】二维码写入到输出流中异常！【异常情况】" + arg8.getMessage());
					arg8.printStackTrace();
				}

			}
		}
	}

	public static void a(String context, OutputStream outStream, Integer width, Integer height) {
		a(context, (OutputStream) outStream, (String) null, width, height, (String) null);
	}

	private static BufferedImage a(BitMatrix bitMatrix) {
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, 1);

		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? -16777216 : -1);
			}
		}

		return image;
	}

	private static BitMatrix a(String context, Integer width, Integer height, String charSet) {
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, charSet);
		MultiFormatWriter writer = new MultiFormatWriter();

		try {
			BitMatrix e = writer.encode(context, BarcodeFormat.QR_CODE, width.intValue(), height.intValue(), hints);
			return e;
		} catch (WriterException arg6) {
			logger.error("【ZXingUtil】取得二维码矩阵异常！");
			return null;
		}
	}

	public static void main(String[] args) {
		a("http://csm.91160.com", "F:" + File.separator + "code.png", Integer.valueOf(300), Integer.valueOf(400));
	}
}