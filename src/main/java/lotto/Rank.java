package lotto;

public enum Rank {
    FIRST("6개 일치", 2_000_000_000),
    SECOND("5개 일치, 보너스 볼 일치", 30_000_000),
    THIRD("5개 일치", 1_500_000),
    FOURTH("4개 일치", 50_000),
    FIFTH("3개 일치", 5_000),
    MISS("미당첨", 0);

    private final String description;
    private final int prize;

    Rank(String description, int prize) {
        this.description = description;
        this.prize = prize;
    }

    public String getDescription() { return description; }
    public int getPrize() { return prize; }
}
