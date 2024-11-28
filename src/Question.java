public class Question {
	public static final int start = 4;
    public static final int a = 5;
    public static final int b = 6;
    public static final int c = 7;
    public static final int d = 8;
    public static final int e = 9;
    public static final int f = 10;
    public static final int g = 11;
    public static final int h = 12;
    

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