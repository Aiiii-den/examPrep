package mouseListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class Clicks extends JFrame implements MouseListener{

	private int heigth, width, arrayLength;
	private JPanel[] panels;
	private JLabel[] labels;
	JPanel grid;
	JButton button;
	int number=0;
	int maxNumber=0;
	int locationRed;
	int [] countsLabel;

	public Clicks(int height, int width) {
		super(); 
		this.heigth=height;
		this.width=width;
		this.arrayLength=height*width;
		this.panels=new JPanel[this.arrayLength];
		this.labels=new JLabel[this.arrayLength];
		this.countsLabel=new int[this.arrayLength];

		this.setTitle("Counting clicks");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		this.getContentPane().add(initContent());

		this.setLocation(300, 200); 
		this.setSize(400, 300); 
		this.setVisible(true);
		this.setResizable(false);

	}

	public JPanel initContent() {

		//Main Panel
		JPanel main = new JPanel(); 
		main.setLayout(new BorderLayout(5, 5));
		main.setBackground(Color.LIGHT_GRAY); 


		//BorderLayout North
		this.grid=new JPanel();
		grid.setLayout(new GridLayout(this.heigth, this.width));
		main.add(this.grid, BorderLayout.CENTER);
		for(int i=0; i<this.arrayLength; i++ ) {
			this.panels[i]= new JPanel();
			this.labels[i]= new JLabel();
			this.labels[i].setText("0");
			this.panels[i].add(this.labels[i]);
			this.grid.add(this.panels[i]);
			this.panels[i].addMouseListener(this);
			this.panels[i].setBackground(Color.LIGHT_GRAY);
			this.panels[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
		}

		//BorderLayout South
		JPanel south=new JPanel();
		south.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		main.add(south, BorderLayout.SOUTH);
		this.button = new JButton("reset");
		south.add(button);
		this.button.addMouseListener(this);

		return main;

	}

	public static void main(String[] args) {
		new Clicks(3, 3);

	}



	@Override
	public void mouseClicked(MouseEvent e) {
		Object src = e.getSource();
		if(src instanceof JButton) {
			reset();
		}

		if(src instanceof JPanel) {
			for(int i=0; i<this.arrayLength; i++) {
				if(src==panels[i]) {
					this.countsLabel[i]++;
					String numberString=""+this.countsLabel[i];
					labels[i].setText(numberString);
					numberString="";
				}

				if(this.countsLabel[i]>0 && this.countsLabel[i]>=this.maxNumber) {
					this.locationRed=i;
					markRed();
					if(this.countsLabel[i]>this.maxNumber) {
						this.maxNumber++;
					}
				}
				/*else {
					this.panels[i].setBackground(Color.LIGHT_GRAY);
				}*/
			}
			for(int y=0; y<this.arrayLength; y++) {
				for(int in=0; in<this.arrayLength; in++) {
					if(this.countsLabel[in]<this.maxNumber) {
						this.panels[in].setBackground(Color.LIGHT_GRAY);
					}
				}
			}
		}
	}


	public void markRed() {
		this.panels[this.locationRed].setBackground(Color.RED);
	}

	public void reset() {
		this.maxNumber=0;
		this.number=0;
		for(int i=0; i<this.arrayLength; i++) {
			panels[i].setBackground(Color.LIGHT_GRAY);
			labels[i].setText("0");
			this.countsLabel[i]=0;
			
		}
		//look up how to repaint a JPanel!!!
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
