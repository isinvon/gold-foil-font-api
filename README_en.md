<img src="./image/gold-foil-font-api title.png" width="800" />

[//]: # (<img src="./image/gold-foil-font-api.png" width="800" />)

<img src="./image/gold-foil-font-api description chinese.png" width="800" />
<img src="./image/gold-foil-font-api description english.png" width="800" />


<div style="display: flex; justify-content: center; align-items: center;gap: 10px;margin: 20px">
  <a href="https://github.com/isinvon/gold-foil-font-api/blob/main/README_zh.md">中文 README.md</a>
  |
  <a href="https://github.com/isinvon/gold-foil-font-api/blob/main/README_en.md">English README.md</a>
</div>

## ✨ Change Font Color ➽

### 🟡 Gold ➽

Simply change the `text` parameter to the desired text without any additional parameters, for example:

http://localhost:8080/api/gold-foil-image?text=新年快乐

<img src="./image/happy new year gold.png" width="800" />

### ⚪ Silver ➽

Change the `text` parameter to the desired text and set the `fontColorType` parameter to `silver`, for example:

http://localhost:8080/api/gold-foil-image?text=新年快乐&fontColorType=silver

<img src="./image/happy new year silver.png" width="800" />

### ⚫ Black ➽

Change the `text` parameter to the desired text and set the `fontColorType` parameter to `black`, for example:

http://localhost:8080/api/gold-foil-image?text=新年快乐&fontColorType=black

<img src="./image/happy new year black.png" width="800" />

### 🖤 Black Gradient ➽

Change the `text` parameter to the desired text and set the `fontColorType` parameter to `blackGradient`, for example:

http://localhost:8080/api/gold-foil-image?text=新年快乐&fontColorType=blackGradient

<img src="./image/happy new year blackGradient.png" width="800" />

## ✏️ Modify Font Content ➽

Change the `text` parameter to the desired text without any additional parameters, for example:

http://localhost:8080/api/gold-foil-image?text=66大顺

<img src="./image/66大顺.png" width="800" />

## 🏮 Couplets Background ➽

Add the `background` parameter as `true`, for example:

http://localhost:8080/api/gold-foil-image?text=鸡你太美&background=true

Each request generates a random background, so each background color will be different, for example:

<img src="./image/ikun-1.png" width="800" />

<img src="./image/ikun-2.png" width="800" />

<img src="./image/ikun-3.png" width="800" />

<img src="./image/ikun-4.png" width="800" />

## ☀️ Gradient Direction ➽

### `leftToRight` - Left to Right

Add the `gradientPos` parameter as `leftToRight`, for example:

http://localhost:8080/api/gold-foil-image?text=LeftToRight&gradientPos=leftToRight

### `topToBottom` - Top to Bottom

Add the `gradientPos` parameter as `topToBottom`, for example:

http://localhost:8080/api/gold-foil-image?text=GlossEffect&gradientPos=topToBottom

### `leftTopToRightBottom` - Left Top to Right Bottom

Add the `gradientPos` parameter as `leftTopToRightBottom`, for example:

http://localhost:8080/api/gold-foil-image?text=LeftToptoRight Bottom&gradientPos=leftTopToRightBottom

### `leftBottomToRightTop` - Left Bottom to Right Top

Add the `gradientPos` parameter as `leftBottomToRightTop`, for example:

http://localhost:8080/api/gold-foil-image?text=LeftBottomtoRightTop&gradientPos=leftBottomToRightTop

### `rightToLeft` - Right to Left

Add the `gradientPos` parameter as `rightToLeft`, for example:

http://localhost:8080/api/gold-foil-image?text=RighttoLeft&gradientPos=rightToLeft

### `bottomToTop` - Bottom to Top

Add the `gradientPos` parameter as `bottomToTop`, for example:

http://localhost:8080/api/gold-foil-image?text=BottomtoTop&gradientPos=bottomToTop

### `rightTopToLeftBottom` - Right Top to Left Bottom

Add the `gradientPos` parameter as `rightTopToLeftBottom`, for example:

http://localhost:8080/api/gold-foil-image?text=RightToptoLeft Bottom&gradientPos=rightTopToLeftBottom

### `rightBottomToLeftTop` - Right Bottom to Left Top

Add the `gradientPos` parameter as `rightBottomToLeftTop`, for example:

http://localhost:8080/api/gold-foil-image?text=RightBottomtoLeftTop&gradientPos=rightBottomToLeftTop

### `circular` - Circular

Add the `gradientPos` parameter as `circular`, for example:

http://localhost:8080/api/gold-foil-image?text=CircularGradient&gradientPos=circular

### `circularRandom` - Random Circular

Add the `gradientPos` parameter as `circularRandom`, for example:

http://localhost:8080/api/gold-foil-image?text=RandomCircular&gradientPos=circularRandom

### `random` - Random (Default)

If the `gradientPos` parameter is omitted, it defaults to a random gradient, for example:

http://localhost:8080/api/gold-foil-image?text=RandomGradient&gradientPos=random

---