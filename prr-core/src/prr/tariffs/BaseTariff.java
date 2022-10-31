package prr.tariffs;

public class BaseTariff extends Tariff {
    public BaseTariff() {
        super(10.0, 10.0, 0.0, // smallText
                16.0, 10.0, 4.0, // mediumCost
                    2.0, 2.0, 4.0, // largeText
                        20.0, 10.0, 10.0, // voiceCost
                            30.0, 20.0, 10.0); // videoCost
    }
}