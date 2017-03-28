package com.david.monthyhallproblem;

public class Door {
	private boolean isWinner;
	private int doorNum;

	public Door(int doorNum){
		this.doorNum = doorNum;
	}
	
	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	public int getDoorNum() {
		return doorNum;
	}

	public void setDoorNum(int doorNum) {
		this.doorNum = doorNum;
	}	
	
	public String open(){
		if(isWinner){
			return "car";
		}else{
			return "goat";
		}
	}
}
