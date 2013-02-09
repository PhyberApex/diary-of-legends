package de.phyberapex.diaryoflegends;

import java.util.ArrayList;
import java.util.List;

public class Observable {

	transient public List<Observer> obs = new ArrayList<Observer>();
	
	public void addObserver(Observer ob){
		obs.add(ob);
	}
	
	public void notifyObservers(Object t){
		for(Observer ob : obs) {
			ob.update(this, t);
		}
	}
}