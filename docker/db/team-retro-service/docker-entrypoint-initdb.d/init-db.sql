-- Инициализация БД в локальном контейнере Docker
-- Создание пользователя
create user team_retro_db_user with password 'team_retro_db_user_password';
-- crud by default for team_retro_db_user
alter default privileges in schema public grant usage on sequences to team_retro_db_user;
alter default privileges in schema public grant select, insert, update, delete on tables to team_retro_db_user;