package baseball.domain;

import java.util.List;

import static baseball.domain.CallResult.*;

public class Referee {

    private static final String SPACE_CHARACTER = " ";
    private static final int INIT_COUNT = 0;
    private static final int OUT_COUNT = 3;

    private int ballCount = INIT_COUNT;
    private int strikeCount = INIT_COUNT;
    private List<Integer> computerDigits;

    public void keepComputerDigits(List<Integer> computerDigits) {
        this.computerDigits = computerDigits;
    }

    public boolean judge(List<Integer> userDigits) {
        resetCounts();
        compareDigits(userDigits);

        return isOut();
    }

    public String getCallMessage() {
        String callMessage = generateCallMessage();
        if (callMessage.isEmpty()) {
            callMessage = NOTHING_CALL.getDescription();
        }
        return callMessage;
    }

    private void resetCounts() {
        ballCount = INIT_COUNT;
        strikeCount = INIT_COUNT;
    }

    private void compareDigits(List<Integer> userDigits) {
        for (int index = 0; index < computerDigits.size(); index++) {
            countBallOrStrike(userDigits, index);
        }
    }

    private void countBallOrStrike(List<Integer> userDigits, int index) {
        Integer userDigit = userDigits.get(index);
        Integer computerDigit = computerDigits.get(index);
        if (userDigit.equals(computerDigit)) {
            strikeCount++;
        } else if (computerDigits.contains(userDigit)) {
            ballCount++;
        }
    }

    private boolean isOut() {
        return strikeCount == OUT_COUNT;
    }

    private String generateCallMessage() {
        StringBuilder call = new StringBuilder();
        if (ballCount != INIT_COUNT) {
            call.append(ballCount)
                    .append(BALL_CALL.getDescription())
                    .append(SPACE_CHARACTER);
        }

        if (strikeCount != INIT_COUNT) {
            call.append(strikeCount)
                    .append(STRIKE_CALL.getDescription());
        }

        return call.toString().trim();
    }
}
