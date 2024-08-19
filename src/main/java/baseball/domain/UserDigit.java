package baseball.domain;

import java.util.List;

public class UserDigit {

    private final String gameId;
    private final int tryCount;
    private final int firstNumber;
    private final int secondNumber;
    private final int thirdNumber;

    public UserDigit(String gameId, int tryCount, List<Integer> digits) {
        this.gameId = gameId;
        this.tryCount = tryCount;
        this.firstNumber = digits.get(0);
        this.secondNumber = digits.get(1);
        this.thirdNumber = digits.get(2);
    }

    public String getGameId() {
        return gameId;
    }

    public int getTryCount() {
        return tryCount;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public int getThirdNumber() {
        return thirdNumber;
    }
}


