package jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
public class MemoryGame implements ActionListener {
	
	JFrame g;
	LinkedList<String> img = new LinkedList<String>(Arrays.asList("img1.jpg","img2.jpg", "img3.jpg", "img4.jpg", "img5.jpg", "img6.jpg", "img7.jpg", "img8.jpg", "img1.jpg","img2.jpg", "img3.jpg", "img4.jpg", "img5.jpg", "img6.jpg", "img7.jpg", "img8.jpg"));
	JLabel l1, l2, l3;
	JPanel p1, p2, p3;
	LinkedList<JButton> p = new LinkedList<JButton>();
	int j=0, tentatives = 5;
	JButton replay = new JButton("Replay");
	JButton quitter = new JButton("Quitter");
	JButton c1,c2, selectedCard;
	Timer t;
	LinkedList<JButton> b = new LinkedList<JButton>();
	LinkedList<JButton> matched = new LinkedList<JButton>();
	
	//constructeur
	public MemoryGame() {
		Collections.shuffle(img);
		g=new JFrame("game");
		l1=new JLabel("Memory game");
		l2=new JLabel("Votre score:"+j);
		l3=new JLabel("Nombre de tentatives restantes:"+tentatives);
		this.g.setSize(800,700);
		p1 = new JPanel(new BorderLayout());
		p2 = new JPanel(new GridLayout(4,4));
		int k = 0;
		while(k<16){
			JButton button = new JButton();
			b.add(button);
			button.setIcon(new ImageIcon(getClass().getResource("img0.png")));
			button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                	Object source = ae.getSource();
                    selectedCard = button;
                    for(int i =0; i<16;i++) {
        	        	if(source == b.get(i)) {		
        	        		tournerCarte(i);
        	        	}
                    }
            		 
                }
            });
			
			p2.add(button);
			k++;
		
		}
		 //construire le timer
        t = new Timer(750, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                verifierCartes();
            }
        });

        t.setRepeats(false);    
		replay.addActionListener(this);
		quitter.addActionListener(this);
		p3=new JPanel(new GridLayout());
		p3.add(l2);
		p3.add(l3);
		p3.add(replay);
		p3.add(quitter);
		p1.add(l1, BorderLayout.NORTH);
		p1.add(p2, BorderLayout.CENTER);
		p1.add(p3, BorderLayout.SOUTH);
		this.g.add(p1);
		g.setVisible(true);
		 
	}
	
	//retourner la carte lors du click
	 public void tournerCarte(int i){
	        if (p.isEmpty()){
	           c1 = selectedCard;
	            c1.setIcon(new ImageIcon(getClass().getResource(img.get(i))));
	            p.add(c1);
	        }

	        else{
	           c2 = selectedCard;
	            c2.setIcon(new ImageIcon(getClass().getResource(img.get(i))));
	            p.clear();
	            t.start();

	        }
	    }
	 
	 //Verifier si les deux cartes tournées sont identiques
	    public void verifierCartes(){
	        if (c1.getIcon().toString().equals(c2.getIcon().toString())){
	            c1.setEnabled(false);
	            c2.setEnabled(false); //Si les cartes sont identiquesles deux bouttons sont grisés 
	            matched.add(c1);
	            matched.add(c2);
	            j++;
				l2.setText("Votre score:"+j);
	            if (this.jeuGagne()){
	                JOptionPane.showMessageDialog(g,"Vous avez gagné!");
	                System.exit(0);
	            }
	        }

	        else{
	        	//si les deux cartes ne sont pas identiques, on décrémente le nombre de tentatives et on fait retourner une autre fois les cartes
	            c1.setIcon(new ImageIcon(getClass().getResource("img0.png"))); 
	            c2.setIcon(new ImageIcon(getClass().getResource("img0.png")));
	            tentatives--;
				l3.setText("Nombre de tentatives restantes:"+tentatives);
				if(tentatives==0) {
					JOptionPane.showMessageDialog(g,"Vous avez gagné!");
					p.clear();
					p2.removeAll();
					p2.setLayout(new GridLayout(1,1));
					JButton j=new JButton("");
					j.setIcon(new ImageIcon(getClass().getResource("img17.jpg")));
					p2.add(j);
					
				}
	        }
	        p.clear();
	    }
	    //vérifier est ce que le joueur a gagné le jeu
	    public boolean jeuGagne(){
	            if (matched.size()==16){
	                return true;
	            }
	        
	        return false;
	    }
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			 if(source == quitter) g.setVisible(false);
    		 else if(source == replay)
    		 {
    			 Collections.shuffle(img);
    			 p.clear();
    			 p2.removeAll();
    			 p2.setLayout(new GridLayout(4,4));
    			 tentatives=3;
    				l3.setText("Nombre de tentatives restantes:"+tentatives);
    				j=0;
    				l2.setText("Votre score:"+j);
    				b.clear();
    				int k = 0;
    				while(k<16){
    					JButton button = new JButton("Button"+k);
    					b.add(button);
    					button.setIcon(new ImageIcon(getClass().getResource("img0.png")));
    					button.addActionListener(new ActionListener(){
    		                public void actionPerformed(ActionEvent ae){
    		                	Object source = ae.getSource();
    		                	 
    		            		
    		                    selectedCard = button;
    		                    for(int i =0; i<16;i++) {
    		        	        	if(source == b.get(i)) {
    		        	        		
    		                   tournerCarte(i);
    		                }
    		                    }
    		            		 
    		                }
    		            }
    							);
    					
    					p2.add(button);
    					k++;
    		 }
    		 }
			
		}
		public static void main(String[] args) {
			MemoryGame g = new MemoryGame();
		}


	
}
