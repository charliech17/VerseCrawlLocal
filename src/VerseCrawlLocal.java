import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.AWTException;
import java.awt.Button;

public class VerseCrawlLocal extends JFrame {
    private JFrame f,f2;
	private JPanel contentPane;
	private JTextField tf1,tf2,tf3;
    private JLabel lb1,lb2,lb3,lb4;
    private JComboBox Box,Box1,Box2,Box3;
    JTextArea textArea,txa2,txa3;
    String bookk[]= {"創","出","利","民","申","書","士","得","撒上","撒下","王上","王下","代上","代下","拉","尼","斯","伯","詩","箴","傳","歌","賽","耶","哀","結","但","何","珥","摩","俄","拿","彌","鴻","哈","番","該","亞","瑪","太","可","路","約","徒","羅","林前","林後","加","弗","腓","西","帖前","帖後","提前","提後","多","門","來","雅","彼前","彼後","約壹","約貳","約參","猶","啟"} ;
    String bookEng[]= {"Genesis","Exodus","Leviticus","Numbers","Deuteronomy","Joshua","Judges","Ruth","1 Samuel","2 Samuel","1Kings","2Kings","1 Chronicles","2 Chronicles","Ezra","Nehemiah","Esther","Job","Psalms","Proverbs","Ecclesiastes","Song of Songs","Isaiah","Jeremiah","Lamentations","Ezekiel","Daniel","Hosea","Joel","Amos","Obadiah","Jonah","Micah","Nahum","Habakkuk","Zephaniah","Haggai","Zechariah","Malachi","Matthew","Mark","Luke","John","Acts","Romans","1 Corinthians","2 Corinthians","Galatians","Ephesians","Philippians","Colossians","1 Thessalonians","2 Thessalonians","1 Timothy","2 Timothy","Titus","Philemon","Hebrews","James","1 Peter","2 Peter","1 John","2 John","3 John","Jude","Revelation"};
	String ChineseNum[]= {"一","二","三","四","五","六","七","八","九","十"};
	String trasNum[]= {"1","2","3","4","5","6","7","8","9","10"};
    String filename="未命名.txt";
	String dirName ="C:\\Users\\user\\Desktop";
	String filePath="",bookkk,b,c,v,findstrb,tw,chars="～~0123456789",vi,vf,verse,save="",s,saveall="",ver2="";//ver2=""
	private JTextField txf,txf4;
	private JButton btn1,btn2,btn3,btn4,btn5,btn6;
	Button btn7;
	
	private JCheckBox chkbox,chkbox2;
    int count=1,num=0,times=0,chk2count=0,book,idx,idx1,idx2;
    JScrollPane sp,sp0,sp2;
    
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
			//pw.println("");
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
	
