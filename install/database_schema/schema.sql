-- Database: ssbd
------------------------------------------------CREATE TABLE------------------------------------------------------------------
CREATE TABLE person
(
  "person_id" bigint NOT NULL,
  "person_login" varchar(30) NOT NULL,
  "person_last_name" varchar(30) NOT NULL,
  "person_name" varchar(20) NOT NULL,
  "person_password" varchar(32) NOT NULL,
  "person_email" varchar(30) NOT NULL,
  "person_last_time_login" timestamp,
  "person_last_ip_login" varchar(15),
  "person_active" boolean DEFAULT TRUE NOT NULL,
  "person_confirm" boolean DEFAULT false NOT NULL,
  "person_date_add" timestamp NOT NULL,
  "person_date_modification" timestamp,
  "person_version" bigint DEFAULT 1,
  CONSTRAINT "PKeyPerson" PRIMARY KEY (person_id)
);
ALTER TABLE ONLY person
    ADD CONSTRAINT person_login_key UNIQUE (person_login);
    
ALTER TABLE public.person OWNER TO ssbd01admin;

--
-- Name: groups; Type: TABLE; Schema: public; Owner: ssbd01admin;  
-- Tworzenie tabeli z rolami
-- Tablespace:
--
CREATE TABLE groups
(
  "groups_id" bigint NOT NULL,
  "person_id"  bigint references person(person_id) NOT NULL,
  "groups_name" varchar(25) NOT NULL,
  "groups_guardian"  bigint references groups(groups_id),
  "groups_active" boolean DEFAULT false NOT NULL,
  "groups_date_modification" timestamp,
  "groups_version" bigint DEFAULT 1,
  CONSTRAINT "PKeyGroups" PRIMARY KEY (groups_id)
);

ALTER TABLE public.groups OWNER TO ssbd01admin;

--
-- Name: previous_password; Type: TABLE; Schema: public; Owner: ssbd01admin
-- Tworzenie tabeli z histori logowania użytkownika
-- Tablespace:
--
CREATE TABLE previous_password
(
  "previous_password_id" bigint NOT NULL,
  "person_id"  bigint references person(person_id) NOT NULL,
  "person_password" varchar(32) NOT NULL,
  "previous_password_date_add" timestamp NOT NULL,
  "previous_password_date_modification" timestamp,
  "previous_password_version" bigint DEFAULT 1,
  CONSTRAINT "PKeyPersonPass" PRIMARY KEY (previous_password_id)
);

ALTER TABLE public.previous_password OWNER TO ssbd01admin;
--
-- Name: glassfish_auth_view; Type: VIEW; Schema: public; Owner: ssbd01admin
-- Tworzenie widoku glassfish_auth_view
--
CREATE VIEW glassfish_auth_view AS
    SELECT person.person_login, person.person_password, groups.groups_name 
    FROM (person JOIN groups ON ((person.person_id = groups.person_id))) 
    WHERE (person.person_active = true AND person.person_confirm = true)
    AND groups.groups_active = true;

ALTER TABLE public.glassfish_auth_view OWNER TO ssbd01admin;

--Tworzenie tabeli z egzaminami
CREATE TABLE exam
(
  "exam_id" bigint NOT NULL,
  "exam_title" varchar(100) NOT NULL,
  "exam_count_take_exam" integer NOT NULL,
  "exam_date_start" timestamp NOT NULL,
  "exam_date_end" timestamp NOT NULL,
  "exam_duration" integer NOT NULL,
  "exam_count_question" integer NOT NULL,
  "exam_count_finish_exam" integer,
  "exam_avg_results" decimal, 
  "exam_creator_id" bigint references groups(groups_id) NOT NULL,
  "exam_modifier_id" bigint references groups(groups_id),
  "exam_date_add" timestamp NOT NULL,
  "exam_date_modification" timestamp,
  "exam_version" bigint DEFAULT 1,
  CONSTRAINT "PKeyExam" PRIMARY KEY (exam_id)
);

