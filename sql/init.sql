DROP TABLE IF EXISTS "task";
DROP TABLE IF EXISTS "user";

CREATE TABLE "user" (
	"id"          SERIAL PRIMARY KEY,
	"first_name"  TEXT   NOT NULL,
	"middle_name" TEXT,
	"last_name"   TEXT   NOT NULL,
	"birthday"    DATE   NOT NULL,
	"login"       TEXT   NOT NULL,
	"password"    TEXT   NOT NULL
);

CREATE TABLE "task" (
	"id"       SERIAL PRIMARY KEY,
	"owner_id" INT    NOT NULL REFERENCES "user" ON UPDATE RESTRICT ON DELETE CASCADE,
	"name"     TEXT   NOT NULL,
	"created"  DATE   NOT NULL DEFAULT now(),
	"x_1"      FLOAT  NOT NULL,
	"y_1"      FLOAT  NOT NULL,
	"x_2"      FLOAT  NOT NULL,
	"y_2"      FLOAT  NOT NULL,
	"x_3"      FLOAT  NOT NULL,
	"y_3"      FLOAT  NOT NULL,
	"x_4"      FLOAT  NOT NULL,
	"y_4"      FLOAT  NOT NULL
);

INSERT INTO "user"
-------------------------------------------------------------------------------------------
("id", "first_name", "middle_name", "last_name", "birthday"  , "login" , "password") VALUES
-------------------------------------------------------------------------------------------
(1   , 'Иван'      , 'Иванович'   , 'Иванов'   , '2003-02-01', 'ivanov', '12345'   );
SELECT setval('user_id_seq', 1);

INSERT INTO "task"
---------------------------------------------------------------------------------------------------------------------
("id", "owner_id", "name"              , "created"   , "x_1", "y_1", "x_2", "y_2", "x_3", "y_3", "x_4", "y_4") VALUES
---------------------------------------------------------------------------------------------------------------------
(1   , 1         , 'Простой случай'    , '2024-01-10',  0   ,  0   ,  0   , 1    , 2    , 2    , 1    ,  0   ),
(2   , 1         , 'Тривиальный случай', '2024-01-15',  0   ,  0   ,  0   , 1    , 1    , 1    , 1    ,  0   ),
(3   , 1         , 'Особый случай'     , '2024-01-13', -1   , -2   ,  0   , 1    , 4    , 2    , 3    , -1   ),
(4   , 1         , 'Усложнённый случай', '2024-01-12', -1   , -2   , -5   , 5    , 3    , 4    , 7    , -3   );
SELECT setval('task_id_seq', 4);