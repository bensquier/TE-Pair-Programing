-- Write queries to return the following:
-- Make the following changes in the "world" database.

-- 1. Add Superman's hometown, Smallville, Kansas to the city table. The 
-- countrycode is 'USA', and population of 45001. (Yes, I looked it up on 
-- Wikipedia.)

--SELECT * FROM city WHERE countrycode = 'USA';

INSERT INTO city (name, countrycode, district, population) VALUES ('Smallville', 'USA', 'Kansas', 45001);

--SELECT * FROM city WHERE countrycode = 'USA';


-- 2. Add Kryptonese to the countrylanguage table. Kryptonese is spoken by 0.0001
-- percentage of the 'USA' population.
--SELECT * FROM countrylanguage WHERE countrycode = 'USA';

INSERT INTO countrylanguage (countrycode, language, isofficial, percentage) VALUES ('USA', 'Kryptonese', 'N', 0.0001);

--SELECT * FROM countrylanguage WHERE countrycode = 'USA';

-- 3. After heated debate, "Kryptonese" was renamed to "Krypto-babble", change 
-- the appropriate record accordingly.

UPDATE countrylanguage 
SET language = 'Krypto-babble'
WHERE language = 'Kryptonese';

--SELECT * FROM countrylanguage WHERE countrycode = 'USA';
-- 4. Set the US captial to Smallville, Kansas in the country table.

UPDATE country 
SET capital = (SELECT id FROM city WHERE name = 'Smallville')
WHERE code = 'USA';
/*
SELECT 'This capital of ' || c.name || ' is ' || cty.name AS FriendlyCapital
FROM country c 
        INNER JOIN city cty
               ON c.capital = cty.id
WHERE
        c.code = 'USA';*/
        
-- 5. Delete Smallville, Kansas from the city table. (Did it succeed? Why?)

DELETE FROM city
WHERE name = 'Smallville' AND district = 'Kansas' AND countrycode = 'USA';

-------------------------------------No, it did not work because it violates foreign key constraint----------------------------------------

-- 6. Return the US captial to Washington.

UPDATE country 
SET capital = (SELECT id FROM city WHERE name = 'Washington')
WHERE code = 'USA';

/*SELECT 'This capital of ' || c.name || ' is ' || cty.name AS FriendlyCapital
FROM country c 
        INNER JOIN city cty
               ON c.capital = cty.id
WHERE
        c.code = 'USA';*/


-- 7. Delete Smallville, Kansas from the city table. (Did it succeed? Why?)

DELETE FROM city
WHERE name = 'Smallville' AND district = 'Kansas' AND countrycode = 'USA';

--SELECT * FROM city WHERE countrycode = 'USA';

-------------------------------------Yes it did work because Smallville is no longer a capital which is a foreign key constraint----------------------------------------


-- 8. Reverse the "is the official language" setting for all languages where the
-- country's year of independence is within the range of 1800 and 1972 
-- (exclusive). 
-- (590 rows affected)

/*SELECT * FROM countrylanguage cl
                        INNER JOIN country c
                                ON cl.countrycode = c.code
WHERE indepyear BETWEEN 1800 AND 1972;*/


UPDATE countrylanguage cl
SET isofficial = NOT cl.isofficial
FROM countrylanguage clang
                 INNER JOIN country c
                         ON clang.countrycode = c.code

WHERE indepyear BETWEEN 1800 AND 1972;


/*SELECT * FROM countrylanguage cl
                        INNER JOIN country c
                                ON cl.countrycode = c.code
WHERE indepyear BETWEEN 1800 AND 1972;*/

-- 9. Convert population so it is expressed in 1,000s for all cities. (Round to
-- the nearest integer value greater than 0.)
-- (4079 rows affected)

/*SELECT name, population FROM city;*/

UPDATE city cty
SET population = ROUND(cty.population / 1000, 0);

/*SELECT name, population FROM city;
*/
-- 10. Assuming a country's surfacearea is expressed in square miles, convert it to 
-- square meters for all countries where French is spoken by more than 20% of the 
-- population.
-- (7 rows affected)

SELECT * FROM country cy
        INNER JOIN countrylanguage cl
                ON cy.code = cl.countrycode
WHERE language ='French' AND cl.percentage > 20.0;

UPDATE country c
SET surfacearea = c.surfacearea * 1609.344 -- or 2589988.1103
FROM country cy
        INNER JOIN countrylanguage cl
                ON cy.code = cl.countrycode
WHERE language ='French' AND cl.percentage > 20.0;

SELECT * FROM country cy
        INNER JOIN countrylanguage cl
                ON cy.code = cl.countrycode
WHERE language ='French' AND cl.percentage > 20.0;


