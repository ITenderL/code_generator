
通过windos窗口打开文件夹,不要在Eclipse中点开angogo-gen.bat,会报错的!

运行angogo-gen.bat用于再输入gen 表名,例如:gen sys_user 用于生成代码

主要配置文件为generator.xml,里面可以修改数据库连接,以及java包名文件路径名称

执行代码生成命令(例如: gen sys_user ,xxxxx)后,生成的代码对应拷贝如下:


生成的.sql文件是菜单添加的sql,一般根据业务修改后在数据库执行.
