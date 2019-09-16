# online-exam
在线考试系统

技术框架：SpringBoot + tkmybatis

简单系统Web后端框架:

  1.xss防护。 
  
  2.实现请求白名单功能。  
  
  3.根据token请求头解析用户基本信息存放header上，接口采用@RequestHeader("userId")注解方式获取。
  
  4.实现全局异常拦截，所有返回数据符合自定义返回格式。（业务层直接throw new BusinessException处理业务异常或程序异常）
