ALTER TABLE "request"
  ADD COLUMN "status" int,
  ADD COLUMN "lot_id" int;

ALTER TABLE "lot"
  ADD COLUMN "price" int,
  ADD COLUMN "comment" "text";

ALTER TABLE "request"
  ADD CONSTRAINT "fk_request_lot_id" FOREIGN KEY ("lot_id")
    REFERENCES "lot" ("lot_id");