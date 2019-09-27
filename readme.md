## API методы

* `POST /api/authenticate` - авторизация, данные отправляются в виде form-data
    * Параметры:
        * `username` - логин пользователя (email)
        * `password` - пароль
    * Возвращаемые значения:
        * Пустой ответ с кодом 403 - неправильный логин или пароль
        * Ошибка (stacktrace) с кодом 500 - внутренняя ошибка сервера
        * json c кодом 200, пример:
```json
{
    "user": {
        "role": {
            "privileges": [
                {
                    "code": "create_new_backend_users",
                    "id": 1
                },
                {
                    "code": "edit_backend_users",
                    "id": 2
                }
            ],
            "code": "SUPERUSER",
            "id": 1
        },
        "needToChangePassword": true,
        "id": 1,
        "email": "ura2178@gmail.com"
    },
    "token": "qwertyuiol0mnbvcfdrtyuik1mndbvcfgasd.eyJpc3MiOiJzZWN1cmUtYXBpIiwiYXVkIjoic2VjdXJlLWFwcCIsInN1YiI6InVyYTIxNzhAZ21haWwuY29tIiwiZXhwIjoxNTcwNDcxMzA2LCJyb2wiOlsiY3JlYXRlX25ld19iYWNrZW5kX3VzZXJzIiwiZWRpdF9iYWNrZW5kX3VzZXJzIl19.ABq-D3qPP1E33ukyyUCwkR8IezKbCA1A9XkYW5v_-2QhBPWUxjhY6AzGqsDocLvBAWpLxrUz5gyg7uZc9Al0NA"
}
```