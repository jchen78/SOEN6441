package game.action.scaffold.dataGathering;

import game.action.scaffold.IActionVisitee;
import game.action.scaffold.IExecutableAction;

/**
 * 
 * 
 * IInterruptibleDataGathering.java
 * 
 * /

public interface IInterruptibleDataGatherer extends IActionDataGatherer, IExecutableAction, IActionVisitee {
	String getInterruptorName();
}
