import { ref, watch, onUnmounted, computed } from "vue";

// 自定义 hook 用于图片和 SVG 预览处理
export function useImagePreview(props) {
    const imageBlobUrl = ref(null); // 存储图片 Blob URL
    const showImage = ref(false);   // 控制图片显示
    const showSvg = ref(false);     // 控制 SVG 显示
    const svgContent = ref('');     // 存储 SVG 内容

    let prevImageUrl = null;        // 上一次的图片 URL
    let prevSvgContent = '';        // 上一次的 SVG 内容

    // 监听 imageUrl 数据变化，生成临时链接并显示图片
    watch(
        () => props.imageUrl,
        (newValue) => {
            if (newValue instanceof Blob) {
                if (newValue !== prevImageUrl) {
                    // 当图片链接更新时，生成新的链接
                    imageBlobUrl.value = URL.createObjectURL(newValue);
                    showImage.value = true;  // 显示图片
                    showSvg.value = false;   // 隐藏 SVG
                    prevImageUrl = newValue; // 更新为当前图片链接
                }
            } else {
                showImage.value = false; // 如果 imageUrl 不是 Blob 类型，则隐藏图片
            }
        },
        { immediate: true } // 立即执行，初始化时也触发监听器
    );

    // 监听 svgContent 数据变化，显示 SVG
    watch(
        () => props.svgContent,
        (newValue) => {
            if (newValue !== prevSvgContent) {
                // 当 SVG 内容更新时，更新显示的 SVG
                svgContent.value = newValue;
                showSvg.value = true;  // 显示 SVG
                showImage.value = false; // 隐藏图片
                prevSvgContent = newValue; // 更新为当前 SVG 内容
            } else {
                showSvg.value = false; // 如果 SVG 内容没有变化，则隐藏 SVG
            }
        },
        { immediate: true } // 立即执行，初始化时也触发监听器
    );

    // 将 SVG 内容转换为 Data URL
    const svgImageUrl = computed(() => {
        if (svgContent.value) {
            const encodedSvg = encodeURIComponent(svgContent.value);
            return `data:image/svg+xml;charset=utf-8,${encodedSvg}`;
        }
        return '';
    });

    // 下载图片
    const downloadImage = () => {
        const link = document.createElement('a');
        link.href = imageBlobUrl.value;
        link.download = 'gold-foil-image.png'; // 设置下载的文件名
        document.body.appendChild(link);
        link.click(); // 触发点击事件进行下载
        document.body.removeChild(link); // 下载后移除链接元素
    };

    // 下载 SVG
    const downloadSVG = () => {
        const blob = new Blob([props.svgContent], { type: 'image/svg+xml' });
        const url = URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = 'gold-foil.svg'; // 设置下载的文件名
        document.body.appendChild(link);
        link.click(); // 触发点击事件进行下载
        document.body.removeChild(link); // 下载后移除链接元素
        URL.revokeObjectURL(url); // 释放 URL
    };

    // 组件销毁时释放 Blob URL
    onUnmounted(() => {
        if (imageBlobUrl.value) {
            URL.revokeObjectURL(imageBlobUrl.value); // 释放临时链接
        }
    });

    return {
        imageBlobUrl,
        showImage,
        showSvg,
        svgContent,
        svgImageUrl,
        downloadImage,
        downloadSVG
    };
}
