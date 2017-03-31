
# 利用Goolge Tag Manager 管理首个Google Analytics 和facebook pixel
最近由于工作需要，研究了一下用GTM管理GA和facebook pixel     
GTM：GTM是谷歌提供的免费的标签管理工具，通过这个在线工具，你可以在你的站点或APP上管理和发布不同的营销和分析代码。    
GA：数据统计服务。可以对目标网站进行访问数据统计和分析，并提供多种参数供网站拥有者使用。如对您的 Flash、视频以及社交网络网站和应用进行跟踪。    
facebook pixel：Facebook在2013年推广的广告的转化追踪    

## 1.创建GTM容器
首先登陆GTM  https://tagmanager.google.com 创建一个容器后可看到容器版面如下图。    

![Image text](https://github.com/miozeng/daily/blob/master/GTM/containner.png)   
选择admin-->Install GTM  获取GTM code 复制到你的页面中

## 2.利用GTM添加GA     
登陆GA创建web sit data   获取GA code 如：UA-96251985-1      
在GTM 中新增tag，如下图所示：
![Image text](https://github.com/miozeng/daily/blob/master/GTM/GA.png)

## 3.利用GTM添加GA event       
添加自定义Variables 如下图     
![Image text](https://github.com/miozeng/daily/blob/master/GTM/varib.png)     
添加自定义tag     
![Image text](https://github.com/miozeng/daily/blob/master/GTM/evnt1.png)   
![Image text](https://github.com/miozeng/daily/blob/master/GTM/event2.png)  
或者   
![Image text](https://github.com/miozeng/daily/blob/master/GTM/event2-1.png)     
添加对应的trigger  
![Image text](https://github.com/miozeng/daily/blob/master/GTM/trigger.png)     
页面处理   
```JavaScript·
   	 dataLayer.push({
				'event':'clickevent',
				'eventCategory':'appointment',
				'eventAction':'submit',
				'eventValue':appointmentType,
				'eventLabel':'appointmentType'
			});  
```
      
## 4.利用GTM 添加facebook pixel
https://www.facebook.com/ads/manager/pixel 点击 set up pixel有详细的介绍就不说了
     
     
## 5.利用GTM添加facebook pixel event
https://www.facebook.com/ads/manager/pixel 点击 set up pixel有详细的介绍就不说了
