package racingcar.controller;

import java.util.List;
import racingcar.view.InputView;

public class RacingController {
    private final InputView inputView;
    private final StringSplitter stringSplitter;
    private final NumberParser numberParser;

    public RacingController(
            InputView inputView,
            StringSplitter stringSplitter,
            NumberParser numberParser
    ) {
        this.inputView = inputView;
        this.stringSplitter = stringSplitter;
        this.numberParser = numberParser;
    }

    public void run() {
        String userInput = inputView.getCarNames();
        List<String> splitNames = stringSplitter.split(userInput);

        userInput = inputView.getTotalRace();
        Integer parsedNumber = numberParser.parse(userInput);

    }
}