ALTER TABLE ONLY exam
    ADD CONSTRAINT exam_title_key UNIQUE (exam_title);

ALTER TABLE public.exam OWNER TO ssbd01admin;

--Tworzenie tabeli łaczącej nauczycieli z egzaminami. Wielu nauczycieli może sprawdzać egzamin. 
CREATE TABLE exam_groups
(
  "exam_id"  bigint references exam(exam_id),
  "groups_id"  bigint references groups(groups_id)
);

ALTER TABLE public.exam_groups OWNER TO ssbd01admin;

--Tworzenie tabeli z pytaniami
CREATE TABLE question
(
  "question_id" bigint NOT NULL,
  "question_content" varchar(255) NOT NULL,
  "question_sample_answer" varchar(255) NOT NULL,
  "question_creator_id" bigint references groups(groups_id) NOT NULL,
  "question_modifier_id" bigint references groups(groups_id),
  "question_date_add" timestamp NOT NULL,
  "question_date_modification" timestamp,
  "question_version" bigint DEFAULT 1,
  CONSTRAINT "PKeyQuestion" PRIMARY KEY ("question_id")
);

ALTER TABLE public.question OWNER TO ssbd01admin;

--Tworzenie tabeli z egzaminy-pytania
CREATE TABLE exam_question
(
  "exam_id"  bigint references exam(exam_id),
  "question_id"  bigint references question(question_id)
);

ALTER TABLE public.exam_question OWNER TO ssbd01admin;

--Tworzenie tabeli z podejściami
CREATE TABLE approach
(
  "approach_id" bigint NOT NULL ,
  "approach_date_start" timestamp NOT NULL,
  "approach_date_end" timestamp NOT NULL,
  "approach_disqualification" boolean NOT NULL DEFAULT false,
  "approach_exam_id" bigint references exam(exam_id) NOT NULL,
  "approach_entrant_id" bigint references groups(groups_id) NOT NULL,
  "approach_date_add" timestamp NOT NULL,
  "approach_date_modification" timestamp,
  "approach_version" bigint DEFAULT 1,
  CONSTRAINT "PKeyApproach" PRIMARY KEY ("approach_id")
);

ALTER TABLE public.approach OWNER TO ssbd01admin;

--Tworzenie tabeli z odpowiedziami
CREATE TABLE answer
(
  "answer_id" bigint NOT NULL,
  "answer_approach_id" bigint references approach(approach_id) NOT NULL,
  "answer_question_id" bigint references question(question_id) NOT NULL,
  "answer_teacher_id" bigint references groups(groups_id),
  "answer_content" varchar(255) NOT NULL,
  "answer_grade" integer NOT NULL,
  "answer_date_add" timestamp NOT NULL,
  "answer_date_modification" timestamp,
  "answer_version" bigint DEFAULT 1,
  CONSTRAINT "PKeyAnswer" PRIMARY KEY ("answer_id"),
  CONSTRAINT answer_grade_check CHECK (((answer_grade >= 0) AND (answer_grade <= 2)))
);

ALTER TABLE public.answer OWNER TO ssbd01admin;

--Tworzenie tabeli generatora id
CREATE TABLE generator (
    class_name character varying(32),
    id_range bigint
);

ALTER TABLE public.generator OWNER TO ssbd01admin;
------------------------------------------------------------------------------------------------------------------------
------------------------------------------------Indeksy----------------------------------------------------------------
-- Klucz główny generator
ALTER TABLE ONLY generator
 ADD CONSTRAINT "PKeyGenerator" PRIMARY KEY (class_name);

-- Indeksy answer
CREATE INDEX answer_approach_id on answer(answer_approach_id);
CREATE INDEX answer_question_id on answer(answer_question_id);
CREATE INDEX answer_teacher_id on answer(answer_teacher_id);

--Indeksy approach
CREATE INDEX approach_entrant_id on approach(approach_entrant_id);
CREATE INDEX approach_exam_id on approach(approach_exam_id);

--Indeksy exam
CREATE INDEX exam_creator_id on exam(exam_creator_id);
CREATE INDEX exam_modifier_id on exam(exam_modifier_id);

