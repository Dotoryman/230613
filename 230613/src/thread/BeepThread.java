package thread;

import java.awt.Toolkit;

public class BeepThread extends Thread{

	
	@Override
	public void run() {
//		super.run();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		for (int i = 0; i < 5; i++) {
			toolkit.beep();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
