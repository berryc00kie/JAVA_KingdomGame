import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KingdomGamePanel extends JPanel implements ActionListener {
    private List<Question> questions;
    private Random random;
    private int defense = 50;
    private int treasury = 50;
    private int loyalty = 50;
    private int turnCount = 0;

    private JLabel statusLabel;
    private JLabel imageLabel;
    private JLabel questionLabel;
    private JButton yesButton;
    private JButton noButton;
    //시작 이미지 
    private JLabel introLabel;
    //진엔딩 이미지
    private JLabel endingLabel;
    
    private ImageIcon[] images;
    
    private static final int GAMEOVER1 = 7; 
    private static final int GAMEOVER2 = 8; 
    private static final int GAMEOVER3 = 9; 
    private static final int GAMEOVER4 = 10; 
    private static final int GAMEOVER5 = 11; 
    private static final int GAMEOVER6 = 12; 
    private static final int GAMEOVER7 = 13; 

    public KingdomGamePanel(final String imgPath) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(700, 400));//600 400

        images = new ImageIcon[14];
        images[Question.a] = new ImageIcon(imgPath + "a.png");
        images[Question.b] = new ImageIcon(imgPath + "b.png");
        images[Question.c] = new ImageIcon(imgPath + "c.png");
        images[Question.d] = new ImageIcon(imgPath + "d.png");
        images[Question.e] = new ImageIcon(imgPath + "e.png");
        images[Question.f] = new ImageIcon(imgPath + "f.png");
        images[GAMEOVER1] = new ImageIcon(imgPath + "gameover1.png");
        images[GAMEOVER2] = new ImageIcon(imgPath + "gameover2.png");
        images[GAMEOVER3] = new ImageIcon(imgPath + "gameover3.png");
        images[GAMEOVER4] = new ImageIcon(imgPath + "gameover4.png");
        images[GAMEOVER5] = new ImageIcon(imgPath + "gameover5.png");
        images[GAMEOVER6] = new ImageIcon(imgPath + "gameover6.png");
        images[GAMEOVER7] = new ImageIcon(imgPath + "gameover7.png");
        
        //시작 이미지
        introLabel = new JLabel();
        introLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        //진엔딩 이미지
        endingLabel = new JLabel();
        endingLabel.setHorizontalAlignment(SwingConstants.CENTER);


        // 상태창 (맨 위)
        statusLabel = new JLabel("", SwingConstants.CENTER);
        add(statusLabel, BorderLayout.NORTH);

        // 중앙 패널 (이미지와 질문)
        JPanel centerPanel = new JPanel(new BorderLayout());
        
        // 이미지 (중앙 상단)
        imageLabel = new JLabel(images[Question.start]);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(imageLabel, BorderLayout.CENTER);

        // 질문 (중앙 하단)
        questionLabel = new JLabel("", SwingConstants.CENTER);
        centerPanel.add(questionLabel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        // 버튼 패널 (맨 아래)
        JPanel buttonPanel = new JPanel(new FlowLayout());
        yesButton = new JButton("예");
        noButton = new JButton("아니오");
        yesButton.addActionListener(this);
        noButton.addActionListener(this);
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        add(buttonPanel, BorderLayout.SOUTH);

        questions = new ArrayList<>();
        initializeQuestions();
        random = new Random();

        updateStatusLabel();
    }

    private void initializeQuestions() {
    	//국방,국고, 민심 순서
    	
    	 questions.add(new Question("적국이 국경을 넘어왔습니다. 전쟁을 선포하시겠습니까?",
    	            new Answer("전쟁을 선포했지만 처참히 패했습니다.", -20, -20, -20),
    	            new Answer("적당한 경고로 무사히 넘어갔습니다", 0, 0, 0),
    	            Question.a));
    	 
    	 questions.add(new Question("적국이 국경을 넘어왔습니다. 전쟁을 선포하시겠습니까?",
 	            new Answer("전쟁을 선포했고 엄청난 승리를 거뒀습니다", +20, +20, +20),
 	            new Answer("위험을 감수하진 않았습니다. 그러나 국경의 백성들이 고통스러워합니다.", 0, 0, -10),
 	            Question.a));

    	        questions.add(new Question("백성들이 높은 세금에 고통스러워하고 있사옵니다. 부디 백성들의 고통을 헤아려 관용을 베푸소서",
    	            new Answer("세금을 인하했습니다. 민심은 상승했지만 국고가 감소했습니다.", 0, -10, 10),
    	            new Answer("세금 인하 요구를 거부했습니다. 국고는 유지되었지만 민심이 동요합니다.", 0, 0, -10),
    	            Question.b));

    	        questions.add(new Question("흉년이 들어 백성들이 굶주리고 있사옵니다. 바라건대, 곡식을 나누어 주시어 백성들의 굶주림을 덜어 주시옵소서.",
    	                new Answer("곡식을 나누어줬습니다. 백성들이 행복해합니다.", 0, -20, +10),
    	                new Answer("곡식을 나누지 않았습니다. 백성들은 계속 굶주립니다.", 0, 0, -10),
        	            Question.c));

    	        questions.add(new Question("어떤 지역에서 전염병이 창궐하고 있사옵니다. 막대한 자금을 들여 백성들을 치료하는 대신 지역을 봉쇄하소서",
    	                new Answer("약간의 희생으로 다수를 지켰습니다", 0, 0, -5),
    	                new Answer("많은 손해가 있었지만 소수의 백성도 백성입니다.", 0, -20, 20),
        	            Question.d));

    	        questions.add(new Question("최근 외적의 침입이 빈번하옵니다. 군사력을 강화하시옵소서.",
    	                new Answer("군사력을 강화했습니다", +10,-10, 0),
    	                new Answer("군사력을 강화하지 않은 가운데 외적의 침입이 있었습니다.", -10, -10, -10),
        	            Question.e));
    	        
    	        questions.add(new Question("최근 외적의 침입이 빈번하옵니다. 군사력을 강화하시옵소서.",
    	                new Answer("군사력을 강화했습니다", +10, -10, 0),
    	                new Answer("군사력을 강화하지 않았습니다. 그러나 아무일도 일어나지 않았습니다. 좋은 절약이었던 것 같습니다.", +5, 0, 0),
        	            Question.e));

    	        questions.add(new Question("국가의 재정이 어려워지고 있사옵니다. 절약 방안을 마련하시길 청하옵니다.",
    	                new Answer("재정을 유지하였습니다.", 0, 0, 0),
    	                new Answer("재정이 더 심각해졌습니다.", 0, -10, 0),
        	            Question.b));

    	        questions.add(new Question("나라에 인재가 부족한 듯 싶습니다. 과거 제도를 확대하시겠습니까?",
    	                new Answer("나랏일을 거드는 훌륭한 인재가 많아지니 국정이 평탄해졌습니다", 0, -10, +10),
    	                new Answer("그것도 다 나랏돈으로 녹을 받는 인간들인데 돈을 낭비해서야 되겠습니까.", 0, 0, 0),
        	            Question.f));

    }

    public void startGame() {
        showIntro();
        nextTurn();
    }
 
    private void showIntro() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "게임 시작", true);
        dialog.setUndecorated(false);  // 창 테두리 표시 (닫기 버튼 포함)

        ImageIcon introIcon = new ImageIcon(KingdomGameGUI.IMAGE_PATH + "start.png");
        JLabel imageLabel = new JLabel(introIcon);
        dialog.add(imageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.pack();

        // 프로그램 중앙에 위치시키기
        dialog.setLocationRelativeTo(this);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);  // 창 닫기 버튼 활성화
        dialog.setVisible(true);
    }

    private void nextTurn() {
        turnCount++;
        if (turnCount > 29 ) {
            endGame1();
        } 
        else if (defense <= 0 ) {
            endGame2();
        } 
        else if (treasury <= 0) {
            endGame3();
        } 
        else if ( loyalty <= 0) {
            endGame4();
        } 
        else if (defense <= 0 && treasury <= 0 ) {
            endGame5();
        } 
        else if (treasury <= 0 && loyalty <= 0) {
            endGame6();
        } 
        else  if (defense <= 0 && loyalty <= 0) {
            endGame7();
        } 
        
        else {
            Question currentQuestion = questions.get(random.nextInt(questions.size()));
            questionLabel.setText("<html><body style='width: 300px; text-align: center'>" + currentQuestion.getQuestionText() + "</body></html>");
            imageLabel.setIcon(images[currentQuestion.getImageType()]);
            yesButton.setActionCommand("yes:" + questions.indexOf(currentQuestion));
            noButton.setActionCommand("no:" + questions.indexOf(currentQuestion));
            yesButton.setEnabled(true);
            noButton.setEnabled(true);
            updateStatusLabel();
        }
    }

    private void endGame1() {
    	JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "해피엔딩", true);
        dialog.setUndecorated(false);  // 창 테두리 표시 (닫기 버튼 포함)

        ImageIcon endingIcon = new ImageIcon(KingdomGameGUI.IMAGE_PATH + "ending.png");
        JLabel imageLabel = new JLabel(endingIcon);
        dialog.add(imageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.pack();

        // 프로그램 중앙에 위치시키기
        dialog.setLocationRelativeTo(this);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);  // 창 닫기 버튼 활성화
        dialog.setVisible(true);
         
         // 버튼 비활성화
         yesButton.setEnabled(false);
         noButton.setEnabled(false);
    }
    
    private void endGame2() {
   	 String result = String.format("조선은 외세의 침략으로 멸망했습니다\n\n최종 결과:\n국방: %d\n국고: %d\n민심: %d", defense, treasury, loyalty);
        
        // 상태 레이블 업데이트
        updateStatusLabel();
        
        // 이미지 레이블에 게임 종료 이미지 설정
        imageLabel.setIcon(images[GAMEOVER2]);
        
        // 질문 레이블에 결과 텍스트 설정
        questionLabel.setText("<html><body style='width: 300px; text-align: center'>" + result.replace("\n", "<br>") + "</body></html>");
        
        // 버튼 비활성화
        yesButton.setEnabled(false);
        noButton.setEnabled(false);
   }
    
    private void endGame3() {
   	 String result = String.format("조선은 제정붕괴로 멸망했습니다\n\n최종 결과:\n국방: %d\n국고: %d\n민심: %d", defense, treasury, loyalty);
        
        // 상태 레이블 업데이트
        updateStatusLabel();
        
        // 이미지 레이블에 게임 종료 이미지 설정
        imageLabel.setIcon(images[GAMEOVER3]);
        
        // 질문 레이블에 결과 텍스트 설정
        questionLabel.setText("<html><body style='width: 300px; text-align: center'>" + result.replace("\n", "<br>") + "</body></html>");
        
        // 버튼 비활성화
        yesButton.setEnabled(false);
        noButton.setEnabled(false);
   }
    
    private void endGame4() {
   	 String result = String.format("성난 민중이 궁을 불태웠습니다. 당신은 백성들의 손에 죽었습니다.\n\n최종 결과:\n국방: %d\n국고: %d\n민심: %d", defense, treasury, loyalty);
        
        // 상태 레이블 업데이트
        updateStatusLabel();
        
        // 이미지 레이블에 게임 종료 이미지 설정
        imageLabel.setIcon(images[GAMEOVER4]);
        
        // 질문 레이블에 결과 텍스트 설정
        questionLabel.setText("<html><body style='width: 300px; text-align: center'>" + result.replace("\n", "<br>") + "</body></html>");
        
        // 버튼 비활성화
        yesButton.setEnabled(false);
        noButton.setEnabled(false);
   }
    
    private void endGame5() {
   	 String result = String.format("제정이 붕괴한 상황속에서 외세의 침략까지 더해지니 조선은 더이상 버틸 수 없었습니다. \n\n최종 결과:\n국방: %d\n국고: %d\n민심: %d", defense, treasury, loyalty);
        
        // 상태 레이블 업데이트
        updateStatusLabel();
        
        // 이미지 레이블에 게임 종료 이미지 설정
        imageLabel.setIcon(images[GAMEOVER5]);
        
        // 질문 레이블에 결과 텍스트 설정
        questionLabel.setText("<html><body style='width: 300px; text-align: center'>" + result.replace("\n", "<br>") + "</body></html>");
        
        // 버튼 비활성화
        yesButton.setEnabled(false);
        noButton.setEnabled(false);
   }
    
    private void endGame6() {
   	 String result = String.format("성난 민중이 들고 일어났습니다. 백성의 손에서 새로운 나라가 탄생하고 조선은 역사속으로 사라집니다.\n\n최종 결과:\n국방: %d\n국고: %d\n민심: %d", defense, treasury, loyalty);
        
        // 상태 레이블 업데이트
        updateStatusLabel();
        
        // 이미지 레이블에 게임 종료 이미지 설정
        imageLabel.setIcon(images[GAMEOVER6]);
        
        // 질문 레이블에 결과 텍스트 설정
        questionLabel.setText("<html><body style='width: 300px; text-align: center'>" + result.replace("\n", "<br>") + "</body></html>");
        
        // 버튼 비활성화
        yesButton.setEnabled(false);
        noButton.setEnabled(false);
   }
    
    private void endGame7() {
      	 String result = String.format("성난 민중이 들고 일어났습니다. 그를 막을 군대도 없습니다. 당신은 백성의 손에 죽습니다.\n\n최종 결과:\n국방: %d\n국고: %d\n민심: %d", defense, treasury, loyalty);
           
           // 상태 레이블 업데이트
           updateStatusLabel();
           
           // 이미지 레이블에 게임 종료 이미지 설정
           imageLabel.setIcon(images[GAMEOVER7]);
           
           // 질문 레이블에 결과 텍스트 설정
           questionLabel.setText("<html><body style='width: 300px; text-align: center'>" + result.replace("\n", "<br>") + "</body></html>");
           
           // 버튼 비활성화
           yesButton.setEnabled(false);
           noButton.setEnabled(false);
      }

    private void updateStatusLabel() {
        String status = String.format("턴: %d | 국방: %d | 국고: %d | 민심: %d", turnCount, defense, treasury, loyalty);
        statusLabel.setText(status);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String[] parts = command.split(":");
        int questionIndex = Integer.parseInt(parts[1]);
        Question currentQuestion = questions.get(questionIndex);
        Answer selectedAnswer = parts[0].equals("yes") ? currentQuestion.getYesAnswer() : currentQuestion.getNoAnswer();

        defense += selectedAnswer.getDefenseChange();
        treasury += selectedAnswer.getTreasuryChange();
        loyalty += selectedAnswer.getLoyaltyChange();

        defense = Math.max(0, Math.min(100, defense));
        treasury = Math.max(0, Math.min(100, treasury));
        loyalty = Math.max(0, Math.min(100, loyalty));

        JOptionPane.showMessageDialog(this, selectedAnswer.getResponseText(), "결과", JOptionPane.INFORMATION_MESSAGE);
        updateStatusLabel();

        nextTurn();
    }
}