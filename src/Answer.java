public class Answer {
    private String responseText;
    private int defenseChange;
    private int treasuryChange;
    private int loyaltyChange;

    public Answer(String responseText, int defenseChange, int treasuryChange, int loyaltyChange) {
        this.responseText = responseText;
        this.defenseChange = defenseChange;
        this.treasuryChange = treasuryChange;
        this.loyaltyChange = loyaltyChange;
    }

    public String getResponseText() { return responseText; }
    public int getDefenseChange() { return defenseChange; }
    public int getTreasuryChange() { return treasuryChange; }
    public int getLoyaltyChange() { return loyaltyChange; }
}