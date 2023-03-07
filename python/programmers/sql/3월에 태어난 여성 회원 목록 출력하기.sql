-- 코드를 입력하세요
SELECT member_id,member_name,gender,date_format(date_of_birth,'%Y-%m-%d') AS "DATE_OF_BIRTH"
FROM MEMBER_PROFILE
WHERE MONTH(date_of_birth) = 3 AND tlno IS NOT NULL AND gender = 'W'
ORDER BY member_id