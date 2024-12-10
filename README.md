<img src="./image/gold-foil-font-api title.png" width="800" />

[//]: # (<img src="./image/gold-foil-font-api.png" width="800" />)


<img src="./image/gold-foil-font-api description chinese.png" width="800" />
<img src="./image/gold-foil-font-api description english.png" width="800" />

<div style="display: flex; justify-content: center; align-items: center;gap: 20px;margin: 20px">
<a href="https://github.com/isinvon/gold-foil-font-api/blob/main/README.md">
<img src="./image/chinese readme.png" alt="README.md"/>
</a>

<a href="https://github.com/isinvon/gold-foil-font-api/blob/main/README_en.md">
<img src="image/english readme.png" alt="README_en.md">
</a>
</div>

## 🚀 测试环境 ➽

- Java v21
- Maven v3.9.8

## ✨ 修改字体颜色 ➽

### 🟡 金色 ➽

修改参数 `text` 为想要的字即可，无需加其他参数，例如:

http://localhost:8080/api/gold-foil-image?text=新年快乐


<img src="./image/happy new year gold.png" width="800" />

### ⚪ 银色 ➽

修改参数 `text` 为想要的字，且需要将参数 `fontColorType` 设置为 `silver`，例如:

http://localhost:8080/api/gold-foil-image?text=新年快乐&fontColorType=silver


<img src="./image/happy new year silver.png" width="800" />

### ⚫ 黑色 ➽

修改参数 `text` 为想要的字，且需要将参数 `fontColorType` 设置为 `black`，例如:

http://localhost:8080/api/gold-foil-image?text=新年快乐&fontColorType=black


<img src="./image/happy new year black.png" width="800" />

### 🖤 黑色渐变 ➽

修改参数 `text` 为想要的字，且需要将参数 `fontColorType` 设置为 `blackGradient`，例如:

http://localhost:8080/api/gold-foil-image?text=新年快乐&fontColorType=blackGradient


<img src="./image/happy new year blackGradient.png" width="800" />

## ✏️ 修改字体内容 ➽

修改参数 `text` 为想要的字即可，无需加其他参数，例如:

http://localhost:8080/api/gold-foil-image?text=66大顺

<img src="./image/66大顺.png" width="800" />

## 🏮 春联背景 ➽

添加参数 `background` 为 `true`，例如:

http://localhost:8080/api/gold-foil-image?text=鸡你太美&background=true

每一次请求都是随机的，所以每次请求的背景颜色都是不一样的，例如:

<img src="./image/ikun-1.png" width="800" />

<img src="./image/ikun-2.png" width="800" />

<img src="./image/ikun-3.png" width="800" />

<img src="./image/ikun-4.png" width="800" />

## ☀️ 渐变方向 ➽

### `leftToRight` - 从左到右

添加参数 `gradientPos` 为 `leftToRight`，例如:

http://localhost:8080/api/gold-foil-image?text=鸡你太美&gradientPos=leftToRight

### `topToBottom` - 从上到下

添加参数 `gradientPos` 为 `topToBottom`，例如:

http://localhost:8080/api/gold-foil-image?text=光泽效果&gradientPos=topToBottom

### `leftTopToRightBottom` - 从左上到右下

添加参数 `gradientPos` 为 `leftTopToRightBottom`，例如:

http://localhost:8080/api/gold-foil-image?text=从左上到右下&gradientPos=leftTopToRightBottom

### `leftBottomToRightTop` - 从左下到右上

添加参数 `gradientPos` 为 `leftBottomToRightTop`，例如:

http://localhost:8080/api/gold-foil-image?text=从左下到右上&gradientPos=leftBottomToRightTop

### `rightToLeft` - 从右到左

添加参数 `gradientPos` 为 `rightToLeft`，例如:

http://localhost:8080/api/gold-foil-image?text=从右到左&gradientPos=rightToLeft

### `bottomToTop` - 从下到上

添加参数 `gradientPos` 为 `bottomToTop`，例如:

http://localhost:8080/api/gold-foil-image?text=从下到上&gradientPos=bottomToTop

### `rightTopToLeftBottom` - 从右上到左下

添加参数 `gradientPos` 为 `rightTopToLeftBottom`，例如:

http://localhost:8080/api/gold-foil-image?text=从右上到左下&gradientPos=rightTopToLeftBottom

### `rightBottomToLeftTop` - 从右下到左上

添加参数 `gradientPos` 为 `rightBottomToLeftTop`，例如:

http://localhost:8080/api/gold-foil-image?text=从右下到左上&gradientPos=rightBottomToLeftTop

### `circular` - 圆形

添加参数 `gradientPos` 为 `circular`，例如:

http://localhost:8080/api/gold-foil-image?text=圆形渐变&gradientPos=circular

### `circularRandom` - 圆形随机

添加参数 `gradientPos` 为 `circularRandom`，例如:

http://localhost:8080/api/gold-foil-image?text=圆形随机&gradientPos=circularRandom

### `random` - 随机 (如果不加 gradientPos 参数，默认就是这个随机的)

添加参数 `gradientPos` 为 `random`，例如:

http://localhost:8080/api/gold-foil-image?text=随机渐变&gradientPos=random

## 🌟 SVG 生成

请使用以下接口

http://localhost:8080/api/gold-foil-svg?text=你好

请求之后出现如下页面, 直接复制即可

<img src="./image/svg.png" width="800" />

