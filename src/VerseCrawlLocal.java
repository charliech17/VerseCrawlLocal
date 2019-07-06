import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

public class VerseCrawlLocal extends JFrame {
    private JFrame f;
	private JPanel contentPane;
	private JTextField tf1,tf2,tf3;
    private JLabel lb1,lb2,lb3,lb4;
    private JComboBox Box;
    JTextArea textArea,txa2;
    String bookk[]= {"創","出","利","民","申","書","士","得","撒上","撒下","王上","王下","代上","代下","拉","尼","斯","伯","詩","箴","傳","歌","賽","耶","哀","結","但","何","珥","摩","俄","拿","彌","鴻","哈","番","該","亞","瑪","太","可","路","約","徒","羅","林前","林後","加","弗","腓","西","帖前","帖後","提前","提後","多","門","來","雅","彼前","彼後","約壹","約貳","約參","猶","啟"} ;
    String bookEng[]= {"Genesis","Exodus","Leviticus","Numbers","Deuteronomy","Joshua","Judges","Ruth","1 Samuel","2 Samuel","1Kings","2Kings","1 Chronicles","2 Chronicles","Ezra","Nehemiah","Esther","Job","Psalms","Proverbs","Ecclesiastes","Song of Songs","Isaiah","Jeremiah","Lamentations","Ezekiel","Daniel","Hosea","Joel","Amos","Obadiah","Jonah","Micah","Nahum","Habakkuk","Zephaniah","Haggai","Zechariah","Malachi","Matthew","Mark","Luke","John","Acts","Romans","1 Corinthians","2 Corinthians","Galatians","Ephesians","Philippians","Colossians","1 Thessalonians","2 Thessalonians","1 Timothy","2 Timothy","Titus","Philemon","Hebrews","James","1 Peter","2 Peter","1 John","2 John","3 John","Jude","Revelation"};
	String ChineseNum[]= {"一","二","三","四","五","六","七","八","九","十"};
	String trasNum[]= {"1","2","3","4","5","6","7","8","9","10"};
    String filename="未命名.txt";
	String dirName ="C:\\Users\\user\\Desktop";
	String filePath="",bookkk,b,c,v,findstrb,tw,chars="～~0123456789",vi,vf,ver2="",verse,save="",s;
	private JTextField txf,txf4;
	private JButton btn1,btn2,btn3,btn4,btn5,btn6;
	private JCheckBox chkbox;
    int count=1,num=0,times=0,book,idx,idx1,idx2;
    JScrollPane sp,sp0;
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerseCrawlLocal frame = new VerseCrawlLocal();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void getfileWrite(String txt){
		try{
			File dir = new File (dirName);
	    	File file=new File(dir,filename);
		    FileWriter fw;
		    fw = new FileWriter(file,true);
		    PrintWriter pw=new PrintWriter(fw);
		    pw.println(txt);
			pw.println("");
			pw.close();	
		}catch(Exception ex) {}
	}
	
	public String getFileText(String filePath){
		StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
 
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        String html=contentBuilder.toString();
		
        return html;
	}
	
	public void showFileChange(){
		//讀取文字
		String text=getFileText(dirName+"\\"+filename);
		txa2.setText(text);
	}
	
	public void ifChoosechkShowfilechange() {
		 if(chkbox.isSelected()) {
			  showFileChange();
		  }
		
	}
	
