# 🚗 미션 - 자동차 경주

사용자가 입력한 자동차들로 **간단한 경주 게임을 진행**하는 프로그램을 구현한다.  
자동차들은 무작위로 전진하며, 가장 멀리 이동한 자동차가 우승한다.

## 🎯 사용자 Usecase

1. 사용자는 경주에 참여할 자동차들의 이름을 입력한다.
2. 사용자는 경주를 진행할 횟수를 입력한다.
3. 사용자는 각 라운드마다 자동차들의 이동 상황을 확인한다.
4. 사용자는 경주가 끝난 후 우승자를 확인한다.
5. 사용자는 잘못된 입력을 했을 때 예외를 받는다.

## ✨ 기능 구현 목록

```mermaid
%%{init: {'theme': 'base', 'themeVariables': { 'fontSize': '14px' }, 'flowchart': { 'diagramPadding': 20 } } }%%
flowchart LR
    user(["🧑‍💻 사용자"])

    subgraph 자동차 경주 게임
        subgraph 게임초기화
            A1((**자동차 이름 입력**))
            A2((**이름 검증**))
            A3((**이동 횟수 입력**))
            A4((**횟수 검증**))
        end

        subgraph 게임진행
            B1((**라운드 진행**))
            B2((**자동차 이동**))
            B3((**상태 출력**))
        end

        subgraph 게임종료
            C1((**우승자 선정**))
            C2((**결과 출력**))
        end
    end

    error[**예외 발생**]

    user --> A1
    A1 --> A2
    A2 --> A3
    A3 --> A4
    A4 --> B1
    B1 --> B2
    B2 --> B3
    B3 -->|"라운드 남음"| B1
    B3 -->|"라운드 종료"| C1
    C1 --> C2
    C2 --> user

    A2 -.->|"검증 실패"| error
    A4 -.->|"검증 실패"| error

    classDef user fill:#e8f5e9,stroke:#2e7d32,stroke-width:2px,rx:8px
    class user user
```

### 1. 게임 초기화

- 자동차 이름 입력 받기
    - 안내 문구 출력: `경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)`
    - 사용자 입력값 검증
        - 이름은 쉼표(,)로 구분하여 분리
        - 각 이름은 trim() 처리
        - 구분된 이름들에 대한 유효성 검사
          - 구분된 이름이 하나 이상인지 확인 (이름이 하나인 경우에도 경주 수행)
        - 각 이름에 대한 유효성 검사
            - 이름이 비어있지 않은지 확인
            - 이름이 5자 이하인지 확인
            - 이름이 중복되는 경우는 별도 처리하지 않음 (입력 순서로 판별 가능)
        - 검증 실패 시 `IllegalArgumentException` 발생
    - 입출력 예시:
        ```
        경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)
        pobi,woni,jun
        ```

- 이동 횟수 입력 받기
    - 안내 문구 출력: `시도할 횟수는 몇 회인가요?`
    - 사용자 입력값 검증
        - 입력값이 숫자인지 확인
        - 입력값이 0 이상인 정수인지 확인
            - 입력값이 0인 경우에도 가능 (모든 참가자가 우승)
        - 검증 실패 시 `IllegalArgumentException` 발생
    - 입출력 예시:
        ```
        시도할 횟수는 몇 회인가요?
        5
        ```

### 2. 게임 진행

- 라운드 진행
    - 실행 결과 헤더 출력: `실행 결과`
    - 각 자동차별 이동 처리
        - 0-9 사이의 무작위 값 생성
        - 값이 4 이상인 경우 전진
        - 값이 3 이하인 경우 멈춤
    - 각 자동차의 현재 상태 출력
        - 형식: `[자동차이름] : [이동거리(-)]`
        - 예시: `pobi : --`
    - 출력 예시:
        ```
        실행 결과
        pobi : -
        woni : 
        jun : -

        pobi : --
        woni : -
        jun : --

        pobi : ---
        woni : --
        jun : ---
        ```

### 3. 게임 종료

- 우승자 선정
    - 가장 멀리 이동한 자동차(들) 선별
    - 우승자 출력
        - 단독 우승 시: `최종 우승자 : [이름]`
        - 공동 우승 시: `최종 우승자 : [이름1], [이름2]`
    - 출력 예시:
        ```
        최종 우승자 : pobi, jun
        ```
