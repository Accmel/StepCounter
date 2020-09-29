import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author acc
 *
 */
public class StepCounter extends JFrame{

	public final String extension;
	private int count;

	public static void main(String[] args) {
		System.out.println("Please select extension.");
		Scanner sc = new Scanner(System.in);
		String extension = sc.next();
		sc.close();

		System.out.println();
		System.out.println("==============================");
		//　ファイル選択画面を表示
		StepCounter counter = new StepCounter(extension);
		System.out.println("Please select File or Directory");
		System.out.println();
		counter.display();
	}

	public StepCounter(String extension) {
		this.extension = extension;
		this.count = 0;
	}

	public void display() {
		JFileChooser filechooser = new JFileChooser("C:\\Users\\taich\\Documents\\programing");
	    filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

	    int selected = filechooser.showOpenDialog(this);
	    if (selected == JFileChooser.APPROVE_OPTION){
	      File file = filechooser.getSelectedFile();
	      if(file.isFile()) {
	    	  checkFileStep(file);
	      }else if(file.isDirectory()) {
	    	  checkDirectoryStep(file);
	      }else {
	    	  System.out.println("The selected file directory is invalid.");
	    	  System.exit(0);
	      }

	      System.out.println("==============================");
	      System.out.println("DrectoryAllCount : " + count + " lines");
	    }
	    System.exit(0);
	}

	public void checkDirectoryStep(File directory) {
		if(!directory.isDirectory()) System.exit(0);

		for(File file : directory.listFiles()) {
			if(file.isFile()) {
				if(file.getName().endsWith(this.extension)) {
					checkFileStep(file);
				}
			}else if(file.isDirectory()) {
				checkDirectoryStep(file);
			}
		}
	}


	public  void checkFileStep(File file) {
		int count = 0;

		try {
			FileReader fr = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);

			while(br.readLine() != null) count++;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		System.out.println(file.getName() + " : " + count + " lines");

		this.count += count;
	}

}