--Indeksy exam_groups
CREATE INDEX exam_groups_exam_id on exam_groups(exam_id);
CREATE INDEX exam_groups_groups_id on exam_groups(groups_id);

--Indeksy exam_question
CREATE INDEX exam_question_exam_id on exam_question(exam_id);
CREATE INDEX exam_question_question_id on exam_question(question_id);

--Indeksy groups
CREATE INDEX groups_groups_guardian on groups(groups_guardian);
CREATE INDEX groups_person_id on groups(person_id);

--Indeks previous password
CREATE INDEX previous_password_person_id on previous_password(person_id);


--Indeksy question
CREATE INDEX question_creater_id on question(question_creator_id);
CREATE INDEX question_modifier_id on question(question_modifier_id);
------------------------------------------------------------------------------------------------------------------------
------------------------------------------------NADAWANIE UPRAWNIEN UŻYTKOWNIKOM BAZODANOWYM----------------------------

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
REVOKE ALL ON SCHEMA public FROM ssbd01admin;

GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
GRANT ALL ON SCHEMA public TO ssbd01admin;

-- ssbd01authdb
REVOKE ALL ON SCHEMA public FROM ssbd01authdb;
GRANT SELECT ON TABLE glassfish_auth_view TO ssbd01authdb;
GRANT SELECT, UPDATE ON TABLE generator TO ssbd01authdb;

-- ssbd01mok
REVOKE ALL ON SCHEMA public FROM ssbd01mok;
GRANT SELECT, UPDATE, INSERT ON TABLE groups TO ssbd01mok;
GRANT SELECT, UPDATE, INSERT ON TABLE person TO ssbd01mok;
GRANT SELECT, UPDATE, INSERT ON TABLE previous_password TO ssbd01mok;
GRANT SELECT, UPDATE ON TABLE generator TO ssbd01mok;

-- ssbd01mze
REVOKE ALL ON SCHEMA public FROM ssbd01mze;
GRANT SELECT ON TABLE groups TO ssbd01mze;
GRANT SELECT ON TABLE person TO ssbd01mze;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE exam_groups TO ssbd01mze;
GRANT SELECT, UPDATE, INSERT ON TABLE exam TO ssbd01mze;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE question TO ssbd01mze;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE exam_question TO ssbd01mze;
GRANT SELECT ON TABLE answer TO ssbd01mze;
GRANT SELECT ON TABLE approach TO ssbd01mze;
GRANT SELECT, UPDATE ON TABLE generator TO ssbd01mze;

-- ssbd01moe
REVOKE ALL ON SCHEMA public FROM ssbd01moe;
GRANT SELECT, UPDATE ON TABLE groups TO ssbd01moe;
GRANT SELECT ON TABLE person TO ssbd01moe;
GRANT SELECT ON TABLE exam_groups TO ssbd01moe;
GRANT SELECT, UPDATE ON TABLE exam TO ssbd01moe;
GRANT SELECT, UPDATE ON TABLE approach TO ssbd01moe;
GRANT SELECT, UPDATE ON TABLE answer TO ssbd01moe;
GRANT SELECT ON TABLE question TO ssbd01moe;
GRANT SELECT, UPDATE ON TABLE generator TO ssbd01moe;

-- ssbd01mre
REVOKE ALL ON SCHEMA public FROM ssbd01mre;
GRANT SELECT ON TABLE groups TO ssbd01mre;
GRANT SELECT ON TABLE person TO ssbd01mre;
GRANT SELECT, UPDATE ON TABLE exam TO ssbd01mre;
GRANT SELECT, UPDATE, INSERT ON TABLE approach TO ssbd01mre;
GRANT SELECT, UPDATE, INSERT ON TABLE answer TO ssbd01mre;
GRANT SELECT, UPDATE ON TABLE question TO ssbd01mre;
GRANT SELECT ON TABLE exam_question TO ssbd01mre;
GRANT SELECT, UPDATE ON TABLE generator TO ssbd01mre;
