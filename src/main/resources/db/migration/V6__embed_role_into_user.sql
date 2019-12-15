ALTER TABLE "user" ADD COLUMN "role" VARCHAR(10);

UPDATE "user" SET "role"='USER' WHERE "role_id" IN (SELECT "role_id" FROM "role" WHERE "name"='USER');
UPDATE "user" SET "role"='COLLECTOR' WHERE "role_id" IN (SELECT "role_id" FROM "role" WHERE "name"='COLLECTOR');
UPDATE "user" SET "role"='CREATOR' WHERE "role_id" IN (SELECT "role_id" FROM "role" WHERE "name"='CREATOR');
UPDATE "user" SET "role"='ADMIN' WHERE "role_id" IN (SELECT "role_id" FROM "role" WHERE "name"='ADMIN');

ALTER TABLE "user" DROP "role_id";

DROP TABLE "role";