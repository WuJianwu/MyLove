package com.wuijanwu.love.mylove.test;

/**
 * 花园类Garden
 * Created by ex-wujianwu on 2017/9/6.
 */

public class Garden {
    public Bloom createRandomBloom(int x, int y) {
        //创建一个随机的花朵半径
        int radius = MyUtil.randomInt(Options.minBloomRadius, Options.maxBloomRadius);
        //创建一个随机的花朵颜色
        int color = MyUtil.randomrgba(Options.minRedColor, Options.maxRedColor, Options.minGreenColor, Options.maxGreenColor, Options.minBlueColor, Options.maxBlueColor, Options.opacity);
        //创建随机的花朵中花瓣个数
        int petalCount = MyUtil.randomInt(Options.minPetalCount, Options.maxPetalCount);
        return createBloom(x, y, radius, color, petalCount);
    }

    //创建花朵
    public Bloom createBloom(int x, int y, int radius, int color, int petalCount) {
        return new Bloom(new Point(x, y), radius, color, petalCount);
    }

    static class Options {
        //用于控制产生随机花瓣个数范围
        static int minPetalCount = 8;
        static int maxPetalCount = 15;
        //用于控制产生延长线倍数范围
        static float minPetalStretch = 2f;
        static float maxPetalStretch = 3.5f;
        //用于控制产生随机增长因子范围,增长因子决定花瓣绽放速度
        static float minGrowFactor = 1f;
        static float maxGrowFactor = 1.1f;
        //用于控制产生花朵半径随机数范围
        static int minBloomRadius = 8;
        static int maxBloomRadius = 10;
        //用于产生随机颜色
        static int minRedColor = 128;
        static int maxRedColor = 255;
        static int minGreenColor = 0;
        static int maxGreenColor = 128;
        static int minBlueColor = 0;
        static int maxBlueColor = 128;
        //花瓣的透明度
        static int opacity = 50;//0.1
    }

}
