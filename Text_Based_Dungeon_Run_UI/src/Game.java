import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class Game extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;

	CardLayout layout = new CardLayout();
	
	JFrame frame = new JFrame();
	JPanel deck = new JPanel();
	
	JPanel startpanel = new JPanel();
	JPanel mainpanel = new JPanel();
	JPanel giftpanel = new JPanel();
	JPanel fightpanel = new JPanel();
	JPanel continuepanel = new JPanel();
	JPanel endpanel = new JPanel();
	
	JTextArea gametextarea;
	JLabel label, label1, label2;
	JButton startbutton, giftButton;
	JButton warriorButton, archerButton, mageButton; 
	JButton attackButton, healButton, runButton;
	JButton continueButton, exitButton;
	
	String text, character;
	int i;
	
	String[] monsters = {"Skeleton", "Zombie", "Assasin", "Ancient Dragon"};
	int enemyhp = 75; //Max enemy hp
	int enemieshp; //Enemy hp if it isn't an Ancient Dragon
	String enemy; //Chosen enemy
	int hp = 100; //Starting hp
	private int dmg = 50; //Max users damage
	int score = 0;
	private int potions = 0; //Starting potions
	boolean dead = false; //If you died
	
	Random rand = new Random();
	
	int soundCue;
	SoundEffect se = new SoundEffect();
	
	public Game() {
		deck.setLayout(layout);
		
		startpanel.setLayout(null);
		startpanel.setBackground(Color.black);
		
		mainpanel.setLayout(null);
		mainpanel.setBackground(Color.black);
		
		giftpanel.setLayout(null);
		giftpanel.setBackground(Color.black);
		
		fightpanel.setLayout(null);
		fightpanel.setBackground(Color.black);	
		
		continuepanel.setLayout(null);
		continuepanel.setBackground(Color.black);
		
		endpanel.setLayout(null);
		endpanel.setBackground(Color.black);
		
		deck.add(startpanel, "first"); //Add panels to deck
		deck.add(mainpanel, "second");
		deck.add(giftpanel, "third");
		deck.add(fightpanel, "fourth");
		deck.add(continuepanel, "fifth");
		deck.add(endpanel, "sixth");
		
		label2 = new JLabel();
		label2.setText("Welcome");
		label2.setBounds(130, 20, 340, 80);
		label2.setFont(new Font("Times New Roman", Font.PLAIN, 80));
		label2.setForeground(Color.white);
		label2.setBackground(Color.black);
		startpanel.add(label2);
		
		label1 = new JLabel("To The");
		label1.setBounds(200, 130, 200, 60);
		label1.setFont(new Font("Times New Roman", Font.PLAIN, 60));
		label1.setForeground(Color.white);
		label1.setBackground(Color.black);
		startpanel.add(label1);
		
		label = new JLabel("Dungeon");
		label.setBounds(135, 200, 340, 100);
		label.setFont(new Font("Goudy Old Style", Font.BOLD, 90));
		label.setForeground(Color.red);
		label.setBackground(Color.black);
		startpanel.add(label);
		
		startbutton = new JButton("START");
		startbutton.setBounds(190, 370, 200, 50);
		startbutton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		startbutton.setForeground(Color.white);
		startbutton.setBackground(Color.black);
		startpanel.add(startbutton);
		
		startbutton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	startbutton.setBackground(Color.GREEN);
		    	startbutton.setForeground(Color.BLUE);
		    }

		    public void mouseExited(MouseEvent evt) {
		    	startbutton.setBackground(Color.black);
		    	startbutton.setForeground(Color.white);
		    }
		});
		
		startbutton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				selectCharacter();
				layout.show(deck, "second");
			}
		});
		
		gametextarea = new JTextArea();
		gametextarea.setBounds(20, 20, 570, 200);
		gametextarea.setEditable(false);
		gametextarea.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		gametextarea.setForeground(Color.white);
		gametextarea.setBackground(Color.black);
		gametextarea.setWrapStyleWord(true);
		gametextarea.setLineWrap(true);
		mainpanel.add(gametextarea);

		//choose buttons
		warriorButton = new JButton("WARRIOR");
		warriorButton.setBounds(190, 300, 200, 50);
		warriorButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		warriorButton.setForeground(Color.white);
		warriorButton.setBackground(Color.black);
		mainpanel.add(warriorButton);
		
		archerButton = new JButton("ARCHER");
		archerButton.setBounds(190, 370, 200, 50);
		archerButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		archerButton.setForeground(Color.white);
		archerButton.setBackground(Color.black);
		mainpanel.add(archerButton);
		
		mageButton = new JButton("MAGE");
		mageButton.setBounds(190, 440, 200, 50);
		mageButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mageButton.setForeground(Color.white);
		mageButton.setBackground(Color.black);
		mainpanel.add(mageButton);
		
		warriorButton.addMouseListener(this);
		
		warriorButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				character = "Warrior";
				showChar(character);
				layout.show(deck, "third");
			}
		});
		
		archerButton.addMouseListener(this);
		
		archerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				character = "Archer";
				showChar(character);
				layout.show(deck, "third");
			}
		});
		
		mageButton.addMouseListener(this);
		
		mageButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				character = "Mage";
				showChar(character);
				layout.show(deck, "third");
			}
		});
		
		//gift button
		giftButton = new JButton("THANKS");
		giftButton.setBounds(190, 370, 200, 50);
		giftButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		giftButton.setForeground(Color.white);
		giftButton.setBackground(Color.black);
		giftpanel.add(giftButton);
		
		giftButton.addMouseListener(this);
		
		giftButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				fight(character);
				layout.show(deck, "fourth");
			}
		});
		
		//choose buttons
		attackButton = new JButton("ATTACK");
		attackButton.setBounds(190, 300, 200, 50);
		attackButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		attackButton.setForeground(Color.white);
		attackButton.setBackground(Color.black);
		fightpanel.add(attackButton);
		
		healButton = new JButton("HEAL");
		healButton.setBounds(190, 370, 200, 50);
		healButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		healButton.setForeground(Color.white);
		healButton.setBackground(Color.black);
		fightpanel.add(healButton);
		
		runButton = new JButton("RUN");
		runButton.setBounds(190, 440, 200, 50);
		runButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		runButton.setForeground(Color.white);
		runButton.setBackground(Color.black);
		fightpanel.add(runButton);
		
		attackButton.addMouseListener(this);
		healButton.addMouseListener(this);
		runButton.addMouseListener(this);
		
		attackButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				boolean f1 = false;
				f1 = fightEnemy(character);
				if(!dead) {
					if(f1){
						layout.show(deck, "fifth");
					}
					else {
						layout.show(deck, "fourth");
					}
				}
				else {
					layout.show(deck, "sixth");
				}
			}
		});
		
		healButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				heal(character);
				layout.show(deck, "fourth");
			}
		});
		
		runButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				boolean f1 = false;
				f1 = run(character);
				if(!dead) {
					if(f1){
						layout.show(deck, "fifth");
					}
					else {
						layout.show(deck, "fourth");
					}
				}
				else {
					layout.show(deck, "sixth");
				}
			}
		});
		
		continueButton = new JButton("CONTINUE FIGHTING");
		continueButton.setBounds(170, 335, 240, 50);
		continueButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		continueButton.setForeground(Color.white);
		continueButton.setBackground(Color.black);
		continuepanel.add(continueButton);
		
		exitButton = new JButton("EXIT THE DUNGEON");
		exitButton.setBounds(170, 405, 240, 50);
		exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		exitButton.setForeground(Color.white);
		exitButton.setBackground(Color.black);
		continuepanel.add(exitButton);
		
		continueButton.addMouseListener(this);
		exitButton.addMouseListener(this);
		
		continueButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				fight(character);
				layout.show(deck, "fourth");
			}
		});
		
		exitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				end();
				layout.show(deck, "sixth");
			}
		});
			
		layout.show(deck, "first");
		
		frame.addMouseListener(this);
		frame.add(deck);
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Dungeon");
		frame.setVisible(true);
	}

	Timer timer = new Timer(40, new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			
			char[] character = text.toCharArray();
			int arrayNumber = character.length;
			
			String s = String.valueOf(character[i]);
			
			gametextarea.append(s);
			
			i++;
			soundCue++;
			
			if(soundCue == 4) {
				URL typeSoundURL = getClass().getResource("/type.wav");
				se.setURL(typeSoundURL);
				se.play();
				soundCue = 0;
			}
			
			if(i == arrayNumber){
				i = 0;
				timer.stop();
			}
		}       
	});
	
	public void prepareText() {
		i = 0;
		gametextarea.setText("");
		timer.start();
	}
	
	public void selectCharacter() {
		text = "Choose your Character: \r\n\n"
				+ "   1. Warrior (More defence => A chance to take less damage).\r\n\n"
				+ "   2. Archer  (More attack => A chance to deal more damage).\r\n\n"
				+ "   3. Mage    (More luck => Bigger chance to find healing potions).";
		prepareText();
	}
	
	public void showChar(String character) {
		giftpanel.add(gametextarea);
		gametextarea.setBounds(100, 70, 570, 100);
		if(character.equals("Archer")) {
			text = "You are an " + character + ".\n\n";
		}else {
			text = "You are a " + character + ". \n\n";
		}
		text +=  "Here is a little gift: 3 healing potions.";
		potions += 3;
		prepareText();
	}
	
	public void fight(String character) {
		createEnemy();
		fightpanel.add(gametextarea);
		gametextarea.setBounds(20, 20, 550, 200);
		text += "	What would you like to do?";
		prepareText();
	}
	
	public void createEnemy() {
		int randEnemy = rand.nextInt(8);
		enemyhp = 75; //Make max enemy hp 75 every time
		 
		if(randEnemy == 2) {
			enemy = "Ancient Dragon";
		}
		else {
			enemy = monsters[rand.nextInt(monsters.length)];
		}
		 
		while(randEnemy != 2 && enemy == "Ancient Dragon") { //If enemy is ancient dragon but randEnemy isn't, then change the enemy to something else
			enemy = monsters[rand.nextInt(monsters.length)];
		}
			
		if(enemy == "Zombie" || enemy == "Skeleton") {
			text = "  A " + enemy + " has appeared! \n\n";
		}
		else {
			text = "  An " + enemy + " has appeared! \n\n";
		}
		 
		enemieshp = rand.nextInt(enemyhp - 25); //Give the enemy random hp
		while(true) {
			if(enemieshp < 20) { //Make the random enemy hp between 20-50
				enemieshp = rand.nextInt(enemyhp - 25);
			}
			else{
				break;
			}
		}
		 
		if(enemy == "Ancient Dragon") { //If enemy is Ancient Dragon, then it will have 75hp
			text += "	Your HP = " + hp + "\n\n"
					+ "	" + enemy + "'s HP = " + enemyhp + "\n\n";
		}
		else { //Else enemiesshp is random
			text += "	Your HP = " + hp + "\n\n"
					+ "	" + enemy + "'s HP = " + enemieshp + "\n\n";
		}
	}
	 
	public boolean fightEnemy(String character){	
		boolean flag;
		flag = attackEnemy(character);
		if(!dead) {
			if(!flag) {
				fightpanel.add(gametextarea);
				gametextarea.setBounds(20, 10, 550, 280);
				text += "	What would you like to do?";
				prepareText();
			}
			else {
				win();
			}
		}
		else {
			died();
		}
		return flag;
	}
	public boolean attackEnemy(String character) {
		boolean flag = false;
		int randmg = rand.nextInt(dmg); 
		
		if(character.equals("Archer")) {
			randmg = rand.nextInt(dmg + 5);
		}
		
		text = "	> You strike the " + enemy + " for " + randmg + " damage \n\n";
		
		if(enemy.equals("Ancient Dragon")) {
			enemyhp -= randmg;
			randmg = rand.nextInt(dmg - 10);
		
			if(character.equals("Warrior")) { //if character is Warrior give the random enemy attack damage a chance to become lower
				randmg = rand.nextInt(dmg - 20);
			}
			
			if(enemyhp > 0) { //if enemy didn't die by the attack then user will take some retaliation damage
				text += "	> You receive " + randmg + " damage in retaliation \n\n";
				hp -= randmg;
			}
		}
		else {
			enemieshp -= randmg;
			randmg = rand.nextInt(dmg - 20);
			if(character.equals("Warrior")) { //if user character is a warrior give the random enemy attack damage a chance to become lower
				randmg = rand.nextInt(dmg - 25);
			}
			if(enemieshp > 0) { //if enemy didn't die by the attack then user will take some retaliation damage
				text += "	> You receive " + randmg + " damage in retaliation \n\n";
				hp -= randmg;
			}
		}
		
		if(enemy == "Ancient Dragon") { //If enemy is Ancient Dragon, then it will have 75hp
			text += "	Your HP = " + hp + "\n\n"
					+ "	" + enemy + "'s HP = " + enemyhp + "\n\n";
		}
		else { //Else enemyshp is random
			text += "	Your HP = " + hp + "\n\n"
					+ "	" + enemy + "'s HP = " + enemieshp + "\n\n";
		}
				
		if(enemyhp <= 0 || enemieshp <= 0) { //If enemy died
			flag = true;
		}
		
		if(hp <= 0) {
			dead = true;
		}
		return flag;
	}
	public void win() {
		won();
		continuepanel.add(gametextarea);
		gametextarea.setBounds(20, 20, 550, 300);
		text += "	What would you like to do?";
		prepareText();
	}
	public void won() {
		text = "	" + enemy + " was defeated! \r\n\n"
				+ "	Your HP = " + hp + "\n\n";
		//Score points for the killed enemy
		if(enemy =="Skeleton") {
			score += 10;
		}
		else if(enemy == "Zombie") {
			score += 15;
		}
		else if(enemy == "Assasin") {
			score += 20;
		}
		else{
			score += 50;
		}
		
		int chance = rand.nextInt(8); //Get a 1/8 chance to find a potion
		if(enemy == "Ancient Dragon") { //If the user killed an Ancient dragon then they get 1 potion
			text += "After defeating the dragon you found a healing potion! \n\n";
			potions++;
			text += "	Your potion count is: " + potions + "\n\n";
		}
		else if(chance == 2) { //Else they have a 1/8 chance to find one
			text += "	You got lucky and found a healing potion!\n\n";
			potions += 1;
			text += "	Your potion count is: " + potions + "\n\n";
		}
		else if(character.equals("Mage")) { //If user character is a Mage the they have a 2/8 chance to find one
			if(chance == 3) {
				text += "	You got lucky and found a healing potion!\n\n";
				potions += 1;
				text += "	Your potion count is: " + potions + "\n\n";
			}
		}
	}
	
	public void heal(String character) {
		healing(character);
		fightpanel.add(gametextarea);
		gametextarea.setBounds(20, 20, 550, 270);
		text += "	What would you like to do?";
		prepareText();
	}
	public void healing(String character) {
		int randmg = rand.nextInt(dmg); 
		
		if(potions > 0) { //If they have more than 0 potions then they can drink one 
			text = "You drank a healing potion healing you for 45 health points\n\n";
			hp += 45;
			potions -= 1;
			text += "	Your HP is now: " + hp + "\r\n"
					+ "	Your potion count is: " + potions + "\n\n";
			
			if(enemy == "Ancient Dragon") {
				randmg = rand.nextInt(dmg - 10);
				if(character.equals("Warrior")) { //if user character is a warrior give the random enemy attack damage a chance to become lower
					randmg = rand.nextInt(dmg - 20);
				}
				text += "	> You receive " + randmg + " damage from an enemy attack \n\n";
				hp -= randmg;
			}
			else {
				randmg = rand.nextInt(dmg - 20);
				if(character.equals("Warrior")) {//if user character is a warrior give the random enemy attack damage a chance to become lower
					randmg = rand.nextInt(dmg - 25);
				}
				text += "	> You receive " + randmg + " damage from an enemy attack \n\n";
				hp -= randmg;
			}
			
			if(enemy == "Ancient Dragon") { //If enemy is Ancient Dragon, then it will have 75hp
				text += "	Your HP = " + hp + "\n"
						+ "	" + enemy + "'s HP = " + enemyhp + "\n";
			}
			else { //Else enemyshp is random
				text += "	Your HP = " + hp + "\n"
						+ "	" + enemy + "'s HP = " + enemieshp + "\n";
			}
		}
		else {
			text = "	You are out of healing potions\n\n"
					+ "	Defeat enemies for a chance to find one!\n\n"
					+"	Your HP = " + hp + "\n\n"
					+ "	" + enemy + "'s HP = " + enemieshp + "\n\n";
		}
	}
	
	public boolean run(String character) {
		boolean flag;
		flag = runFromEnemy(character);
		
		if(!dead) {
			if(!flag) {
				fightpanel.add(gametextarea);
				gametextarea.setBounds(20, 10, 550, 280);
				text += "	What would you like to do?";
				prepareText();
			}
			else {
				left();
			}
		}
		else {
			died();
		}
		return flag;
	}
	public boolean runFromEnemy(String character) {
		boolean flag = false;
		int i = rand.nextInt(3); //User will have a 1/3 chance to escape
		int randmg = rand.nextInt(dmg);
		
		if(i == 1) {
			flag = true;
		}
		else {
			text = "	The " + enemy + " didn't let you run away \n\n"
					+ "	This resulted in receiving some damage\n\n";
			if(enemy == "Ancient Dragon") {
				randmg = rand.nextInt(dmg - 10);
				if(character.equals("Warrior")) { //if user character is a warrior give the random enemy attack damage a chance to become lower
					randmg = rand.nextInt(dmg - 20);
				}
				text += "	> You receive " + randmg + " from an enemy attack \n\n";
				hp -= randmg;
			}
			else {
				randmg = rand.nextInt(dmg - 20);
				if(character.equals("Warrior")) { //if user character is a warrior give the random enemy attack damage a chance to become lower
					randmg = rand.nextInt(dmg - 25);
				}
				text += "	> You receive " + randmg + " from an enemy attack \n\n";
				hp -= randmg;
			}
			
			if(enemy == "Ancient Dragon") { //If enemy is Ancient Dragon, then it will have 75hp
				text += "	Your HP = " + hp + "\n"
						+ "	" + enemy + "'s HP = " + enemyhp + "\n\n";
			}
			else { //Else enemyshp is random
				text += "	Your HP = " + hp + "\n"
						+ "	" + enemy + "'s HP = " + enemieshp + "\n\n";
			}
		}
		if(hp <= 0) {
			dead = true;
		}
		
		return flag;
	}
	public void left() {
		leaving(character);
		continuepanel.add(gametextarea);
		gametextarea.setBounds(20, 20, 550, 270);
		text += "	What would you like to do?";
		prepareText();
	}
	public void leaving(String character){
		text = "You got lucky and managed to run away from the " + enemy + "!\n\n\n";
	}
	
	public void end() {
		label.setBounds(140, 50, 330, 100);
		endpanel.add(label);
		ending();
		endpanel.add(gametextarea);
		gametextarea.setBounds(50, 200, 500, 300);
		text += "	THANKS FOR PLAYING!";
		prepareText();
	}
	public void ending() {
		text = "You exit the dungeon, successful from your adventures!\n\n\n"
				+ "	Your score is: " + score + "!\n\n\n";
	}
	
	public void died() {
		label.setBounds(140, 50, 330, 100);
		endpanel.add(label);
		lost();
		endpanel.add(gametextarea);
		gametextarea.setBounds(50, 200, 500, 300);
		text += "	THANKS FOR PLAYING!";
		prepareText();
	}
	public void lost() {
		text = "	You died inside the dungeon...\n\n\n"
				+ "	Your score is: " + score + "!\n\n\n";
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		timer.stop();
		gametextarea.setText(text);
		i = 0;
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		e.getComponent().setBackground(Color.lightGray);
		e.getComponent().setForeground(Color.black);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		e.getComponent().setBackground(Color.black);
		e.getComponent().setForeground(Color.white);
	}
}