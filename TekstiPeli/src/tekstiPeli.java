/**
 * 
 */

/**
 * @author ppulk
 *Tekstipeli
 */

// Tuodaan kirjastot käyttöön
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
public class tekstiPeli {
	
	//otetaan ikkuna käyttöön ja importataan kirjastosta samalla
	JFrame ikkuna;
	//alusta johon voi laittaa useita asioita, ikkuna on alimmainen kerros, Containerin sisältö toinen kerros ikkunan päälle
	Container con;
	//teksti "paneeli" ikkunaan
	JPanel pelinNimiPaneeli, aloitaPeliNappiPaneeli, paaTekstiPaneeli, valintaNappiPaneeli, pelaajaInfoPaneeli;
	//JLabel näyttää teksin ruudulla
	JLabel otsikkoNimi, hpLabel, hpLabelNumero, weaponLabel, weaponLabelNimi;
	// luodaan ja haetaan kirjastosta Fontti, asetetaan fontin tyyli, ja viimeisenä koko
	Font otsikkiFontti = new Font("Times New Roman", Font.BOLD, 50);
	Font aloitaPeliFontti = new Font("Times New Roman",Font.BOLD,20 );
	//Luodaan ja importataan kirjastosta nappi, tässä tapauksessa pelin aloitusta, ja valintoja varten, jotka lisäsin paljon myöhemmin
	JButton aloitaPeliNappi, valinta1, valinta2, valinta3, valinta4;
	JTextArea paaTekstiAlue;
	int pelaajaHP;
	String Weapon, position;
	int ghoulDMG;
	int ghoulHP;
	int playerDMG;
	//nopat
	int noppa;
	int noppa1;
	TitleScreenHandler arKasittelija = new TitleScreenHandler();
	ChoiceHandler cHandler = new ChoiceHandler();
	
