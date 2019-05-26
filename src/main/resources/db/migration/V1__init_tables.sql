CREATE TABLE "user"
(
    "user_id"  serial       NOT NULL,
    "email"    varchar(100) NOT NULL UNIQUE,
    "name"     varchar(20)  NOT NULL,
    "password" varchar(255) NOT NULL,
    "role_id"  int          NOT NULL,
    CONSTRAINT "pk_user" PRIMARY KEY ("user_id")
);

CREATE TABLE "role"
(
    "role_id" serial      NOT NULL,
    "name"    varchar(20) NOT NULL,
    CONSTRAINT "pk_role" PRIMARY KEY ("role_id")
);

CREATE TABLE "profession"
(
    "profession_id" serial      NOT NULL,
    "name"          varchar(20) NOT NULL,
    "craft"         bool        NOT NULL,
    CONSTRAINT "pk_profession" PRIMARY KEY ("profession_id")
);

CREATE TABLE "sertificate"
(
    "sertificate_id" serial NOT NULL,
    "profession_id"  int    NOT NULL,
    "user_id"        int    NOT NULL,
    CONSTRAINT "pk_sertificate" PRIMARY KEY ("sertificate_id")
);

CREATE TABLE "item"
(
    "item_id" serial       NOT NULL,
    "name"    varchar(255) NOT NULL,
    CONSTRAINT "pk_item" PRIMARY KEY ("item_id")
);

CREATE TABLE "request"
(
    "request_id"        serial NOT NULL,
    "requested_item_id" int    NOT NULL,
    "buyer_user_id"     int    NOT NULL,
    "seller_user_id"    int    NULL,
    "parent_request_id" int    NULL,
    "count"             int    NOT NULL,
    CONSTRAINT "pk_request" PRIMARY KEY ("request_id")
);

CREATE TABLE "lot"
(
    "lot_id"         serial NOT NULL,
    "item_id"        int    NOT NULL,
    "seller_user_id" int    NOT NULL,
    "count"          int    NOT NULL,
    CONSTRAINT "pk_lot" PRIMARY KEY ("lot_id")
);

CREATE TABLE "recipe"
(
    "recipe_id"       serial NOT NULL,
    "profession_id"   int    NOT NULL,
    "created_item_id" int    NOT NULL,
    CONSTRAINT "pk_recipe" PRIMARY KEY ("recipe_id")
);

CREATE TABLE "recipe_item"
(
    "recipe_item_id"   serial NOT NULL,
    "recipe_id"        int    NOT NULL,
    "required_item_id" int    NOT NULL,
    "count"            int    NOT NULL,
    CONSTRAINT "pk_recipe_item" PRIMARY KEY ("recipe_item_id")
);

ALTER TABLE "sertificate"
    ADD CONSTRAINT "fk_sertificate_profession_id" FOREIGN KEY ("profession_id")
        REFERENCES "profession" ("profession_id");

ALTER TABLE "sertificate"
    ADD CONSTRAINT "fk_sertificate_user_id" FOREIGN KEY ("user_id")
        REFERENCES "user" ("user_id");

ALTER TABLE "request"
    ADD CONSTRAINT "fk_request_requested_item_id" FOREIGN KEY ("requested_item_id")
        REFERENCES "item" ("item_id");

ALTER TABLE "request"
    ADD CONSTRAINT "fk_request_buyer_user_id" FOREIGN KEY ("buyer_user_id")
        REFERENCES "user" ("user_id");

ALTER TABLE "request"
    ADD CONSTRAINT "fk_request_seller_user_id" FOREIGN KEY ("seller_user_id")
        REFERENCES "user" ("user_id");

ALTER TABLE "request"
    ADD CONSTRAINT "fk_request_parent_request_id" FOREIGN KEY ("parent_request_id")
        REFERENCES "request" ("request_id");

ALTER TABLE "lot"
    ADD CONSTRAINT "fk_lot_item_id" FOREIGN KEY ("item_id")
        REFERENCES "item" ("item_id");

ALTER TABLE "lot"
    ADD CONSTRAINT "fk_lot_seller_user_id" FOREIGN KEY ("seller_user_id")
        REFERENCES "user" ("user_id");

ALTER TABLE "recipe"
    ADD CONSTRAINT "fk_recipe_profession_id" FOREIGN KEY ("profession_id")
        REFERENCES "profession" ("profession_id");

ALTER TABLE "recipe"
    ADD CONSTRAINT "fk_recipe_created_item_id" FOREIGN KEY ("created_item_id")
        REFERENCES "item" ("item_id");

ALTER TABLE "recipe_item"
    ADD CONSTRAINT "fk_recipe_item_recipe_id" FOREIGN KEY ("recipe_id")
        REFERENCES "recipe" ("recipe_id");

ALTER TABLE "recipe_item"
    ADD CONSTRAINT "fk_recipe_item_required_item_id" FOREIGN KEY ("required_item_id")
        REFERENCES "item" ("item_id");

ALTER TABLE "user"
    ADD CONSTRAINT "fk_user_role_id" FOREIGN KEY ("role_id")
        REFERENCES "role" ("role_id");

INSERT INTO "role"("name")
VALUES ('USER');
INSERT INTO "role"("name")
VALUES ('COLLECTOR');
INSERT INTO "role"("name")
VALUES ('CREATOR');
INSERT INTO "role"("name")
VALUES ('ADMIN');