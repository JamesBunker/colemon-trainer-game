/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package CPT;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Colvin
 */
public class pokemon_main {
    public static void main(String[] args) {
        pokemon myPoke = null;
        String [] shopMoveList = {"Earthquake", "SteelWing", "DragonClaw", "QuickAttack","IronTail","BrickBreak", "FireSpin", "Acrobatics", "Bulldoze"};
        
        System.out.println("Continue");
        System.out.println("New Game");
        
        int homeScreenDecision = numCheck();
        boolean validSave = checkSaveData();
        if(validSave && homeScreenDecision==1){
            myPoke = getSaveData();
            homeScreenDecision=0;
        }
        else{
            System.out.println("No Save Files- Starting New Game...");
            homeScreenDecision=2;
        }
        
        if(homeScreenDecision==2){
            introduction();
            myPoke = newPoke();
        }
        
        //main loop
        boolean quit = false;
        while (!quit){
            System.out.println("Battle (1)");
            System.out.println("My Pokemon(2)");
            System.out.println("Pokemon Center(3)");
            System.out.println("small box bot left Shop(4)");
            System.out.println("small box bot right $" + myPoke.getMoney());
            System.out.println("small box bot right Save(5)");
            System.out.println("small box top right quit(6)");
            int choice = choiceCheck();
            
            switch(choice){
                case 1:
                    battle(myPoke);
                    break;
                case 2:
                    myPokemon(myPoke);
                    break;
                case 3:
                    myPoke.heal();
                    break;
                case 4:
                    shop(shopMoveList, myPoke);
                    break;
                case 5:
                    myPoke.saveData();
                    break;
                case 6:
                    quit= true;
                    break;
            }
        }
        
    }
    
    public static int numCheck(){
        int decision = 0;
        boolean validnumber = false;
        Scanner s = new Scanner(System.in);
        while (!validnumber){
            System.out.println("Press 1 to load save file, press 2 to start new game");
            while(!s.hasNextInt()) {
                System.out.println("Not a valid input (1 or 2)!");
                System.out.println("");
                s.nextLine();
            }
            
            decision = s.nextInt();
            if (decision == 2 || decision==1){
                validnumber = true;
            }
            else{
                System.out.println("Not a valid number (1 or 2)!");
                System.out.println("");
            }
        }
        
        System.out.println("");
        return decision;
    }
 
    public static boolean checkSaveData(){
        try{
            FileReader fr1 =new FileReader("pokemonData.txt"); 
            Scanner savecheckscan = new Scanner(fr1);     
            
            while(savecheckscan.hasNextInt())
            {
                if(savecheckscan.nextInt()==1){
                    savecheckscan.close();
                    return true;
                }
                else{
                    savecheckscan.close();
                    return false;
                }
            }
            
        }catch(IOException e){
            System.out.println("No Save Data");
            return false;
        }
        return false;
    }    
    
    public static pokemon getSaveData(){
        String pokemonchoice = "Null";
        int [] stats = new int [5];
        int pokemonHp = 0;
        String [] moveset = new String [4];
        int level = 0;
        int experience = 0;
        int money = 0;
        try{
            FileReader fr =new FileReader("pokemonData.txt"); 
            Scanner savescan = new Scanner(fr);     
            int LineNum=0;
            
            while(savescan.hasNext())
            {
                switch (LineNum) {
                    case 0:
                        System.out.println("Loading");
                        savescan.next();
                    case 1:
                        pokemonchoice = savescan.next(); 
                    case 2:
                        stats[0] = savescan.nextInt();
                    case 3:
                        stats[1] = savescan.nextInt();
                    case 4:
                        stats[2] = savescan.nextInt();
                    case 5:
                        stats[3] = savescan.nextInt();
                    case 6:
                        stats[4] = savescan.nextInt();
                    case 7:
                        pokemonHp = savescan.nextInt();
                    case 8:
                        moveset[0]= savescan.next();
                    case 9:
                        moveset[1]= savescan.next();
                    case 10:
                        moveset[2]= savescan.next();
                    case 11:
                        moveset[3]= savescan.next();
                    case 12:
                        level = savescan.nextInt();
                    case 13:
                        experience = savescan.nextInt();
                    case 14:
                        money=savescan.nextInt();;
                    default:
                        System.out.println("Done Loading Data");
                }
                LineNum++;
            }
            pokemon savePoke = new pokemon (pokemonchoice, stats, pokemonHp, moveset,level, experience, money);
            savescan.close();
            return savePoke;
        }catch(IOException e){
            System.out.println("No Save Data");
            return null;
        } 
    }
    
