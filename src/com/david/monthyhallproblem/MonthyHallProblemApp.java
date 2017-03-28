/**
 * 
 * This small program simulates the Monthy Hall Problem
 * 		- the premise is that you are on a game show
 * 		- there are 3 doors and you have to choose 1
 * 		- there is 1 car behind 1 of the doors and the other 2 doors have goats behind them
 * 		- you make your choice : say you choose door 1
 * 		- then the game show host says he will open door 3, which he does, and there is a goat standing there
 * 		- he then asks you if you would like to stick with your original choice or switch to door 2???
 * 		- NOW, this is the point of the problem:
 * 			-  are the odds better if you stick with your original choice or, change to the remaining door
 * 			-  this program proves statistically that you have a 66% chance of winning if you change your choice
 * 			-  leaving you with a 33% chance of winning if you stick with your original choice
 */

package com.david.monthyhallproblem;

import java.util.Random;

public class MonthyHallProblemApp {
	
	private Door[] gameDoors;
	private int winningDoor;
	private Player player;
	private static double stickAndWinCounter;
	private static double stickAndLoseCounter;
	private static double totalGamesPastFirstDoor;

	public static void main(String[] args) {
		
		//run simulation 1 million times & then print results
		for(int i = 0; i < 1_000_000; ++i){
			new MonthyHallProblemApp().startGame();
		}
		
		printResults();
	}
	
	private void startGame() {
		player = new Player();
		gameDoors = create3NewDoors();
		
		//assign random number to 1 of 3 doors - setting & capturing value of the winning door
		int prizeDoor = getRandomNumberBetween0And2();
		winningDoor = prizeDoor +1;
		gameDoors[prizeDoor].setWinner(true);
		
		//set player choice
		player.setDoorChoice(getRandomNumberBetween1And3());
		
		if(openFirstDoorAndNotCar()){
			checkIfPlayerWinsIfStickOrTwist();
		}
	}

	private int getRandomNumberBetween1And3(){
		Random random = new Random();
		int randomNum = (random.nextInt(3) + 1);
		return randomNum;
	}
	
	private int getRandomNumberBetween0And2(){
		Random random = new Random();
		int randomNum = random.nextInt(3);
		return randomNum;
	}
	
	private Door[] create3NewDoors(){
		int doorNum = 1;
		Door[] gameDoors = new Door[3];
		for (int i = 0; i < gameDoors.length; ++i) {
			gameDoors[i] = new Door(doorNum);
			++doorNum;
		}
		return gameDoors;
	}
	
	private boolean openFirstDoorAndNotCar(){
		int openRandomFirstDoor = getRandomNumberBetween0And2();
		
		if(gameDoors[openRandomFirstDoor].isWinner()){
			return false;
		}
		return true;
	}
	
	private void checkIfPlayerWinsIfStickOrTwist(){
		if(player.getDoorChoice() == winningDoor){
			++stickAndWinCounter;
		}else{
			++stickAndLoseCounter;
		}
	}
	
	private static void printResults(){
		System.out.println("\n\nEND RESULTS...");
		totalGamesPastFirstDoor = stickAndWinCounter + stickAndLoseCounter;
		System.out.printf("%s%.0f%s%.0f%s%.0f", "\nTimes stuck: " , stickAndWinCounter , 
												"\nTimes twisted: " , stickAndLoseCounter , 
												"\n\nTotal games played past the 1st door: " , totalGamesPastFirstDoor);
		
		System.out.printf("%s%.0f%s", "\nGame played " , totalGamesPastFirstDoor , " times");
		System.out.printf("\n%s%.0f%s", "When player stuck, they won " , ((stickAndWinCounter / totalGamesPastFirstDoor) * (100/1)) , "% of the time");
		System.out.printf("\n%s%.0f%s", "When player twisted, they won ", ((stickAndLoseCounter / totalGamesPastFirstDoor) * (100/1)) , "% of the time");
	}
}
