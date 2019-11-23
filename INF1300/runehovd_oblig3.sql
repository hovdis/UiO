/*
    Oblig 3 - runehovd
    Siden du itte skriver kallene med store bokstaver endret je alle mine
    til å være store bokstaver for shits and giggles
    Kos deg
 */

 -- Oppgave 2.1
SELECT *
FROM timelistelinje
WHERE timelistenr = 3;

 -- Oppgave 2.2
SELECT count(timelistenr)
FROM timeliste;

 -- Oppgave 2.3
SELECT count(timelistenr)
FROM timeliste
WHERE status != 'utbetalt'; -- Kunne jo så klart brukt andre ting enn !=

 -- Oppgave 2.4
SELECT count(timelistenr)
FROM timelistelinje
WHERE starttid > sluttid;

 -- Oppgave 2.5
SELECT sum(sluttid - starttid)
FROM timeliste tl
    INNER JOIN timelistelinje tll
    ON tl.timelistenr = tll.timelistenr
WHERE tl.status != 'utbetalt'; -- Kan også skrive <> i stella for !=

 -- Oppgave 2.6
SELECT (sum(varighet)/60)
FROM Varighet v
  INNER JOIN timelistelinje tll
  ON v.timelistenr=tll.timelistenr
WHERE tll.startdato >= '2016-07-01'  AND tll.startdato<'2016-08-01';

 -- Oppgave 2.7 -- MÅ TESTE DENNI
SELECT DISTINCT tl.timelistenr, tl.beskrivelse
FROM timelistelinje tll
  INNER JOIN timeliste tl
  ON tl.timelistenr=tll.timelistenr
WHERE lower(tll.beskrivelse) LIKE '%test%';

 -- Oppgave 2.8
SELECT DISTINCT tll.beskrivelse
FROM timelistelinje tll
  INNER JOIN timeliste tl ON tll.timelistenr = tl.timelistenr
WHERE tl.status NOT LIKE 'utbetalt';

 -- Oppgave 3*
INSERT INTO Timeliste
VALUES(8, 'utbetalt', '2016-07-29', '2016-08-10', 'Planlegging av neste trinn'),
(9, 'levert', '2016-08-03', NULL, 'Detaljering av neste trinn'),
(10, 'aktiv', NULL, NULL, 'Skriving av rapport');

INSERT INTO Timelistelinje
VALUES(8, 1, '2016-07-25', '10:15', '17:30', 50, 'diskusjONer'),
(8 , 2, '2016-07-27', '12:45', '14:00', NULL, 'kONkretisering'),
(9 , 1, '2016-07-27', '15:15', '18:45', 70, 'del1'),
(9 , 2, '2016-07-28', '10:00', '14:00', 35, 'del2'),
(9 , 3, '2016-07-28', '21:00', '04:15', 90, 'del3'),
(9 , 4, '2016-08-02', '13:00', '17:00', NULL, 'del4'),
(10 , 1, '2016-08-03', '10:50', '16:10', 40, 'kap1'),
(10 , 2, '2016-08-05', '18:00', NULL, NULL, 'kap2');

 -- Oppgave 4*
 -- Håper det er sånn her du ville ha det :*
 SELECT *
 FROM Timeliste
 WHERE timelistenr>=8;

 SELECT *
 FROM Timelistelinje
 WHERE timelistenr>=8;