    public static void introduction(){
        //intro stuff
        System.out.println("Oak : Hello there! Welcome to the world of POKEMON! My name is OAK! People call me the POKEMON PROF! This world is inhabited by creatures called POKEMON! For some people, POKEMON are pets. Others use them for fights.");
        System.out.println("Myself...I study POKEMON as a profession.");
        System.out.println("");
        System.out.println("You will be in charge of my little Charmander here and I want you to train and befriend him!");
        System.out.println("Feel free to battle the pokemon around the neighbourhood to power up your pokemon, defeating pokemon will reward you with experience and money.");
        System.out.println("When you do enough training your pokemon will be sure to learn all sorts of new powerful moves, and maybe even evolve into a stronger form!");
        System.out.println("");
        System.out.println("If your pokemon become injured you can heal it at the Pokemon Center for a small free, but if your pokemon faints you will be charged a hefty fine for the nurses to have to go to you.");
        System.out.println("With your aquired money your can purchase TM's which can provide your pokemon with powerful new moves!");
        System.out.println("");
        System.out.println("Your very own Pokémon adventure is about to unfold! A world of dreams and adventures with Pokémon awaits! Let's go!");
    }
    
    public static pokemon newPoke(){
        String pokemonchoice = "Charmander";
        int [] stats = {11,9,12,11,12};
        int pokemonHp = 19;
        String [] moveset = {"Growl", "Scratch", "Ember", "Counter"};
        int level = 5;
        int experience = 0;
        int money = 0;
        pokemon myPoke = new pokemon(pokemonchoice, stats, pokemonHp, moveset, level, experience, money);
        return myPoke;
    }
    
    public static int choiceCheck(){
        int choice = 0;
        boolean validnumber = false;
        Scanner s = new Scanner(System.in);
        while (!validnumber){
            while(!s.hasNextInt()) {
                System.out.println("Not a valid input (1 - 5)!");
                System.out.println("");
                s.nextLine();
            }
            
            choice = s.nextInt();
            if (choice == 1 || choice==2 || choice==3 ||choice==4 ||choice==5 ||choice==6){
                validnumber = true;
            }
            else{
                System.out.println("Not a valid number (1 - 5)!");
                System.out.println("");
            }
        }
        
        System.out.println("");
        return choice;
    }
    
    public static void battle(pokemon myPoke){
        
    }
    
