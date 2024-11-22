public class Question {
	public static final int start = 0;
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    

    private String questionText;
    private Answer yesAnswer;
    private Answer noAnswer;
    private int imageType;

    public Question(String questionText, Answer yesAnswer, Answer noAnswer, int imageType) {
        this.questionText = questionText;
        this.yesAnswer = yesAnswer;
        this.noAnswer = noAnswer;
        this.imageType = imageType;
    }

    public String getQuestionText() { return questionText; }
    public Answer getYesAnswer() { return yesAnswer; }
    public Answer getNoAnswer() { return noAnswer; }
    public int getImageType() { return imageType; }
}