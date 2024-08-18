package baseball.domain;

import baseball.presentation.NumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class Computer {

    private static final int COMPUTER_DIGITS_SIZE = 3;
    private final NumberGenerator randomNumberGenerator;


    public Computer(NumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public List<Integer> generateRandomDigits() {
        List<Integer> digits = new ArrayList<>();

        while (digits.size() < COMPUTER_DIGITS_SIZE) {
            addToDigits(digits, randomNumberGenerator.generate());
        }

        return digits;
    }

    private void addToDigits(List<Integer> digits, int randomNumber) {
        if (!digits.contains(randomNumber)) {
            digits.add(randomNumber);
        }
    }
}