    public static void myPokemon(pokemon myPoke){
        boolean returnMenu = false;
        int [] stats = myPoke.getStats();
        int attack = stats[0];
        int defense = stats[1];
        int spDefense = stats[2];
        int spAttack = stats[3];
        int speed = stats[4];
        
        String[] moveset = myPoke.getMoves();
        String move1 = moveset[0];
        String move2 = moveset[1];
        String move3 = moveset[2];
        String move4 = moveset[3];
        
        int hp = myPoke.getHp();
        int tempHp = myPoke.getTempHp();
        int level = myPoke.getLevel();
        int experience = myPoke.getExperience();
        while(!returnMenu){
        System.out.println("Level: " + level);
        System.out.println("Experience: " + experience);
        System.out.println("Health: " + tempHp + " / " + hp);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defense);
        System.out.println("Sp. Defense: " + spDefense);
        System.out.println("Sp. Attack: " + spAttack);
        System.out.println("Speed: " + speed);
        
        System.out.println("");
        System.out.println("Moves:");
        System.out.println(move1);
        System.out.println(move2);
        System.out.println(move3);
        System.out.println(move4);
        System.out.println("Press 0 to return to menu");
        returnMenu = returnMenuCheck();
        System.out.println("");}
    }
    
    public static boolean returnMenuCheck(){
        int choice = 1;
        boolean validnumber = false;
        Scanner s = new Scanner(System.in);
        while (!validnumber){
            while(!s.hasNextInt()) {
                System.out.println("Not a valid input (0 to return to menu)!");
                System.out.println("");
                s.nextLine();
            }
            
            choice = s.nextInt();
            if (choice==0){
                validnumber = true;
            }
            else{
                System.out.println("0 to return to menu!");
            }
        }
        return true;
    }
    
    public static void shop(String [] shopMoveList, pokemon myPoke){
        int shopChoice = 4;
        String [] shopItem = {shopMoveList[(int) (Math.random() * (2 - 0 + 1) + 0)],shopMoveList[(int) (Math.random() * (5 - 3 + 1) + 3)],shopMoveList[(int) (Math.random() * (8 - 6 + 1) + 6)]};

        while(shopChoice !=0){
            System.out.println("1 Powerful 1000$: " + shopItem[0]);
            System.out.println("2 Average 500$: " + shopItem[1]);
            System.out.println("3 Weak 100$: " + shopItem[2]);
            System.out.println("press 0 to return to menu");
            shopChoice = shopChoiceCheck();
            if (shopChoice ==1 && (myPoke.getMoney()>=1000)){
                myPoke.setMoney(1000);
                newMove(myPoke, shopItem[shopChoice-1]);
            }
            
            else if (shopChoice ==2 && (myPoke.getMoney()>=500)){
                myPoke.setMoney(500);
                newMove(myPoke, shopItem[shopChoice-1]);            }
            
            else if (shopChoice ==3 && (myPoke.getMoney()>=100)){
                myPoke.setMoney(100);
                newMove(myPoke, shopItem[shopChoice-1]);            }
            else{
                System.out.println("Insufficient funds... Battle more pokemon!");
            }
            
        }
    }
    
    public static int shopChoiceCheck(){
        int choice = 0;
        boolean validnumber = false;
        Scanner s = new Scanner(System.in);
        while (!validnumber){
            while(!s.hasNextInt()) {
                System.out.println("Not a valid input (0-3)!");
                System.out.println("");
                s.nextLine();
            }
            
            choice = s.nextInt();
            if (choice == 1 || choice==2 || choice==3 ||choice==0){
                validnumber = true;
            }
            else{
                System.out.println("Not a valid number (0 - 3)!");
                System.out.println("");
            }
        }
        
        System.out.println("");
        return choice;
    }
        
    public static void newMove(pokemon myPoke, String newMove){
        String[] moveset=myPoke.getMoves();
        for (int i = 0; i <= 3; i++) {
            System.out.println((i+1) +": " + moveset[i]);
        }
        int moveChangeNum = moveChangeNumCheck();
        System.out.println("Select which move to change");
        if (moveChangeNum==1){
            myPoke.setMoves(newMove, moveChangeNum);
        }
        
        else if (moveChangeNum==2){
            myPoke.setMoves(newMove, moveChangeNum);
        }
        
        else if (moveChangeNum==3){
            myPoke.setMoves(newMove, moveChangeNum);
        }
        
        else if (moveChangeNum==4){
            myPoke.setMoves(newMove, moveChangeNum);
        }
        System.out.println("Move changed!");
    }
    
    public static int moveChangeNumCheck(){
        int choice = 0;
        boolean validnumber = false;
        Scanner s = new Scanner(System.in);
        while (!validnumber){
            while(!s.hasNextInt()) {
                System.out.println("Not a valid input (1-4)!");
                System.out.println("");
                s.nextLine();
            }
            
            choice = s.nextInt();
            if (choice == 1 || choice==2 || choice==3 ||choice==4){
                validnumber = true;
            }
            else{
                System.out.println("Not a valid number (1 - 4)!");
                System.out.println("");
            }
        }
        
        System.out.println("");
        return choice;
    }
}
