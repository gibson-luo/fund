
-- Table load_fund_record
CREATE TABLE "load_fund_record" (
  "id" int8 NOT NULL,
  "customer_id" varchar NOT NULL,
  "load_amount" numeric NOT NULL,
  "time" timestamp NOT NULL,
  "accepted" bool
);

-- Table rule
CREATE SEQUENCE IF NOT EXISTS rule_id_seq;

CREATE TABLE "rule" (
  "id" int8 NOT NULL DEFAULT nextval('rule_id_seq'::regclass),
  "description" varchar,
  "key" varchar,
  "value" varchar,
  PRIMARY KEY ("id")
);

-- Table load_calculation_daily
CREATE SEQUENCE IF NOT EXISTS load_calculation_daily_id_seq;

CREATE TABLE "load_calculation_daily" (
  "id" int8 NOT NULL DEFAULT nextval('load_calculation_daily_id_seq'::regclass),
  "customer_id" varchar,
  "load_amount" numeric,
  "load_times" int,
  "date" date,
  PRIMARY KEY ("id")
);

CREATE INDEX ON "load_calculation_daily" ("customer_id", "date");


INSERT INTO rule (description, key, value) values ('A maximum of $5,000 can be loaded per day', 'maximumAmountPerDay', '5000');
INSERT INTO rule (description, key, value) values ('A maximum of $20,000 can be loaded per week', 'maximumAmountPerWeek', '20000');
INSERT INTO rule (description, key, value) values ('A maximum of 3 loads can be performed per day, regardless of amount', 'maximumLoadsPerDay', '3');