	//Ei käytössä
	public void tiedostonLuku() {
		try {
			File munEsine = new File ("ascii1.txt");
			Scanner munLukija = new Scanner (munEsine);
			while (munLukija.hasNextLine()) {
				String data = munLukija.nextLine();
				paaTekstiAlue.setText(data);
			}
			munLukija.close();
		} catch (FileNotFoundException e) {
			System.out.println ("Tapahtui virhe");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		new tekstiPeli();
		
	}

	
	
	public tekstiPeli() {
		
		//aktivoidaa ikkuna
		ikkuna = new JFrame();
		//Annnetaan ikkunalle koko
		ikkuna.setSize(1100,800);
		//annetaan ikkunalle sulkutoiminto, x- merrki oikeassa ylänurkassa
		ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Asetetaan ikkunalle taustaväri, ja haetaan se samalla kirjastosta
		ikkuna.getContentPane().setBackground(Color.red);
		//Deaktivoidaan alkuperäinen ikkunan sisällön asettelu
		ikkuna.setLayout(null);
		//Asetetaan ikkuna näkymään käyttäjälle
		ikkuna.setVisible(true);
		con=ikkuna.getContentPane();
		
		//Aktivoidaan paneeli
		pelinNimiPaneeli = new JPanel();
		//rajaus johon paneeli menee (paneeli alkaa x- akselista kohdasta 100, sama mutta y- akselista, leveys, korkeus)
		pelinNimiPaneeli.setBounds(50,100,1000,150);
		//Pelin nimi paneeeli erivärillä jottä näen mille alueelle tulee pelin nimi, muutan myöhemmin myös punaiseksi
		pelinNimiPaneeli.setBackground(Color.red);
		//Aktivoidaan JLabel, sulkuihin kirjoitan pelin nimen/ otsikon
		otsikkoNimi = new JLabel("Vampire the Masquerade, Chicago by night");
		//Asetan tekstin nimen fontin värin
		otsikkoNimi.setForeground(Color.black);
		//asetetaan Pelin nimen fontiksi aikaisemmin tehty fonttin, joka haetaan luokasta laittamalla sen nimi sulkuihin
		otsikkoNimi.setFont(otsikkiFontti);
		
		
		
		
		aloitaPeliNappiPaneeli = new JPanel();
		aloitaPeliNappiPaneeli.setBounds(350,300,400,100);
		aloitaPeliNappiPaneeli.setBackground(Color.red);
		
		//Pelin aloitus napin teksti, tekstin taustan väri, tekstin fontin väri, luodun fontin haku käyttöön
		aloitaPeliNappi = new JButton("BEGIN YOUR NIGHT");
		aloitaPeliNappi.setBackground(Color.red);
		aloitaPeliNappi.setForeground(Color.black);
		aloitaPeliNappi.setFont(aloitaPeliFontti);
		//Poistaa ruman ulkonäön joka oli kuin nappi napin sisällä
		aloitaPeliNappi.setFocusPainted(false);
		// nappi tunnistaa klikkauksen, ja kutsuu arKasittelijan
		aloitaPeliNappi.addActionListener(arKasittelija);
		
		//Asettaa JLabelin tekstin, eli tässä tapauksessa pelin nimen paneeliin
		pelinNimiPaneeli.add(otsikkoNimi);
		//lisätään nappi paneelin sisälle
		aloitaPeliNappiPaneeli.add(aloitaPeliNappi);
		
		con.add(pelinNimiPaneeli);
		con.add(aloitaPeliNappiPaneeli);
		
		
	}
	// luodaan uusi ikkuna joka aukeaa kun painamme aiemmin luotua nappia
	public void luoPeliNäyttö() {
		//meidän täytyy tässä metodissa joka luo uuden ikkunan peliä varten deaktivoida aikaisemman ikkunan paneelit
		//muuten paneelit häiritsevät napin toimintoa ja uusi ikkuna ei aukea.
		
		pelinNimiPaneeli.setVisible(false);
		aloitaPeliNappiPaneeli.setVisible(false);
		
		paaTekstiPaneeli = new JPanel();
		paaTekstiPaneeli.setBounds(150, 150, 700, 300);
		paaTekstiPaneeli.setBackground(Color.red);
		con.add(paaTekstiPaneeli);
		
		paaTekstiAlue = new JTextArea("tämä on pääteksti alue");
		paaTekstiAlue.setBounds(150, 150, 700, 300);
		paaTekstiAlue.setBackground(Color.red);
		//Tekstin väri valkoinen
		paaTekstiAlue.setForeground(Color.black);
		paaTekstiAlue.setFont(aloitaPeliFontti);
		
		//LineWrap pitää tekstin kurissa siltä varalta että kirjoitan sen liian pitkäksi
		paaTekstiAlue.setLineWrap(true);
		
		//lisätään teksti alue ikkunassa olevaan paneeliin
		paaTekstiPaneeli.add(paaTekstiAlue);
		
		//luodaan paneeli, eli alue mihin valintanapit tulevat
		valintaNappiPaneeli = new JPanel();
		valintaNappiPaneeli.setBounds(250,550,500,200);
		valintaNappiPaneeli.setBackground(Color.red);
		//asetetaan napit allekkain, muuten olisivat "neliö muodostelmassa". Ensimmäinen luku on vertical, toinen horizontal. eli pystysyynnassa 4, vaakasuunnassa 1
		valintaNappiPaneeli.setLayout(new GridLayout(4,1));
		
		//lisätään paneeli containeriin
		con.add(valintaNappiPaneeli);
		
		//lisätään 4 valintanappia oikeaan paikkaan
		valinta1=new JButton("valinta1");
		valinta1.setBackground(Color.red);
		valinta1.setForeground(Color.black);
		valinta1.setFont(otsikkiFontti);
		valinta1.setFocusPainted(false);
		valinta1.addActionListener(cHandler);
		//set ActionCommandia käyttämällä toivon että ohjelma tunnistaa napit toisistaan
		valinta1.setActionCommand("c1");
		valintaNappiPaneeli.add(valinta1);
		valinta2=new JButton("valinta2");
		valinta2.setBackground(Color.red);
		valinta2.setForeground(Color.black);
		valinta2.setFont(otsikkiFontti);
		valinta2.setFocusPainted(false);
		valinta2.addActionListener(cHandler);
		valinta2.setActionCommand("c2");
		valintaNappiPaneeli.add(valinta2);
		valinta3=new JButton("valinta3");
		valinta3.setBackground(Color.red);
		valinta3.setForeground(Color.black);
		valinta3.setFont(otsikkiFontti);
		valinta3.setFocusPainted(false);
		valinta3.addActionListener(cHandler);
		valinta3.setActionCommand("c3");
		valintaNappiPaneeli.add(valinta3);
		valinta4=new JButton("valinta4");
		valinta4.setBackground(Color.red);
		valinta4.setForeground(Color.black);
		valinta4.setFont(otsikkiFontti);
		valinta4.setFocusPainted(false);
		valinta4.addActionListener(cHandler);
		valinta4.setActionCommand("c4");
		valintaNappiPaneeli.add(valinta4);
		
		pelaajaInfoPaneeli = new JPanel();
		pelaajaInfoPaneeli.setBounds(100, 20, 500, 50);
		pelaajaInfoPaneeli.setBackground(Color.red);
		pelaajaInfoPaneeli.setLayout(new GridLayout (1,4));
		con.add(pelaajaInfoPaneeli);
		
		hpLabel= new JLabel("HP: ");
		hpLabel.setFont(aloitaPeliFontti);
		hpLabel.setForeground(Color.black);
		pelaajaInfoPaneeli.add(hpLabel);
		hpLabelNumero = new JLabel();
		hpLabelNumero.setFont(aloitaPeliFontti);
		hpLabelNumero.setForeground(Color.black);
		pelaajaInfoPaneeli.add(hpLabelNumero);
		weaponLabel = new JLabel("Weapon: ");
		weaponLabel.setFont(aloitaPeliFontti);
		weaponLabel.setForeground(Color.black);
		pelaajaInfoPaneeli.add(weaponLabel);
		weaponLabelNimi =  new JLabel();
		weaponLabelNimi.setFont(aloitaPeliFontti);
		weaponLabelNimi.setForeground(Color.black);
		pelaajaInfoPaneeli.add(weaponLabelNimi);
		
		pelaajaSetup();
	}
	//Annetaan pelaajalle elämäpisteet ja ase pelin aloitukseen
	public void pelaajaSetup() {
		//vihollisen ja pelaajan hp ja vaurio
		ghoulDMG = 1;
		ghoulHP = 11;
		playerDMG = 1;
		pelaajaHP = 12;
		Weapon = "Fists";
		weaponLabelNimi.setText(Weapon);
		//minun täytyin laittaa tyhjää teksitä johon lisäsin integerin, en saanut muuten numeroa näkyville
		hpLabelNumero.setText("" +pelaajaHP);
		//Kutsutaan metodi joka asettaa tekstin teksti kenttään ja nimet vaihtoehdoille napeissa
		wakeup();
		
	}
	//Metodi pelin aloitukseen
	public void wakeup () {
		position = "beginning";
		pelaajaHP = 12;
		hpLabelNumero.setText("" +pelaajaHP);
		//Estetään teksti alueen editointi
		paaTekstiAlue.setEditable(false);
		paaTekstiAlue.setText("You wake up in a strange room. \n Your head is throbbing, there is a body on the floor next to you. \n The body is of a young female, pale, and lifeless.\n Small puncture wounds on her neck \n \n What do you do?");
		
		valinta1.setText("Examine");
		valinta2.setText("Try to remember");
		valinta3.setText("Cry for help");
		valinta4.setText("");
	}
	//Metodi johon tulee edellisen metodin seuraukset
	public void tutki() {
		// Examine
		position = "beginning";
		paaTekstiAlue.setText("The young female is around her 20`s \n she looks like she could have died to a loss of blood \n she has no ID on her \n is this blood on your face? What is this hunger?");
		
		valinta1.setText("");
		valinta2.setText("Try to remember");
		valinta3.setText("Cry for help");
		valinta4.setText("");
		
	}
	public void muista() {
		
		position="beginning";
		paaTekstiAlue.setText("You were drinking with you friends, you scored a cute girl with raven hair \n she´s real pale... a goth perhaps? Not the dead one on the floor \n You killed this one...why? You were hungry, that´s why.");
		
		valinta1.setText("Examine");
		valinta2.setText("");
		valinta3.setText("Cry for help");
		valinta4.setText("");
	}
	
	public void apua() {
		
		position = "forward";
		
		paaTekstiAlue.setText("You cry as hard as you can for help, you hear footsteps from the door \n you back up against the opposite wall, and the door swings open \n Tall man in trenchcoat, with long black hair stands in front of you.\n his voice is coarse.\n So you fed. Good. and don´t even think about running, You´re coming with me.");
		
		valinta1.setText("Run");
		valinta2.setText("Attack the man");
		valinta3.setText("Question");
		valinta4.setText("Comply");
	}
	
	public void juokse() {
		
		position = "forward";
		
		paaTekstiAlue.setText("You try to run past the man, despite is warning. \n You are not fast enough, and he seems to be much stronger.\n He stops you and flings you back like a wet rag. \n Didn´t I tell you not to run!");
		
		pelaajaHP = pelaajaHP-1;
		hpLabelNumero.setText("" +pelaajaHP);
		if(pelaajaHP<1) {
			paaTekstiAlue.setText("Game Over");
			wakeup ();
			
		}
		else {
		valinta1.setText("");
		valinta2.setText("Attack the man");
		valinta3.setText("Question");
		valinta4.setText("Comply");
		}
	}
	
	public void hyokkaa () {
		
		position = "forward";
		
		paaTekstiAlue.setText("You hit the man squarely on the chin, he looks down at you annoyed. \n You decide to back up. Damn your hand hurts!");
		pelaajaHP = pelaajaHP-1;
		if(pelaajaHP<1) {
			paaTekstiAlue.setText("Game Over");
			wakeup ();
			}
		else {
		hpLabelNumero.setText("" +pelaajaHP);
		valinta1.setText("Run");
		valinta2.setText("");
		valinta3.setText("Question");
		valinta4.setText("Comply");
		}
	}
	
	
	public void kysy() {
		
		position = "forward";
		
		paaTekstiAlue.setText("You ask the man what the hell is going on \n The man sighs and says: Look. This is complicated, and I´ll try to keep this short and simple \n Your sire turned you without a permission from the prince.\nYou are one of us now, a Kindred.\n My job is to get you to the Prince, so he can decide your fate.");
		
		valinta1.setText("Run");
		valinta2.setText("Attack the man");
		valinta3.setText("Question");
		valinta4.setText("Comply");
	}
	
	public void hyvaksy() {
		
		position = "garage";
		
		paaTekstiAlue.setText("You decide to go with the guy and comply. Attlest for now.\nGood thing you are this reasonable.\nWouldn´t be the first time I´d have to put someone down.\nYou can call me Sheriff.\nYou get out of the room and start walking behind the Sheriff.\nYou are in some kind of underground garage.");
		
		valinta1.setText("kindred?");
		valinta2.setText("What is the Prince?");
		valinta3.setText("Look around");
		valinta4.setText("Just follow");
	}
	
	public void kindred() {
		
		position="garage";
		
		paaTekstiAlue.setText("Kindred is what we are. Vampires, but we prefer Kindred.\nI´s a more civilised word.\nDon´t go calling anyone a vampire,\nit´s looked down upon by the civilised community.\nAnd don´t go telling anyone about your condition, or I´ll personally introduce you to the sunrise.\n And it´s not like the movies either,\nsomeone pulls out a cross, you shove it where the sun don´t shine.\nGarlic? Who cares. Running water? You can bathe.\nShotgun to the head? That´s trouble.\nYou play your cards right and you have a chance at eternal life.");
		
		valinta1.setText("");
		valinta2.setText("What is the Prince?");
		valinta3.setText("Look around");
		valinta4.setText("Just follow");
	}
	
	public void prince() {
		
		position="garage";
		
		paaTekstiAlue.setText("The prince is is the leader of this city.\nHere in Chicago rules prince Vannevar, the one I serve.\nThe one I´m taking you to see what he decides to do with you.\n");
		
		valinta1.setText("Kindred?");
		valinta2.setText("");
		valinta3.setText("Look around");
		valinta4.setText("Just follow");
	}
	
	
	public void Ymparille () {
		
		position="garage";
		
		paaTekstiAlue.setText("You look around the underground garage.\nIt´s old, damp, dim, flickering lights doing poor job.\nYou see a small knife on the ground and pick it up.");
		//Annetaan pelaajalle uusi ase
		Weapon = "knife";
		//ja sen myötä suurempi vaurio
		playerDMG = playerDMG + 2;
		//asetetaan ase näkymään ikkunassa
		weaponLabelNimi.setText(Weapon);
		valinta1.setText("kindred?");
		valinta2.setText("What is the Prince?");
		valinta3.setText("");
		valinta4.setText("Just follow");
	}
	
	
	public void seuraa() {
		
		
		position="car";
		
		paaTekstiAlue.setText("You follow the Sheriff to a Black SUV, with dark windows.\nSit in the back. He tells you.\n You obey and sit inside. He starts car and begins driving out into the night.\n Are you still feeling the hungry? He asks, while offering a bloodbag to you");
		
		valinta1.setText("Take the blood");
		valinta2.setText("Don´t take the blood");
		valinta3.setText("");
		valinta4.setText("");
	}
	
	public void otaVeri() {
		
		
		position="carHappy";
		
		paaTekstiAlue.setText("You shudder, take the bag, hunger takes over and you drink deep.\nIt´s not as good as the young female on the storage room floor you think.\nHunger seems to go away a bit. What have you become?\n Wait! Just now you notice you don´t have a pulse...oh God!You`re not breathing.\nThe Sheriff seems to be chuckling to your realization.");
		
		pelaajaHP = pelaajaHP+2;
		hpLabelNumero.setText("" +pelaajaHP);
		
		valinta1.setText("");
		valinta2.setText("");
		valinta3.setText("Sit quietly");
		valinta4.setText("");
		
		
		
		
	}
	
	
	public void elaOta() {
		
		position="carNoHappy";
		
		paaTekstiAlue.setText("Blood! No way! You feel sick, and yet hungrier at the same time.\nYou can´t. You are not a monster!");
		
		valinta1.setText("");
		valinta2.setText("");
		valinta3.setText("Sit quietly");
		valinta4.setText("");
		
	}
	
	public void istuOnnellinen() {
		
		position="destination";
		
		paaTekstiAlue.setText("Rest of the drive goes smoothly. You´re not hungry anymore.\nThe blood made you feel actually pretty good, like you could take on the world.");
		
		valinta1.setText("");
		valinta2.setText("Get out of the car");
		valinta3.setText("");
		valinta4.setText("");
		
	}
	
	public void istuSurullinen() {
		
		position = "destination";
		
		paaTekstiAlue.setText("You hear a whisper in your ear. Not quite audible, but sinister.\nIt wants you to strike at the Sheriff, to drink someone dry.\nThe Sheriff sees your twitching and strugling to keep hold of yourself.\n That´s the beast. He says.\n Keep yourself fed and hold on to your humanity or it will get loose on a rampage.\nMy job would be to grant you the final death should that happen.");
		
		valinta1.setText("");
		valinta2.setText("Get out of the car");
		valinta3.setText("");
		valinta4.setText("");
	}
	
	public void ulosAutosta() {
		
		position = "aonCenter";
		
		paaTekstiAlue.setText("You and the Sheriff get out of the car.\nYou are infront of Aon Center, it´s and old skyscraper, nearly 350m tall.");
		//tässä kohdassa lisä vaihtoehtoa mikäli pelaaja on saanut veitsen aikaisemmin
		if(Weapon=="knife") {
			valinta1.setText("Stab the Sheriff");
			valinta2.setText("Follow the Sheriff");
			valinta3.setText("This place?");
			valinta4.setText("Trouble?");
		}else {
		
		valinta2.setText("Follow the Sheriff");
		valinta3.setText("This place?");
		valinta4.setText("Trouble?");
		}
	}
	public void puukota() {
		
		position ="aonCenter";
		paaTekstiAlue.setText("You have had enough of this madness. It´s your time to strike.\nYou walk behind the Sheriff and quickly stab him.\nThe knife finds its way between his ribs, and he growls in anger.\nThe Sheriff takes the knife out, throws it far away and grabs you by your neck.\nHe smashes you to the ground and growls: Last warning.\nYou feel like something broke.");
		pelaajaHP = pelaajaHP-5;
		hpLabelNumero.setText("" +pelaajaHP);
		if(pelaajaHP<1) {
			
			wakeup ();
			}
		else {
				
		
		valinta1.setText("");
		valinta2.setText("Follow the Sheriff");
		valinta3.setText("This place?");
		valinta4.setText("Trouble?");
		}
		
	}
	
	public void Follow() {
		position="lobby";
		paaTekstiAlue.setText("You follow the Shreriff into the building.The lobby is dim.\nTwo men in suits guide you to an elevator. The Sheriff presses puts in a keycard, and presses the top floor button.\n");
		
		valinta1.setText(">");
		valinta2.setText("");
		valinta3.setText("");
		valinta4.setText("");
		
		
	}
	
	public void tamaPaikka(){
		position="aonCenter";
		paaTekstiAlue.setText("What is this place? You ask.\nThis is the ivory tower,the seat of power, the palace of our prince.\nWe are going all the way up.");
		
		valinta2.setText("Follow the Sheriff");
		valinta3.setText("");
		valinta4.setText("Trouble?");
	}
	
	public void ongelmia() {
		position="aonCenter";
		paaTekstiAlue.setText("Am I in trouble, what´s going to happen?.\n Short aswer: Yes. Either you will be given a chance to prove yourself\n or put to final death.\nBy our ancient laws that govern our kindred society you need a permission to sire someone.\nThose with no permission will be ended with their childe.\nYour sire did not have permission..\nNow it´s up to the Prince to decide");
		
		valinta2.setText("Follow the Sheriff");
		valinta3.setText("This place?");
		valinta4.setText("Trouble?");
		
	}
	public void aula() {
		position= "IvoryTower";
		paaTekstiAlue.setText("After a long elevator drive with classical music playing in the background, you are at the top.\nThe elevator doors open and reaveal beautifull room\nfull of well dressed people. The floors and wall are all marble.");
		valinta1.setText(">");
		valinta2.setText("");
		valinta3.setText("");
		valinta4.setText("");
		
	}
	public void torni() {
		
		position="IvoryTower";
		paaTekstiAlue.setText("The Sherif takes the lead and walks to a some kind of a throne.\nHe kneels and says: Here is the sireless childe.\nHe awaits your judgement.");
		valinta1.setText("");
		valinta2.setText(">");
		valinta3.setText("");
		valinta4.setText("");
	}
	
	public void polvistuu() {
		position="throneRoom";
		paaTekstiAlue.setText("Thank you Sherif. You may rise.\nUsually we would put this sireless childe to final death\nbut I´m not completely heartless. We shall put him to test.\nIf he succeeds, he will swear fealty\nand continue his undeath under close supervision.");
		
		valinta1.setText("Test?");
		valinta2.setText("Him?");
		valinta3.setText("");
		valinta4.setText("");
	}
	
	public void nousee() {
		position="throneRoom";
		paaTekstiAlue.setText("Glad you asked. Sherif. Get the traitor.\nThe Sherif leaves to one of the adjacent rooms, and returns\nshortly carrying a man with handcuffs on his hands and legs, mouth taped shut.\nYou will fight eachother. A newborn childe versus traitorous ghoul. If you win\nYou will join Camarilla.\nIf the traitor wins, he will get a 10 minute headstart to escape.");
			
		valinta1.setText("Test?");
		valinta2.setText("Him?");
		valinta3.setText("");
		valinta4.setText("");
		
		
	}
	
	public void challenge() {
		position="throneRoom";
		paaTekstiAlue.setText("His name is John, and he´s a ghoul.\nLesser than us, but they are quite usefull servants.\nMore powerfull than humans tough, and you are a newborn.\nThis should be most entertaining.");
		
		valinta1.setText("Test?");
		valinta2.setText("Him?");
		valinta3.setText("I´m ready");
		valinta4.setText("");
		
	}
	
	public void ready() {
		position="fight";
		paaTekstiAlue.setText("Everyone! Move into a nice circle around them!\nRelease the traitor and let them figth!");
		valinta1.setText(">");
		valinta2.setText("");
		valinta3.setText("");
		valinta4.setText("");
	}
	public void fightMechanic() {
		position="fight";
		paaTekstiAlue.setText("John the Ghoul.\n"+"Ghoul HP "+ ghoulHP+"\nGhoul roll: " + noppa1 +"\nYour damage roll: "+ noppa + "\nBase damage: "+ Weapon +" " +playerDMG);
		

		
		valinta1.setText("");
		valinta2.setText("Attack");
		valinta3.setText("");
		valinta4.setText("");
		playerDMG = 1;
		if(Weapon =="knife") {
			playerDMG = playerDMG +2;

		}
		if(ghoulHP <1) {
			
			voitto();}
		if(pelaajaHP<1) {
			
			playerDeath();}

System.out.println(noppa);
		
	}
	public void inflictDamage(){
		noppa = D4();
		/* kutsutaan noppa metodia, joka heittää noppaa, ja alhaalla vähentää elämäpisteistä nopan heiton ja vaurion
		 * laskin nopat niin että vastustajalla on pieni etu nopan maksimissa, mutta mikäli pelaaja on saanut veitsen
		 * molempien potentiaalinen maksimi on sama, mutta pelaajalla tulee etu minimi tuloksiin.
		 */
		ghoulHP = ghoulHP-noppa;
		ghoulHP = ghoulHP-playerDMG;
		
		
		noppa1 = D6();
		
		pelaajaHP=pelaajaHP-noppa1;
		pelaajaHP=pelaajaHP-ghoulDMG;
		hpLabelNumero.setText("" +pelaajaHP);


		
		
	}
	public void voitto() {
		position="WIN";
		paaTekstiAlue.setText("The goul collapses to the groud lifeless. You are now a killer.\nNot like you had any choice, but the feeling stings deep.\nThe audience around you claps, the Prince seems pleased.");
		
		valinta1.setText("What now?");
		valinta2.setText("This is wrong!");
		valinta3.setText("I´m a killer.");
		valinta4.setText("");
		
		
	}
	public void now() {
		position="WIN";
		paaTekstiAlue.setText("What now? You ask.\nNow childe, you are one of us. One of Camarilla.The Prince grins.\nThe traitors blood you spilled is your wow of fealty.");
		
		valinta1.setText("What now?");
		valinta2.setText("This is wrong!");
		valinta3.setText("I´m a killer.");
		valinta4.setText("");
		
	}
	
	public void wrong() {
		position = "WIN";
		paaTekstiAlue.setText("This is all so wrong and twisted!\nThis is what you are now the Prince says\nCreature of the night, preying on the kine to sustain yourself.");
		
		valinta1.setText("What now?");
		valinta2.setText("This is wrong!");
		valinta3.setText("I´m a killer.");
		valinta4.setText("");
	}
public void killer() {
	position="WIN";
	paaTekstiAlue.setText("Yes. I am. I will be. Anything to survive.\nFollow me. The Sherif says.\nI´ll take you to your new home");
	
	valinta1.setText("What now?");
	valinta2.setText("This is wrong!");
	valinta3.setText("I´m a killer.");
	valinta4.setText("Follow");
	
}

public void END() {
	position="end";
	paaTekstiAlue.setText("You arrive at a cheap apartment.\nThe Sherif gives you keys and leaves. It´s allmost sunrise.\nall the windows have dark heavy drapes so no light can penetrate.\nAs the sun rises you feel the blood in your body to congeal, your limbs stiffen\nYou conciousnes fades and you feel like falling endlesly in the darkness.");
	
	valinta1.setText("Play again");
	valinta2.setText("Quit");
	valinta3.setText("");
	valinta4.setText("");
	
	
}

	public void peliLoppu() {
		
		position="gameOver";
		paaTekstiAlue.setText("Game Over, You won.\nCreator Pekka Pulkkanen 10/2020.\nProject prototype for school Keuda Sipoo.\nSpecial thanks: The Player.");
		
		valinta1.setText("Play again");
		valinta2.setText("Quit");
		valinta3.setText("");
		valinta4.setText("");
	}
	
	public void playerDeath(){
		
		position="gameOver";
		paaTekstiAlue.setText("Game Over, you died.\nCreator Pekka Pulkkanen 10/2020.\nProject prototype for school Keuda Sipoo.\nSpecial thanks: The Player.");
		
		valinta1.setText("Play again");
		valinta2.setText("Quit");
		valinta3.setText("");
		valinta4.setText("");
	}
	
	//Neljä sivuinen noppa
	public int D4() {
		
		Random rand = new Random();
		int noppa = rand.nextInt(5);
		while (noppa==0) {
			noppa=rand.nextInt(5);
		}return noppa;
		
	}
	//kuusi sivuinen noppa
	public int D6() {
		
		Random rand = new Random();
		int noppa = rand.nextInt(7);
		while (noppa==0) {
			noppa=rand.nextInt(7);
		}return noppa;
		
	}
	
	public void Close() {
		
		System.exit(0);
	}
	

	
	
	public class TitleScreenHandler implements ActionListener{
		
		public void actionPerformed (ActionEvent toiminto) {
			// aloitus napin painalluksen seuraus tänne
			//kutsutaan ylempää metodi, joka luo uuden ikkunan
			luoPeliNäyttö();
		}
		
	}
	public class ChoiceHandler implements ActionListener{
		
		public void actionPerformed (ActionEvent toiminto) {
			//Merkkijonon sisällöksi asetetaan se toiminto minkä pelaaja valitsee
			// esimerkiksi valinta 1 tapauksessa sinunValinta on c1
			//tätä yritän hyväksikäyttää jo niin että ohjelmaa tunnistaa valinnan
			String sinunValinta = toiminto.getActionCommand();
			
			switch(position) {
			
			case "beginning":
				switch(sinunValinta) {
				case "c1": tutki(); break;
				case "c2": muista();break;
				case "c3": apua(); break;
				
				}
				break;
				
			case "forward":
				switch(sinunValinta) {
				case "c1":juokse(); break;
				case "c2":hyokkaa(); break;
				case "c3":kysy(); break;
				case "c4":hyvaksy();break;
				
				}
				break;
				
			case "garage":
				switch(sinunValinta) {
				case "c1": kindred(); break;
				case "c2": prince(); break;
				case "c3": Ymparille();break;
				case "c4": seuraa(); break;
				}
				break;
				
			case "car":
				switch(sinunValinta) {
				case "c1": otaVeri(); break;
				case "c2": elaOta(); break;
				
				}
				break;
				
			case "carHappy":{
				switch(sinunValinta) {
				case "c3": istuOnnellinen(); break;
				
				
				}
				break;
			}
			case "carNoHappy":{
				switch(sinunValinta) {
				case "c3": istuSurullinen(); break;
				
				}
				break;
				
			}
			case "destination":{
				switch(sinunValinta) {
				case "c2":ulosAutosta(); break;
				
				
				
				}
				break;
				
				
			}
			case "aonCenter":{
				switch(sinunValinta) {
				case "c1":puukota(); break;
				case "c3":tamaPaikka(); break;
				case "c2":Follow(); break;
				case "c4":ongelmia(); break;
				}
				break;
			}
			case"lobby":{
				switch(sinunValinta) {
				case "c1":aula(); break;
				}
				break;
			}
			case"IvoryTower":{
				switch(sinunValinta) {
				case "c1": torni(); break;
				case "c2": polvistuu(); break;
				
				
				
				}
				break;
				
			}
			case "throneRoom":{
				switch(sinunValinta) {
				case "c1": nousee(); break;
				case "c2":challenge(); break;
				case "c3":ready(); break;
				
				
				}
				break;
			}
			case "fight":{
				switch(sinunValinta) {
				case "c1": fightMechanic(); break;
				case "c2": inflictDamage();fightMechanic();
				
				
				}
				
				break;
			}
			case "WIN":{
				switch(sinunValinta) {
				case "c1": now(); break;
				case "c2": wrong();break;
				case "c3": killer(); break;
				case "c4": END(); break;
				
				
				}
				break;
				
			}
			case "end":{
				switch(sinunValinta) {
				case "c1":pelaajaSetup();break;
				case "c2":peliLoppu(); break;
				
				
				}
			}
			case "gameOver":{
				switch(sinunValinta) {
				case "c1": pelaajaSetup();break;
				case "c2": Close();break;
				
				}
			}
			}
			}
		}


		
	}

