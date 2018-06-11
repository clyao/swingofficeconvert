# swingofficeconvert
swingofficeconvert是一款将word文档和PDF文档直接转换为图片的工具

## 代码托管同步更新

GitHub.com

* 代码托管：[https://github.com/clyao/swingofficeconvert](https://github.com/clyao/swingofficeconvert)
* 项目文档：请直接阅读源码

## Latest Release

V1.0 更新说明

发布于：2018-06-08 15:00
1、实现了整个软件代码编写及测试
2、上传到了github上

## Dwonload

正式版：

* jar包 [点击下载](https://github.com/clyao/swingofficeconvert/blob/master/dist/swingofficeconvert.jar) 
* zip包 由于网络github网络问题，上传大文件卡死，因此使用exe4j打包成了exe应用程序，并内置了jre1.8的zip包请私聊获取）

## Development Guide

```
public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
					BeautyEyeLNFHelper.launchBeautyEyeLNF();
					UIManager.put("RootPane.setupButtonVisible", false);
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
```

## Preview

效果：![效果图片](https://github.com/clyao/swingofficeconvert/blob/master/dist/screenshots/1.png)

## 特别致谢

感谢JackJiang提供的beautyeye皮肤




