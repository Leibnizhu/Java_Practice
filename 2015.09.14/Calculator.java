package leibniz.hu;

public class Calculator {
	private String num1Str;
	private String num2Str;
	private String opt;
	private String result;
	
	public String getNum1Str() {
		return num1Str;
	}
	public void setNum1Str(String num1Str) {
		this.num1Str = num1Str;
	}
	public String getNum2Str() {
		return num2Str;
	}
	public void setNum2Str(String num2Str) {
		this.num2Str = num2Str;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public Calculator(){
		
	}
	
	public void calc(){
		double dNum1 = Double.valueOf(num1Str);
		double dNum2 = Double.valueOf(num2Str);
		char cOpt = opt.charAt(0);
		
		switch(cOpt){
			case '+':
				result = String.valueOf(dNum1 + dNum2);
				break;			
			case '-':
				result = String.valueOf(dNum1 - dNum2);
				break;				
			case '*':
				result = String.valueOf(dNum1 * dNum2);
				break;				
			case '/':
				result = String.valueOf(dNum1 / dNum2);
				break;
			default:
				throw new RuntimeException("�Ƿ�������...");
		}
	}
}
