package domain;

public class LottoNumber {
    private final int number;

    public LottoNumber(final String inputNumber) {
        validateLottoNumberFormat(inputNumber);
        this.number = Integer.parseInt(inputNumber);
        validateLottoNumberRange(this.number);
    }

    public LottoNumber(final int inputNumber) {
        this.number = inputNumber;
    }


    private void validateLottoNumberFormat(final String number) {
        if (!number.matches("^[1-9]([0-9]*)$")) {
            throw new IllegalArgumentException("로또 번호가 숫자의 형태가 아닙니다.");
        }
    }

    private void validateLottoNumberRange(final int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호가 유효한 범위(1-45)가 아닙니다.");
        }
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
}

