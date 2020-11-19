-- Write queries to return the following:
-- The following queries utilize the "world" database.

-- 1. The city name, country name, and city population of all cities in Europe with population greater than 1 million
-- (36 rows)

SELECT cty.name, c.name, cty.population
FROM city cty
        INNER JOIN country c
                ON cty.countrycode = c.code
WHERE continent = 'Europe'AND cty.population > 1000000;

-- 2. The city name, country name, and city population of all cities in countries where French is an official language and the city population is greater than 1 million
-- (2 rows)

SELECT cty.name, c.name, cty.population
FROM city cty
        INNER JOIN country c
                ON cty.countrycode = c.code
                INNER JOIN countrylanguage cl
                        ON c.code = cl.countrycode
WHERE language = 'French'AND cty.population > 1000000 AND isofficial IS true;

-- 3. The name of the countries and continents where the language Javanese is spoken
-- (1 row)

SELECT c.name, continent
FROM country c
        INNER JOIN countrylanguage cl
                ON cl.countrycode = c.code
WHERE  language = 'Javanese';              

-- 4. The names of all of the countries in Africa that speak French as an official language
-- (5 row)

SELECT c.name AS country_name
FROM country c
        INNER JOIN countrylanguage cl
                ON c.code = cl.countrycode
WHERE language = 'French' AND isofficial IS true AND continent = 'Africa';

-- 5. The average city population of cities in Europe
-- (average city population in Europe: 287,684)

SELECT AVG(cty.population) AS average_city_pop_in_Europe
FROM city cty
        INNER JOIN country c
                ON cty.countrycode = c.code
WHERE continent = 'Europe';

-- 6. The average city population of cities in Asia
-- (average city population in Asia: 395,019)

SELECT AVG(cty.population) AS average_city_pop_in_Asia
FROM city cty
        INNER JOIN country c
                ON cty.countrycode = c.code
WHERE continent = 'Asia';

-- 7. The number of cities in countries where English is an official language
-- (number of cities where English is official language: 523)

SELECT COUNT(*) AS number_of_cities_where_english_is_spoken
FROM city cty
        INNER JOIN country c
                ON cty.countrycode = c.code
                INNER JOIN countrylanguage cl
                        ON c.code = cl.countrycode
WHERE language = 'English' AND isofficial IS true;

-- 8. The average population of cities in countries where the official language is English
-- (average population of cities where English is official language: 285,809)

SELECT AVG(cty.population) AS average_population_of_cities_where_English_is_offical_language
FROM city cty
        INNER JOIN country c
                ON cty.countrycode = c.code
                INNER JOIN countrylanguage cl
                        ON c.code = cl.countrycode
WHERE language = 'English' AND isofficial IS true;

-- 9. The names of all of the continents and the population of the continent’s largest city
-- (6 rows, largest population for North America: 8,591,309)

SELECT c.continent, MAX(cty.population)
FROM country c
        INNER JOIN city cty
                ON c.code = cty.countrycode
GROUP BY c.continent;


-- 10. The names of all of the cities in South America that have a population of more than 1 million people and the official language of each city’s country
-- (29 rows)

SELECT cty.name AS cities_in_south_america_with_population_larger_than_1000000, cl.language AS official_language
FROM city cty
         INNER JOIN country c
                ON cty.countrycode = c.code
                INNER JOIN countrylanguage cl
                        ON c.code = cl.countrycode
WHERE c.continent = 'South America' AND cty.population > 1000000 AND isofficial IS true;
