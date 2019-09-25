<%-- 
    Document   : book-detail
    Created on : Aug 26, 2019, 6:49:58 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/webjars/bootstrap/3.4.1/css/bootstrap.min.css" />" 
              type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-12" style="text-align: center">
                    <h3>Book Detail</h3>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <h4>Name: ${b.name}</h4>
                </div>
                <div class="col-sm-4">
                    <h4>Author: ${b.author}</h4>
                </div>
                <div class="col-sm-4">
                    <h4>ISBN: ${b.bookDetails.isbn}</h4>
                </div>
                <div class="col-sm-4">
                    <h4>Number of Page: ${b.bookDetails.numberOfPage}</h4>
                </div>
                <div class="col-sm-4">
                    <h4>Price: 
                        <fmt:formatNumber minFractionDigits="3" type="number"
                                          value="${b.bookDetails.price}" />
                        VNĐ</h4>
                    <h4>Price:   
                        <fmt:formatNumber pattern="###,###" type="number"
                                          value="${b.bookDetails.price}" />
                        VNĐ
                    </h4>
                </div>
                <div class="col-sm-4">
                    <h4>Published Date: 
                        <fmt:formatDate pattern="dd/MM/yyy" value="${b.bookDetails.publishedDate}" />
                    </h4>
                </div>
                <div class="col-sm-4">
                    <h4>Category Name: ${b.category.name}</h4>
                </div>
            </div>
        </div>
    </body>
</html>
