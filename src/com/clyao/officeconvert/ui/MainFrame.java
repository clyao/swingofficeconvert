package com.clyao.officeconvert.ui;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import com.clyao.officeconvert.customcomponent.ImagebgJPanel;
import com.clyao.officeconvert.util.PdfConvertToImage;
import com.clyao.officeconvert.util.PdfFileFilterUtil;
import com.clyao.officeconvert.util.WordConvertToImage;
import com.clyao.officeconvert.util.WordFileFilterUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JRadioButton;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.swing9patch.toast.Toast;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

/**   
* @Title: 文件转换图片UI类
* @Description: 文件转换图片UI类
* @author clyao   
* @date 2018-06-07 15:00 
* @version V1.0   
*/
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private String selectFilePath;
	private String saveFilePath;
	private String selectFileName;
	private File convertFile;
	
	private JPanel contentPane;
	private JTextField tfSelectFile;
	private JTextField tfSaveFile;
	private JButton btnSaveFile;
	private JButton btnSelectFile;
	private JButton btnConvert;
	private JPanel pnlTop;
	private ButtonGroup buttonGroup;
	private JRadioButton rdbtnWord;
	private JRadioButton rdbtnPdf;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(false);
		setTitle("文件转换图片工具");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 470);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBorder(null);
		contentPane.add(panel);
		
		tfSaveFile = new JTextField();
		tfSaveFile.setText("请选择保存的文件夹");
		tfSaveFile.setBounds(10, 363, 378, 35);
		tfSaveFile.setFocusable(false);
		tfSaveFile.setFocusTraversalKeysEnabled(false);
		tfSaveFile.setEditable(false);
		tfSaveFile.setColumns(10);
		
		btnConvert = new JButton("开始转换");
		btnConvert.setForeground(Color.WHITE);
		btnConvert.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		btnConvert.setIcon(new ImageIcon(this.getClass().getResource("/convert.png")));
		btnConvert.setBounds(412, 363, 110, 37);
		btnConvert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//判断是否有选择文件
				if(selectFilePath != null && saveFilePath != null){
					//判断选择转换的文件类型和是否有选择文件和保存目录
					if(rdbtnWord.isSelected() && selectFileName.indexOf(".doc") != -1){
						//将word转换为图片
						Toast.showTost(3, "正在进行转换中......", null);
						WordConvertToImage.convertWord(selectFilePath, convertFile, saveFilePath, selectFileName);
					}else if(rdbtnPdf.isSelected() && selectFileName.indexOf(".pdf") != -1){
						//将pdf转换为图片
						Toast.showTost(3, "正在进行转换中......", null);
						PdfConvertToImage.convertPdf(selectFilePath, convertFile, saveFilePath, selectFileName);
					}else{
						JOptionPane.showMessageDialog(null, "选择的文件和要转换的类型对应", "提示", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "请选择需要转换的文件和保存的路径", "提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.setLayout(null);
		
		btnSaveFile = new JButton();
		btnSaveFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSaveFile.setFocusPainted(false);
		btnSaveFile.setContentAreaFilled(false);
		btnSaveFile.setIcon(new ImageIcon(this.getClass().getResource("/selectfolder.png")));
		btnSaveFile.setBounds(353, 363, 35, 35);
		btnSaveFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser jFileChooser=new JFileChooser();
				jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );  
				jFileChooser.showDialog(new JLabel(), "选择");  
		        File file=jFileChooser.getSelectedFile();
		        if(file != null && file.isDirectory()){
		        	saveFilePath = file.getAbsolutePath();
		        	tfSaveFile.setText(saveFilePath);
		        }
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSaveFile.setIcon(new ImageIcon(this.getClass().getResource("/selectfoldered.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSaveFile.setIcon(new ImageIcon(this.getClass().getResource("/selectfolder.png")));
			}
		});
		
		btnSelectFile = new JButton();
		btnSelectFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSelectFile.setFocusPainted(false);
		btnSelectFile.setContentAreaFilled(false);
		btnSelectFile.setIcon(new ImageIcon(this.getClass().getResource("/selectfile.png")));
		btnSelectFile.setBounds(353, 293, 35, 35);
		btnSelectFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser jFileChooser=new JFileChooser();
				if(rdbtnWord.isSelected()){
					WordFileFilterUtil wordFileFilterUtil = new WordFileFilterUtil();
					jFileChooser.addChoosableFileFilter(wordFileFilterUtil);
					jFileChooser.setFileFilter(wordFileFilterUtil);
				}else{
					PdfFileFilterUtil pdfFileFilterUtil = new PdfFileFilterUtil();
					jFileChooser.addChoosableFileFilter(pdfFileFilterUtil);
					jFileChooser.setFileFilter(pdfFileFilterUtil);
				}
				jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
				jFileChooser.showDialog(new JLabel(), "选择");  
		        File file=jFileChooser.getSelectedFile();
		        if(file != null && file.isFile()){
		        	selectFilePath = file.getAbsolutePath();
		        	selectFileName = file.getName();
		        	tfSelectFile.setText(selectFilePath);
		        }
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSelectFile.setIcon(new ImageIcon(this.getClass().getResource("/selectfiled.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSelectFile.setIcon(new ImageIcon(this.getClass().getResource("/selectfile.png")));
			}
		});
		
		pnlTop = new ImagebgJPanel(this.getClass().getResource("/top.png"));
		pnlTop.setBounds(0, 0, 550, 273);
		pnlTop.setBorder(null);
		panel.add(pnlTop);
		panel.add(btnSelectFile);
		
		tfSelectFile = new JTextField();
		tfSelectFile.setText("请选择需要转换的文档");
		tfSelectFile.setBounds(10, 293, 378, 35);
		tfSelectFile.setFocusTraversalKeysEnabled(false);
		tfSelectFile.setFocusable(false);
		tfSelectFile.setEditable(false);
		tfSelectFile.setColumns(10);
		panel.add(tfSelectFile);
		panel.add(btnSaveFile);
		panel.add(tfSaveFile);
		panel.add(btnConvert);
		
		rdbtnWord = new JRadioButton("Word转图片");
		rdbtnWord.setSelected(true);
		rdbtnWord.setFont(new Font("宋体", Font.PLAIN, 14));
		rdbtnWord.setBounds(412, 293, 104, 23);
		panel.add(rdbtnWord);
		
		rdbtnPdf = new JRadioButton("PDF转图片");
		rdbtnPdf.setFont(new Font("宋体", Font.PLAIN, 14));
		rdbtnPdf.setBounds(412, 328, 104, 23);
		panel.add(rdbtnPdf);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnWord);
		buttonGroup.add(rdbtnPdf);
		
		JLabel lblCopyright = new JLabel("Copyright ©2018 Powered By: clyao  QQ：837904664  Email：837904664@qq.com");
		lblCopyright.setFont(new Font("宋体", Font.BOLD, 12));
		lblCopyright.setForeground(new Color(60, 159, 247));
		lblCopyright.setBounds(10, 410, 512, 15);
		panel.add(lblCopyright);
	}
	
}
