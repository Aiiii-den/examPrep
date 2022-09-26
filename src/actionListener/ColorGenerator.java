package actionListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ColorGenerator extends JFrame implements ActionListener{

	int fr, fb, fg, currentIndex;
	JPanel top, color;
	JTextField rt, rb, rg;
	ArrayList<Color> colors;
	JButton back, reset, save, forward;
	Color c;
	JLabel label;

	public ColorGenerator() {
		super(); 
		this.setTitle("GUI Übung");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		//this.getContentPane().add(initContent());

		JPanel upperPanel = inputRGB();
		this.getContentPane().add(upperPanel, BorderLayout.NORTH);

		this.color=new JPanel();
		this.color.setBackground(Color.WHITE);
		this.getContentPane().add(this.color, BorderLayout.CENTER);

		JPanel buttonPanel = createButtonPanel();
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		this.setLocation(300, 200); 
		this.setSize(400, 300); 
		this.setVisible(true);
		this.setResizable(false);

		this.colors=new ArrayList();
	}

	private JPanel inputRGB() {
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(2,1));

		JPanel topFlow = new JPanel();
		topFlow.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
		top.add(topFlow, BorderLayout.NORTH);

		JPanel topGrid = new JPanel();
		topGrid.setLayout(new GridLayout(3,2, 10, 3));
		top.add(topGrid);

		this.label = new JLabel("Geben Sie jeweils eine Zahl von 0 bis 255 ein!");
		topFlow.add(label);

		JLabel rot = new JLabel("Rot:", 10);
		JLabel blau = new JLabel("Blau:", 10);
		JLabel gruen = new JLabel("Gruen:", 10);
		this.rt = new JTextField();
		this.rb = new JTextField();
		this.rg = new JTextField();

		topGrid.add(rot);
		topGrid.add(rt);
		topGrid.add(blau);
		topGrid.add(rb);
		topGrid.add(gruen);
		topGrid.add(rg);

		return top;
	}

	private JPanel createButtonPanel() {
		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.back = new JButton("<");
		this.reset = new JButton("reset");
		this.save = new JButton("save");
		this.forward = new JButton(">");

		bottom.add(this.back);
		bottom.add(this.reset);
		bottom.add(this.save);
		bottom.add(this.forward);

		this.back.addActionListener(this);
		this.reset.addActionListener(this);
		this.save.addActionListener(this);
		this.forward.addActionListener(this);

		return bottom;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		//Object src = e.getSource();
		int rn = 0, bn = 0, gn = 0;
		System.out.println("action performed");

		//if (src instanceof JButton) {
		//JButton newB = (JButton)src;

		if(e.getSource() == this.save) {
			System.out.println("Save");
			String rt = this.rt.getText();
			String rb = this.rb.getText();
			String rg = this.rg.getText();
			try{
				rn = Integer.parseInt(rt);
				bn = Integer.parseInt(rb);
				gn = Integer.parseInt(rg);
			}
			catch (NumberFormatException ex){
				this.label.setText("Eingabe muss eine Zahl sein!");
			}

			boolean red =(rn >0 && rn<266);
			boolean green =(gn >0 && gn<266);
			boolean blue =(bn >0 && bn<266);

			if(red==true && green==true && blue== true) {
				c = new Color(rn, gn, bn);
				this.color.setBackground(c);
				//this.color.revalidate();
				//this.color.repaint();
				colors.add(c);
				this.label.setText("Geben Sie jeweils eine Zahl von 0 bis 255 ein!");
			}

			else {
				this.label.setText("Eingabe muss eine Zahl zwischen 0 und 255 sein!");
			}

			this.currentIndex = (this.colors.size())-1;
		}


		else if(e.getSource() == this.reset) {
			System.out.println("reset");
			this.colors.clear();
			this.rt.setText("");
			this.rb.setText("");
			this.rg.setText("");
			this.color.setBackground(Color.WHITE);

			/*Iterator<Color> it = this.colors.iterator();
			while(it.hasNext()) {
				Color current = it.next();
				if(current!=null){
					it.remove();
				}
			}*/
			//this.color.revalidate();
			//this.color.repaint(); 
		}


		else if(e.getSource()==this.back && this.currentIndex>0) {
			System.out.println("back");
			this.c=this.colors.get(this.currentIndex-1);
			this.color.setBackground(this.c);
			this.currentIndex--;

			//this.color.revalidate();
			//this.color.repaint(); 
		}



		else if(e.getSource()==this.forward && this.currentIndex<this.colors.size()-1) {
			System.out.println("forward");
			this.c=this.colors.get(this.currentIndex);
			this.color.setBackground(this.c);
			this.currentIndex++;
			//this.color.revalidate();
			//this.color.repaint(); 
		}
	}
	//}


	public static void main(String[] args) {
		new ColorGenerator();

	}	

}
