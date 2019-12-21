ALTER TABLE "lot" ADD COLUMN "status" VARCHAR(20);

ALTER TABLE "request" DROP "lot_id";
ALTER TABLE "request" ADD COLUMN "price" int;
ALTER TABLE "request" ADD COLUMN "payment" int;
ALTER TABLE "request" ADD COLUMN "prepayment" int;

ALTER TABLE "user" ADD COLUMN "locked" boolean;

ALTER TABLE "certificate" ADD COLUMN "approved" boolean;
