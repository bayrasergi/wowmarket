UPDATE "lot" SET "status"='SOLD' WHERE "status" IN ('SELL');
UPDATE "lot" SET "status"='SELLING' WHERE "status" IN ('AVAILABLE');