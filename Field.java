
public class Field {
	private byte State=0;
		/* 0 : �res mez�
		 * 1 : X
		 * 2 : O
		 * 3 : Hat�r
		 */
	public Field(){
		State=0;
	}
	public byte getState(){
		return State;
	}
	public void takeX(){
		State=1;
	}
	public void takeO(){
		State=2;
	}
	public void doBorder(){
		State=3;
	}
}
