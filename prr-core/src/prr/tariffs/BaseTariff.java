package prr.tariffs;

public class BaseTariff extends Tariff {
    public BaseTariff() {
        super(10, 10, 0, // smallText
                16, 10, 4, // mediumCost
                    2, 2, 4, // largeText
                        20, 10, 10, // voiceCost
                            30, 20, 10); // videoCost
    }
}