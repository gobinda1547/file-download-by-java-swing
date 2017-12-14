package org.ju.cse.gobinda.downloader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WebDownloader extends JFrame {

	private static final long serialVersionUID = 1L;

	private static WebDownloader mainFrame;

	private JPanel contentPane;
	private JTextField tfDownloadFileSize;
	private JTextField tfDownloadFileName;
	private JTextField tfDownloadRUl;
	private JTextField tfSaveInDirectory;

	private JButton btnNewButton;
	private JProgressBar progressBar;

	public static void initialize() {
		if (mainFrame == null)
			mainFrame = new WebDownloader();
		mainFrame.setVisible(true);
	}

	public WebDownloader() {
		setTitle("File Downloader");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 469);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnNewButton = new JButton("Download");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Download.startDownload(tfDownloadRUl.getText().trim());
				refreshAllDesign(false, false);
			}
		});
		btnNewButton.setBounds(122, 140, 121, 33);
		contentPane.add(btnNewButton);

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 354, 331, 19);
		contentPane.add(progressBar);

		JLabel lblFileName = new JLabel("Download File Name");
		lblFileName.setBounds(10, 203, 331, 19);
		contentPane.add(lblFileName);

		JLabel lblDownloadFileSize = new JLabel("Download File Size");
		lblDownloadFileSize.setBounds(10, 263, 331, 19);
		contentPane.add(lblDownloadFileSize);

		tfDownloadFileSize = new JTextField();
		tfDownloadFileSize.setEditable(false);
		tfDownloadFileSize.setBounds(10, 293, 331, 19);
		contentPane.add(tfDownloadFileSize);
		tfDownloadFileSize.setColumns(10);

		tfDownloadFileName = new JTextField();
		tfDownloadFileName.setEditable(false);
		tfDownloadFileName.setColumns(10);
		tfDownloadFileName.setBounds(10, 233, 331, 19);
		contentPane.add(tfDownloadFileName);

		JLabel lblProgress = new JLabel("Progress");
		lblProgress.setBounds(10, 323, 331, 19);
		contentPane.add(lblProgress);

		JLabel lblDownloadUrl = new JLabel("Download URL");
		lblDownloadUrl.setBounds(10, 11, 331, 19);
		contentPane.add(lblDownloadUrl);

		tfDownloadRUl = new JTextField();
		tfDownloadRUl.setColumns(10);
		tfDownloadRUl.setBounds(10, 41, 331, 19);
		contentPane.add(tfDownloadRUl);

		JLabel lblSaveInDirectory = new JLabel("Save in Directory");
		lblSaveInDirectory.setBounds(10, 71, 331, 19);
		contentPane.add(lblSaveInDirectory);

		tfSaveInDirectory = new JTextField();
		tfSaveInDirectory.setEditable(false);
		tfSaveInDirectory.setText(getFileWhereToSave());
		tfSaveInDirectory.setColumns(10);
		tfSaveInDirectory.setBounds(10, 101, 331, 19);
		contentPane.add(tfSaveInDirectory);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(122, 396, 121, 33);
		contentPane.add(btnExit);
	}

	public static String getFileWhereToSave() {
		String computerName = System.getProperty("user.name");
		return "C:\\Users\\" + computerName + "\\Downloads";
	}

	public static void setTfDownloadFileSize(String downloadFileSize) {
		mainFrame.tfDownloadFileSize.setText(downloadFileSize);
	}

	public static void setTfDownloadFileName(String downloadFileName) {
		mainFrame.tfDownloadFileName.setText(downloadFileName);
	}

	public static void setProgress(int x) {
		mainFrame.progressBar.setValue(x);
	}

	public static void refreshAllDesign(boolean downloadButton, boolean tfDownloadFileURL) {
		mainFrame.btnNewButton.setEnabled(downloadButton);
		mainFrame.tfDownloadRUl.setEditable(tfDownloadFileURL);
		mainFrame.tfDownloadFileName.setText("");
		mainFrame.tfDownloadFileSize.setText("");
		mainFrame.tfDownloadRUl.setText("");
	}

}
