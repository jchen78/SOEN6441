package game.action.sequence.visitor;

import game.action.sequence.interfaces.IVisitee;

import java.util.List;

public class Printer {
	public void print(IVisitee action) {
		System.out.println(action.getDescription());
	}
	public void print(List<IVisitee> choices) {
		
	}
}
