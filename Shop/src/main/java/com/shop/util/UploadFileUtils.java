package com.shop.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

	/**
	 * 파일 업로드 할때 파일이름 앞에 랜덤한 uuid와 _을 추가하여 uploadPath와 함께 오늘 날짜를 계산하여 그 경로에 파일을
	 * 저장하며, 해당 경로에 올리는 타입이 이미지이면 미리보기 이미지를 만들고 아니면 아이콘을 만들어 해당 경로를 반환한다.
	 * 
	 * @param originalName
	 * @param uploadPath
	 * @param fileData
	 * @return
	 * @throws IOException
	 */
	public static String uploadFile(String originalName, String uploadPath, byte[] fileData) throws IOException {
		UUID uid = UUID.randomUUID();
		String path = calcPath(uploadPath);

		String savedName = uid.toString() + "_" + originalName;
		File file = new File(uploadPath + path, savedName);

		FileCopyUtils.copy(fileData, file);

		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadFileName = "";

		if (MediaUtils.getMediaType(formatName) != null) {
			uploadFileName = makeThumnail(uploadPath, path, savedName);
		} else {
			uploadFileName = makeIcon(uploadPath, path, savedName);
		}
		return uploadFileName;
	}

	/**
	 * 업로드가 되는 타입이 이미지가 아닌 경우 해당 경로를 만들어 넘겨준다.
	 * 
	 * @param uploadPath
	 * @param path
	 * @param savedName
	 * @return
	 */
	public static String makeIcon(String uploadPath, String path, String savedName) {
		String iconName = uploadPath + path + File.separator + savedName;
		String name = iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
		return name;
	}

	/**
	 * 업로드가 되는 타입이 이미지일 경우 해당 경로의 이미지를 불러와 자동으로 이미지 크기를 줄이고, 이미지 이름 앞에 s_를 추가 하여 크기를
	 * 줄인 이미지를 한번더 저장하고 경로를 반환한다.
	 * 
	 * @param uploadPath
	 * @param path
	 * @param savedName
	 * @return
	 * @throws IOException
	 */
	public static String makeThumnail(String uploadPath, String path, String savedName) throws IOException {
		BufferedImage fileName = ImageIO.read(new File(uploadPath + path, savedName));
		BufferedImage sourceImg = Scalr.resize(fileName, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

		String thumnail = uploadPath + path + File.separator + "s_" + savedName;
		File tumnFile = new File(thumnail);

		String formatName = savedName.substring(savedName.lastIndexOf(".") + 1);
		ImageIO.write(sourceImg, formatName.toUpperCase(), tumnFile);

		String name = thumnail.substring(uploadPath.length()).replace(File.separatorChar, '/');
		return name;
	}

	/**
	 * 오늘 날짜를 계산하여 디렉토리를 만들어 반환한다.
	 * 
	 * @param uploadPath
	 * @return
	 */
	public static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		mkdir(uploadPath, yearPath, monthPath, datePath);
		return datePath;
	}

	/**
	 * 넘겨 받은 매게변수를 가지고 폴더를 생성한다.
	 * 
	 * @param uploadPath
	 * @param paths
	 */
	public static void mkdir(String uploadPath, String... paths) {
		if (new File(paths[paths.length - 1]).exists())
			return;

		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
}
