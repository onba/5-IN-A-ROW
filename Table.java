public class Table {
	
	private Field[][] fields = new Field[33][33];
	
	public void init(){
		for (int i=0 ; i<33 ; i++){
			for (int j=0 ; j<33 ; j++){
				fields[i][j] = new Field();
				if (i==32 || j==32) fields[i][j].doBorder();
			}
		}
	}
	
	public Table(){
		init();
	}
	public byte getField(int i, int j){
		return fields[i][j].getState();
	}
	public void takeX(int i, int j){
		fields[i][j].takeX();
	}
	public void takeO(int i, int j){
		fields[i][j].takeO();
	}
}
