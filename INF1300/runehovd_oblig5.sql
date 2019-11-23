/*
 * INF1300 Oblig 5
 * brukernavn: runehovd
 */

--Oppgave 1
select distinct filmcharacter, count(partid) countchar
from filmcharacter
group by filmcharacter
having count(partid)>800
order by countchar desc;

--Oppgave 2
select fpar.personid, person.lastname, film.title, fcountry.country
from filmparticipation fpar
  inner join person on fpar.personid = person.personid
  left join film on fpar.filmid = film.filmid
  inner join filmcountry fcountry on fpar.filmid = fcountry.filmid
  inner join filmcharacter fchar on fpar.partid = fchar.partid
where person.firstname = 'Ingrid'
  and fchar.filmcharacter = 'Ingrid'
order by film.title;

--Oppgave 3
select person.firstname, person.lastname, fchar.filmcharacter, film.title
from filmparticipation fpar
  inner join person on fpar.personid = person.personid
  inner join filmcharacter fchar on fpar.partid = fchar.partid
  left join film on fpar.filmid = film.filmid
  where person.personid = 3914169
    and fchar.filmcharacter like '_%';

--Oppgave 4
with ingrid as(
  select person.personid, person.firstname, person.lastname, count(fpar.partid) cnt
  from filmparticipation fpar
    inner join person on person.personid = fpar.personid
    inner join filmcharacter fchar on fpar.partid = fchar.partid
  where fchar.filmcharacter = 'Ingrid'
  group by person.personid, person.firstname, person.lastname
)

select *
from ingrid
group by ingrid.personid, ingrid.firstname, ingrid.lastname, ingrid.cnt
having ingrid.cnt = (select max(ingrid.cnt) from ingrid);

--Oppgave 5
with samename as(
  select fchar.filmcharacter, count(fpar.personid) cnt
  from filmcharacter fchar
    inner join filmparticipation fpar on fchar.partid = fpar.partid
    inner join person p on fpar.personid = p.personid
  where fchar.filmcharacter = p.firstname
  group by fchar.filmcharacter
)
select *
from samename
group by samename.filmcharacter, samename.cnt
having samename.cnt = (select max(samename.cnt) from samename)
order by samename.cnt desc;

--Oppgave 6
select film.title, filmp.parttype, count(filmp.personid) count
from filmparticipation filmp
  inner join film on filmp.filmid = film.filmid
  inner join filmitem on filmp.filmid = filmitem.filmid
where film.title like '%Lord of the Rings%'
  and filmitem.filmtype = 'C'
group by film.title, filmp.parttype;

--Oppgave 7
select distinct film.title
from film
inner join filmgenre fg on film.filmid = fg.filmid
where fg.filmid in
  (select filmid from filmgenre where genre = 'Film-Noir')
and fg.filmid in
  (select filmid from filmgenre where genre = 'Comedy');

--Oppgave 8
select film.filmid, film.title, count(fg.genre)
  from film, filmgenre fg
    where film.title like '%Antoine %'
    group by film.filmid, film.title;

--Oppgave 9

with unikefilmkar as(
  select distinct fchar.filmcharacter
  from filmcharacter fchar;
)

with pc as (
  select p.personid, p.firstname, p.lastname, fc.filmcharacter
  from person p
  natural join filmparticipation fp
  natural join filmcharacter fc
)

select pc.personid, pc.firstname, pc.lastname, count(*) "antall filmer"
from pc
where pc.filmcharacter in (
  select pc.filmcharacter
  from pc
  group by pc.filmcharacter
  having count(*) = 1
)
group by pc.personid, pc.firstname, pc.lastname
having count(*) > 199;
