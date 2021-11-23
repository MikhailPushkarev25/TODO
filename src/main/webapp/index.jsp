<%@ page import="ru.job4j.model.User" %>
<%@ page import="ru.job4j.store.Hiber" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="format.css">
    <title>TODO</title>
    <script src="js/add.js"></script>
    <script src="js/status.js"></script>
    <script src="js/shows.js"></script>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
<body>
<form>
    <div class="form-group">
        <div class="container-fluid">
            <label for="formForDefinition1">Описание новой задачи</label>
            <textarea class="form-control" id="formForDefinition1" rows="2"></textarea>
        </div>
        <br>
        <div class="form-group">
            <div class="col-md-6 mb-6">
                <button type="reset" class="btn btn-primary" onclick="add()">Добавить</button>
            </div>
        </div>
    </div>
</form>
<div class="row">
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/create.jsp"> <c:out value="${category.name}"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <c:out value="${user.name}"/></a>
        </li>
        <c:if test="${User != null}">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">Выйти</a>
            </li>
        </c:if>
    </ul>
</div>
<br>
<br>
<div class="form-group form-check">
    <input type="checkbox" class="ui-corner-right" id="checkbox1" onclick="showAll()" checked>
    <label class="form-check-label" for="checkbox1">Показать все</label>
</div>
<table class="table" id="table">
    <caption>Список задач</caption>
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Задача</th>
        <th scope="col">Категория</th>
        <th scope="col">Автор</th>
        <th scope="col">Дата создания</th>
        <th scope="col">Статус</th>
    </tr>
    </thead>
    <tbody id="bodyTableId">
    </tbody>
</table>

</body>
</html>