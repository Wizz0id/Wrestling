package com.example.Wrestling.enumurate;

public enum MatchType {
    Single("Один на Один", "Стандартный матч, в котором участвуют два рестлера. " +
            "Побеждает тот, кто удерживает соперника до отсчета рефери или заставляет его сдаться."),
    TagTeam("Командный", "Матч между двумя командами, каждая из которых состоит из двух или более рестлеров. " +
            "Побеждает команда, один из членов которой удерживает соперника или заставляет его сдаться."),
    TripleThreat("Тройная Угроза", "Матч с участием трех рестлеров, где побеждает тот, " +
            "кто удерживает или заставляет сдаться любого из двух других участников."),
    Handicap("Командный матч с преимуществом", "Матч, в котором одна команда имеет численное преимущество над другой."),
    FatalFourWay("Четырёхсторонний", "Матч с участием четырех рестлеров, " +
            "где побеждает тот, кто удерживает или заставляет сдаться любого из трех других участников."),
    Submission("Подчинение", "Матч, в котором победа достигается только путем болевого или удушающего приемов. " +
            "Удержания не учитываются."),
    RoyalRumble("Королевская битва", "Матч, в котором участвуют более 20 рестлеров. Они выходят на ринг через равные промежутки времени. " +
            "Побеждает тот, кто остается последним на ринге, выбросив всех остальных через верхний канат."),
    HellInCell("Ад в Клетке", "Матч, проходящий внутри большой стальной клетки, окружающей ринг " +
            "и зону вокруг него. Победа достигается удержанием или подчинением."),
    IronMan("Железный человек", "Матч с ограниченным временем, в котором рестлеры соревнуются за наибольшее количество " +
            "удержаний или подчинений в течение заданного времени. Побеждает тот, кто набрал больше очков."),
    SteelCage("Стальная клетка", "Матч внутри стальной клетки, окружающей ринг. " +
            "Победа достигается удержанием, подчинением или выходом из клетки."),
    Coffin("С гробом", "Матч, в котором цель — положить соперника в гроб и закрыть крышку."),
    EliminationChamber("Камера уничтожения", "Матч, проходящий внутри большой стальной конструкции, окружающей ринг. " +
            "Участвуют шесть рестлеров, двое начинают матч, " +
            "а остальные выходят через равные промежутки времени. Побеждает тот, кто остается последним после устранения всех остальных."),
    IQuit("Я сдаюсь", "Матч, в котором победа достигается, заставив соперника произнести слова \"Я сдаюсь\" в микрофон."),
    MoneyInTheBank("Деньги в банке", "Лестничный матч, в котором несколько рестлеров соревнуются за право получить кейс, " +
            "висящий над рингом. В кейсе находится контракт на чемпионский матч в любое время в течение года."),
    SurvivorSeries("Серия на выживание", "Командный матч на выбывание, в котором участвуют две команды по четыре или пять рестлеров. " +
            "Побеждает команда, в которой остается хотя бы один участник"),
    WarGames("Военные игры", "Матч, в котором две команды соревнуются внутри двух соединенных рингов, окруженных стальной клеткой. " +
            "Побеждает команда, которая заставляет всех членов противоположной команды сдаться или быть удержанными."),
    TLC("Столы, лестницы и стулья", "Матч, в котором разрешено использование столов, лестниц и стульев. " +
            "Победа достигается удержанием или подчинением."),
    ExtremeRules("Экстремальные правила", "Матч без дисквалификаций, в котором разрешены любые приемы и использование оружия. " +
            "Победа достигается удержанием или подчинением."),
    LastManStanding("Последний выживший", "Матч, в котором побеждает тот, кто может подняться на ноги до окончания отсчета рефери до десяти," +
            " в то время как соперник не может.");

    private final String name;
    private final String description;

    MatchType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
