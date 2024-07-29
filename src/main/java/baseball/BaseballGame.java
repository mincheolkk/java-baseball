package baseball;

import baseball.domain.Computer;
import baseball.presentation.GameManager;
import baseball.domain.Referee;
import baseball.presentation.GameStatus;
import baseball.presentation.MessagePrinter;
import baseball.presentation.input.Input;
import baseball.presentation.input.InputProvider;

import java.util.List;
import java.util.Scanner;

public class BaseballGame {

    public static void launch() {
        final Computer computer = new Computer();
        final Referee referee = new Referee();
        final InputProvider inputProvider = new Input(new Scanner(System.in));
        final GameManager gameManager = new GameManager(new MessagePrinter(), inputProvider);

        boolean isApplicationOver = false;
        while (!isApplicationOver) {
            GameStatus gameStatus = gameManager.init();

            if (gameStatus.equals(GameStatus.START)) {
                List<Integer> computerDigits = gameManager.getComputerDigits(computer);
                referee.keepComputerDigits(computerDigits);
            } else if (gameStatus.equals(GameStatus.QUIT)) {
                gameManager.applicationOver();
            }

            boolean isOut = false;
            while (!isOut) {
                List<Integer> userDigits = gameManager.getUserDigits();
                isOut = referee.judge(userDigits);
                String callMessage = referee.getCallMessage();
                gameManager.call(callMessage);
            }

            gameManager.gameOver();
        }
    }
}
