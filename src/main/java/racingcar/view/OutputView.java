package racingcar.view;

import java.util.List;
import racingcar.dto.CarsPositionDto;

public class OutputView {
    private static final String RESULT_MESSAGE = "\n실행 결과:";
    private static final String POSITION_MARKER = "-";
    private static final String NAME_POSITION_SEPARATOR = " : ";
    private static final String NEW_LINE = "\n";

    public void printRoundResults(List<CarsPositionDto> carsPositionDtoList) {
        StringBuilder fullResult = new StringBuilder(RESULT_MESSAGE)
                .append(NEW_LINE);

        carsPositionDtoList.forEach(roundResult ->
                appendRoundResult(fullResult, roundResult));

        System.out.print(fullResult);
    }

    private void appendRoundResult(StringBuilder fullResult, CarsPositionDto roundResult) {
        roundResult.carPositions().forEach(carPosition ->
                fullResult.append(carPosition.name())
                        .append(NAME_POSITION_SEPARATOR)
                        .append(POSITION_MARKER.repeat(carPosition.position()))
                        .append(NEW_LINE)
        );

        fullResult.append(NEW_LINE);
    }
}