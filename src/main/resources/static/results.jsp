<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Результаты поиска врачей</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f9;
        }
        h1 {
            text-align: center;
        }
        .doctor-list {
            list-style-type: none;
            padding: 0;
        }
        .doctor-item {
            background-color: white;
            margin: 10px 0;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .doctor-item b {
            font-size: 1.2em;
        }
        .doctor-item a {
            text-decoration: none;
            color: #007bff;
        }
        .doctor-item a:hover {
            text-decoration: underline;
        }
        .back-link {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<h1>Результаты поиска</h1>

<c:if test="${empty doctors}">
    <p>Врачи не найдены. Попробуйте изменить запрос.</p>
</c:if>

<ul class="doctor-list">
    <c:forEach var="doctor" items="${doctors}">
        <li class="doctor-item">
            <b>${doctor.name}</b> — ${doctor.specialization} — ${doctor.clinic} — Рейтинг: ${doctor.rating}
            — <a href="${doctor.profileUrl}" target="_blank">Профиль</a>
        </li>
    </c:forEach>
</ul>

<div class="back-link">
    <a href="/index.html">Назад</a>
</div>

</body>
</html>
