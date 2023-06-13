package synchronize;

// User1 쓰레드 : calculator memory = 100
public class User1 extends Thread {
	private Calculator calculator;
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	
	
	@Override
	public void run() {
		calculator.setMemory(100);
	}
}
