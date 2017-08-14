import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
JFrame obj=new JFrame();
GamePlayofWallBreaker gpw=new GamePlayofWallBreaker();
obj.setBounds(100,100,700,600);
obj.setTitle("Wall Breaker");
obj.setVisible(true);
obj.setResizable(false);
obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
obj.add(gpw);
}
}
