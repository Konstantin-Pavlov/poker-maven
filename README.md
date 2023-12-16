# Poker game

## Application simulating poker game (maven project)

### The project structure

<pre>
org
└── example
    ├── cards
    │   └── Card.java
    ├── deck
    │   └── Deck.java
    ├── enums
    │   ├── Rank.java
    │   ├── Suit.java
    │   └── WinningCombinations.java
    ├── hand
    │   └── Hand.java
    ├── helper
    │   └── Helper.java
    └── main
        └── Main.java

test
└── org
    └── example
        └── test
            └── WinningCombinationTest.java
</pre>

В этой схеме:

* Каждый пакет размещен в соответствующей директории.
* Классы распределены в соответствующих пакетах.
* Тесты находятся в пакете test, который отражает иерархию пакетов основного кода (org.example).
* Использованы отступы для обозначения иерархии пакетов и классов.
