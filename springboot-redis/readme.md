连接虚拟机里的redis,需要：
1. 开放端口
```
# 启动防火墙 
systemctl start firewalld

# 查询已开放的端口
firewall-cmd --zone=public --list-ports

# 开放6379端口
firewall-cmd --zone=public --add-port=6379/tcp --permanent
```

2. 修改bind，否则会连接失败
```
# 默认bind 本地回环地址，只有redis服务器本机的客户端能够连接
bind 你的网卡地址
```
