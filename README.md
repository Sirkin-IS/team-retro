# Team retro service
## Service for holding retrospectives software development teams.
### if you know how to do it better, do it

## Настройка БД
1. Запустить контейнер db-team-retro с postgers db
2. Выполнить проливку миграций liquibase: TeamRetroServiceLiquibaseJobApplication.main

## Настройка nginx

1. Установка OpenSSL, настройка окружения (для генерации самоподписанного сертификата)
2. Генерация самоподписанного сертификата в openssl
   #### выполнить команду:

    ```
    openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 -keyout privateKey.key -out certificate.crt
    ```

   #### где:
   - x509 — создание самоподписанного сертификата;
   - newkey — автоматическое создание ключа сертификата;
   - days — срок действия сертификата в днях;
   - keyout — путь (если указан) и имя файла ключа;
   - out — путь (если указан) и имя файла сертификата.

   #### Просмотр данных Сертификата:
    ```
    openssl x509 -noout -text -in cert.crt
    ```

   #### Просмотр данных CSR запроса:
    ```
    openssl req -noout -text -in cert.csr
    ```

   сгенерируются 2 файла:
   - certificate.crt — сам сертификат;
   - privateKey.key — файл ключа.

3. Указать в настройках `ssl.conf` путь к сертификату параметр `ssl_certificate`, по умолчанию: `/etc/nginx/ssl/certificate.crt`
   и путь к приватному ключу `ssl_certificate_key`, по умолчанию: `/etc/nginx/ssl/privateKey.key`
4. Настройка Security/Server Side TLS (для протоколов ниже TLS 1.3)
   команда для генерации файла dhparam: `openssl dhparam -out /etc/nginx/ssl/dhparam.pem 4096`
   указать в настройках `ssl.conf` параметр `ssl_dhparam` путь к dhparam.pem, по умолчанию: `/etc/nginx/ssl/dhparam.pem`
5. Запустить контейнер nginx-srv