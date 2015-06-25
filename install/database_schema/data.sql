
------------------------------------------------INSERT : PRZYKŁADOWE DANE-----------------------------------------------
--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: ssbd01admin
-- Wprowadza dane do tabeli person
--
INSERT INTO person (person_id, person_login, person_last_name, person_name, person_password, person_email, person_active, person_date_add, person_confirm)
 VALUES (1, 'nauczyciel', 'Kowalski', 'Tomasz', '53f1fce859c22ffdeff1adfcc31f670f', 'nauczyciel@test.pl', true, current_timestamp, true);

INSERT INTO person (person_id, person_login, person_last_name, person_name, person_password, person_email, person_active, person_date_add, person_confirm)
 VALUES (2, 'rodzic', 'Dzbanek', 'Anna', '19b8ba4a53158838a57b59bb766654fd', 'rodzic@test.pl', true, current_timestamp, true);
 
INSERT INTO person (person_id, person_login, person_last_name, person_name, person_password, person_email, person_active, person_date_add, person_confirm)
 VALUES (3, 'uczen', 'Dzbanek', 'Janek', 'd0bc4421f0910959b65e9c80aa4746c1', 'uczen@test.pl', true, current_timestamp, true);

INSERT INTO person (person_id, person_login, person_last_name, person_name, person_password, person_email, person_active, person_date_add, person_confirm)
 VALUES (4, 'egzaminator', 'Nowak', 'Janusz', '9d5ac2f5cb0267ab2c718ebfcd94665e', 'egzaminator@test.pl', true, current_timestamp, true);

 INSERT INTO person (person_id, person_login, person_last_name, person_name, person_password, person_email, person_active, person_date_add, person_confirm)
 VALUES (5, 'admin', 'adminNazwisko', 'adminImię', '21232f297a57a5a743894a0e4a801fc3', 'admin@test.pl', true, current_timestamp, true);

--
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: ssbd01admin
-- Wprowadza dane do tabeli groups
--
-- Rola dla użytkownika nauczyciel o id 1
INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (1, 1, 'teacher', null, true);
 
INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (2, 1, 'guardian', null, false);

 INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (3, 1, 'student', null, false);

INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (4, 1, 'examiner', null, false);

INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (5, 1, 'administrator', null, false);

-- Rola dla użytkownika rodzic o id 2
INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (6, 2, 'teacher', null, false);
 
INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (7, 2, 'guardian', null, true);

 INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (8, 2, 'student', null, false);

INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (9, 2, 'examiner', null, false);

INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (10, 2, 'administrator', null, false);
 
-- Rola dla użytkownika uczen o id 3
INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (11, 3, 'teacher', null, false);
 
INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (12, 3, 'guardian', null, false);

 INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (13, 3, 'student', 7, true);

INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (14, 3, 'examiner', null, false);

INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (15, 3, 'administrator', null, false);

-- Rola dla użytkownika egzaminator o id 4
INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (16, 4, 'teacher', null, false);
 
INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (17, 4, 'guardian', null, false);

 INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (18, 4, 'student', null, false);

INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (19, 4, 'examiner', null, true);

INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (20, 4, 'administrator', null, false);

-- Rola dla użytkownika admin o id 5
INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (21, 5, 'teacher', null, false);
 
INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (22, 5, 'guardian', null, false);

 INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (23, 5, 'student', null, false);

INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (24, 5, 'examiner', null, false);

INSERT INTO groups (groups_id, person_id, groups_name, groups_guardian, groups_active)
 VALUES (25, 5, 'administrator', null, true);

--
-- Data for Name: previous_password; Type: TABLE DATA; Schema: public; Owner: ssbd01admin
-- Wprowadza dane do tabeli previous_password
--
INSERT INTO previous_password (previous_password_id, person_id, person_password, previous_password_date_add)
 VALUES (1, 1, '88334ae7924306f90d7bbafc6bf3ec13', current_timestamp);

INSERT INTO previous_password (previous_password_id, person_id, person_password, previous_password_date_add)
 VALUES (2, 2, 'efd225d42edd5a074d31f42f09635ee6', current_timestamp);
 
INSERT INTO previous_password (previous_password_id, person_id, person_password, previous_password_date_add)
 VALUES (3, 3, '4fde3c10235b94a62ce9d541c4c5b47e', current_timestamp);

INSERT INTO previous_password (previous_password_id, person_id, person_password, previous_password_date_add)
 VALUES (4, 4, 'a94b6895ba2f3d425218f2fd89fface8', current_timestamp);

INSERT INTO previous_password (previous_password_id, person_id, person_password, previous_password_date_add)
 VALUES (5, 1, '0be1f465da23c9358fb25c498dbd851e', current_timestamp);

INSERT INTO previous_password (previous_password_id, person_id, person_password, previous_password_date_add)
 VALUES (6, 2, 'c6eba4944a23a89c089c737375a9fd57', current_timestamp);
 
INSERT INTO previous_password (previous_password_id, person_id, person_password, previous_password_date_add)
 VALUES (7, 3, '138cd10f3897df6ddf3ba23cc8a672dd', current_timestamp);

