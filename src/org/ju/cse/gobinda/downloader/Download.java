package org.ju.cse.gobinda.downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class Download implements Runnable {

	private String fileURL;

	public Download(String fileURL) {
		this.fileURL = fileURL;
	}

	@Override
	public void run() {

		boolean isProblemOccurs = false;
		try {

			URL url = new URL(fileURL);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

			long contentLength = httpConn.getContentLength();
			String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());

			System.out.println("Content-Length = " + contentLength);
			WebDownloader.setTfDownloadFileSize(String.valueOf(contentLength));
			System.out.println("fileName = " + fileName);
			WebDownloader.setTfDownloadFileName(fileName);

			InputStream inputStream = httpConn.getInputStream();

			String saveFilePath = WebDownloader.getFileWhereToSave() + File.separator + fileName;
			FileOutputStream outputStream = new FileOutputStream(saveFilePath);

			int BUFFER_SIZE = 4096;
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			long totalBytesRead = 0;
			int percentCompleted = 0;
			long fileSize = contentLength;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
				totalBytesRead += bytesRead;
				percentCompleted = (int) (totalBytesRead * 100 / fileSize);

				WebDownloader.setProgress(percentCompleted);
			}

			outputStream.close();
			inputStream.close();
			httpConn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Exception occurs. try again.");
			isProblemOccurs = true;
		}

		WebDownloader.refreshAllDesign(true, true);
		if (isProblemOccurs == false)
			JOptionPane.showMessageDialog(null, "Download Complete");
	}

	public static void startDownload(String fileURL) {
		new Thread(new Download(fileURL)).start();
	}

}
