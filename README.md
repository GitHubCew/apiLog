# 使用步骤：

1. 使用git clone 项目
2. 使用maven install 命令安装到本地maven仓库
3. 在项目中引用依赖:

        ```xml
        <dependency>
            <groupId>api.log</groupId>
            <artifactId>api-log</artifactId>
            <version>1.0.0</version>
        </dependency>
        ```

4. 如果项目中有安全校验，则需要放开路径：
    - `/alog-ws`
    - `/alog-terminal/**`

5. 启动项目
6. 访问项目web + `/alog-terminal/alog-terminal.html`  
   例如： `localhost:80/context/alog-terminal/alog-terminal.html` (context: 为项目的context-path上下文)

7. 进入alog,如果出现如下界面，则成功

┌──────────────────────────────┐
│   欢迎使用 ALog Terminal    │
│        作者: chenenwei      │
└──────────────────────────────┘

    输入 'help' 查看可用命令

8.命令介绍
- connect 连接 WebSocket 服务器
- exit/quit 断开 WebSocket 连接
- clear 清空终端屏幕
- help 显示本帮助信息
- monitor [path] 监控指定API接口
- remove [path] 移除对指定API接口的监控
- clearall 清空全部监控的API接口

提示：使用 ↑/↓ 箭头键浏览历史命令