## API методы

Все методы могут вернуть stacktrace с кодом 500

* `POST /api/authenticate` - авторизация
    * Параметры (form-data):
        * `username` - логин пользователя (email)
        * `password` - пароль
    * Возвращаемые значения:
        * Пустой ответ с кодом 403 - неправильный логин или пароль
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

* `GET /api/public/user/resetPassword` - восстановление пароля
    * Параметры (query param like "https://url?param="):
        * email - email пользователя
    * Возвращаемые значения:
        * "Не найден пользователь с таким e-mail адресом!" с кодом 400
        * "Вам на почту отправлен временный пароль. \nПроверьте ваш почтовый ящик, 
           не забудьте проверить папку \"Нежелательная почта\"!" с кодом 200

* `POST /api/public/user/register` - регистрация пользователя
    * Параметры (json-объект):
        * `username`
        * `password`
        * `phoneNumber` - optional
        * `roleCode` - optional
    * Возвращаемые значения:
        * "Вы не можете задавать код пользователя" с кодом 400 - если указан roleCode и пользователь не SUPERUSER
        * "Такой пользователь уже существует!" с кодом 400 - если username уже есть в бд
        * "Код роли не найден!" с кодом 400 - если пользователь superuser и указан roleCode, но такой роли нет в БД
        * "" с кодом 200 - всё OK

* `GET /api/user/getMe` - возвращает данные о пользователе
    * Возвращаемые значения:
        * Пустой ответ с кодом 403 - не авторизован
        * json c кодом 200, пример:

```json
{
    "createdBy": "NBU",
    "createdAt": "2019-09-27T17:27:10.000+0000",
    "updatedBy": "NBU",
    "updatedAt": "2019-09-27T17:27:10.000+0000",
    "id": 1,
    "email": "ura2178@gmail.com",
    "needToChangePassword": true,
    "phoneNumber": "+79999999999",
    "authorities": [
        {
            "authority": "create_new_backend_users"
        },
        {
            "authority": "edit_backend_users"
        }
    ]
}
```