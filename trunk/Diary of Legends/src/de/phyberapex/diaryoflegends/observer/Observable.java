package de.phyberapex.diaryoflegends.observer;

import java.util.ArrayList;
import java.util.List;

public class Observable {

	transient private List<Observer> obs = new ArrayList<Observer>();

	public void addObserver(Observer ob) {
		if (obs == null) {
			obs = new ArrayList<Observer>();
		}
		obs.add(ob);
	}

	public void notifyObservers(Object t) {
		for (Observer ob : obs) {
			ob.update(this, t);
		}
	}
}