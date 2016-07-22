
public class Field {
	private byte State=0;
		/* 0 : üres mezõ
		 * 1 : X
		 * 2 : O
		 * 3 : Határ
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
