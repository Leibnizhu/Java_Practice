/*EnumDemo---Leibniz.Hu 2015.07.05
Execises from <Thinking in Java> (TIJ in short)
Page 107 Execise 5.21 &5.22
@author Leibniz.Hu
@version 1.0.0
*/

public class EnumDemo {
	Money mSample;
	public static void main (String[] args) {
		//Execise 5.21 print all values and ordinal.
for(Money m : Money.values()) {
			System.out.println(m + "\tordinal: " + m.ordinal());
		}
		EnumDemo fen = new EnumDemo(Money.ONE__FEN);
		EnumDemo jiao = new EnumDemo(Money.FIFTY_FEN);
		EnumDemo yuan1 = new EnumDemo(Money.FIVE_YUAN);
		EnumDemo yuan2 = new EnumDemo(Money.TEN_YUAN);
		fen.show();
		jiao.show();
		yuan1.show();
		yuan2.show();
	}

	public EnumDemo(Money m) {
		/*this.*/mSample = m;
	}
	public void show() {
		//System.out.println();
		switch(mSample) {
		case ONE__FEN:
		case FIVE_FEN:
			System.out.println("Less than 1 \"mao\".");
			break;
		case TEN__FEN:
		case FIFTY_FEN:
			System.out.println("Less than 1 yuan.");
			break;
		case ONE_YUAN:
		case FIVE_YUAN:
			System.out.println("We offen use them.");
			break;
		case TEN_YUAN:
		case TWENTY_YUAN:
			System.out.println("We can use them to have a lunch.");
			break;
		}
	}
}

