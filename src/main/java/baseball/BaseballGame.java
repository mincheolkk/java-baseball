package baseball;

import static java.util.UUID.randomUUID;

import baseball.domain.Computer;
import baseball.domain.ComputerDigit;
import baseball.domain.UserDigit;
import baseball.presentation.GameManager;
import baseball.domain.Referee;
import baseball.presentation.GameStatus;
import baseball.presentation.MessagePrinter;
import baseball.presentation.RandomNumberGenerator;
import baseball.presentation.input.Input;
import baseball.presentation.input.InputProvider;

import java.util.List;
import java.util.Scanner;

public class BaseballGame {

    public static void launch() throws IllegalAccessException {
        final Computer computer = new Computer(new RandomNumberGenerator());
        final Referee referee = new Referee();
        final InputProvider inputProvider = new Input(new Scanner(System.in));
        final GameManager gameManager = new GameManager(new MessagePrinter(), inputProvider);

        boolean isApplicationOver = false;
        while (!isApplicationOver) {
            GameStatus gameStatus = gameManager.init();
            String gameId = randomUUID().toString();
            ComputerDigit computerDigit = null;

            if (gameStatus.equals(GameStatus.START)) {
                List<Integer> computerDigits = gameManager.getComputerDigits(computer);
                computerDigit = new ComputerDigit(gameId, computerDigits);

            } else if (gameStatus.equals(GameStatus.QUIT)) {
                gameManager.applicationOver();
            }

            boolean isOut = false;
            int tryCount = 0;
            while (!isOut) {
                tryCount++;
                List<Integer> userDigits = gameManager.getUserDigits();
                UserDigit userDigit = new UserDigit(gameId, tryCount, userDigits);

                isOut = referee.judge(computerDigit, userDigit);
                String callMessage = referee.getCallMessage();
                gameManager.call(callMessage);
            }

            gameManager.gameOver();
        }
    }
}
