# 🚀 java-lotto

# 구현할 기능 목록

## 입력

- **구입 금액을 입력받는다.** ()
    - [ 예외 ] 구입 금액은 `1000원 단위` 로 나눠져야한다.
    - [ 예외 ] 음수가 아니어야 한다.
    - [ 예외 ] 문자가 아니어야 한다.
    - [ 예외 ] 빈 문자열이 아니어야 한다.
    - [ 예외 ] 구입 금액은 `10만원을 초과할 수 없다.`

- **지난 주 당첨 번호를 입력 받는다.** ()
    - [ 예외 ] 각 숫자는 1 ~ 45 범위 내에 있어야한다.
    - [ 예외 ] 문자가 아니어야 한다.
    - [ 예외 ] 빈 문자열이 아니어야 한다.

- **보너스 볼을 입력 받는다.** ( bonus)
    - [ 예외 ] 각 숫자는 1 ~ 45 범위 내에 있어야한다.
    - [ 예외 ] 문자가 아니어야 한다.
    - [ 예외 ] 빈 문자열이 아니어야 한다.
    - [ 예외 ] 숫자 중복 불가

## 진행

- **구입 금액 / 1000원 = 로또 갯수를 계산한다.**
- **1 ~ 45 의 범위 내에서 숫자 6개를 생성하여 로또를 발행한다.**  ( List<> Lotto )
    - [ 예외 ] 숫자 중복 불가
    - [ 예외 ] 6개 인지 확인
- **로또 갯수 만큼 로또를 발행한다.**  (List<Lotto> Lottos) // Collections.shuffle() 사용
- **당첨 통계를 산출한다.**  (LottoResult) - rank Enum
-

```text
  - 3개 -> 5천원
  - 4개 -> 5만원
  - 5개 -> 15만원
  - 5개 + 보너스 볼 ->  3천만원
  - 6개 -> 20억
```

- **수익률을 계산한다**
    - (당첨금액 / 투입금액)


## 페어 프로그래밍 룰
- 객체 생성에 null 금지!
- am 10시 ~ pm 6시 사이에 -> 토의 필수 참여 ! (미션 종료까지)
- 5분 텀으로 역할 교체
- 점심시간은 1시 ~ 2시 !!
- 휴식 시간 보장 
- 동의했을 때만 적용

## 추가 된 요구사항

- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
- 함수(또는 메서드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- 배열 대신 컬렉션을 사용한다.
- Java Enum을 적용한다.
- 모든 원시 값과 문자열을 포장한다
- 줄여 쓰지 않는다(축약 금지).
- 일급 컬렉션을 쓴다.

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
