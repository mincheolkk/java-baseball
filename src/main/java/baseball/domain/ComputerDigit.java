package baseball.domain;

import java.util.List;

public class ComputerDigit {

    private final String gameId;
    private final int firstNumber;
    private final int secondNumber;
    private final int thirdNumber;

    public ComputerDigit(String gameId, List<Integer> digits) {
        this.gameId = gameId;
        this.firstNumber = digits.get(0);
        this.secondNumber = digits.get(1);
        this.thirdNumber = digits.get(2);
    }

    public String getGameId() {
        return gameId;
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
