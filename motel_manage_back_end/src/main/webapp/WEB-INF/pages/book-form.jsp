<%-- 
    Document   : user-form
    Created on : Aug 21, 2019, 6:49:16 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="/webjars/bootstrap/3.4.1/css/bootstrap.min.css" />" 
              type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-12" style="text-align: center">
                    <h3>Book Form</h3>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <mvc:form action="${pageContext.request.contextPath}/${action}" 
                              method="post" class="form-horizontal" modelAttribute="book">
                        <c:if test="${action=='edit-book'}">
                            <input name="id" value="${book.id}" hidden />
                            <input name="bookDetails.id" value="${book.bookDetails.id}" 
                                   hidden />
                        </c:if>

                        <div class="form-group">
                            <label for="name" class="control-label col-sm-2">Name
                                <span style="color: red"> (*)</span></label>
                            <div class="col-sm-10">
                                <input id="name" type="text" name="name" 
                                       class="form-control" required value="${book.name}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="author" class="control-label col-sm-2">Author</label>
                            <div class="col-sm-10">
                                <input id="author" type="text" name="author" 
                                       class="form-control" value="${book.author}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="isbn" class="control-label col-sm-2">ISBN</label>
                            <div class="col-sm-10">
                                <input id="isbn" type="text" name="bookDetails.isbn" 
                                       class="form-control" value="${book.bookDetails.isbn}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="publishedDate" class="control-label col-sm-2">Published Date</label>
                            <div class="col-sm-10">
                                <input id="publishedDate" type="date" name="bookDetails.publishedDate" 
                                       class="form-control" value="${book.bookDetails.publishedDate}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="numberOfPage" class="control-label col-sm-2">Number of Page</label>
                            <div class="col-sm-10">
                                <input id="numberOfPage" type="number" name="bookDetails.numberOfPage" 
                                       class="form-control" value="${book.bookDetails.numberOfPage}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="price" class="control-label col-sm-2">Price</label>
                            <div class="col-sm-10">
                                <input id="price" type="text" name="bookDetails.price" 
                                       class="form-control"  value="${book.bookDetails.price}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">Category</label>
                            <div class="col-sm-10">
                                <select name="category.id" class="form-control">
                                    <c:forEach var="c" items="${categories}">
                                        <c:if test="${c.id == book.category.id}">
                                            <option value="${c.id}" selected>${c.name}</option>
                                        </c:if>
                                        <c:if test="${c.id != book.category.id}">
                                            <option value="${c.id}">${c.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group" style="text-align: center">
                            <button type="submit" class="btn btn-primary">
                                Submit
                            </button>
                        </div>
                    </mvc:form>
                </div>
            </div>
        </div>
    </body>
</html>
