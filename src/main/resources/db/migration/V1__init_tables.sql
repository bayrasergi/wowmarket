CREATE TABLE "User"
(
    "user_id"   serial       NOT NULL,
    "name"      varchar(20)  NOT NULL,
    "password"  varchar(255) NOT NULL,
    "user_role" varchar(20)  NOT NULL,
    CONSTRAINT "pk_User" PRIMARY KEY ("user_id")
);

CREATE TABLE "Profession"
(
    "profession_id" serial      NOT NULL,
    "name"          varchar(20) NOT NULL,
    "craft"         bool        NOT NULL,
    CONSTRAINT "pk_Profession" PRIMARY KEY ("profession_id")
);

CREATE TABLE "Sertificate"
(
    "sertificate_id" serial NOT NULL,
    "profession_id"  int    NOT NULL,
    "user_id"        int    NOT NULL,
    CONSTRAINT "pk_Sertificate" PRIMARY KEY ("sertificate_id")
);

CREATE TABLE "Item"
(
    "item_id" serial       NOT NULL,
    "name"    varchar(255) NOT NULL,
    CONSTRAINT "pk_Item" PRIMARY KEY ("item_id")
);

CREATE TABLE "Request"
(
    "request_id"        serial NOT NULL,
    "requested_item_id" int    NOT NULL,
    "buyer_user_id"     int    NOT NULL,
    "seller_user_id"    int    NULL,
    "count"             int    NOT NULL,
    CONSTRAINT "pk_Request" PRIMARY KEY ("request_id")
);

CREATE TABLE "Lot"
(
    "lot_id"         serial NOT NULL,
    "item_id"        int    NOT NULL,
    "seller_user_id" int    NOT NULL,
    "count"          int    NOT NULL,
    CONSTRAINT "pk_Lot" PRIMARY KEY ("lot_id")
);

CREATE TABLE "Request_request"
(
    "request_request_id" serial NOT NULL,
    "parent_request_id"  int    NOT NULL,
    "child_request_id"   int    NOT NULL,
    CONSTRAINT "pk_Request_request" PRIMARY KEY ("request_request_id")
);

CREATE TABLE "Recipe"
(
    "recipe_id"       serial NOT NULL,
    "profession_id"   int    NOT NULL,
    "created_item_id" int    NOT NULL,
    CONSTRAINT "pk_Recipe" PRIMARY KEY ("recipe_id")
);

CREATE TABLE "Recipe_item"
(
    "recipe_item_id"   serial NOT NULL,
    "recipe_id"        int    NOT NULL,
    "required_item_id" int    NOT NULL,
    "count"            int    NOT NULL,
    CONSTRAINT "pk_Recipe_item" PRIMARY KEY ("recipe_item_id")
);

ALTER TABLE "Sertificate"
    ADD CONSTRAINT "fk_Sertificate_profession_id" FOREIGN KEY ("profession_id")
        REFERENCES "Profession" ("profession_id");

ALTER TABLE "Sertificate"
    ADD CONSTRAINT "fk_Sertificate_user_id" FOREIGN KEY ("user_id")
        REFERENCES "User" ("user_id");

ALTER TABLE "Request"
    ADD CONSTRAINT "fk_Request_requested_item_id" FOREIGN KEY ("requested_item_id")
        REFERENCES "Item" ("item_id");

ALTER TABLE "Request"
    ADD CONSTRAINT "fk_Request_buyer_user_id" FOREIGN KEY ("buyer_user_id")
        REFERENCES "User" ("user_id");

ALTER TABLE "Request"
    ADD CONSTRAINT "fk_Request_seller_user_id" FOREIGN KEY ("seller_user_id")
        REFERENCES "User" ("user_id");

ALTER TABLE "Lot"
    ADD CONSTRAINT "fk_Lot_item_id" FOREIGN KEY ("item_id")
        REFERENCES "Item" ("item_id");

ALTER TABLE "Lot"
    ADD CONSTRAINT "fk_Lot_seller_user_id" FOREIGN KEY ("seller_user_id")
        REFERENCES "User" ("user_id");

ALTER TABLE "Request_request"
    ADD CONSTRAINT "fk_Request_request_parent_request_id" FOREIGN KEY ("parent_request_id")
        REFERENCES "Request" ("request_id");

ALTER TABLE "Request_request"
    ADD CONSTRAINT "fk_Request_request_child_request_id" FOREIGN KEY ("child_request_id")
        REFERENCES "Request" ("request_id");

ALTER TABLE "Recipe"
    ADD CONSTRAINT "fk_Recipe_profession_id" FOREIGN KEY ("profession_id")
        REFERENCES "Profession" ("profession_id");

ALTER TABLE "Recipe"
    ADD CONSTRAINT "fk_Recipe_created_item_id" FOREIGN KEY ("created_item_id")
        REFERENCES "Item" ("item_id");

ALTER TABLE "Recipe_item"
    ADD CONSTRAINT "fk_Recipe_item_recipe_id" FOREIGN KEY ("recipe_id")
        REFERENCES "Recipe" ("recipe_id");

ALTER TABLE "Recipe_item"
    ADD CONSTRAINT "fk_Recipe_item_required_item_id" FOREIGN KEY ("required_item_id")
        REFERENCES "Item" ("item_id");

