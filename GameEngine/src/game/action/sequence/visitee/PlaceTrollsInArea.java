/**
*
*/
package game.action.sequence.visitee;
import game.action.sequence.interfaces.IVisitee;
import game.action.sequence.interfaces.IVisitor;
/**
* @author Jason
*
*/
public class PlaceTrollsInArea implements IVisitee {
/* (non-Javadoc)
* @see game.action.sequence.interfaces.IVisitee#accept(game.action.sequence.interfaces.IVisitor)
*/
@Override
public void accept(IVisitor visitor) throws GameOverException {
// TODO Auto-generated method stub
}
/* (non-Javadoc)
* @see game.action.sequence.interfaces.IVisitee#getDescription()
*/
@Override
public String getDescription() {

return "Place a Troll in the area affected";
}
}
