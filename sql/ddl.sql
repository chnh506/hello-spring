-- 영한님 왈: 이렇게 따로 sql 디렉터리를 파서 sql 쿼리문도 관리를 해 주는 편이다!

drop table if exists member CASCADE;
create table member (
    id bigint generated by default as identity,
    name varchar(255),
    primary key (id)
);