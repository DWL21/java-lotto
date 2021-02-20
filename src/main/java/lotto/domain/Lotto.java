package lotto.domain;

import java.util.List;

public class Lotto {
    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateLottoNumbers(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException("로또는 6개의 번호가 있어야합니다.");
        }

        if (!lottoNumbers.stream().allMatch((i) -> i >= 1 && i <= 45)) {
            throw new IllegalArgumentException("유효하지 않은 로또 번호입니다.");
        }
    }

    public int matchingCount(WinningNumber winningNumber) {
        List<Integer> numbers = winningNumber.getWinningNumber();
        return (int) numbers.stream()
                .filter(lottoNumbers::contains)
                .count();
    }

    public boolean isBonusMatch(int bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
