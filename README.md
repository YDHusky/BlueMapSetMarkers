# BlueMapSetMarkers
### 玩家指令
- /sm help              插件帮助
- /sm gui               打开标记点GUI
- /sm add [name] [icon] 添加标记点
- /sm addhelp           添加标记点帮助
- /sm del [name]        删除标记点
### 添加帮助
/sm add name [icon] 添加标记点  
[icon]为可选参数，应该为图片直链  
建议32x32大小的图片  
若不加[icon]则默认为命名牌  
icon默认图片链接可在config.yml修改  
也可以使用指令修改
#### 告示牌添加
第一行：[map]  
第二行：name1  
第三行：name2  
第四行：icon直链  
前三行为必要，第四行不写使用默认图标  
破坏告示牌不会删除标记，需要使用指令删除
### 管理员指令
- /asm help     插件管理员帮助
- /asm gui      打开标记点管理GUI
- /asm del name 删除标记点
- /asm get name 查看name标记点的所有信息
- /asm set icon 设置默认icon链接
- /asm search   &查看所有标记点
- /asm reload   插件重载
### 注意事项
本插件需要以[BlueMap](https://github.com/BlueMap-Minecraft/BlueMap)作为前置插件  
本插件使用1.19的paper-api，但1.20测试可用  
其他版本未测试  
GUI暂未开发，后期推出  
管理员权限节点：`bluemapsetmarkers.admin`

>欢迎各位对插件提出建议反馈bug(QQ:3088506834)