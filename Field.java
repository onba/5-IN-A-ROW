
public class Field {
	private byte State=0;
		/* 0 : üres mezõ
		 * 1 : X
		 * 2 : O
		 * 3 : Határ
		 */
	private int x;
	private int y;
	public Field(int i, int j){
		x=i;
		y=j;
		State=0;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
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
