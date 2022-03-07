package domain;

import utils.Validator;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final String LOTTO_NUMBER_RANGE_MESSAGE =
            "로또 번호가 유효한 범위(" + LOTTO_NUMBER_MIN + "-" + LOTTO_NUMBER_MAX + ")가 아닙니다.";

    private final int number;

    private LottoNumber(final int number) {
        this.number = number;
    }

    public static LottoNumber getLottoNumber(final String inputNumber) {
        validateInputLottoNumber(inputNumber);
        int number = Integer.parseInt(inputNumber);
        validateLottoNumberRange(number);
        return new LottoNumber(number);
    }

    public static LottoNumber getNumber(final int number) {
        return new LottoNumber(number);
    }

    private static void validateInputLottoNumber(final String inputNumber) {
        Validator.checkNullOrEmpty(inputNumber);
        Validator.checkFormat(inputNumber);
    }

    private static void validateLottoNumberRange(final int number) {
        if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_MESSAGE);
        }
    }

    @Override
    public int compareTo(final LottoNumber otherLottoNumber) {
        return this.number - otherLottoNumber.number;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final LottoNumber that = (LottoNumber) object;

        return number == that.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
