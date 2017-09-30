package com.nis.comm.utils;

import com.nis.comm.utils.ab;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public class ae {
	public static final String qg = "typeNoMatch";
	public static final String qh = "maxSize";
	public static final String qi = "noExtis";

	public static String a(MultipartFile photo, HttpServletRequest request, Integer maxSize, String dirName)
			throws Exception {
		String hostPath = "";
		if (photo == null) {
			return "noExtis";
		} else {
			String dealFileName = photo.getOriginalFilename();
			if (dealFileName.lastIndexOf(".jpg") == -1 && dealFileName.lastIndexOf(".png") == -1
					&& dealFileName.lastIndexOf(".bmp") == -1 && dealFileName.lastIndexOf(".gif") == -1) {
				return "typeNoMatch";
			} else {
				String spath = request.getRealPath("");
				String savePath = spath.substring(0, spath.lastIndexOf(File.separator));
				if (photo.getSize() / 1048576L > (long) maxSize.intValue()) {
					return "maxSize";
				} else {
					Date mydate = new Date();
					String Fileext = dealFileName.substring(dealFileName.indexOf("."), dealFileName.length());
					String RandomNum = ab.b(2);
					String newFileName = mydate.getTime() + "_" + RandomNum + Fileext;
					String path = savePath + "/download/uploadFile/" + dirName + "/";
					hostPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
							+ "/download/uploadFile/" + dirName + "/";
					File aa = new File(path);
					if (!aa.exists()) {
						aa.mkdirs();
					}

					photo.transferTo(new File(path, newFileName));
					hostPath = hostPath + newFileName;
					return hostPath;
				}
			}
		}
	}

	public static void a(InputStream is, String fileName, String filePath) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		File f = new File(filePath + File.separator + fileName);

		try {
			bis = new BufferedInputStream(is);
			fos = new FileOutputStream(f);
			bos = new BufferedOutputStream(fos);
			byte[] e = new byte[4096];

			int len;
			while ((len = bis.read(e)) > 0) {
				bos.write(e, 0, len);
			}
		} catch (Exception arg17) {
			arg17.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
					bos = null;
				}

				if (fos != null) {
					fos.close();
					fos = null;
				}

				if (is != null) {
					is.close();
					is = null;
				}

				if (bis != null) {
					bis.close();
					bis = null;
				}
			} catch (Exception arg16) {
				arg16.printStackTrace();
			}

		}

	}
}