import java.util.ArrayList;

public class Table {
	
	private Field[][] fields = new Field[33][33];
	
	public void init(){
		for (int i=0 ; i<33 ; i++){
			for (int j=0 ; j<33 ; j++){
				fields[i][j] = new Field(i,j);
				if (i==32 || j==32) fields[i][j].doBorder();
			}
		}
	}
	
	public Table(){
		init();
	}
	/*Ezzel a függvényünkkel megnézzük, hogy van-e a táblázatunkban 5-ös sorozat.
	 */
	public ArrayList<Field> isThere5(){
		ArrayList<Field> WinnerFields = new ArrayList<Field>();
		WinnerFields.clear();
		int k;
		for (int i=0;i<32;i++){
			for (int j=0; j<32;j++){
				if (fields[i][j].getState()!=0){
				byte x = fields[i][j].getState();
					if (fields[i+1][j].getState()==x && fields[i+2][j].getState()==x && fields[i+3][j].getState()==x && fields[i+4][j].getState()==x){
						for(k=0;k<5;k++){
						WinnerFields.add(fields[i+k][j]);
						}
						return WinnerFields;	//vízszintes
					}
					if (fields[i+1][j+1].getState()==x && fields[i+2][j+2].getState()==x && fields[i+3][j+3].getState()==x && fields[i+4][j+4].getState()==x){
						for(k=0;k<5;k++){
							WinnerFields.add(fields[i+k][j+k]);
							}
						return WinnerFields;	//jobb-le átló
					}
					if (fields[i][j+1].getState()==x && fields[i][j+2].getState()==x && fields[i][j+3].getState()==x && fields[i][j+4].getState()==x){
						for(k=0;k<5;k++){
							WinnerFields.add(fields[i][j+k]);
							}
						return WinnerFields;	//függõleges
					}
					if (i>=4 && fields[i-1][j+1].getState()==x && fields[i-1][j+2].getState()==x && fields[i-1][j+3].getState()==x && fields[i-1][j+4].getState()==x){
						for(k=0;k<5;k++){
							WinnerFields.add(fields[i-k][j+k]);
							}
						return WinnerFields;  //bal-le átló
					}
				}
			}
			}
		return null;
	}
	public Field getField(int i, int j){
		return fields[i][j];
	}
	public void takeX(int i, int j){
		fields[i][j].takeX();
	}
	public void takeO(int i, int j){
		fields[i][j].takeO();
	}
}
