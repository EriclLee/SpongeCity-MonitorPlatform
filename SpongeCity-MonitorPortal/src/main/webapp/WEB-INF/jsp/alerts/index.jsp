<%--
  Created by IntelliJ IDEA.
  User: EriclLee
  Date: 15/12/29
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page isELIgnored="false" %>
<script type="text/javascript">
    function onpageclick(pageIndex)
    {
        console.log("sethash");
        var hashObject = GetHash();
        hashObject["pageIndex"] = pageIndex;
        var hashurl = "";
        for( var key in hashObject ){
            hashurl += key + "=" + hashObject[key]+"&";
        }
        location.hash = hashurl.slice(0,-1);
    }
</script>
<div style="height:0px; overflow:hidden;">&nbsp;</div>
<div class="table_wrap">
    <table class="table table-bordered" style="border-radius:5px">
        <tr style=" background:#f9f9f9">
            <td style="border-radius:5px">时间</td>
            <td>告警</td>
            <td>区域</td>
            <td>地块</td>
            <td>单项措施</td>
            <td>设备</td>
            <td>描述</td>
        </tr>
        <c:forEach items="${alerts.data}" var="al">
            <tr>
                <td><fmt:formatDate value="${al.createtime}" pattern="yyyy-MM-dd" /></td>
                <td>${al.title}</td>
                <td>${al.areaName}</td>
                <td>${al.blockName}</td>
                <td>${al.measureName}</td>
                <td>${al.devicename}</td>
                <td>${al.comments}</td>
            </tr>
        </c:forEach>

    </table>

  <nav>
    <ul class="pagination">
        <c:if test="${alerts.pageCount>0}">
      <c:if test="${alerts.currentPageIndex==0}">
        <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
      </c:if>
      <c:if test="${alerts.currentPageIndex>0}">
        <li ><a href="javascript:void(0);" onclick="onpageclick('0')" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
      </c:if>
      <c:forEach var="i" begin="0" end="${alerts.pageCount-1}">
        <c:if test="${alerts.currentPageIndex==i}">
          <li class="active"><a href="javascript:void(0);" onclick="onpageclick('${i}')">${i+1} <span class="sr-only">(current)</span></a></li>
        </c:if>
        <c:if test="${alerts.currentPageIndex!=i}">
          <li><a href="javascript:void(0);" onclick="onpageclick('${i}')">${i+1}</a></li>
        </c:if>

      </c:forEach>
      <c:if test="${alerts.currentPageIndex==alerts.pageCount-1}">
        <li class="disabled">
          <a href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </c:if>
      <c:if test="${alerts.currentPageIndex<alerts.pageCount-1}">
        <li>
          <a href="javascript:void(0);" onclick="onpageclick('${alerts.pageCount-1}')" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </c:if>
            </c:if>
    </ul>
  </nav>
</div>