	private void keypress() {
		txa2.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent key) {
				
				if(key.getKeyCode() == KeyEvent.VK_S &&  ((key.getModifiers() & KeyEvent.CTRL_MASK) != 0))

	      			btn6.doClick();
			}
			
			});
		
	}
	
	public void keypress2 (){
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
		  .addKeyEventDispatcher(new KeyEventDispatcher() {
		      @Override
		      public boolean dispatchKeyEvent(KeyEvent e) {
		       if(e.getKeyCode() == KeyEvent.VK_D && e.getID()==KeyEvent.KEY_PRESSED && (e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
		    	  btn1.doClick();
		       }
		       
		       if(e.getKeyCode() == KeyEvent.VK_E && e.getID()==KeyEvent.KEY_PRESSED && (e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
		    	   PointerInfo a = MouseInfo.getPointerInfo();Point b = a.getLocation();
		    	    int xOrig = (int) b.getX();
		    	    int yOrig = (int) b.getY();
		    	   Point p=lb1.getLocationOnScreen();
		    	    int mouseX=p.x; // int mouseX =getX()+lb1.getX() +12;  //getX()+lb1.getX() +12;
		    	    int mouseY=p.y;// int mouseY =getY()+lb1.getY() +35; 
		          
		          Robot robot;
				try {
					  robot = new Robot();
					  robot.mouseMove(mouseX, mouseY);
			          
					  robot.mousePress(InputEvent.BUTTON1_MASK);robot.mouseRelease(InputEvent.BUTTON1_MASK);robot.mousePress(InputEvent.BUTTON1_MASK);robot.mouseRelease(InputEvent.BUTTON1_MASK);
			          robot.mouseWheel(10);
			          robot.mouseMove(xOrig, yOrig);
				} catch (AWTException e1) {e1.printStackTrace();}
		       }
		       
		       if(e.getKeyCode() == KeyEvent.VK_F && e.getID()==KeyEvent.KEY_PRESSED && (e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
			    	chkbox2.doClick();
			       }
		       if(e.getKeyCode() == KeyEvent.VK_Q && e.getID()==KeyEvent.KEY_PRESSED && (e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
		    	   toFront();
		    	   requestFocusInWindow();
		    	   txf.requestFocusInWindow();
			       }
		       
		       if(e.getKeyCode() == KeyEvent.VK_T && e.getID()==KeyEvent.KEY_PRESSED && (e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
		    	   	txf.setText(null);
		    	   	tf1.setText(null);
			       }
		       if(e.getKeyCode() == KeyEvent.VK_G && e.getID()==KeyEvent.KEY_PRESSED && (e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
			    	chkbox.doClick();
			       }
		        return false;
		      }
		});
		
		}
      
    
	public VerseCrawlLocal() {
		setBackground(new Color(255, 255, 255));
		setAlwaysOnTop(true);
		setTitle("經文查詢or紀錄");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 100, 590, 275);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setFocusable(true);
		setResizable(false);
	
		
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

		
		
		btn1 = new JButton("\u5217\u51FA\u7D93\u7BC0"+" (Ctrl+d)");
		btn1.setBounds(365, 34, 160, 23);
		contentPane.add(btn1);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);         
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("微軟正黑體",Font.PLAIN,16));
		
		
		sp0=new JScrollPane(textArea);
		sp0.setBounds(10, 145, 550,73);
		contentPane.add(sp0);
		
		
		
		btn2 = new JButton("\u589E\u52A0\u5206\u9694\u7DDA (Ctrl-m)");
		btn2.setBounds(235, 80, 160, 23);
		contentPane.add(btn2);
		
		chkbox = new JCheckBox("\u986F\u793A\u6240\u6709\u7D93\u6587");
		chkbox.setBounds(360, 56, 104, 23);
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
		
		btn5 = new JButton("\u66F4\u8B8A\u5132\u5B58\u6A94\u6848 (Ctrl-w)");
		btn5.setBounds(235, 113, 160, 23);
		contentPane.add(btn5);
		
		
		
		btn2.setEnabled(false);
		btn3.setEnabled(false);
		btn5.setEnabled(false);
		chkbox.setEnabled(false);
		
		Box1 = new JComboBox();
		Box1.setBounds(399, 105, 59, 21);
		contentPane.add(Box1);
		String[] fonttyp= {"黑體","微軟正黑體","新細明體","標楷體"};
		for(int i=0 ;i<fonttyp.length ;i++){Box1.addItem(fonttyp[i]);}
		Box1.setSelectedIndex(1);
		
		Box2 = new JComboBox();
		Box2.setBounds(465, 105, 46, 21);
		contentPane.add(Box2);
		Object[] thick= {"細","粗"};
		for(int i=0 ;i<thick.length ;i++){Box2.addItem(thick[i]);}
		Box2.setSelectedIndex(0);
		
		Box3 = new JComboBox();
		Box3.setBounds(520, 105, 46, 21);
		Object[] size= {12,14,16,18,20,24};
		contentPane.add(Box3);
		for(int i=0 ;i<size.length ;i++){Box3.addItem(size[i]);}
		Box3.setSelectedIndex(2);
		
		
		
		JLabel lb5 = new JLabel("\u5B57\u9AD4");
		lb5.setHorizontalAlignment(SwingConstants.CENTER);
		lb5.setBounds(405, 90, 46, 15);
		contentPane.add(lb5);
		
		JLabel lb6 = new JLabel("\u7C97\u7D30");
		lb6.setHorizontalAlignment(SwingConstants.CENTER);
		lb6.setBounds(465, 90, 46, 15);
		contentPane.add(lb6);
		
		JLabel lb7 = new JLabel("\u5927\u5C0F");
		lb7.setHorizontalAlignment(SwingConstants.CENTER);
		lb7.setBounds(520, 90, 46, 15);
		contentPane.add(lb7);
		
		JLabel lblNewLabel = new JLabel("\u986F\u793A\u5B57\u9AD4");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(465, 67, 50, 20);
		contentPane.add(lblNewLabel);
		
		btn7 = new Button("\u78BA\u8A8D\u5B57\u9AD4");
		btn7.setForeground(new Color(0, 0, 0));
		btn7.setBackground(new Color(230, 230, 250));
		btn7.setFont(new Font("Ebrima", Font.BOLD, 12));
		btn7.setBounds(400, 129, 167, 15);
		contentPane.add(btn7);
		
		chkbox2 = new JCheckBox("\u526A\u8CBC\u7C3F");
		chkbox2.setBounds(510, 220, 65, 15);
		contentPane.add(chkbox2);
		
		btn1.addActionListener((ActionListener)new btn1Listener() );
		btn2.addActionListener((ActionListener)new btn2Listener() );
		btn3.addActionListener((ActionListener)new btn3Listener() );
		btn4.addActionListener((ActionListener)new btn4Listener());
		btn5.addActionListener((ActionListener)new btn5Listener());
		btn7.addActionListener((ActionListener)new btn7Listener());
		chkbox.addActionListener((ActionListener)new chkboxListener());
		chkbox2.addActionListener((ActionListener)new chk2boxListener());
		
		keypress2();
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
			 		//System.out.println(v);
			 		
			 		
			 		//書卷
			 		idx1=txf.getText().lastIndexOf(v);
			 		//System.out.println("idx1="+idx1);
			 		tw=txf.getText().substring(0,idx1);
			 		for(int i=0;i<ChineseNum.length;i++) {
			 			b=tw.replaceAll(ChineseNum[i], "");
			 			tw=b;
			 		}
			 		//System.out.println(b);
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
			 		
			 		//System.out.println(c);
			 		
			 		
			 		break;
			 }
			 
			 if(v.contains("~")) {
				 vi=v.substring(0,v.indexOf("~"));
		 		 vf=v.substring(v.indexOf("~")+1);
				 for(int i=Integer.valueOf(vi);i<=Integer.valueOf(vf);i++) {versefind(bookkk,c,String.valueOf(i));} 
			 }else {versefind(bookkk,c,v);}
			 
			 
			 
			 if(chk2count==0) {saveall+=textArea.getText();}else if(chk2count==1) {saveall=txa3.getText()+textArea.getText();txa3.setText(saveall);}
			 
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
			    		  if(count==2) {
			     			   getfileWrite(ver2);
			     			   ifChoosechkShowfilechange();
			     		   }
		    }
		    
    		textArea.setText(save);
  			textArea.setCaretPosition(0);
  			
		   
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
		    	btn1.setText("增加經節 (Ctrl+d)");
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
			chkbox.setSelected(false);
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
	class btn7Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {			
			textArea.setFont(new Font(Box1.getSelectedItem().toString(),Box2.getSelectedIndex(),Integer.valueOf(Box3.getSelectedItem().toString())));
			if(count==2) {
				textArea.setFont(new Font(Box1.getSelectedItem().toString(),Box2.getSelectedIndex(),Integer.valueOf(Box3.getSelectedItem().toString())));
			}
		}
}
	class chkboxListener implements ActionListener {
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(chkbox.isSelected()){
				//開啟f
			/*	*/
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
				f.setResizable(false);
				
				txa2 = new JTextArea();
				txa2.setBounds(10, 10, 450, 450);
				txa2.setLineWrap(true);         
				txa2.setWrapStyleWord(true);
				txa2.setFont(new Font("微軟正黑體",Font.PLAIN,16));
				
				sp=new JScrollPane(txa2);
				sp.setBounds(10, 10, 450, 450);
			    f.getContentPane().add(sp);
				
				showFileChange();
				btn6 = new JButton("save (Ctrl+S)");
				btn6.setBounds(320, 500, 150, 50);
				f.add(btn6);
				
				btn6.addActionListener((ActionListener)new btn6Listener());
				keypress();
				
			}else {
				btn6.doClick();
				f.dispatchEvent(new WindowEvent(f,WindowEvent.WINDOW_CLOSING));
				
			}
		    	
		}

}
	
	class chk2boxListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(chkbox2.isSelected()) {
				f2=new JFrame("顯示所有經文");
				f2.setLayout(null);
				f2.setVisible(true);
				f2.setSize(500,550);
				f2.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
				f2.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						chkbox2.setSelected(false);
			            }
					
				});
				f2.setResizable(false);
				
				txa3 = new JTextArea();
				txa3.setLineWrap(true);         
				txa3.setWrapStyleWord(true);
				txa3.setFont(new Font("微軟正黑體",Font.PLAIN,16));
				
				sp2=new JScrollPane(txa3);
				sp2.setBounds(15, 10, 450, 450);
			    f2.getContentPane().add(sp2);
				
				txa3.setText(saveall);
				chk2count=1;
				
			}else {
				saveall=txa3.getText();
				f2.dispatchEvent(new WindowEvent(f2,WindowEvent.WINDOW_CLOSING));
				chk2count=0;
			}
		    	
		}
}
	
	
}