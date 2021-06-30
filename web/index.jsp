<%--
  Created by IntelliJ IDEA.
  User: 57423
  Date: 2020/12/13
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>jsp home</title>
  </head>
  <body>
      <h2>添加商品</h2>
      <!--/product/save 参考controller配置 -->
      <form action="/webpro/product/save.mvc" method="post">
            <!-- 属性支持注入,前提是名称必须相同 -->
          name:<input type="text" name="name" value="AAA"/><br />
          price:<input type="text" name="price" value="200.00" /><br />
          remark:<input type="text" name="remark" value="我是备注" /><br />
          <input type="submit" value="提交" />
      </form>
  </body>
</html>