	public void lb1setting() {
		lb1.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	//num=0
		    	switch(num) {
		    		case 0:
		    			Box.setVisible(false);
				        contentPane.remove(Box);
				        
				        txf = new JTextField();
						txf.setBounds(22, 35, 59, 21);
						contentPane.add(txf);
						num=1;
						break;
		    		case 1:
		    			tf1.setEnabled(false);
		    			tf2.setEnabled(false);
		    			tf1.setVisible(false);
		    			tf2.setVisible(false);
		    			lb1.setText("書卷加章節");
		    			lb1.setBounds(20, 10, 75, 15);
		    			lb2.setText("");
		    			lb3.setText("");
		    			lb4=new JLabel("<html>書卷加經節需要輸入正確，<br/>以下為範例: 創一1、出二1~10</html>",SwingConstants.CENTER);
		    			lb4.setFont(new Font("新細明體", Font.BOLD, 14));
		    			lb4.setBounds(134, 0, 200, 70);
		    			contentPane.add(lb4);
		    			
		    			num=2;
		    			break;
		    		case 2:
		    			contentPane.remove(txf);
		    			contentPane.remove(lb4);
		    			tf1.setEnabled(true);
		    			tf2.setEnabled(true);
		    			tf1.setVisible(true);
		    			tf2.setVisible(true);
		    			lb1.setText("書卷");
		    			lb1.setBounds(20, 10, 46, 15);
		    			lb2.setText("章");
		    			lb3.setText("節");
		    			
		    			Box = new JComboBox();
		    			Box.setBounds(22, 35, 59, 21);
		    			contentPane.add(Box);
		    			for(int i=0 ;i<bookk.length ;i++){Box.addItem(bookk[i]);}
		    			
		    			num=0;
		    			break;
		    	}
		    	 
					
		    }  
		});		
		
	}
	
	
	public VerseCrawlLocal() {
		setAlwaysOnTop(true);
		setTitle("經文查詢or紀錄");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 100, 581, 267);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lb1 = new JLabel("\u66F8\u5377");
		lb1.setFont(new Font("新細明體", Font.BOLD, 14));
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setBounds(20, 10, 46, 15);
		contentPane.add(lb1);
		lb1setting();
		
		

		lb2 = new JLabel("\u7AE0");
		lb2.setFont(new Font("新細明體", Font.BOLD, 14));
		lb2.setHorizontalAlignment(SwingConstants.CENTER);
		lb2.setBounds(134, 10, 59, 15);
		contentPane.add(lb2);
		
		tf1 = new JTextField();
		tf1.setBounds(114, 35, 96, 21);
		contentPane.add(tf1);
		tf1.setColumns(10);
		
		tf2 = new JTextField();
		tf2.setBounds(259, 35, 96, 21);
		contentPane.add(tf2);
		tf2.setColumns(10);
		
		lb3 = new JLabel("\u7BC0");
		lb3.setHorizontalAlignment(SwingConstants.CENTER);
		lb3.setFont(new Font("新細明體", Font.BOLD, 14));
		lb3.setBounds(284, 10, 46, 15);
		contentPane.add(lb3);
		
		Box = new JComboBox();
		Box.setBounds(22, 35, 59, 21);
		contentPane.add(Box);
		for(int i=0 ;i<bookk.length ;i++){Box.addItem(bookk[i]);}

		
		
		btn1 = new JButton("\u5217\u51FA\u7D93\u7BC0");
		btn1.setBounds(365, 34, 87, 23);
		contentPane.add(btn1);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);         
		textArea.setWrapStyleWord(true);
		
		sp0=new JScrollPane(textArea);
		sp0.setBounds(10, 145, 550,73);
		contentPane.add(sp0);
		
		
		btn2 = new JButton("\u589E\u52A0\u5206\u9694\u7DDA");
		btn2.setBounds(246, 80, 124, 23);
		contentPane.add(btn2);
		
		chkbox = new JCheckBox("\u986F\u793A\u6240\u6709\u7D93\u6587");
		chkbox.setBounds(365, 56, 97, 23);
		contentPane.add(chkbox);
		
		
		tf3 = new JTextField();
		tf3.setBounds(22, 81, 96, 21);
		contentPane.add(tf3);
		tf3.setColumns(10);
		
		btn3 = new JButton("\u589E\u52A0\u6587\u5B57");
		btn3.setBounds(134, 80, 87, 23);
		contentPane.add(btn3);
		
		txf4 = new JTextField();
		txf4.setBounds(22, 114, 96, 21);
		contentPane.add(txf4);
		txf4.setColumns(10);
		
		btn4 = new JButton("\u5B58\u6A94\u540D\u7A31");
		btn4.setBounds(134, 113, 87, 23);
		contentPane.add(btn4);
		
		btn5 = new JButton("更便儲存檔案");
		btn5.setBounds(246, 113, 124, 23);
		contentPane.add(btn5);
		
		
		
		btn2.setEnabled(false);
		btn3.setEnabled(false);
		btn5.setEnabled(false);
		chkbox.setEnabled(false);
		
		btn1.addActionListener((ActionListener)new btn1Listener() );
		btn2.addActionListener((ActionListener)new btn2Listener() );
		btn3.addActionListener((ActionListener)new btn3Listener() );
		btn4.addActionListener((ActionListener)new btn4Listener());
		btn5.addActionListener((ActionListener)new btn5Listener());
		chkbox.addActionListener((ActionListener)new chkboxListener());
		
	}
	


	public void bcvclear() {
		bookkk="";
		c="";
		v="";
		ver2="";
		verse="";
		times=0;
		save="";
	}

	
	class btn1Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			 
			 switch(num) {
			 	case 0: //有select box
			 		 book= Box.getSelectedIndex();	
			 		 bookkk=bookEng[book];
			 		 c=tf1.getText();
			 		 v=tf2.getText();
			 		 break;
			 	case 1: //書卷用輸入的
			 		findstrb=txf.getText();
			 		if(Arrays.asList(bookk).contains(findstrb)) {
						book=Arrays.asList(bookk).indexOf(findstrb);
						bookkk=bookEng[book];	
						
					}
			 		c=tf1.getText();
			 		v=tf2.getText();
			 		break;
			 	case 2: //全部用輸入的
			 		//節
			 		
			 		v=txf.getText().replaceAll("[^" + chars + "]","");   // "\\D+"
			 		if(v.contains("～")) {v=v.replaceAll("～", "~"); txf.setText(txf.getText().replaceAll("～", "~"));} 
			 		System.out.println(v);
			 		
			 		
			 		//書卷
			 		idx1=txf.getText().lastIndexOf(v);
			 		System.out.println("idx1="+idx1);
			 		tw=txf.getText().substring(0,idx1);
			 		for(int i=0;i<ChineseNum.length;i++) {
			 			b=tw.replaceAll(ChineseNum[i], "");
			 			tw=b;
			 		}
			 		System.out.println(b);
			 		book=Arrays.asList(bookk).indexOf(b);
			 		bookkk=bookEng[book];
			 		
			 		
			 		//章
			 		idx2=b.length();
			 		tw=txf.getText().substring(idx2, idx1);	
			 		for(int i=0;i<ChineseNum.length-1;i++) {
			 			c=tw.replaceAll(ChineseNum[i], trasNum[i]);
			 			tw=c;
			 		}
			 		if(c.contains("十")) {
			 			if     (c.length()==1)                 {c=c.replaceAll("十", "10");}
			 			else if(c.indexOf("十")==c.length()-1)  {c=c.replaceAll("十", "0");}
			 			else if(c.length()!=1&& c.indexOf("十")==0)  {c=c.replaceAll("十", "1");}
			 			else {c=c.replaceAll("十","");}
			 		}
			 		
			 		System.out.println(c);
			 		
			 		
			 		break;
			 }
			 
			 if(v.contains("~")) {
				 vi=v.substring(0,v.indexOf("~"));
		 		 vf=v.substring(v.indexOf("~")+1);
				 for(int i=Integer.valueOf(vi);i<=Integer.valueOf(vf);i++) {versefind(bookkk,c,String.valueOf(i));} 
			 }else {versefind(bookkk,c,v);}
			 
			 
			
			 
			 bcvclear();
		}

		private void versefind(String bookkk,String c,String v) {	 
			filePath = ".\\VerseData\\"+bookkk+c+".txt"; 
			
			org.jsoup.nodes.Document doc= Jsoup.parse(getFileText(filePath));
		    Elements ids = doc.select("a[name=\""+v+"\"]");
		    
		    for(Element id:ids) {
			    	       verse=id.parent().parent().text();
			    		   
			    		   String versef=verse.replaceAll("\\d+","");
			    		   if(verse!=null|!verse.isEmpty()) {
			    				   ver2=bookk[book]+c+":"+v+versef.substring(1); //.substring(1) //Box.getSelectedItem().toString()+c+":"+v+versef.substring(1)   	    
				    	}else {System.out.println("經文章節錯誤");}
			   
			    		 save=save+ver2+"\n";
			    		 System.out.println(ver2);
			  			 textArea.setText(save);
			  			  if(count==2) {
			     			   getfileWrite(ver2);
			     			   ifChoosechkShowfilechange();
			     		   }
		    
		    }
		   
				 
		  }
	
}
	
	
	
	
	class btn2Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {			
			        getfileWrite("_________________________________________________________________________________________________________________________________________________________________________________________\r\n" + 
		    		   		"");
					textArea.setText("分隔線");
					ifChoosechkShowfilechange();
		}
}
	
	
	class btn3Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
					getfileWrite(tf3.getText());
					textArea.setText(tf3.getText());  
					ifChoosechkShowfilechange();
		}
}
	
	
	class btn4Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
				
		    	filename=txf4.getText()+".txt";
		    	textArea.setText("Save to "+filename);
		    	
		    	count=2;
		    	btn1.setText("增加經節");
				btn2.setEnabled(true);
				btn3.setEnabled(true);
				btn5.setEnabled(true);
				btn4.setEnabled(false);
		    	chkbox.setEnabled(true);
		}
}
	

	
	class btn5Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			btn4.setEnabled(true);
			
			count=1;
	    	btn1.setText("列出經節");
			btn2.setEnabled(false);
			btn3.setEnabled(false);
			btn5.setEnabled(false);
			chkbox.setEnabled(false);
		}
}
	
	class btn6Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			  try{
		            /* RandomAccessFile file= new RandomAccessFile(dirName+"\\"+filename,"rw");
		             file.setLength(0);
		             getfileWrite(txa2.getText());
		             file.close();
		             */
				  FileWriter fw=new FileWriter(dirName+"\\"+filename,true);
				  RandomAccessFile file= new RandomAccessFile(dirName+"\\"+filename,"rw");
		          file.setLength(0);
		          txa2.write(fw);   
				  file.close();
		             }catch(Exception ex){}
		    	
		}
}
	
	class chkboxListener implements ActionListener {
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(chkbox.isSelected()){
				//開啟F2
				f=new JFrame("顯示所有經文");
				f.setLayout(null);
				f.setVisible(true);
				f.setSize(500,600);
				f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
				f.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						btn6.doClick();
						chkbox.setSelected(false);
			            }
					
				});
				
				txa2 = new JTextArea();
				//txa2.setBounds(10, 10, 450, 450);
				txa2.setLineWrap(true);         
				txa2.setWrapStyleWord(true);
				
				sp=new JScrollPane(txa2);
				sp.setBounds(10, 10, 450, 450);
			    f.getContentPane().add(sp);
				
				showFileChange();
				btn6 = new JButton("save");
				btn6.setBounds(400, 500, 80, 50);
				f.add(btn6);
				
				btn6.addActionListener((ActionListener)new btn6Listener());
				
			}else {
				btn6.doClick();
				f.dispatchEvent(new WindowEvent(f,WindowEvent.WINDOW_CLOSING));
			}
		    	
		}
}
	
}
	
	
	
