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

public class Game extends JFrame implements MouseListener, ActionListener{
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
	JLabel label, label1, label2, scorelabel;
	JButton startbutton, giftButton;
	JButton warriorButton, archerButton, mageButton; 
	JButton attackButton, healButton, runButton;
	JButton continueButton, exitButton;
	
	String text; //Text in every panel
	int i;
	
	String[] monsters = {"Skeleton", "Zombie", "Assasin", "Ancient Dragon"}; //Array with all the enemies
	int enemyhp = 75; //Max enemy hp
	int enemieshp; //Enemy hp if it isn't an Ancient Dragon
	String enemy; //Chosen enemy
	int hp = 100; //Starting hp
	String character; //Chosen character name
	private int dmg = 50; //Max users damage
	int score = 0; //User score
	private int potions = 0; //Starting potions
	boolean dead = false; //If you died
	
	Random rand = new Random();
	
	int soundCue;
	SoundEffect se = new SoundEffect();
	
	public Game() {
		deck.setLayout(layout);
		
		//Set all panels
		makePanel(startpanel);
		makePanel(mainpanel);
		makePanel(giftpanel);
		makePanel(fightpanel);
		makePanel(continuepanel);
		makePanel(endpanel);
		
		//Add panels to deck
		deck.add(startpanel, "first");
		deck.add(mainpanel, "second");
		deck.add(giftpanel, "third");
		deck.add(fightpanel, "fourth");
		deck.add(continuepanel, "fifth");
		deck.add(endpanel, "sixth");
		
		//Add 3 labels at the starting screen
		label2 = new JLabel("Welcome");
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
		
		//Add button to start
		startbutton = new JButton("START");
		startbutton.setBounds(190, 370, 200, 50);
		createButton(startbutton);
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
		
		//Add main textArea
		gametextarea = new JTextArea();
		gametextarea.setBounds(20, 20, 570, 200);
		gametextarea.setEditable(false);
		gametextarea.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		gametextarea.setForeground(Color.white);
		gametextarea.setBackground(Color.black);
		gametextarea.setWrapStyleWord(true);
		gametextarea.setLineWrap(true);
		mainpanel.add(gametextarea);

		//Add 3 buttons to choose your character
		warriorButton = new JButton("WARRIOR");
		warriorButton.setBounds(190, 300, 200, 50);
		createButton(warriorButton);
		mainpanel.add(warriorButton);
		
		archerButton = new JButton("ARCHER");
		archerButton.setBounds(190, 370, 200, 50);
		createButton(archerButton);
		mainpanel.add(archerButton);
		
		mageButton = new JButton("MAGE");
		mageButton.setBounds(190, 440, 200, 50);
		createButton(mageButton);
		mainpanel.add(mageButton);
		
		//Add mouse and action listeners
		warriorButton.addMouseListener(this);
		archerButton.addMouseListener(this);
		mageButton.addMouseListener(this);
		
		warriorButton.addActionListener(this);
		archerButton.addActionListener(this);
		mageButton.addActionListener(this);
		
		//Thanks button
		giftButton = new JButton("THANKS");
		giftButton.setBounds(190, 370, 200, 50);
		createButton(giftButton);
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
		
		//3 buttons to choose what will you do when you find an enemy
		attackButton = new JButton("ATTACK");
		attackButton.setBounds(190, 300, 200, 50);
		createButton(attackButton);
		fightpanel.add(attackButton);
		
		healButton = new JButton("HEAL");
		healButton.setBounds(190, 370, 200, 50);
		createButton(healButton);
		fightpanel.add(healButton);
		
		runButton = new JButton("RUN");
		runButton.setBounds(190, 440, 200, 50);
		createButton(runButton);
		fightpanel.add(runButton);
		
		attackButton.addMouseListener(this);
		healButton.addMouseListener(this);
		runButton.addMouseListener(this);
		
		attackButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				boolean f1 = false; //False if enemy is alive
				f1 = fightEnemy(character); //Find out if enemy is dead or not
				if(!dead) { //If you are alive
					if(f1){ //If enemy is dead
						layout.show(deck, "fifth");
					}
					else { //If enemy is alive
						layout.show(deck, "fourth");
					}
				}
				else { //If you are dead
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
				boolean r1 = false; //False if enemy did not leave you escape
				r1 = run(character); //Find out if you left or not
				if(!dead) { //If you are alive
					if(r1){ //If you escaped
						layout.show(deck, "fifth");
					}
					else { //If enemy did not leave you escape
						layout.show(deck, "fourth");
					}
				}
				else { //If you are dead
					layout.show(deck, "sixth");
				}
			}
		});
		
		//2 buttons to choose what will you do when you kill or escape from an enemy
		continueButton = new JButton("CONTINUE FIGHTING");
		continueButton.setBounds(170, 335, 240, 50);
		createButton(continueButton);
		continuepanel.add(continueButton);
		
		exitButton = new JButton("EXIT THE DUNGEON");
		exitButton.setBounds(170, 405, 240, 50);
		createButton(exitButton);
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
			
		scorelabel = new JLabel("Score: " + score);
		scorelabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		scorelabel.setForeground(Color.white);
		scorelabel.setBackground(Color.black);
		scorelabel.setBounds(10, 540, 100, 30);
		fightpanel.add(scorelabel);
		
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
	
	public void createButton(JButton button) {
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		button.setForeground(Color.white);
		button.setBackground(Color.black);
	}
	
	public void makePanel(JPanel panel) {
		panel.setLayout(null);
		panel.setBackground(Color.black);
	}

	Timer timer = new Timer(40, new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			
			char[] character = text.toCharArray(); //Add every text character in an array
			int arrayNumber = character.length; //Get the length of the text
			
			String s = String.valueOf(character[i]);
			
			gametextarea.append(s); //add the new character in the textarea
			
			i++;
			soundCue++;
			
			if(soundCue == 4) {
				URL typeSoundURL = getClass().getResource("/type.wav");
				se.setURL(typeSoundURL);
				se.play();
				soundCue = 0;
			}
			
			if(i == arrayNumber){ //if it's finished stop the timer and reset i
				i = 0;
				timer.stop();
			}
		}       
	});
	
	public void prepareText() {
		i = 0; //reset i
		gametextarea.setText(""); //empty the text area
		timer.start(); //start writing the letters
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
		scorelabel.setText("Score: " + score);
		fightpanel.add(scorelabel);
		gametextarea.setBounds(20, 20, 550, 200);
		text += "	What would you like to do?";
		prepareText();
	}
	public void createEnemy() {
		int randEnemy = rand.nextInt(8);
		enemyhp = 75; //Make max enemy hp 75
		 
		if(randEnemy == 2) { //1/8 chance to find a dragon
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
		flag = attackEnemy(character); //Find out if enemy is defeated
		if(!dead) { //If you are alive
			if(!flag) { //If you did not defeat the enemy
				fightpanel.add(gametextarea);
				scorelabel.setText("Score: " + score);
				fightpanel.add(scorelabel);
				gametextarea.setBounds(20, 10, 550, 280);
				text += "	What would you like to do?";
				prepareText();
			}
			else { //If you killed the enemy
				win();
			}
		}
		else { //If you died
			died();
		}
		return flag;
	}
	public boolean attackEnemy(String character) {
		boolean flag = false; //If enemy is alive then it is set to false
		int randmg = rand.nextInt(dmg); 
		
		if(character.equals("Archer")) { //Chance for more damage if character is an archer
			randmg = rand.nextInt(dmg + 5);
		}
		
		text = "	> You strike the " + enemy + " for " + randmg + " damage \n\n";
		
		if(enemy.equals("Ancient Dragon")) {
			enemyhp -= randmg;
			randmg = rand.nextInt(dmg - 10);
		
			if(character.equals("Warrior")) { //If character is Warrior give the random enemy attack damage a chance to become lower
				randmg = rand.nextInt(dmg - 20);
			}
			
			if(enemyhp > 0) { //If enemy didn't die by the attack then user will take some retaliation damage
				text += "	> You receive " + randmg + " damage in retaliation \n\n";
				hp -= randmg;
			}
		}
		else {
			enemieshp -= randmg;
			randmg = rand.nextInt(dmg - 20);
			if(character.equals("Warrior")) { //If user character is a warrior give the random enemy attack damage a chance to become lower
				randmg = rand.nextInt(dmg - 25);
			}
			if(enemieshp > 0) { //If enemy didn't die by the attack then user will take some retaliation damage
				text += "	> You receive " + randmg + " damage in retaliation \n\n";
				hp -= randmg;
			}
		}
		
		if(enemy == "Ancient Dragon") {
			text += "	Your HP = " + hp + "\n\n"
					+ "	" + enemy + "'s HP = " + enemyhp + "\n\n";
		}
		else {
			text += "	Your HP = " + hp + "\n\n"
					+ "	" + enemy + "'s HP = " + enemieshp + "\n\n";
		}
				
		if(enemyhp <= 0 || enemieshp <= 0) { //If enemy died
			flag = true;
		}
		
		if(hp <= 0) { //If you died
			dead = true;
		}
		return flag;
	}
	
	public void win() {
		won();
		continuepanel.add(gametextarea);
		scorelabel.setText("Score: " + score);
		continuepanel.add(scorelabel);
		gametextarea.setBounds(20, 20, 550, 300);
		text += "	What would you like to do?";
		prepareText();
	}
	public void won() {
		text = "	" + enemy + " was defeated! \r\n\n"
				+ "	Your HP = " + hp + "\n\n";
		//Score points for the enemies you killed
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
		scorelabel.setText("Score: " + score);
		fightpanel.add(scorelabel);
		gametextarea.setBounds(20, 20, 550, 270);
		text += "	What would you like to do?";
		prepareText();
	}
	public void healing(String character) {
		String settext;
		if(potions > 0) { //If they have more than 0 potions then they can drink one 
			text = "You drank a healing potion healing you for 45 health points\n\n";
			hp += 45;
			potions -= 1;
			text += "	Your HP is now: " + hp + "\r\n"
					+ "	Your potion count is: " + potions + "\n\n";
			settext = text;
			
			text = addText(settext);
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
		flag = runFromEnemy(character);	//Find out if you managed to escape
		
		if(!dead) { //If you are alive
			if(!flag) { //If you did not defeat the enemy
				fightpanel.add(gametextarea);
				scorelabel.setText("Score: " + score);
				fightpanel.add(scorelabel);
				gametextarea.setBounds(20, 10, 550, 280);
				text += "	What would you like to do?";
				prepareText();
			}
			else { //If you left
				left();
			}
		}
		else { //If you died
			died();
		}
		return flag;
	}
	public boolean runFromEnemy(String character) {
		boolean flag = false;
		String settext;
		
		int i = rand.nextInt(3); //User will have a 1/3 chance to escape
		
		if(i == 1) {
			flag = true; //You escaped
		}
		else {
			text = "	The " + enemy + " didn't let you run away \n\n"
					+ "	This resulted in receiving some damage\n\n";
			
			settext = text;
			text = addText(settext);
		}
		if(hp <= 0) { //If you died
			dead = true;
		}
		
		return flag;
	}
	
	public void left() {
		continuepanel.add(gametextarea);
		scorelabel.setText("Score: " + score);
		continuepanel.add(scorelabel);
		gametextarea.setBounds(20, 20, 550, 270);
		text = "You got lucky and managed to run away from the " + enemy + "!\r\n\n\n" 
				+ "	What would you like to do?";
		prepareText();
	}
	
	public void end() {
		label.setBounds(140, 50, 330, 100);
		endpanel.add(label);
		endpanel.add(gametextarea);
		gametextarea.setBounds(50, 200, 500, 300);
		text = "You exit the dungeon, successful from your adventures!\r\n\n\n"
				+ "	Your score is: " + score + "!\r\n\n\n"
				+ "	THANKS FOR PLAYING!";
		prepareText();
	}
	
	public void died() {
		label.setBounds(140, 50, 330, 100);
		endpanel.add(label);
		endpanel.add(gametextarea);
		gametextarea.setBounds(50, 200, 500, 300);
		text = "	You died inside the dungeon...\r\n\n\n"
				+ "	Your score is: " + score + "!\r\n\n\n"
				+ "	THANKS FOR PLAYING!";
		prepareText();
	}
	
	public String addText(String settext) {

		int randmg = rand.nextInt(dmg);
		
		if(enemy == "Ancient Dragon") {
			randmg = rand.nextInt(dmg - 10);
			if(character.equals("Warrior")) { //If user character is a warrior give the random enemy attack damage a chance to become lower
				randmg = rand.nextInt(dmg - 20);
			}
			settext += "	> You receive " + randmg + " damage from an enemy attack \n\n";
			hp -= randmg;
		}
		else {
			randmg = rand.nextInt(dmg - 20);
			if(character.equals("Warrior")) {//If user character is a warrior give the random enemy attack damage a chance to become lower
				randmg = rand.nextInt(dmg - 25);
			}
			settext += "	> You receive " + randmg + " damage from an enemy attack \n\n";
			hp -= randmg;
		}
		
		if(enemy == "Ancient Dragon") {
			settext += "	Your HP = " + hp + "\n"
					+ "	" + enemy + "'s HP = " + enemyhp + "\n";
		}
		else {
			settext += "	Your HP = " + hp + "\n"
					+ "	" + enemy + "'s HP = " + enemieshp + "\n";
		}
		
		return settext;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(warriorButton)) {
			character = "Warrior";
			showChar(character);
			layout.show(deck, "third");
		}
		else if(e.getSource().equals(archerButton)) {
			character = "Archer";
			showChar(character);
			layout.show(deck, "third");
		}
		else {
			character = "Mage";
			showChar(character);
			layout.show(deck, "third");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		timer.stop(); //If mouse is clicked outside the text area then stop the timer
		gametextarea.setText(text); //Show the message
		i = 0; //Reset i
	}

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

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
}
