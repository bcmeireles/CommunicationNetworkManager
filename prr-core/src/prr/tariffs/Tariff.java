package prr.tariffs;

import java.io.Serializable;

public abstract class Tariff implements Serializable {
    private long _smallTextCostNormal;
    private long _smallTextCostGold;
    private long _smallTextCostPlatinum;

    private long _mediumTextCostNormal;
    private long _mediumTextCostGold;
    private long _mediumTextCostPlatinum;

    private long _largeTextCostNormal;
    private long _largeTextCostGold;
    private long _largeTextCostPlatinum;

    private long _voiceCostNormal;
    private long _voiceCostGold;
    private long _voiceCostPlatinum;

    private long _videoCostNormal;
    private long _videoCostGold;
    private long _videoCostPlatinum;

    public Tariff(long smallTextCostNormal, long smallTextCostGold, long smallTextCostPlatinum,
                    long mediumTextCostNormal, long mediumTextCostGold, long mediumTextCostPlatinum,
                    long largeTextCostNormal, long largeTextCostGold, long largeTextCostPlatinum,
                    long voiceCostNormal, long voiceCostGold, long voiceCostPlatinum,
                    long videoCostNormal, long videoCostGold, long videoCostPlatinum) {
                
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
    
    public long getTextCostNormal(int duration) {
        if (duration < 50)
            return _smallTextCostNormal;

        if (50 <= duration && duration < 100) 
            return _mediumTextCostNormal;

        if (duration >= 100)
            return _largeTextCostNormal * duration;

        return 0;
    }
    
    public long getTextCostGold(int duration) {
        if (duration < 50)
            return _smallTextCostGold;

        if (50 <= duration && duration < 100) 
            return _mediumTextCostGold;

        if (duration >= 100)
            return _largeTextCostGold * duration;

        return 0;
    }

    public long getTextCostPlatinum(int duration) {
        if (duration < 50)
            return _smallTextCostPlatinum;

        if (50 <= duration && duration < 100) 
            return _mediumTextCostPlatinum;

        if (duration >= 100)
            return _largeTextCostPlatinum;
            
        return 0;
    }

    public long getVoiceCostNormal(int duration) {
        return _voiceCostNormal * duration;
    }

    public long getVoiceCostGold(int duration) {
        return _voiceCostGold * duration;
    }

    public long getVoiceCostPlatinum(int duration) {
        return _voiceCostPlatinum * duration;
    }

    public long getVideoCostNormal(int duration) {
        return _videoCostNormal * duration;
    }

    public long getVideoCostGold(int duration) {
        return _videoCostGold * duration;
    }

    public long getVideoCostPlatinum(int duration) {
        return _videoCostPlatinum * duration;
    }
}