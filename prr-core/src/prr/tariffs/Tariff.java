package prr.tariffs;

import java.io.Serializable;

public abstract class Tariff implements Serializable {
    private double _smallTextCostNormal;
    private double _smallTextCostGold;
    private double _smallTextCostPlatinum;

    private double _mediumTextCostNormal;
    private double _mediumTextCostGold;
    private double _mediumTextCostPlatinum;

    private double _largeTextCostNormal;
    private double _largeTextCostGold;
    private double _largeTextCostPlatinum;

    private double _voiceCostNormal;
    private double _voiceCostGold;
    private double _voiceCostPlatinum;

    private double _videoCostNormal;
    private double _videoCostGold;
    private double _videoCostPlatinum;

    public Tariff(double smallTextCostNormal, double smallTextCostGold, double smallTextCostPlatinum,
            double mediumTextCostNormal, double mediumTextCostGold, double mediumTextCostPlatinum,
            double largeTextCostNormal, double largeTextCostGold, double largeTextCostPlatinum,
            double voiceCostNormal, double voiceCostGold, double voiceCostPlatinum,
            double videoCostNormal, double videoCostGold, double videoCostPlatinum) {
                
        _smallTextCostNormal = smallTextCostNormal;
        _smallTextCostGold = smallTextCostGold;
        _smallTextCostPlatinum = smallTextCostPlatinum;

        _mediumTextCostNormal = mediumTextCostNormal;
        _mediumTextCostGold = mediumTextCostGold;
        _mediumTextCostPlatinum = mediumTextCostPlatinum;

        _largeTextCostNormal = largeTextCostNormal;
        _largeTextCostGold = largeTextCostGold;
        _largeTextCostPlatinum = largeTextCostPlatinum;

        _voiceCostNormal = voiceCostNormal;
        _voiceCostGold = voiceCostGold;
        _voiceCostPlatinum = voiceCostPlatinum;

        _videoCostNormal = videoCostNormal;
        _videoCostGold = videoCostGold;
        _videoCostPlatinum = videoCostPlatinum;
    }
    
    public double getTextCostNormal(int n) {
        if (n < 50)
            return _smallTextCostNormal;

        if (50 <= n < 100) 
            return _mediumTextCostNormal;

        if (n >= 100)
            return _largeTextCostNormal * n;
    }
    
    public double getTextCostGold(int n) {
        if (n < 50)
            return _smallTextCostGold;

        if (50 <= n < 100) 
            return _mediumTextCostGold;

        if (n >= 100)
            return _largeTextCostGold * n;
    }

    public double getTextCostPlatinum(int n) {
        if (n < 50)
            return _smallTextCostPlatinum;

        if (50 <= n < 100) 
            return _mediumTextCostPlatinum;

        if (n >= 100)
            return _largeTextCostPlatinum;
    }

    public double getVoiceCostNormal(int duration) {
        return _voiceCostNormal * duration;
    }

    public double getVoiceCostGold(int duration) {
        return _voiceCostGold * duration;
    }

    public double getVoiceCostPlatinum(int duration) {
        return _voiceCostPlatinum * duration;
    }

    public double getVideoCostNormal(int duration) {
        return _videoCostNormal * duration;
    }

    public double getVideoCostGold(int duration) {
        return _videoCostGold * duration;
    }

    public double getVideoCostPlatinum(int duration) {
        return _videoCostPlatinum * duration;
    }
}