INSERT INTO previous_password (previous_password_id, person_id, person_password, previous_password_date_add)
 VALUES (8, 4, '0238fa74acf88d35e5eea7c7d18a91b5', current_timestamp);
 

 --Wprowadza dane do tabeli exam
INSERT INTO exam (exam_id, exam_title, exam_count_take_exam, exam_date_start, exam_date_end, exam_duration, exam_count_question, exam_creator_id, exam_date_add, exam_avg_results, exam_count_finish_exam)
 VALUES (1, 'Język C + + podstawy', 3, current_timestamp - interval '2' day, current_timestamp + interval '5' day, 60, 10, 4, current_timestamp, 10, 1);
 
INSERT INTO exam (exam_id, exam_title, exam_count_take_exam, exam_date_start, exam_date_end, exam_duration, exam_count_question, exam_creator_id, exam_date_add, exam_avg_results, exam_count_finish_exam)
 VALUES (2, 'Test z wiedzy o społeczenstwie', 2, current_timestamp - interval '2' day, current_timestamp + interval '4' day, 30, 5, 4, current_timestamp, 5, 1);

INSERT INTO exam (exam_id, exam_title, exam_count_take_exam, exam_date_start, exam_date_end, exam_duration, exam_count_question, exam_creator_id, exam_date_add, exam_avg_results, exam_count_finish_exam)
 VALUES (3, 'Test z astronomii', 1, current_timestamp - interval '5' day, current_timestamp - interval '1' day, 45, 7, 4, current_timestamp, 7, 1);

INSERT INTO exam (exam_id, exam_title, exam_count_take_exam, exam_date_start, exam_date_end, exam_duration, exam_count_question, exam_creator_id, exam_date_add, exam_avg_results, exam_count_finish_exam)
 VALUES (4, 'Test z  angielskiego - nazwy geograficzne', 4, current_timestamp - interval '4' day, current_timestamp - interval '2' day, 80, 3, 4, current_timestamp, 3, 1);

--Wprowadza dane do tabeli exam_groups
INSERT INTO exam_groups (groups_id, exam_id)
 VALUES (1, 1);
INSERT INTO exam_groups (groups_id, exam_id)
 VALUES (1, 2);
 INSERT INTO exam_groups (groups_id, exam_id)
 VALUES (1, 3);
 INSERT INTO exam_groups (groups_id, exam_id)
 VALUES (1, 4);

