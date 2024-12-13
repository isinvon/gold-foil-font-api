package com.sinvon.goldfoilfontapi.strategy.context;

/**
 * 上下文对象
 *
 * @author : sinvon
 * @since :  2024/12/13 下午2:32
 */
public class GoldFoilGenerationContext {
    /**
     * 文本
     */
    private String text;
    /**
     * 渐变方向
     */
    private String gradientPos;
    /**
     * 字体颜色类型
     */
    private String fontColorType;
    /**
     * 是否有背景
     */
    private Boolean isBackground;

    public GoldFoilGenerationContext(String text, String gradientPos, String fontColorType, Boolean isBackground) {
        this.text = text;
        this.gradientPos = gradientPos;
        this.fontColorType = fontColorType;
        this.isBackground = isBackground;
    }

    public String getText() {
        return text;
    }

    public String getGradientPos() {
        return gradientPos;
    }

    public String getFontColorType() {
        return fontColorType;
    }

    public Boolean getIsBackground() {
        return isBackground;
    }
}
