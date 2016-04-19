#!/bin/bash 
#目标文件所在服务器IP，如服务器A同步到B，则这里写的应该是B的IP
host=125.210.143.96
#源文件所在目录，绝对路径，如果需要多个文件夹同步，这里配置多个src
src=/home/support/tomcat-huasu-manager/webapps/resources

#认证模块名称，必须与客户端保持一致   
des=lutong 
#建立密码文件的用户名
user=lutong

#通过inotify实时监控文件的变化
/usr/local/inotify/bin/inotifywait -mrq --timefmt '%d/%m/%y %H:%M' --format '%T %w%f%e' -e modify,delete,create,attrib $src \ 
| while read files 
do
#实际执行同步的地方，如果有多个配置为多行，然后修改$src即可
/usr/bin/rsync -vzrtopg --delete --progress --password-file=/usr/local/rsync/rsync.passwd $src $user@$host::$des 
echo "${files} was rsynced" >>/opt/rsync.log 2>&1 
done

#参考地址：http://www.jb51.net/article/57011.htm
#启动rsync.sh语句：sh /usr/local/inotify/rsync.sh &