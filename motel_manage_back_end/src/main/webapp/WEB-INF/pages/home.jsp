<%-- 
    Document   : home
    Created on : Aug 19, 2019, 8:41:27 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/webjars/bootstrap/3.4.1/css/bootstrap.min.css" />" 
              type="text/css" rel="stylesheet" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-12" style="text-align: center">
                    <h3>Display Books</h3>
                </div>
            </div>
            <c:if test="${message!=null && message!=''}">
                <div class="row">
                    <div class="col-sm-12">
                        <c:if test="${!status}">
                            <div class="alert alert-danger">
                                ${message}
                            </div>
                        </c:if>
                        <c:if test="${status}">
                            <div class="alert alert-success">
                                ${message}
                            </div>
                        </c:if>
                    </div>
                </div>
            </c:if>

            <div class="row">
                <div class="col-sm-6" style="padding-bottom: 10px">
                    <button class="btn btn-primary"
                            onclick="location.href = '<c:url value="/add-book"/>'">Add Book</button>
                </div>

                <div class="col-sm-6" style="text-align: right">
                    <form method="post" 
                          action="${pageContext.request.contextPath}/search" 
                          class="form-inline">
                        <div class="form-group">
                            <input type="text" name="searchTxt" class="form-control"/>
                            <input type="submit" value="Search" class="btn btn-info"/>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <tr>
                                <th>Name</th>
                                <th>Author</th>
                                <th>Category</th>
                                <th>Price</th>
                                <th>ISBN</th>
                                <th>Published Date</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach var="b" items="${books}">
                                <tr>
                                    <td>${b.name}</td>
                                    <td>${b.author}</td>
                                    <td>${b.category.name}</td>
                                    <td>${b.bookDetails.price}</td>
                                    <td>${b.bookDetails.isbn}</td>
                                    <td>${b.bookDetails.publishedDate}</td>
                                    <td>
                                        <button class="btn btn-default"
                                                onclick="location.href = '<c:url value="/edit-book/${b.id}"/>'">Edit</button>

                                        <button class="btn btn-danger"
                                                onclick="location.href = '<c:url value="/delete-book/${b.id}"/>'">Delete</button>

                                        <button class="btn btn-info"
                                                onclick="location.href = '<c:url value="/book-detail/${b.id}"/>'">Detail</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <img src="<c:url value='/resources/images/logo.png'/>" alt="Smiley face" height="42" width="42">
        àasffsfsf
        
    </body>
</html>
