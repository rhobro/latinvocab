/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: core.sql
 * Last Modified: 29/03/2021, 21:52
 */

CREATE DATABASE latin;
CREATE TABLE latin.vocab
(
    id      SMALLSERIAL PRIMARY KEY,
    latin   TEXT,
    english TEXT,
    details TEXT,
    grammar TEXT,
    stage   SMALLINT
);
DROP DATABASE latin;
SELECT *
FROM vocab;
SELECT COUNT(*)
FROM vocab;

SELECT *
FROM vocab
WHERE stage IN (1, 2, 3)
ORDER BY random();

SELECT stage
FROM vocab
GROUP BY stage
ORDER BY stage;

SELECT *
FROM vocab
WHERE latin SIMILAR TO '[abc]%';

SELECT DISTINCT stage
FROM vocab
ORDER BY stage;

INSERT INTO vocab (latin, details, english, grammar, stage)
VALUES (?, ?, ?, ?, ?);