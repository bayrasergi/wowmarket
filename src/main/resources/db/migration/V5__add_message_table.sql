CREATE TABLE "message"
(
  "message_id"  serial          NOT NULL,
  "timestamp"   DATE            NOT NULL,
  "text"        varchar(1000)   NOT NULL,
  "user_id"     int             NOT NULL,
  CONSTRAINT "pk_message" PRIMARY KEY ("message_id")
);

ALTER TABLE "message"
  ADD CONSTRAINT "fk_message_user_id" FOREIGN KEY ("user_id")
    REFERENCES "user" ("user_id");