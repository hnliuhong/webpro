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
      <h2>查询商品</h2>
      <!--/product/save 参考controller配置 -->
      <form action="/webpro/product/queryByName.mvc" method="get">
          name:<input type="text" name="keyword" /><br />
          <input type="submit" value="给我搜" />
      </form>
       <hr />
      requestScope:${requestScope.proList}
      <hr />
      sessionScope:${sessionScope.proList}
      <hr />
      applictionScope:${applicationScope.proList}
  </body>
</html>
