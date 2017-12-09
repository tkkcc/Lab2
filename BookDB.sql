<Context path="/lab2" docBase="" privileged="false" reloadable="true"/>
use mysql; 
update user set host = '%' where user = 'root'; 
flush privileges;
exit;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY '0' WITH GRANT OPTION; 
FLUSH PRIVILEGES;

GRANT ALL PRIVILEGES ON *.* TO 'bilibila'@'localhost'  WITH GRANT OPTION;


CREATE USER 'bilibila'@'localhost' IDENTIFIED BY 'dilidili';
GRANT ALL PRIVILEGES ON *.* TO 'bilibila'@'localhost'  WITH GRANT OPTION;
CREATE USER 'bilibila'@'%' IDENTIFIED BY 'dilidili';
GRANT ALL PRIVILEGES ON *.* TO 'bilibila'@'%' WITH GRANT OPTION;

GRANT ALL ON *.* TO 'root'@'computer.host.com';
GRANT ALL ON *.* TO 'root'@'192.168.1.101';
GRANT ALL ON *.* TO 'root'@'%';

GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '0' WITH GRANT OPTION; 
FLUSH PRIVILEGES;

GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON BookDB.* TO 'root'@'%' IDENTIFIED BY '0';
FLUSH PRIVILEGES;

GRANT INSERT, SELECT, DELETE, UPDATE ON BookDB.* TO 'root'@'localhost' IDENTIFIED BY '0';



Mysql的Access denied for user 'root'@'%的'问题
     最近在分配mysql权限时出错,mysql版本5.6,造成mysql在重新分配权限提示"Access denied for user 'root'@'%"，出错原因reload权限被收回，造成无法重新分配权限，其他类似权限问题也可以参照此方法。
    一·解决办法 
    第一步：停服务
命令行：
/etc/init.d/mysql stop
如果不行，就执行下一行：
service mysqld stop
报：
Stopping mysqld:  [  OK  ]


第二步：跳过密码验证
执行命令行：
# /usr/bin/mysqld_safe --skip-grant-tables
报：
151104 09:07:56 mysqld_safe Logging to '/var/lib/mysql/iZ23dq2wm0jZ.err'.
151104 09:07:56 mysqld_safe Starting mysqld daemon with databases from /var/lib/mysql


mysql -u root 
grant all privileges on *.* to 'root'@'%' identified by '0' with grant option;

问题一：发现无密码条件下，没有授权的写权限
The MySQL server is running with the --skip-grant-tables option so it cannot execute this statement

set global read_only=0;
flush privileges;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '0';
set global read_only=1;
flush privileges;
exit;

set password for 'root'@'%' = password('0');

第五步：重启数据库
service mysqld stop
报：
Stopping mysqld:  [  OK  ]


service mysqld start
报：
Starting mysqld:  [  OK  ]
或者
service mysqld restart