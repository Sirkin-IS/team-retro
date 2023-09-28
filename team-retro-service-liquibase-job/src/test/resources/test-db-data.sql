-- Filling with laminar data
truncate table note, retro, retro_user;

DO
$$
    declare
        stageTypesSize integer = 4;
        stageTypes cstring array = (array [
            'WENT_WELL',
            'DIDNT_GO_WELL',
            'ACTION',
            'KUDOS'
            ]);

        userIdsSize integer = 3;
        userIds uuid array = (array [
            '782f2c44-1a7c-15dc-dc2d-ffe2a636a3a8',
            uuid_in(md5(random()::text || clock_timestamp()::text)::cstring),
            uuid_in(md5(random()::text || clock_timestamp()::text)::cstring)
            ]);

        userLoginsSize integer = 3;
        userLogins cstring array = (array [
            'Ivan',
            'Vasily',
            'Nikolay'
            ]);

        roleTypesSize integer = 3;
        roleTypes cstring array = (array [
            'ADMIN',
            'SCRUM_MASTER',
            'PARTICIPANT'
            ]);
    begin
        -- retro table:
        insert into public.retro(retro_id, caption, description, create_dttm, update_dttm)
        select ('00000000-0000-0000-0000-' || LPAD(id::text, 12, '0'))::uuid,
               'Retro caption',
               'Retro description',
               now() - random() * (interval '14 days'),
               null
        from generate_series(:min, :max) id;

        -- note table:
        insert into public.note(note_id, retro_id, user_id, stage_type, caption, text)
        select ('00000000-0000-0000-0000-' || LPAD(id::text, 12, '0'))::uuid,
               ('00000000-0000-0000-0000-' || LPAD(id::text, 12, '0'))::uuid,
               userIds[id % userIdsSize + 1],
               stageTypes[id % stageTypesSize + 1],
               'caption',
               'text'

        from generate_series(:min, :max) id;

        -- retro_user table
        insert into public.retro_user(user_id, login, password_hash, pepper, name, role)
        select userIds[id % userIdsSize + 1],
               userLogins[id % userLoginsSize + 1],
               'password_hash',
               'pepper',
               'name',
               roleTypes[id % roleTypesSize + 1]
        from generate_series(0, userIdsSize-1) id;
    end

$$;