--Wprowadza dane do tabeli question
INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (1, 'Jak należy deklarować zmienne predefiniowane?', 'np. int x;',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (2,'Jaki jest operator wskaźnika?', '*',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (3, 'Co przetrzymuje wskaźnik?', 'Adres miejsca w pamięci',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (4, 'Co sie stanie po wywołaniu funkcji scanf(); ?', 'będzie oczekiwała na wprowadzenie znaku z klawiatury',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (5, 'Jak należy zadeklarować zmienna 8 bajtową?', 'np. char zmienna;',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (6, 'Co robi funckja free();', 'Zwalnia pamięć do ponownego użycia',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (7, 'Czy operator logiczny jest mocniejszy od arytmetycznego?', 'Tak',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (8, 'Od jakiego indeksu liczymy miejsca w tablicy?', 'Od 0',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (9, 'Czy deklaracja : char str[3] = "abc"; jest poprawna?', 'Tak',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (10, 'char (*wsk)[15]; Co oznacza taka deklaracja?', 'wskaźnik typu char na 15 elementowe ciągi',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (11, ' Pierwszym Prezydentem II Rzeczypospolitej był:', 'Gabriel Narutowicz',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (12, 'Do roku 1997 Hongkong był kolonią :', 'brytyjską',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (13, 'Określenie „Maghreb” dotyczy regionu:', 'Afryki Północnej',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (14, 'Liczba urodzonych w 2007 r. w Polsce dzieci wynosiła w zaokrągleniu:', '388 000',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (15, 'Kadencja Prezesa Narodowego Banku Polskiego wynosi:', '6 lat',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (16, 'Ile % masa Słońca stanowi w ogólnej masie Układu Słonecznego?', '99,87%',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (17, 'Która z planet ma obrót dookoła Słońca wynoszący 225 dni?', 'Wenus',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (18, 'Który południk stanowi granicę zmiany daty?', '180 stopni',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (19, 'W jakim okresie wprowadzono kalendarz gregoriański?', 'W końcu XVI wieku',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (20, 'Czas strefy pomiędzy 7`30 W a 7`30 E nazywamy:', 'czasem uniwersalnym',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (21, 'Ile wynosi długość południka?', '20 000 km',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (22, 'Ile wyróżniamy stref czasowych na Ziemi?', '24',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (23, 'Jak jest po ang słowo: Wyżyna', 'Valley',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (24, 'Jak jest po ang słowo: Wyspa', 'Island',19, current_timestamp);

 INSERT INTO  question (question_id, question_content, question_sample_answer, question_creator_id, question_date_add)
 VALUES (25, 'Jak jest po ang słowo: Jezioro', 'Lake',19, current_timestamp);

--Wprowadza dane do tabeli exam_person
INSERT INTO exam_question (exam_id, question_id)
 VALUES (1, 1);
 
INSERT INTO exam_question (exam_id, question_id)
 VALUES (1, 2);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (1, 3);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (1, 4);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (1, 5);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (1, 6);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (1, 7);
 
INSERT INTO exam_question (exam_id, question_id)
 VALUES (1, 8);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (1, 9);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (1, 10);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (2, 11);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (2, 12);
 
INSERT INTO exam_question (exam_id, question_id)
 VALUES (2, 13);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (2, 14);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (2, 15);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (3, 16);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (3, 17);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (3, 18);
 
INSERT INTO exam_question (exam_id, question_id)
 VALUES (3, 19);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (3, 20);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (3, 21);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (3, 22);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (4, 23);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (4, 24);

INSERT INTO exam_question (exam_id, question_id)
 VALUES (4, 25);

  --Wprowadza dane do tabeli approach
INSERT INTO approach (approach_id, approach_date_start, approach_date_end, approach_exam_id, approach_entrant_id, approach_date_add)
 VALUES (1, current_timestamp - interval '1' day,  current_timestamp - interval '1' day  + interval '53' minute, 1, 13, current_timestamp);

INSERT INTO approach (approach_id, approach_date_start, approach_date_end, approach_exam_id, approach_entrant_id, approach_date_add)
 VALUES (2, current_timestamp - interval '1' day,  current_timestamp - interval '1' day  + interval '25' minute, 2, 13, current_timestamp);

INSERT INTO approach (approach_id, approach_date_start, approach_date_end, approach_exam_id, approach_entrant_id, approach_date_add)
 VALUES (3, current_timestamp - interval '4' day,  current_timestamp - interval '4' day  + interval '41' minute, 3, 13, current_timestamp);

INSERT INTO approach (approach_id, approach_date_start, approach_date_end, approach_exam_id, approach_entrant_id, approach_date_add)
 VALUES (4,  current_timestamp - interval '3' day,  current_timestamp - interval '3' day  + interval '19' minute, 4, 13, current_timestamp);

--Wprowadza dane do tabeli answer
INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (1, 1, 1, 1, 'Przykładowa odpowiedz ucznia 1', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (2, 1, 2, 1, 'Przykładowa odpowiedz ucznia 2', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (3, 1, 3, 1, 'Przykładowa odpowiedz ucznia 3', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (4, 1, 4, 1, 'Przykładowa odpowiedz ucznia 4', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (5, 1, 5, 1, 'Przykładowa odpowiedz ucznia 5', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (6, 1, 6, 1, 'Przykładowa odpowiedz ucznia 6', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (7, 1, 7, 1, 'Przykładowa odpowiedz ucznia 7', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (8, 1, 8, 1, 'Przykładowa odpowiedz ucznia 8', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (9, 1, 9, 1, 'Przykładowa odpowiedz ucznia 9', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (10, 1, 10, 1, 'Przykładowa odpowiedz ucznia 10', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (11, 2, 11, 1, 'Przykładowa odpowiedz ucznia 11', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (12, 2, 12, 1, 'Przykładowa odpowiedz ucznia 12', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (13, 2, 13, 1, 'Przykładowa odpowiedz ucznia 13', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (14, 2, 14, 1, 'Przykładowa odpowiedz ucznia 14', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (15, 2, 15, 1, 'Przykładowa odpowiedz ucznia 15', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (16, 3, 16, 1, 'Przykładowa odpowiedz ucznia 16', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (17, 3, 17, 1, 'Przykładowa odpowiedz ucznia 17', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (18, 3, 18, 1, 'Przykładowa odpowiedz ucznia 18', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (19, 3, 19, 1, 'Przykładowa odpowiedz ucznia 19', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (20, 3, 20, 1, 'Przykładowa odpowiedz ucznia 20', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (21, 3, 21, 1, 'Przykładowa odpowiedz ucznia 21', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (22, 3, 22, 1, 'Przykładowa odpowiedz ucznia 22', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (23, 4, 23, null, 'Przykładowa odpowiedz ucznia 23', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (24, 4, 24, null, 'Przykładowa odpowiedz ucznia 24', 1, current_timestamp);

INSERT INTO answer (answer_id, answer_approach_id, answer_question_id, answer_teacher_id, answer_content, answer_grade, answer_date_add)
 VALUES (25, 4, 25, null, 'Przykładowa odpowiedz ucznia 25', 1, current_timestamp);

 --Wprowadza dane do tabeli generatora
INSERT INTO generator VALUES ('PersonEntity', 100);

INSERT INTO generator VALUES ('GroupsEntity', 100);

INSERT INTO generator VALUES ('PreviousPasswordEntity', 100);

INSERT INTO generator VALUES ('ExamEntity', 100);

INSERT INTO generator VALUES ('QuestionEntity', 100);

INSERT INTO generator VALUES ('ApproachEntity', 100);

INSERT INTO generator VALUES ('AnswerEntity', 100);


------------------------------------------------------------------------------------------------------------------------