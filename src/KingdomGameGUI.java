import javax.swing.JFrame;
//import KingdomGame.KingdomGamePanel;

public class KingdomGameGUI {
    public static final String IMAGE_PATH = "image폴더경로\\";
    
    public static void main(String[] args) {
        KingdomGamePanel panel = new KingdomGamePanel(IMAGE_PATH);
        JFrame frame = new JFrame("통촉하여 주시옵소서 전하");
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        panel.startGame(); // 게임 시작
    }
}