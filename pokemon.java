/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CPT;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Colvin
 */
public class pokemon {
    private String pokemonchoice;
    private int [] stats;
    private int pokemonHp;
    private String [] moveset;
    private int level;
    private int tempHp;
    private int experience;
    private int money;
    
    public pokemon (String pokemonchoiceSave, int [] statsSave, int pokemonHpSave, String [] movesetSave, int levelSave, int experienceSave, int moneySave){
        pokemonchoice=pokemonchoiceSave;
        stats=statsSave;
        pokemonHp=pokemonHpSave;
        moveset=movesetSave;
        level = levelSave;
        tempHp = pokemonHp;
        experience = experienceSave;
        money = moneySave;
    }
    
    public int getMoney(){
        return money;
    }
    
    public int setMoney(int spentMoney){
        money = money-spentMoney;
        return money;
    }
    
    public int getLevel(){
        return level;
    }
    
    public int getExperience(){
        return experience;
    }
    
    public int getTempHp(){
        return tempHp;
    }
    
    public int getHp(){
        return pokemonHp;
    }
    
    public int [] getStats(){
        return stats;
    }
    
    public String [] getMoves(){
        return moveset;
    }
    
    public String [] setMoves(String newMove, int moveNumber){
        moveset[moveNumber-1]=newMove;
        return moveset;
    }
    
    public void heal(){
        tempHp=pokemonHp;
    }
    
    public void saveData(){
        FileWriter fw = null;
        try{
            fw = new FileWriter("pokemonData.txt");
        }
        catch(IOException e){
            System.out.println("An error occurred with FileWriter");
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println("1");
        pw.println(pokemonchoice);
        for (int i = 0; i < stats.length; i++) {
            pw.println(stats[i]);
        }
        pw.println(pokemonHp);
        for (int i = 0; i < moveset.length; i++) {
            pw.println(moveset[i]);
        }
        pw.println(level);
        pw.println(experience);
        pw.println(money);
        pw.close();
    }
    
    
}
